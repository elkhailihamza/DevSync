package com.DevSync.Repositories;

import com.DevSync.Entities.Tag;

public interface TagRepository extends GenericRepository<Tag, Long>{
    Tag fetchByName(String name);
}
