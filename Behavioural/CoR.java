package Behavioural;

// Back to the data structures!
// Linked lists this time.

// CoR deals with trying to figure out the set of actions to take before processing a request
// We will use it to simulate authentication...

// We return a boolean to signify if we have a problem with the request or not...
interface Handlers {
    abstract boolean handleRequest(String obj);
}

abstract class BaseHandlers implements Handlers {
    protected BaseHandlers next;
    
    public BaseHandlers(BaseHandlers... nxt){
        if(nxt.length == 1)
            this.next = nxt[0];
    }

    // Base class deals with how to send stuff to the next
    public boolean sendToNext(String obj){
        if(next != null)
            next.handleRequest(obj);
        return true;
    }
}

// Handlers have to do some logic, but nth else...
class ObjectAvaliable extends BaseHandlers {
    public ObjectAvaliable(BaseHandlers... nxt){
        super(nxt);
    }

    @Override
    public boolean handleRequest(String obj) {
        if(obj.contains("id:23")){
            return super.sendToNext(obj);
        }
        return false;
    }
}

class EmailAvaliable extends BaseHandlers {
    public EmailAvaliable(BaseHandlers... nxt){
        super(nxt);
    }

    @Override
    public boolean handleRequest(String obj) {
        if(obj.contains("email:issaa@appstate.edu")){
            return super.sendToNext(obj);
        }
        return false;
    }
}

public class CoR {
    public static void main(String[] args) {
        // testing...
        String req = "id:23 and email:issaa@appstate.edu";
        EmailAvaliable ea = new EmailAvaliable();
        ObjectAvaliable oa = new ObjectAvaliable(ea);
        if(oa.handleRequest(req)){
            System.out.println("Yay, I can handle your request!");
        }
    }
}
