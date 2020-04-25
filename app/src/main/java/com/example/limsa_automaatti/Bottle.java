package com.example.limsa_automaatti;

public class Bottle {

    private String name;

    private String manufacturer;

    private double total_energy;

    private double size;

    private double price;


    public Bottle(){
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        total_energy = 0.3;
        size = 0.5;
        price = 1.80;
    }

    public Bottle(String name1, String manuf, double totE, double size1, double price1){
        name = name1;
        manufacturer = manuf;
        total_energy = totE;
        size = size1;
        price = price1;
    }

    public String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public double getEnergy(){
        return total_energy;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}
