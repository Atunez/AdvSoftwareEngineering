package Structural;

import java.util.HashMap;
import java.util.Map;

// Flyweight is usually for RAM, so it doesn't matter in a large scheme too much...

// The idea is that we extract the expensive part of an object, and try to cache them
// In this example, consider a system of images, where each image can have multiple alternative texts...

class ExpensiveInformation {
    private String name;

    public ExpensiveInformation(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}

// Class to keep track of any images we have...
class InformationGenerator {
    Map<String, ExpensiveInformation> ExpensiveInformations;
    
    public InformationGenerator(){
        this.ExpensiveInformations = new HashMap<>();
    }

    // Add a new image
    public void addInfo(ExpensiveInformation img){
        if(!ExpensiveInformations.containsKey(img.getName())){
            ExpensiveInformations.put(img.getName(), img);
        }
    }

    // Throws an error if image name isn't there
    public ExpensiveInformation getEntry(String name){
        return ExpensiveInformations.get(name);
    }
    
}

class AltText {
    private ExpensiveInformation img;
    private String desc;

    public AltText(ExpensiveInformation img, String desc){
        this.img = img;
        this.desc = desc;
    }

    public String toString(){
        return this.desc + this.img.getName();
    }
}


public class Flyweight {
    public static void main(String[] args) {
        // Image in question
        ExpensiveInformation img = new ExpensiveInformation("Expensive...");
        // Generator to ensure that we only keep the same image once
        InformationGenerator factory = new InformationGenerator();
        factory.addInfo(img);
        // We can now generate a lot of alt texts without worrying about storing the image multiple times...
        AltText first = new AltText(factory.getEntry("Expensive..."), "Hello World");
        AltText second = new AltText(factory.getEntry("Expensive..."), "Hello World 2!");
        System.out.println(first);
        System.out.println(second);
    }
}
