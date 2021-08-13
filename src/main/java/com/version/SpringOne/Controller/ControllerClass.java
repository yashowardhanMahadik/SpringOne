package com.version.SpringOne.Controller;

import com.version.SpringOne.Model.Tutorial;
import com.version.SpringOne.Repository.DatabaseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerClass {

    @Autowired
    DatabaseRepo db;

    @PostMapping("/create")
    public ResponseEntity<Tutorial> createit(@RequestBody Tutorial tut) {
        try {
            Tutorial newtut = db.save(new Tutorial(tut.getTitle(), tut.getDescription()));
            return new ResponseEntity<>(newtut, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials")
    public List<Tutorial> getAll() {
        try {
            List<Tutorial> li = new ArrayList<>(db.findAll());

            if (li.isEmpty()) {
                return null;
                //return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return li;
            //return new ResponseEntity<>(li, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
            //return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/tutorials/{id}")
    public Tutorial getTutorialById(@PathVariable("id") String id) {
        Tutorial tutorialData = db.findById(id).orElse(null);
        return tutorialData;
    }
    @GetMapping("/find1/{id}")
    public Tutorial findSingle(@PathVariable("id") String id) {
        Tutorial tutorialData = db.findSingle(id).orElse(null);
        return tutorialData;
    }

}
