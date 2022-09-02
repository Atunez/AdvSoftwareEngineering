package Structural;

import java.util.ArrayList;

// Design Patterns, but make it data structures... 
// Or is it the other way around who knows?
// For our case, we will be trying to figure out the cost of things...

interface Executable {
    abstract int getCost();
}

// Assume we have packages, which can either have packages or items, we can operate on this structure w/ this pattern
class Boxes implements Executable {
    ArrayList<Executable> objectsWithExecutables;

    public Boxes(){
        objectsWithExecutables = new ArrayList<>();
    }

    public void addExecutable(Executable obj){
        objectsWithExecutables.add(obj);
    }

    public int getCost(){
        int total = 0;
        for (Executable executable : objectsWithExecutables) {
            total += executable.getCost();
        }
        return total;
    }
}

class Items implements Executable {
    private int cost;

    public Items(int cost){
        this.cost = cost;
    }

    public int getCost(){
        return this.cost;
    }
}

public class Composite {
    public static void main(String[] args) {
        Boxes box1 = new Boxes();
        Boxes box2 = new Boxes();
        Boxes box3 = new Boxes();
        Items item1 = new Items(10);
        Items item2 = new Items(20);
        Items item3 = new Items(30);

        box1.addExecutable(item1);
        box2.addExecutable(item2);
        box2.addExecutable(item3);
        box3.addExecutable(box1);
        box3.addExecutable(box2);

        System.out.println(box3.getCost());
    }
}
