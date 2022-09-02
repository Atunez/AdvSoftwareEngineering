package Behavioural;

// Somewhat similar to State, except stratigies don't know about each other
interface Strats {
    abstract int getCost(int num1, int num2);
}

class ExponentialAddition implements Strats {
    @Override
    public int getCost(int num1, int num2) {
        return (num1 ^ num2) << 2; // XOR menace....
    }
}

class NormalAddition implements Strats {
    @Override
    public int getCost(int num1, int num2) {
        return num1 + num2;
    }
}

class SpecialAddition implements Strats {
    @Override
    public int getCost(int num1, int num2) {
        return Integer.min(num1, num2) * 2;
    }
}

// Our context...
class RandomNumbersAddition {
    private Strats howToAdd;

    public RandomNumbersAddition(){}

    public void setStrat(Strats h){
        howToAdd = h;
    }

    public int addNumbers(int num1, int num2){
        return howToAdd.getCost(num1, num2);
    }
}

public class Strategy {
    public static void main(String[] args) {
        RandomNumbersAddition rng = new RandomNumbersAddition();
        Strats f = new ExponentialAddition();
        Strats s = new NormalAddition();
        Strats t = new SpecialAddition();

        rng.setStrat(f);
        System.out.println(rng.addNumbers(1, 2));
        rng.setStrat(s);
        System.out.println(rng.addNumbers(1, 2));
        rng.setStrat(t);
        System.out.println(rng.addNumbers(1, 2));
    }
}
