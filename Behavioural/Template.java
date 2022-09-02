package Behavioural;

// The best example that comes to my head is a MII CHARACTER IM DONE
abstract class MiiCreator {
    final public void buildTheMii(){
        Face();
        Body();
        Legs();
        Accessories();
    }

    public void Face(){
        System.out.println("Default Face");
    }
    public void Body(){
        System.out.println("Default Body");
    }
    public void Legs(){
        System.out.println("Default Legs");
    }
    public void Accessories(){
        System.out.println("No Accessories");
    }
}

class AbdelMii extends MiiCreator {
    @Override
    public void Accessories(){
        System.out.println("Glasses");
    }

    @Override
    public void Body(){
        System.out.println("Middle Eastern");
    }
}

class FenwickMii extends MiiCreator {
    @Override
    public void Accessories(){
        System.out.println("Monicles");
    }   
}

public class Template {
    public static void main(String[] args) {
        MiiCreator first = new AbdelMii();
        MiiCreator second = new FenwickMii();
        first.buildTheMii();
        second.buildTheMii();
    }
}
