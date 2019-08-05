package com.al.boxipark_visitor.Register.RegisterWithCard.Models.AccountInformation;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountInfo implements Serializable {
    public String result;
    public String accountStatus;
    public String registrationStatus;
    public ArrayList< PointBalances > pointBalances = new ArrayList < PointBalances > ();
}
