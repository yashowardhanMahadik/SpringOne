package com.version.SpringOne.service;

import com.version.SpringOne.Model.PostObject;
import com.version.SpringOne.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;
    public PostObject save(PostObject postObject){
        return postRepo.save(postObject);
    }
}
