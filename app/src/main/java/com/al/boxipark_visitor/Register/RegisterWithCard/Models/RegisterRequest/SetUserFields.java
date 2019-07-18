package com.al.boxipark_visitor.Register.RegisterWithCard.Models.RegisterRequest;

import java.io.Serializable;

public class SetUserFields implements Serializable{
    public String style="strings";
    public String username;
    public String password;
    public String email;
    public String firstName;
    public String mobilePhone;


    public void setStyle(String style) {
        this.style = style;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
