package Structural;

// Base Classes
class ToBeExtendedFirst {
    public ToBeExtendedFirst(){}

    public int canDoSummation(int one, int two){
        return one + two;
    }
}

abstract class ToBeExtendedSecond {
    public int canDoMultiplication(int one, int two){
        return one * two;
    }
}

// Set up twins...
class WishesToExtendBoth extends ToBeExtendedFirst {
    private Twin twin;

    public WishesToExtendBoth(){
        this.twin = new Twin(this);
    }

    public void setTwin(Twin twin){
        this.twin = twin;
    }

    @Override
    public int canDoSummation(int one, int two) {
        return super.canDoSummation(one, two);
    }

    public int canSquare(int one){
        return twin.canDoMultiplication(one, one);
    }

    public int canDoTimesThree(int one){
        return twin.canDoTimesThree(one);
    }

}

class Twin extends ToBeExtendedSecond {
    private WishesToExtendBoth OtherTwin;

    public Twin(WishesToExtendBoth ot){
        this.OtherTwin = ot;
    }

    @Override
    public int canDoMultiplication(int one, int two) {
        return super.canDoMultiplication(one, two);
    }

    public int canDoTimesThree(int one){
        return OtherTwin.canDoSummation(OtherTwin.canDoSummation(one, one), one);
    }
}

// Use Twins...
public class Twins {
    public static void main(String[] args) {
        WishesToExtendBoth first = new WishesToExtendBoth();

        System.out.println(first.canSquare(2));
        System.out.println(first.canDoTimesThree(2));
    }
}
