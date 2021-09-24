package com.version.SpringOne.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="posts")
public class PostObject {
    @Id
    String postId;

    String postName;
    String content;
}
