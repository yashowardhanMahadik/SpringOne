package com.version.SpringOne.Repository;
import com.version.SpringOne.Model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepo extends MongoRepository<UserData,String> {
    @Query("{ id : ?0}")
    Optional<UserData> findUser(String id);

    @Query("{ 'name' : ?0}")
    Optional<UserData> findUserByName(String name);
}
