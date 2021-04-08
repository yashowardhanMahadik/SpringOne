package com.version.SpringOne.Repository;

import com.version.SpringOne.Model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseRepo extends MongoRepository<Tutorial, String >
{

}
