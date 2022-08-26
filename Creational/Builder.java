package AdvSoftwareEngineering.Creational;

import java.util.Arrays;

// The fun pattern!
// I've seen this pattern with UI stuff, which was weird. But nonetheless, interesting...

// The idea is that you are making a fancy constructor...
// Slightly obtuse example, but I am not creative enough to make a good one
class Pizza {
    private String base;
    private String cheese;
    private String[] toppings;

    public Pizza(){
        this.base = "No Base";
        this.cheese = "No Cheese";
        this.toppings = new String[0];
    }

    public void setBase(String base){
        this.base = base;
    }

    public void setCheese(String cheese){
        this.cheese = cheese;
    }

    public void setToppings(String[] toppings){
        this.toppings = toppings;
    }

    public String[] getToppings(){
        return this.toppings;
    }

    public String toString(){
        return "This pizza has: " + base + " " + cheese + " " + Arrays.toString(toppings);
    }
}

class PizzaBuilder{
    private Pizza currentPizza;

    public PizzaBuilder(){
        currentPizza = new Pizza();
    }

    public Pizza getPizza(){
        return currentPizza;
    }

    public void reset(){
        currentPizza = new Pizza();
    }

    public PizzaBuilder setBase(String base){
        currentPizza.setBase(base);
        return this;
    }

    public PizzaBuilder setCheese(String cheese){
        currentPizza.setCheese(cheese);
        return this;
    }

    public PizzaBuilder setToppings(String[] toppings){
        currentPizza.setToppings(toppings);
        return this;
    }   

    public PizzaBuilder addToppings(String topping){
        String[] currToppings = Arrays.copyOf(currentPizza.getToppings(), currentPizza.getToppings().length + 1);
        currToppings[currToppings.length-1] = topping;
        currentPizza.setToppings(currToppings);
        return this;
    }
}

public class Builder {
    public static void main(String[] args) {
        PizzaBuilder pb = new PizzaBuilder();
        pb.setBase("Ranch").setCheese("The Good Stuff").addToppings("Bell Peppers").addToppings("Pineapple");
        System.out.println(pb.getPizza());
        pb.reset();
        System.out.println(pb.getPizza());
    }
}
