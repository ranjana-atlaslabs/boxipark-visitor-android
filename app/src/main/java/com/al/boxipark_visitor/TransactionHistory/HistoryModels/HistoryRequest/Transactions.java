package com.al.boxipark_visitor.TransactionHistory.HistoryModels.HistoryRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class Transactions implements Serializable {

    public String datetime;
    public ArrayList< DetailsList > details = new ArrayList < DetailsList > ();
    public String storeName;
    public int transactionId;
}
