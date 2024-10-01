package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Tasks;
import com.DevSync.Repositories.TaskRepository;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public Tasks findById(Long aLong) {
        return null;
    }

    @Override
    public List<Tasks> fetchAll() {
        return List.of();
    }

    @Override
    public void save(Tasks entity) {

    }

    @Override
    public void update(Tasks entity) {

    }

    @Override
    public void delete(Tasks entity) {

    }
}
