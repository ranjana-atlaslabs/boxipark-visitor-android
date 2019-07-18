package com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
    public String name;
    public List<Choices> choices;
    public List<Object> photos;
    public int order_num;

    public int id;
    public String description;
}
