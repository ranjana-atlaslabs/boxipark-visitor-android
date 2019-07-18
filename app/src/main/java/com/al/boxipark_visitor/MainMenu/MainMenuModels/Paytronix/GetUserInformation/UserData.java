package com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {
    public String result;
    public ArrayList <Integer> accountIds = new ArrayList < Integer>();
    public ArrayList < Object > addresses = new ArrayList < Object > ();
    public Fields fields;
    public ArrayList < String > primaryCardNumbers = new ArrayList< String >();

}
