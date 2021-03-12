package com.users.restapi.controller;

//import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.users.restapi.models.User;
//import com.users.restapi.repository.UserRepository;
import com.users.restapi.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Users")
@CrossOrigin(origins = {"*"}) // [http://dominio.com]
@Validated
public class UserController {

	//@Autowired
	//UserRepository userRespository;
	
	@Autowired
	UserService userService;

	// @Value("${application.name}")
	// private String appName;

	@ApiOperation(value = "Retorna lista de usuarios")
	@GetMapping(value = "/users")
	public ResponseEntity<Page<User>> listUsers(@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam int pageSize, @RequestParam(defaultValue = "name") String sortBy) {

		return new ResponseEntity<Page<User>>(this.userService.getUsers(pageNumber, pageSize, sortBy), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um  usuario")
	@GetMapping("/user/{id}")
	public ResponseEntity<Optional<User>> listSpecificUser(@PathVariable(value = "id") long id) {

		Optional<User> user = Optional.ofNullable(this.userService.findUser(id));
		if (user.isPresent()) {

			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@ApiOperation(value = "Cria um usuário novo")
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		// String senha = new BCryptPasswordEncoder().encode(user.getPassword());
		return new ResponseEntity<User>(this.userService.saveUser(user), HttpStatus.CREATED);

	}

	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping("/user/{id}")
	public ResponseEntity<User> editUser(@PathVariable(value = "id") long id, @Valid @RequestBody User newUser) {
		Optional<User> oldUser = Optional.ofNullable(this.userService.findUser(id));
	
		if (oldUser.isPresent()) {
			User user = oldUser.get();
			user.setName(newUser.getName());
			user.setPassword(newUser.getPassword());
			User userUpdated = this.userService.saveUser(user);
			
			return new ResponseEntity<User>(userUpdated, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@ApiOperation(value = "Deleta um usuario")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long id) {
		Optional<User> deleteUser = Optional.ofNullable((this.userService.findUser(id)));
		if (deleteUser.isPresent()) {
			this.userService.removeUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
