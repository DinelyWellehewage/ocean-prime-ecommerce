package com.ecommerce.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @Size(min = 3,max = 15,message = "First name should have 3-15 characters")
    private String firstName;
    @Size(min = 3,max = 15,message = "Last name should have 3-15 characters")
    private String lastName;

    private String username;

    @Size(min = 5,max=20,message = "Password should have 5-20 characters")
    private String password;
    private String repeatPassword;

    public @Size(min = 3, max = 15, message = "First name should have 3-15 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 3, max = 15, message = "First name should have 3-15 characters") String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 3, max = 15, message = "Last name should have 3-15 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 3, max = 15, message = "Last name should have 3-15 characters") String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @Size(min = 5, max = 20, message = "Password should have 5-20 characters") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 5, max = 20, message = "Password should have 5-20 characters") String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
