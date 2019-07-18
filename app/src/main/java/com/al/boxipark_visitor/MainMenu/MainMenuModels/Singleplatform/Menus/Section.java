package com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus;

import java.io.Serializable;
import java.util.List;

public class Section implements Serializable {
    public int order_num;
    public List<Item> items;
    public String description;
    public String name;
    public int id;
}
