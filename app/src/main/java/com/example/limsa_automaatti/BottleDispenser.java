package com.example.limsa_automaatti;

import java.util.ArrayList;

public class BottleDispenser  {


    private static BottleDispenser bdispenser = null;

    // private int bottles;
    private double money;
    private ArrayList<Bottle> bottleList;

    private BottleDispenser() {

      //  bottles = 5;

        money = 0;

        bottleList = new ArrayList();
        // Add Bottle-objects to the array
        bottleList.add(new Bottle("Coca-Cola", "The Coca-Cola Company", 1.0, 0.5, 2.0));
        bottleList.add(new Bottle("Coca-Cola", "The Coca-Cola Company", 1.0, 0.5, 2.0));
        bottleList.add(new Bottle("Coca-Cola", "The Coca-Cola Company", 1.0, 1.5, 2.5));
        bottleList.add(new Bottle("Coca-Cola", "The Coca-Cola Company", 1.0, 1.5, 2.5));
        bottleList.add(new Bottle("Coca-Cola Zero", "The Coca-Cola Company", 1.0, 0.5, 2.0));
        bottleList.add(new Bottle("Coca-Cola Zero", "The Coca-Cola Company", 1.0, 0.5, 2.0));
        bottleList.add(new Bottle("Coca-Cola Zero", "The Coca-Cola Company", 1.0, 1.5, 2.5));
        bottleList.add(new Bottle("Coca-Cola Zero", "The Coca-Cola Company", 1.0, 1.5, 2.5));
        bottleList.add(new Bottle("Fanta", "The Coca-Cola Company", 1.0, 0.5, 1.95));
        bottleList.add(new Bottle("Fanta", "The Coca-Cola Company", 1.0, 0.5, 1.95));
        bottleList.add(new Bottle("Fanta", "The Coca-Cola Company", 1.0, 1.5, 2.45));
        bottleList.add(new Bottle("Fanta", "The Coca-Cola Company", 1.0, 1.5, 2.45));
        bottleList.add(new Bottle("Pepsi Max", "PepsiCo", 1.0, 0.5, 1.8));
        bottleList.add(new Bottle("Pepsi Max", "PepsiCo", 1.0, 0.5, 1.8));
        bottleList.add(new Bottle("Pepsi Max", "PepsiCo", 1.0, 1.5, 2.2));
        bottleList.add(new Bottle("Pepsi Max", "PepsiCo", 1.0, 1.5, 2.2));
    }

    public void addMoney(int amount) {

        money += amount;

        System.out.println("Klink! Added more money!");
        System.out.println("Now you have " + money);

    }


    public boolean buyBottle(String bottleName, double bottleSize) {

        for (Bottle bottle : bottleList) {
            if (bottle.getName().equals(bottleName) && bottle.getSize() == bottleSize) {
                if (money >= bottle.getPrice()) {
                    money -= bottle.getPrice();
                    bottleList.remove(bottle);
                    return true;
                } else {
                    return false;
                }
            }
        }
            return false;
    }



    public int returnMoney() {
        return (int) money;
    }


    public static BottleDispenser getInstance() {
        if (bdispenser == null) {
            return new BottleDispenser();
        }
        return bdispenser;
    }

}
