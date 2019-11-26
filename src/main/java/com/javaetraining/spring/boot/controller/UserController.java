package com.javaetraining.spring.boot.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javaetraining.spring.boot.exception.UserNotFoundException;
import com.javaetraining.spring.boot.model.User;
import com.javaetraining.spring.boot.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	//-------------------Retrieve All Users--------------------------------------------------------

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users == null || users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
    //-------------------Retrieve Single User--------------------------------------------------------
    
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
//            System.out.println("User with id " + id + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        	throw new UserNotFoundException("User with id " + id + " not found");
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------

	@PostMapping(value = "/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		URI uri = ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//		return ResponseEntity.created(uri);
	}
	
	
 //------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setBirthDate(user.getBirthDate());
        currentUser.setEmail(user.getEmail());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    
    //------------------- Delete a User --------------------------------------------------------
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.OK);
    }
     
	
}
