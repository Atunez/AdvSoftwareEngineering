package Architectural.PipesAndFilters;

// Base Filter all classes would need to use 
interface Filters<E> {
    // E is the object classes want to work with
    // String is the object from the original service
    public E filterData(String info);
}

// Filters only need to implement the abstract method...
class GameFilter implements Filters<String>{
    public String filterData(String info){
        if(info.equals("Hey")){
            return "Up";
        }
        return "Down";
    }
}

// Classes are need to fill the E parameter of the interface...
class HeavyString {
    public String heavy;
    public HeavyString(String toHeavy){
        this.heavy = toHeavy;
    }

    public String toString(){
        return heavy;
    }
}

// Concrete filters can then implement the interface....
class GraphFilter implements Filters<HeavyString>{
    public HeavyString filterData(String info){
        return new HeavyString(info);
    }
}

// Fuck Inhertiance...
// All Activities that have a filter
// should be able to give their own filter
// and get information from their filter....
interface FilterableActivities<E> {
    public Filters<E> getFilter();
    // This method should only be called with an object that is castable
    // to an object of the filter return type
    public void recieveData(Object object);
}

// Implementing an Activitiy...
class GameActivity implements FilterableActivities<String> {
    public Filters<String> classFilter;

    public GameActivity(){
        this.classFilter = new GameFilter();
    }

    public void recieveData(Object datas){
        String data = (String) datas;
        System.out.println(data);
    }

    public Filters<String> getFilter(){
        return this.classFilter;
    }
}

class GraphActivity implements FilterableActivities<HeavyString> {
    public Filters<HeavyString> classFilter;

    public GraphActivity(){
        this.classFilter = new GraphFilter();
    }

    public void recieveData(Object datas){
        HeavyString data = (HeavyString) datas;  
        System.out.println(data);
    }

    public Filters<HeavyString> getFilter(){
        return this.classFilter;
    }
}

// Mid life... 
// Nothing should happen here...
public class Activities {
    
}
