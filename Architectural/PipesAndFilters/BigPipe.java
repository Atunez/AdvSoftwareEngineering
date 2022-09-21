package Architectural.PipesAndFilters;

import java.util.ArrayList;

public class BigPipe {
    public ArrayList<FilterableActivities<?>> filterables;

    // Initialization
    public BigPipe(){
        filterables = new ArrayList<>();
    }

    // Provide a method to add new filters...
    public void addFilter(FilterableActivities<?> classs){
        filterables.add(classs);
    }

    // Input is the service object
    public void sendData(String d){
        for(FilterableActivities<?> act : filterables){
            act.recieveData(act.getFilter().filterData(d));
        }
    }
}
