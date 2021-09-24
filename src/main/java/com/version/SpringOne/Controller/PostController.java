package com.version.SpringOne.Controller;

import com.version.SpringOne.Model.PostObject;
import com.version.SpringOne.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;
    @PostMapping
    public PostObject addPost(@RequestBody PostObject postObject){
        PostObject created = new PostObject();
        created.setPostName(postObject.getPostName());
        created.setContent(postObject.getContent());
        created =  postService.save(created);
        System.out.println(created);
        return created;
    }

}
