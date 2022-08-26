package AdvSoftwareEngineering.Creational;

// DI Deals with injecting an object instead of creating it.
// For example, assume you have some logger that logs into AWS (For example)
// Instead of creating a new instance of that logger, you can use a centeralized logger that will log into a specific place {ahem, ahem, use singleton...}

// Assume all loggers follow an interface..
interface Logging {
    void recordLog();
}

// We have our first version of the logger
class ConcreteLogger1 implements Logging {
    public ConcreteLogger1(){

    }

    public void recordLog(){
        System.out.println("Coming from First Logger");
    }
}

// We have our second version of the logger
class ConcreteLogger2 implements Logging {
    public ConcreteLogger2(){
        
    }

    public void recordLog(){
        System.out.println("Coming from Second Logger");
    }
}

// Assume we have some fancy class that actually uses the loggers
class ComplexMethods {
    private Logging logger;
    public ComplexMethods(Logging logger){
        this.logger = logger;
    }

    public void ComplexMethod(){
        System.out.println("Complex Working Happening...");
        logger.recordLog();
    }
}

// Using the DIs
class DependencyInjection {
    public static void main(String[] args){
        // We start of by actually having the loggers
        Logging First = new ConcreteLogger1();
        Logging Second = new ConcreteLogger2();
        
        // Then, we make the instances with those loggers!
        // The actual class doesn't worry about making the logger and saves the class the pain of figuring out which logger to use.
        ComplexMethods firstInstance = new ComplexMethods(First);
        ComplexMethods secondInstance = new ComplexMethods(Second);

        // Print stuff
        firstInstance.ComplexMethod();
        secondInstance.ComplexMethod();
    }
}