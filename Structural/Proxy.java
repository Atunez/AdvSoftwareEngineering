package AdvSoftwareEngineering.Structural;

import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

interface DataBaseMethods {
    abstract void putData(String key, String value);
    abstract String getData(String key);
}

// Our service implements some methods....
// In our case, we assume we have a database as the service
class DataBase implements DataBaseMethods {
    private Map<String, String> db;

    public DataBase(){
        db = new HashMap<>();
    }

    public void putData(String key, String value){
        db.put(key, value);
    }

    public String getData(String key){
        return db.get(key);
    }
}

// We can a proxy now to access the real service
// You can make this proxy actually be a singleton...
class LoggingProxy implements DataBaseMethods {
    private DataBase db;

    public LoggingProxy(){
        // In this example, I am concretely instantiating the database in the constructor, it is possible to remove this
        // and essentially let the user do it.
        db = new DataBase();
    };

    @Override
    public void putData(String key, String value) {
        System.out.println("About to put in: " + key + " " + value);
        db.putData(key, value);
    }

    @Override
    public String getData(String key) {
        System.out.println("About to get the value of the key: " + key);
        return db.getData(key);
    };

}

public class Proxy {
    // Client now doesn't deal with the database directly, but is using an object that does...
    // This object does more work that the user doesn't really care about (Logging in this instance)
    public static void main(String[] args) {
        LoggingProxy db = new LoggingProxy();

        db.putData("Hello", "World");
        System.out.println(db.getData("Hello"));
    }
}
