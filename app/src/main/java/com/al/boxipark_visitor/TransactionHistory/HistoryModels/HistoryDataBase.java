package com.al.boxipark_visitor.TransactionHistory.HistoryModels;

public class HistoryDataBase {

    private String storeName;
    private String point;
    private int id;
    private String date;

    public HistoryDataBase(String storeName, String point, int id, String date) {
        this.storeName=storeName;
        this.point=point;
        this.id=id;
        this.date=date;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getPoint() {
        return point;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
