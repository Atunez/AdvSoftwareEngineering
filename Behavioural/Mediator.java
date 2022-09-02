package Behavioural;

import java.util.ArrayList;

// Mediators deal with removing the communications between objects and centerlize everything...
// all Mediators should be able recieve a notification...
interface Mediators {
    abstract void notify(Object sender, String event);
}

// The mediator knows what classes it is actually expecting...
class ConcMediator implements Mediators {
    public Dropdowns currentYear;
    public Dropdowns yearBorn;
    public Errors showErrorsHere;

    public ConcMediator(){
        this.currentYear = new Dropdowns(this);
        this.yearBorn = new Dropdowns(this);
        this.showErrorsHere = new Errors(this);
    }


    @Override
    public void notify(Object sender, String event) {
        if((sender == currentYear || sender == yearBorn) && event == "Selected" && yearBorn.isSelected(2003) && currentYear.isSelected(2022)){
            showErrorsHere.showError("Too young to drink!");
        }
    }
    
}

// For this example, we will be using some UI components to determine if someone can drink...
abstract class BaseComponents {
    protected Mediators md;

    public BaseComponents(Mediators md){
        this.md = md;
    }

    public void inFocus(){
        md.notify(this, "inFocus");
    }
}

class Dropdowns extends BaseComponents {
    private ArrayList<Integer> opts;
    private int selected;

    public Dropdowns(Mediators md) {
        super(md);
        this.opts = new ArrayList<>();
    }
    
    public void selected(int s){
        this.selected = s;
        super.md.notify(this, "Selected");
    }

    public void addString(int s){
        this.opts.add(s);
    }

    public boolean isSelected(int s){
        return s == selected;
    }
}

// See if we have to show some error or not...
class Errors extends BaseComponents {
    private String errorShown;

    public Errors(Mediators md) {
        super(md);
    }
    
    public void showError(String msg){
        this.errorShown = msg;
        super.md.notify(this, "showError");
    } 

    public String toString(){
        return this.errorShown;
    }
}

public class Mediator {
    public static void main(String[] args) {
        // Run example...
        ConcMediator md = new ConcMediator();
        md.yearBorn.selected(2003);
        md.currentYear.selected(2022);
        System.out.println(md.showErrorsHere);
        
    }
}
