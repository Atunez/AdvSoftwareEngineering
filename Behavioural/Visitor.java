package Behavioural;

import java.util.ArrayList;

// Ahh... we meet again!
// This Pattern is the most confusing because it deals with the compiler itself :(


// Our visitors can visit X objects
interface Visitors {
    // Note that you must specify the class and *not* the interface of Elements!
    abstract void visitCircle(CircleNode cn);
    abstract void visitSquare(SquareNode sn);
}

// If you can be visited, then you can refer the visitor to the correct section of its methods
interface Elements {
    //  Double Dispatch!
    abstract void accept(Visitors v); 
}

class CircleNode implements Elements {
    @Override
    public void accept(Visitors v) {
        v.visitCircle(this);
    }
}

class SquareNode implements Elements {
    @Override
    public void accept(Visitors v) {
        v.visitSquare(this);
    }
}

// Visiters can do whatever...
// Printing... Or whatever... Regardless the point is that you can change this to be whatever
class PrinterVisitor implements Visitors {
    @Override
    public void visitCircle(CircleNode cn) {
        System.out.println("Printing a Circle Node");
    }

    @Override
    public void visitSquare(SquareNode sn) {
        System.out.println("Printing a Square Node");
    }
}

public class Visitor {
    public static void main(String[] args) {
        ArrayList<Elements> stuff = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            stuff.add(new CircleNode());
            stuff.add(new SquareNode());
        }
        Visitors printer = new PrinterVisitor();
        for (Elements nodElements : stuff) {
            nodElements.accept(printer);
        }
    }
}
