package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.minidev.json.JSONObject;

import javax.swing.*;
import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/person")
public class MyRestController {

    private final PersRepo repo;

    @Autowired
    public MyRestController(PersRepo repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Hello from Spring Boot!";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Person> allPersons() {
        return repo.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody JSONObject person) {
        if (repo.add(person.get("name").toString()) == 1) {
            return new ResponseEntity<String>("added user \"" +
                    person.get("name").toString() + "\"", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("unable to register as \"" +
                person.get("name").toString() + "\"", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody JSONObject object) {

        if (repo.findById(id).isPresent()) {
            Person person = repo.findPersonById(id);
            person.setName(object.get("name").toString());
            repo.save(person);
            return new ResponseEntity<String>("updated user \"" +
                    object.get("name").toString() + "\"", HttpStatus.OK);
        }
        return new ResponseEntity<String>("user with id " + id + "could not be found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (repo.findById(id).isPresent()) {
            repo.delete(repo.findPersonById(id));
            return new ResponseEntity<String>("deleted user having id  " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("user with id " + id + " could not be found", HttpStatus.NOT_FOUND);
    }

}
