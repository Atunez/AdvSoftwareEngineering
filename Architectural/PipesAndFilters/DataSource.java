package Architectural.PipesAndFilters;


// Run Example, Use Intents in Android to Setup classes
public class DataSource {
    public static void main(String[] args) {
        BigPipe bp = new BigPipe();
        FilterableActivities<?> act1 = new GameActivity();
        FilterableActivities<?> act2 = new GraphActivity();
        bp.addFilter(act1);
        bp.addFilter(act2);
        
        bp.sendData("Hello Brady");
        bp.sendData("Hello Luke");
        bp.sendData("Hey");
    }
}
