/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mikmin.resterant_order;

/**
 *
 * @author 2556963
 */
public class Order {
    private Entree entree;
    private Drink drink;
    private String specialRequest;
    private boolean waterSelection;

    public Order(Entree entree, Drink drink, String specialRequest, boolean waterSelection) {
        this.entree = entree;
        this.drink = drink;
        this.specialRequest = specialRequest;
        this.waterSelection = waterSelection;
    }

    public Order() {
        this.entree = Entree.NONE;
        this.drink = Drink.NONE;
        this.specialRequest = "-";
        this.waterSelection = false;
    }
    
    
    
    public double getEntreePrice() {
        switch (entree) {
            case CHICKEN_SALAD:
                return 6.50;
            case TURKEY_SANDWICH:
                return 6.75;
            case VEGETABLE_WRAP:
                return 6.0;
            case GRILLED_CHEESE:
                return 5.0;
            default:
                break;
        }
        return 0;
    }
    
    public double getDrinkPrice() {
        switch (drink) {
            case COFFEE:
                return 1.5;
            case TEA:
                return 1.5;
            case SODA:
                return 2.0;
            case JUICE:
                return 1.75;
            default:
                break;
        }
        return 0;
       
    }
    
    public double getTotalPrice() {
        double price = 0;
        
        price += getDrinkPrice();
        price += getEntreePrice();
        
        return price;
    }

    public Entree getEntree() {
        return entree;
    }

    public void setEntree(Entree entree) {
        this.entree = entree;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public boolean isWaterSelection() {
        return waterSelection;
    }

    public void setWaterSelection(boolean waterSelection) {
        this.waterSelection = waterSelection;
    }
    
    
    
    
    public enum Entree {
    CHICKEN_SALAD, TURKEY_SANDWICH, VEGETABLE_WRAP, GRILLED_CHEESE, NONE;
}
    
    public enum Drink {
        COFFEE, TEA, SODA, JUICE, NONE;
    }
    
}
