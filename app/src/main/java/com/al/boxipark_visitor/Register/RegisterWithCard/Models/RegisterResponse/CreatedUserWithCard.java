package com.al.boxipark_visitor.Register.RegisterWithCard.Models.RegisterResponse;

import java.io.Serializable;

public class CreatedUserWithCard implements Serializable {
    public String result;
    public int accountId;
    public OauthTokensWithCard oauthTokens;
    public String printedCardNumber;
    public String errorMessage;
    public String errorCode;

}
