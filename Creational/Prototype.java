package AdvSoftwareEngineering.Creational;

// Prototype is all about being able to clone yourself and give a "new" instace without having to call new.

interface CloneableProto {
    abstract CloneableProto cloneSelf();
}

class ExpensiveClassToMake implements CloneableProto {
    private String SomethingToStore;

    public ExpensiveClassToMake(){
        // Assume this class does something crazy here...
        SomethingToStore = "Made Expensively...";
    }

    private ExpensiveClassToMake(String expensiveThingFromBefore){
        // We don't have to redo what we did before... We can just pass down the result directly
        this.SomethingToStore = expensiveThingFromBefore;
    }

    public ExpensiveClassToMake cloneSelf(){
        // So, cloning now becomes simple, since you know the result of the expensive thing
        return new ExpensiveClassToMake(this.SomethingToStore);
    }

    public String toString(){
        return this.SomethingToStore;
    }
}

// Very hard to show this off without doing Sleep, but I forgot too much to do that....
public class Prototype {
    public static void main(String[] args) {
        ExpensiveClassToMake inst = new ExpensiveClassToMake();
        ExpensiveClassToMake inst2 = inst.cloneSelf();

        System.out.println(inst);
        System.out.println(inst2);
    }
}
