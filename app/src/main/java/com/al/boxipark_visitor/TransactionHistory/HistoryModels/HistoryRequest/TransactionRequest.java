package com.al.boxipark_visitor.TransactionHistory.HistoryModels.HistoryRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class TransactionRequest implements Serializable {

    public  String result;
    public ArrayList < Transactions > transactions = new ArrayList < Transactions > ();


}
