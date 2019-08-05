package com.al.boxipark_visitor.ResetPassword.ResetPasswordModel.ResetPasswordRequest;

public class ResetRequestModel {
    public String authentication = "anonymous";
    public int merchantId=508;
    public String username;

    public void setUsername(String username) {
        this.username = username;
    }
}
