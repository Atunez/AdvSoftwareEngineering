package Behavioural;

import java.util.ArrayList;
import java.util.Date;

import Behavioural.SaveCommand.UndoCommand;

// This is an attempt of implementing Command and Momento together...


// All Momento classes should have a way to restore from the momento itself
// Note that in this implementation classes that implement Momento are inner classes 
interface Memento {
    abstract void restore();
}

// All apps have a set of commands they can execute
interface FancyApp {
    abstract void start();
    abstract void stop();
    abstract Memento save();
}

// App that implements the interface and a Momento inner object
class ImplementFancyApp implements FancyApp {
    private String currentState;

    public ImplementFancyApp(){
        currentState = "Started App";
    }

    // These
    @Override
    public void start() {
        this.currentState = "Started at: " + new Date().getTime();
    }

    @Override
    public void stop() {
        this.currentState = "Ended at: " + new Date().getTime();
    }

    @Override
    public Memento save() {
        return new AppMomento(this.currentState);
    }

    // this is only avaliable to the inner class (well not in this case, but assume...)
    protected void setState(String state){
        this.currentState = state;
    }

    public String toString(){
        return this.currentState;
    }

    // The momento class is a POJO essentially...
    class AppMomento implements Memento {
        private String appField;
    
        public AppMomento(final String f){
            this.appField = f;
        }
    
        @Override
        public void restore() {
            // use undo Command...
            setState(this.appField);
        }
        
    }
    
}

// Command in this context is to start and stop classes from doing work...
// Each command should extend this base Command....
// abstract class BaseCommands {
//     protected FancyApp app;
//     protected ArrayList<Memento> states;

//     public BaseCommands(FancyApp app){
//         this.app = app;
//         this.states = new ArrayList<>();
//     }

//     protected ArrayList<Memento> getMomentos(){
//         return this.states;
//     }

//     protected void addMomento(Memento m){
//         this.states.add(m);
//     }

//     abstract void execute();
// }

// We can make this an interface instead 
// Since it will save us some trouble
interface BaseCommands {
    abstract void execute();
}

class StopCommand implements BaseCommands {
    private SaveCommand sv;
    private FancyApp app;

    public StopCommand(FancyApp app){
        this.sv = SaveCommand.getInstance(app); 
        this.app = app;
    }

    @Override
    public void execute() {
        app.stop();
        sv.execute();
    }

}

class StartCommand implements BaseCommands {
    private SaveCommand sv;
    private FancyApp app;

    public StartCommand(FancyApp app){
        this.app = app;
        this.sv = SaveCommand.getInstance(app); 
    }

    @Override
    public void execute() {
        app.start();
        sv.execute();
    }  
}

// SaveCommand (and by extension UndoCommand) are the caretakers of the FancyApp...
// They deal with the actual reversion part and storing things...
// But, they are still commands to the main App class...
class SaveCommand implements BaseCommands {
    private static SaveCommand me;
    private FancyApp app;
    protected static ArrayList<Memento> states;


    private SaveCommand(FancyApp app){
        this.app = app;
        states = new ArrayList<>();
    }

    public static SaveCommand getInstance(FancyApp app){
        if(me == null){
            SaveCommand.me = new SaveCommand(app);
        }
        return me;
    }

    protected static ArrayList<Memento> getMomentos(){
        return states;
    }

    protected void addMomento(Memento m){
        states.add(m);
    }

    @Override
    public void execute() {
        states.add(app.save());
    }  
    
    class UndoCommand implements BaseCommands {
        @Override
        public void execute() {
            int size = SaveCommand.getMomentos().size();
            if(size > 0){
                SaveCommand.getMomentos().get(size - 1).restore();
                SaveCommand.getMomentos().remove(size - 1);
            }
        }  
    }
    
}

// Is it possible to mix: Command, Momento, FactoryMethod, Singleton ?
// Essentially, you have a singleton for each FactoryMethod generated BaseCommand, which includes a Momento Command for the application?!
// Ok ignore, I had to do it just to get this to work. Did I mess something up?

public class MomentoCommand {
    public static void main(String[] args) throws InterruptedException {
        FancyApp app = new ImplementFancyApp();
        StartCommand st = new StartCommand(app);
        StopCommand sp = new StopCommand(app);
        UndoCommand ud = (SaveCommand.getInstance(app)).new UndoCommand();   
        SaveCommand sd = SaveCommand.getInstance(app);

        sd.execute();
        Thread.sleep(10);
        st.execute();
        Thread.sleep(10);
        sp.execute();
        Thread.sleep(10);
        st.execute();
        Thread.sleep(10);
        sp.execute();
        ud.execute();
        System.out.println(app);
        ud.execute();
        System.out.println(app);
        ud.execute();
        System.out.println(app);
        ud.execute();
        System.out.println(app);
        ud.execute();
        System.out.println(app);
    }
}
