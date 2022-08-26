package AdvSoftwareEngineering.Creational;

// This Pattern is weird...
// Essentially, it seems that, compared to the AbstractFactory, you only really care about a singular Object
// For example, if you wanted to send a message, and you have 2 queue objects. 
// The client here is actually the queues. They don't actually care about the message you want to send.
// The Message doesn't care about the queue either. It just knows that it exists and to pick one out.

interface Queues {
    abstract void sendMessage(String message);
}

abstract class BaseQueues {
    // The base class differs the usage of a queue for the caller...
    public abstract BaseQueues createQueue();

    // However....
    // The base queue class still needs to be able to do something regardless of the choice...
    public void baseSendMessage(String message){
        BaseQueues queue = createQueue();
        ((Queues) queue).sendMessage(message);
    }
}

class GoodQueues extends BaseQueues implements Queues {
    public GoodQueues(){}

    public void sendMessage(String message){
        System.out.println("Sent from good Queue, message: " + message);
    }

    @Override
    public BaseQueues createQueue() {
        return new GoodQueues();
    }

}

class BadQueues extends BaseQueues implements Queues {
    public BadQueues(){}

    public void sendMessage(String message){
        System.out.println("Sent from bad Queue, message: " + message);
    }

    @Override
    public BaseQueues createQueue() {
        return new BadQueues();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        BaseQueues queue = new GoodQueues();
        queue.baseSendMessage("Testing from main...");

        BaseQueues queue2 = new BadQueues();
        queue2.baseSendMessage("Testing from main...");
    }
}

// Not to be confused with the simple factory...
// Simple Goes from Client (Main) to The Factory Back to Main (If possible...)
// In that case, we would need something from the client to specifiy which factory to use...

// But, in this case, we went from Client (Main..) to the "Creator" namely, BaseQueues. The Creator then, "creates" the child it will use 
// Here, BaseQueues doesn't know which child it will end up using, but it doesn't care. 
// Creator will run whatever it needs to in the child, and then return back to Main (If it wants to...)