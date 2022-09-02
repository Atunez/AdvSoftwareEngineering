package Creational;

// Singleton deals with ensuring that only ONE instance of a class is ever made.
// Consider Singleton if:
// Ownership of a single instance cannot be reasonably assigned 
// Global Access {Although... Careful you aren't actually replacing a global variable with a singleton. Its still a global variable}
// Lazy Initialization

// Seems like logging is really useful huh...?
class LoggingSingleton {
    private static LoggingSingleton me;

    // In this case, I am somewhat making this counter be a "global" state, but you can think that this
    // is a link to log a metric to some website and so on..
    private int counter;

    private LoggingSingleton(){
        this.counter = 0;
    }

    public void recordLog(){
        System.out.println("Logging.... Call #: " + this.counter);
        this.counter++;
    }

    public static LoggingSingleton getInstance(){
        if(me == null){
            me = new LoggingSingleton();
            return me;
        }
        return me;
    }
}

public class Singleton {
    public static void main(String[] args) {
        // Both of these grab the same instance.
        LoggingSingleton inst1 = LoggingSingleton.getInstance();
        LoggingSingleton inst2 = LoggingSingleton.getInstance();

        inst1.recordLog();
        inst2.recordLog();
    }
}
