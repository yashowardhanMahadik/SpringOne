package com.version.SpringOne.Controller;

import com.version.SpringOne.Model.Tutorial;
import com.version.SpringOne.Repository.DatabaseRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerClass {

  @Autowired
    DatabaseRepo db;

  @PostMapping("/create")
  public ResponseEntity<Tutorial> createit(@RequestBody Tutorial tut)
  {
    try {
      Tutorial newtut = db.save(new Tutorial(tut.getTitle(),tut.getDescription()));
      return new ResponseEntity<>(newtut,HttpStatus.ACCEPTED);
    }
    catch (Exception e)
    {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAll()
  {
    try{
      List<Tutorial> li = new ArrayList<>(db.findAll());

      if(li.isEmpty())
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

      return new ResponseEntity<>(li, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
