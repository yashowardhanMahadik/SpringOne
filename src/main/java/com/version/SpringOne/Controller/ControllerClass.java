package com.version.SpringOne.Controller;

import com.version.SpringOne.Model.JwtRequest;
import com.version.SpringOne.Model.JwtResponse;
import com.version.SpringOne.Model.Tutorial;
import com.version.SpringOne.Repository.DatabaseRepo;

import java.util.*;

import com.version.SpringOne.service.UserService;
import com.version.SpringOne.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerClass {

    @Autowired
    DatabaseRepo db;
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

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

    @GetMapping("/findTitle/{title}")
    ArrayList<Tutorial> getSameTitle(@PathVariable("title") String title) {
        return db.getAllWithTitle(title);
    }

    @GetMapping("/tutorialsPage")
    public Map<String, Object> getAllTutorials(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
    ) {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            Pageable paging = PageRequest.of(page, size);

            Page<Tutorial> pageTuts;
            if (title == null)
                pageTuts = db.findAll(paging);
            else
                pageTuts = null;

            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return response;

//            ArrayList<Integer> alist ;
//            Collections.copy();
            //return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }
}
