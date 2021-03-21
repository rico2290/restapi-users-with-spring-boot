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

import com.users.restapi.domain.service.CrudUserService;
import com.users.restapi.models.User;
import com.users.restapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Users")
@CrossOrigin(origins = {"*"})  // [http://dominio.com]
@Validated
public class UserController {
	
	@Autowired
	CrudUserService crudUserService;
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Retorna lista de usuarios")
	@GetMapping(value = "/users")
	public ResponseEntity<Page<User>> getUsers(@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam int pageSize, @RequestParam(defaultValue = "name") String sortBy) {

		return new ResponseEntity<Page<User>>(this.userService.getUsers(pageNumber, pageSize, sortBy), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um  usuario")
	@GetMapping("/user/{userId}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "userId") long userId) {

		Optional<User> user = Optional.ofNullable(crudUserService.findById(userId));
		if (user.isPresent()) {

			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);

	}

	@ApiOperation(value = "Cria um usuário novo")
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		// String senha = new BCryptPasswordEncoder().encode(user.getPassword());
		return new ResponseEntity<User>(crudUserService.save(user), HttpStatus.CREATED);

	}

	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping("/user/{userId}")
	public ResponseEntity<User> editUser(@PathVariable(value = "userId") long userId, @Valid @RequestBody User newUser) {
		Optional<User> oldUser = Optional.ofNullable(userService.findById(userId));
	
		if (oldUser.isPresent()) {
			User user = oldUser.get();
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			User userUpdated = userService.save(user);
			
			return new ResponseEntity<User>(userUpdated, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

	}

	@ApiOperation(value = "Deleta um usuario")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "id") long userId) {
		Optional<User> deleteUser = Optional.ofNullable((crudUserService.findById(userId)));
		if (deleteUser.isPresent()) {
			this.userService.removeUser(userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);

	}

}
