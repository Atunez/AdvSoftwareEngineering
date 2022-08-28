package AdvSoftwareEngineering.Structural;

// Adapter is just a fancy way to cast objects.
// Although, from your end, you don't really have to worry about how the casting is done.

// I want the data in an int format...
// Although realistically all the outputs of these adapters would be objects
interface ServiceAdapterInterface {
    abstract int getDataFromService();
}

// Assume we have a service we can't change...
class ExistingService {
    public ExistingService(){}

    public String outputOfService(){
        return "2";
    }
}

// Our adapter is responsible for initilization but also implementing the interface
class ServiceAdapter implements ServiceAdapterInterface {
    private ExistingService service;

    public ServiceAdapter(){
        service = new ExistingService();
    }

    public int getDataFromService(){
        return Integer.parseInt(service.outputOfService());
    }
}

public class Adapter {
    // In main, we get to now use the data that was transformed
    public static int multiplyByTwo(int x){
        return 2*x;
    }
    public static void main(String[] args) {
        ServiceAdapter adpt = new ServiceAdapter();
        // Testing to get 4
        System.out.println(multiplyByTwo(adpt.getDataFromService()));
    }
}
