package com.ecommerce.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    @Size(min =3, max=10, message = "Invalid first name(3-10 characters)")
    private String firstName;
    @Size(min =3, max=10, message = "Invalid first name(3-10 characters)")
    private String lastName;
    private String username;
    @Size(min =5, max=15, message = "Invalid password(5-15 characters)")
    private String password;
    private String repeatPassword;


    public @Size(min = 3, max = 10, message = "Invalid first name(3-10 characters)") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 3, max = 10, message = "Invalid first name(3-10 characters)") String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 3, max = 10, message = "Invalid first name(3-10 characters)") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 3, max = 10, message = "Invalid first name(3-10 characters)") String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @Size(min = 5, max = 15, message = "Invalid password(5-15 characters)") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 5, max = 15, message = "Invalid password(5-15 characters)") String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
