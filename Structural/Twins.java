package AdvSoftwareEngineering.Structural;

// Base Classes
class ToBeExtendedFirst {
    public ToBeExtendedFirst(){}

    public int canDoSummation(int one, int two){
        return one + two;
    }
}

class ToBeExtendedSecond {
    public ToBeExtendedSecond(){}

    public int canDoMultiplication(int one, int two){
        return one * two;
    }
}

// Set up twins...
class WishesToExtendBoth extends ToBeExtendedFirst {
    private Twin twin;

    public WishesToExtendBoth(){}

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
}

class Twin extends ToBeExtendedSecond {
    private WishesToExtendBoth OtherTwin;

    public Twin(){}

    public void setTwin(WishesToExtendBoth twin){
        this.OtherTwin = twin;
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
        Twin second = new Twin();

        // We must actually do this part by hand unfortunately...
        first.setTwin(second);
        second.setTwin(first);

        System.out.println(first.canSquare(2));
        System.out.println(second.canDoTimesThree(2));
    }
}
