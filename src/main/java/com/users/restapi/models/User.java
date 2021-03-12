/**
 * 
 */
package com.users.restapi.models;

/**
 * @author rico22
 *
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="TB_USER", schema = "public")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column( name = "id")
	private long id;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Column( name = "name")
	private String name;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Column( name= "password")
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<File> files;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

}

