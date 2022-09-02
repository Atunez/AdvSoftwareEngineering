package Structural;

// Decorator is just adding things on top of things till it works
// Starting with a base and moving upwards...

// The thing we ultimately will decorate is a webpage
// All webpages that we can decorate have these two methods...
interface Webpage {
    abstract String getHTML();
    abstract String getAdditions();
}

class BasicPage implements Webpage {
    public BasicPage(){};

    public String getHTML() {
        return "Base HTML";
    }

    public String getAdditions() {
        return "Base";
    }
}

// For every decorator we want to write, they should be able to do the following:
abstract class WebpageDecorator implements Webpage {
    private final Webpage oldWebpage;

    public WebpageDecorator(Webpage oldWebpage){
        this.oldWebpage = oldWebpage;
    }

    public String getHTML() {
        return oldWebpage.getHTML();
    }

    public String getAdditions() {
        return oldWebpage.getAdditions();
    }    
}

// Now our concrete decorators will fill in the gaps of the abstract class
class SideNavWebpageDecorator extends WebpageDecorator {
    public SideNavWebpageDecorator(Webpage oldWebpage) {
        super(oldWebpage);
    }
    
    public String getHTML() {
        return "Wrapped With SideNav " + super.getHTML() + " Wrapped With SideNav";
    }

    public String getAdditions() {
        return super.getAdditions() + " Added SideNav";
    }    
}

class HelpSideWebpageDecorator extends WebpageDecorator {
    public HelpSideWebpageDecorator(Webpage oldWebpage) {
        super(oldWebpage);
    }
    
    public String getHTML() {
        return "Wrapped With HelpSide " + super.getHTML() + " Wrapped With HelpSide";
    }

    public String getAdditions() {
        return super.getAdditions() + " Added HelpSide";
    }    
}

public class Decorator {
    public static void main(String[] args) {
        Webpage wp = new SideNavWebpageDecorator(new HelpSideWebpageDecorator(new BasicPage()));
        Webpage wp2 = new HelpSideWebpageDecorator(new SideNavWebpageDecorator(new BasicPage()));
        System.out.println(wp.getAdditions());
        System.out.println(wp2.getAdditions());
    }
}
