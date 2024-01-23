package com.example.FirstSpringProject.rest;

import com.example.FirstSpringProject.User;
import com.example.FirstSpringProject.service.UserRepository;
import com.example.FirstSpringProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ComponentScan("")
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userRecords")
    public List<User> getAllRecords() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public User getPatientById(@PathVariable(value="id") String id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User createRecord(@RequestBody User patientRecord) {
        return userRepository.save(patientRecord);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/*")
    public ResponseEntity<String> handleInvalidEndpoint() throws ClassNotFoundException {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid endpoint. Please use /api/users");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class InvalidRequestException extends RuntimeException {
        public InvalidRequestException(String s) {
            super(s);
        }
    }

    @PutMapping
    public User updateUser(@RequestBody User userRecord) throws ChangeSetPersister.NotFoundException {
        if (userRecord == null || userRecord.getId() == null) {
            throw new InvalidRequestException("UserRecord or ID must not be null!");
        }
        Optional<User> optionalRecord = userRepository.findById(userRecord.getId());
//        if (optionalRecord.isEmpty()) {
//            throw new ChangeSetPersister.NotFoundException("Patient with ID " + userRecord.getId() + " does not exist.");
//        }
        User existingPatientRecord = optionalRecord.get();

        existingPatientRecord.setName(userRecord.getName());
        existingPatientRecord.setAge(userRecord.getAge());
        existingPatientRecord.setId(userRecord.getId());

        return userRepository.save(existingPatientRecord);
    }
}
