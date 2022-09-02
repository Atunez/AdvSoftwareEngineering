package Behavioural;

import java.util.ArrayList;


// Nodes to work with...
// Could make this a generic class and abstract it...
class StringNode {
    private String value;
    private ArrayList<StringNode> children;

    public StringNode(String v, ArrayList<StringNode> c){
        this.value = v;
        this.children = c;
    }

    public ArrayList<StringNode> getChildren() {
        return this.children;
    }

    public void addChild(StringNode e) {
        this.children.add(e);
    }

    public String toString(){
        return this.value;
    }
}

// This pattern is somewhat similar to the Iterators in Java...
// We have the requirements of an iterator
interface TreeIterators {
    abstract StringNode getNext();
    abstract boolean hasNext();
}
// We have the requirement that the object can provide these iterators...
interface Trees {
    abstract TreeIterators createBFSIterator();
    abstract TreeIterators createDFSIterator();
}

// Then our object can implement the generic interact for it...
// We can add more requirements to the interface, but that doesn't matter fo rnow
class nDegreeTrees implements Trees { 
    private StringNode root;

    public nDegreeTrees(StringNode root){
        this.root = root;
    }

    @Override
    public TreeIterators createBFSIterator() {
        return new BFSIterator(root);
    }

    @Override
    public TreeIterators createDFSIterator() {
        return new DFSIterator(root);
    }

    public void addChild(StringNode e){
        root.addChild(e);
    }
}

// I wont implement the logic for the actual iteration..
// its 3am and my brain is too fried
class DFSIterator implements TreeIterators {
    private StringNode root;

    public DFSIterator(StringNode rt){
        this.root = rt;
    }

    @Override
    public StringNode getNext() {
        return this.root;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return true;
    }

}

class BFSIterator implements TreeIterators {
    private StringNode root;

    public BFSIterator(StringNode rt){
        this.root = rt;
    }

    @Override
    public StringNode getNext() {
        return this.root;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return true;
    }
}

public class Iterator {
    public static void main(String[] args) {
        StringNode root = new StringNode("Hello", null);
        nDegreeTrees tree = new nDegreeTrees(root);

        // Iterators are made without knowing what is actually happening in the tree...
        TreeIterators first = tree.createBFSIterator();
        TreeIterators second = tree.createDFSIterator();

        // Then we can get next and print...
        System.out.println(first.getNext());
        System.out.println(second.getNext());
    }
}
