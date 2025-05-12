/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Aly
 */
public abstract class User {
    // 91. User (Abstract Class)
    // • Responsibility: Base class for all system users
    // • Key Attributes: userId, username, password, name, email, contactInfo
    // • Key Methods: login(), logout(), updateProfile()

    private String userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String contactInfo;

    public User() {

    }

    public User(String userId, String username, String password, String name, String email, String contactInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
    }

    public abstract boolean login(String username, String password);

    public void logout() {
        // Logic for user logout
    }

   public abstract void updateProfile();




    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
