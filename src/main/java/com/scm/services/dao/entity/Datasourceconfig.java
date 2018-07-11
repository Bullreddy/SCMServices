package com.scm.services.dao.entity;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name = "datasourceconfig")
@NamedQueries({
@NamedQuery(name="Datasourceconfig.findAll", query="SELECT d FROM Datasourceconfig d"),

@NamedQuery(name="Datasourceconfig.findOne", query="SELECT d FROM Datasourceconfig d WHERE d.name=:name")
})
public class Datasourceconfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String driverclassname;

	private String name;

	private String password;

	private String url;

	private String username;

	public Datasourceconfig() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDriverclassname() {
		return this.driverclassname;
	}

	public void setDriverclassname(String driverclassname) {
		this.driverclassname = driverclassname;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}