package com.version.SpringOne.Repository;

import com.version.SpringOne.Model.PostObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<PostObject,String> {

}
