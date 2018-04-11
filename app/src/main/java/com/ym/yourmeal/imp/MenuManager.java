package com.ym.yourmeal.imp;

import com.ym.yourmeal.inter.iMenu;
import com.ym.yourmeal.models.Menu;

import java.util.ArrayList;


public class MenuManager implements iMenu {

    public ArrayList<Menu> menus = new ArrayList <Menu>();

    static MenuManager menuManager = null;

    public static MenuManager getInstance() {
        if (menuManager == null) {
            menuManager = new MenuManager();
        }
        return menuManager;
    }

    @Override
    public void setMenu(Menu menu){
        menus.add(menu);
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }



}
