package Behavioural;

import org.w3c.dom.Node;

// State machines on crack...
// I can't come up with a good example :(

interface Nodes {
    abstract void moveNext();
    abstract void sendMessage();
}

class ApplicationOfNodes {
    public Nodes currNode;

    public ApplicationOfNodes(){
        this.currNode = new NormalNode(this);
    }

    public void moveNext(){
        currNode.moveNext();
    }

    public void sendMessage(){
        currNode.sendMessage();
    }
}

// This isn't a really good demo tbf, unless you have a big number of nodes, a switch may do fine...
class NormalNode implements Nodes {
    public ApplicationOfNodes nds;
    private int n;

    public NormalNode(ApplicationOfNodes nds){
        this.nds = nds;
        n = 3;
    }

    @Override
    public void moveNext() {
        if(n == 0){
            nds.currNode = new ErrorNode(nds);
        }
        n--;
    }

    @Override
    public void sendMessage() {
        System.out.println("You have " + n + " tries left...");
    }
   
}

class ErrorNode implements Nodes {
    public ApplicationOfNodes nds;

    public ErrorNode(ApplicationOfNodes nds){
        this.nds = nds;
    }

    @Override
    public void moveNext() {}

    @Override
    public void sendMessage() {
        System.out.println("You are at error node...");
    }
}

public class State {
    public static void main(String[] args) {
        ApplicationOfNodes nds = new ApplicationOfNodes();
        nds.sendMessage();
        nds.moveNext();
        nds.sendMessage();
        nds.moveNext();
        nds.sendMessage();
        nds.moveNext();
        nds.sendMessage();
        nds.moveNext();
        nds.sendMessage();
    }
}
