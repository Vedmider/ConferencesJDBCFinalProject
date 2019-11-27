package com.study.persistence.DTO;

import java.util.Objects;

public class UserDTO {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDTO role;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode(){
        return Objects.hash(login, password, firstName, lastName, email, role);
    }

    @Override
    public boolean equals(final Object obj){
        if(obj instanceof UserDTO){
            final UserDTO other = (UserDTO) obj;
            return Objects.equals(role, other.role)
                    && login == other.login
                    && password == other.password
                    && firstName == other.firstName
                    && lastName == other.lastName
                    && email == other.email;
        } else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
