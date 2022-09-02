package Structural;


// Client needs an abstraction to some actual application for the bridge to work
// Note that the client itself doesn't have to know about the abstraction in the background...

// In our case, all of our devices should really have some common API
interface Devices {
    abstract void turnOn();
    abstract void turnOff();
    abstract boolean getState();
}

// Then we can have concrete classes 
class TV implements Devices {
    private boolean state;

    public TV(){
        state = false;
    }

    @Override
    public boolean getState(){
        return this.state;
    }

    @Override
    public void turnOn() {
        state = true;
    }
    
    @Override
    public void turnOff() {
        state = false;
    }
}

// Then we have our abstraction....
// Users will use the remote, the remote understands the API of the devices and can hide external work from the clients
// It is possible to make more complex remotes, but we can have them extend the remote class itself...
class TVRemote {
    private Devices device;

    public TVRemote(){
        device = new TV();
    }

    public void switchState(){
        if(device.getState()){
            device.turnOff();
        }else{
            device.turnOn();
        }
    }

    public boolean getState(){
        return device.getState();
    }
}


public class Bridge {
    public static void main(String[] args) {
        // It is possible to actually remove the TV specifion from this
        // since TV is just a generic device that implements the interface...
        TVRemote rmt = new TVRemote();
        System.out.println(rmt.getState());
        rmt.switchState();
        System.out.println(rmt.getState());
        rmt.switchState();
        System.out.println(rmt.getState());
    }
}


