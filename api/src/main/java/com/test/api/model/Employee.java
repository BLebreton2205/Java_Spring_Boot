package com.test.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }


    @Column(name="last_name")
    private String lastName;
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    private String mail;
    public String getMail() {
        return this.mail;
    }
    public void setMail(String mail) {
    	this.mail = mail;
    }

    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }

}
