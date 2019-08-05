package com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterResponse;

import java.io.Serializable;
import java.util.Map;

public class CreatedUser implements Serializable {
    public String result;
    public int accountId;
    public OauthTokens oauthTokens;
    public String printedCardNumber;
    public String errorMessage;
    public String errorCode;
    public Map errorsByField;

}
