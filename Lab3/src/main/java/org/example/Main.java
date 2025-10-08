package org.example;

import MenuPackage.MainMenu;

public class Main {

    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        
        mainMenu.switchMenuOptions(mainMenu.menu());
    }
}