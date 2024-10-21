package RepositoryTest;

import com.DevSync.Entities.Task;
import com.DevSync.Repositories.implementations.TaskRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskRepositoryTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<Task> query;

    @InjectMocks
    private TaskRepositoryImpl taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    void testFindById() {
        Task task = new Task();
        task.setId(1L);

        when(session.get(Task.class, 1L)).thenReturn(task);

        Task result = taskRepository.findById(1L);

        assertEquals(1L, result.getId());
        verify(session).get(Task.class, 1L);
        verify(session).close();
    }

    @Test
    void testFetchAll() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(session.createQuery("FROM Task ", Task.class)).thenReturn(query);
        when(query.list()).thenReturn(tasks);

        List<Task> result = taskRepository.fetchAll();

        assertEquals(2, result.size());
        verify(session).createQuery("FROM Task ", Task.class);
        verify(query).list();
        verify(session).close();
    }

    @Test
    void testSave() {
        Task task = new Task();
        when(session.beginTransaction()).thenReturn(transaction);

        taskRepository.save(task);

        verify(session).persist(task);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testUpdate() {
        Task task = new Task();
        when(session.beginTransaction()).thenReturn(transaction);

        taskRepository.update(task);

        verify(session).merge(task);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testDelete() {
        Task task = new Task();
        task.setId(1L);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Task.class, 1L)).thenReturn(task);

        taskRepository.delete(task);

        verify(session).remove(task);
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    void testFetchUserCreatedTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(session.createQuery("FROM Task WHERE creator.id = :creatorId", Task.class)).thenReturn(query);
        when(query.setParameter("creatorId", 1L)).thenReturn(query);
        when(query.list()).thenReturn(tasks);

        List<Task> result = taskRepository.fetchUserCreatedTasks(1L);

        assertEquals(2, result.size());
        verify(session).createQuery("FROM Task WHERE creator.id = :creatorId", Task.class);
        verify(query).setParameter("creatorId", 1L);
        verify(query).list();
        verify(session).close();
    }

    @Test
    void testFetchPendingTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        LocalDateTime now = LocalDateTime.now();
        when(session.createQuery("FROM Task t WHERE t.taskRequest.managerApproved = false AND t.replacementDate IS NOT NULL AND t.replacementDate <= :limit", Task.class))
                .thenReturn(query);
        when(query.setParameter("limit", now.minusHours(12))).thenReturn(query);
        when(query.getResultList()).thenReturn(tasks);

        List<Task> result = taskRepository.fetchPendingTasks(now);

        assertEquals(2, result.size());
        verify(session).createQuery("FROM Task t WHERE t.taskRequest.managerApproved = false AND t.replacementDate IS NOT NULL AND t.replacementDate <= :limit", Task.class);
        verify(query).setParameter("limit", now.minusHours(12));
        verify(query).getResultList();
        verify(session).close();
    }

    @Test
    void testFetchOverDueTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        LocalDateTime now = LocalDateTime.now();
        when(session.createQuery("FROM Task t WHERE t.dueDate < :date AND t.isReplaceable != :boolean", Task.class))
                .thenReturn(query);
        when(query.setParameter("date", now)).thenReturn(query);
        when(query.setParameter("boolean", false)).thenReturn(query);
        when(query.getResultList()).thenReturn(tasks);

        List<Task> result = taskRepository.fetchOverDueTasks(now);

        assertEquals(2, result.size());
        verify(session).createQuery("FROM Task t WHERE t.dueDate < :date AND t.isReplaceable != :boolean", Task.class);
        verify(query).setParameter("date", now);
        verify(query).setParameter("boolean", false);
        verify(query).getResultList();
        verify(session).close();
    }
}
