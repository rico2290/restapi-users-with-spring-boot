/**
 * 
 */
package com.users.restapi.models;

/**
 * @author rico22
 *
 */

import java.io.Serializable;

import java.time.LocalDateTime;
//import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Data
@Table(name="TB_USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;
	
	@NotBlank
	@Column( name = "nome", length= 100)
	private String nome;
	
	@NotBlank
	@Email
	@Column( name= "email", length= 50)
	private String email;
	
	
	@NotBlank
	@Column( name = "endereco")
	private String endereco;
	
	@NotBlank
	@Column( name = "cidade", length= 15)
	private String cidade;
	
	@Column(name= "createdAt")
	private LocalDateTime createdAt;
	
	/*
	@NotNull
	@NotBlank
	//@Min(5)
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column( name= "password")
	private String password;
	 */
	
	/*
	 * @OneToMany(mappedBy="user")
	private List<FileUpload> files;
	*/
	
	/*
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

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime localDateTime) {
		this.createdAt = localDateTime;
	}
	

	public List<FileUpload> getFiles() {
		return files;
	}
	

	public void setFiles(List<FileUpload> files) {
		this.files = files;
	}
	*/
	/*
	public User() {
		// TODO Auto-generated constructor stub
	}
	*/


}

