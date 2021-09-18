package com.version.SpringOne.Repository;

import com.version.SpringOne.Model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public interface DatabaseRepo extends MongoRepository<Tutorial, String >
{
    @Query("{ id : ?0}")
    Optional<Tutorial> findSingle(String id);

    @Query("{ title : ?0}")
    ArrayList<Tutorial> getAllWithTitle(String title);
}
