package com.version.SpringOne.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutorials")
public class Tutorial {

  @Id
  private String Id;
  private String title;
  private String description;

  public Tutorial() {
  }

  public Tutorial(String title, String description) {
    this.title = title;
    this.description = description;
  }



  @Override
  public String toString() {
    return "Tutorial{" +
        "Id='" + Id + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  public String getId() {
    return Id;
  }

  public void setId(String id) {
    Id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }







}
