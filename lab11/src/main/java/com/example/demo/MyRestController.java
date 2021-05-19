package com.example.demo;

import com.example.demo.requests.RequestHandler;
import com.example.demo.utils.ControllerExceptionHandler;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.minidev.json.JSONObject;
import org.springframework.web.context.request.WebRequest;

import javax.sql.rowset.WebRowSet;
import javax.swing.*;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
//@RestControllerAdvice
@RequestMapping(value = "/person")
public class MyRestController {

    private final PersRepo repo;
    private final ControllerExceptionHandler controllerExceptionHandler = new ControllerExceptionHandler();


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
        } else {
            return new ResponseEntity<String>(String.valueOf(controllerExceptionHandler.globalExceptionHandler
                    (new IOException())), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody JSONObject object) {

        if (repo.findById(id).isPresent()) {
            Person person = repo.findPersonById(id);
            person.setName(object.get("name").toString());
            repo.save(person);
            return new ResponseEntity<String>("updated user \"" +
                    object.get("name").toString() + "\"", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(String.valueOf(controllerExceptionHandler.resourceNotFoundException
                    (new ResourceNotFoundException("user with id " + id + " could not be found"))), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (repo.findById(id).isPresent()) {
            repo.delete(repo.findPersonById(id));
            return new ResponseEntity<String>("deleted user having id  " + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>(String.valueOf(controllerExceptionHandler.resourceNotFoundException
                    (new ResourceNotFoundException("user with id " + id + " could not be found"))), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/most-k-connected", method = RequestMethod.POST)
    public String mostKConnected(@RequestBody JSONObject object) {
        List<Map<Integer, Integer>> top = null;
        top = repo.mostConnected((Integer) object.get("limit"));

        String response = "Most connected : \n";
        for (Map<Integer, Integer> integerIntegerMap : top) {
            List<Integer> aux = (List<Integer>) integerIntegerMap.values();
            response += "\"" + repo.findPersonById(aux.get(0)).getName() + "\"";
            response += " connected with " + aux.get(1) + "\n";
        }
        return response;
    }

    @RequestMapping(value = "/least-k-connected", method = RequestMethod.POST)
    public String leastKConnected(@RequestBody JSONObject object) {
        List<Map<Integer, Integer>> top = null;
        top = repo.leastConnected((Integer) object.get("limit"));

        String response = "Lest connected : \n";
        for (Map<Integer, Integer> integerIntegerMap : top) {
            List<Integer> aux = (List<Integer>) integerIntegerMap.values();
            response += "\"" + repo.findPersonById(aux.get(0)).getName() + "\"";
            response += " connected with " + aux.get(1) + "\n";
        }
        return response;
    }

    @RequestMapping(value = "/command/{id}", method = RequestMethod.POST)
    public String executeCommand(@RequestBody JSONObject object, @PathVariable int id) {
        String command = String.valueOf(object.get("command"));
        RequestHandler requestHandler = new RequestHandler(command);
        return requestHandler.execute(id);
    }

}
