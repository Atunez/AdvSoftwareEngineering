package Creational;

// Abstract Factory deals with creating a family of objects in a consistent manner...

// Consider you want to make some application, If you run it on Windows you want to use some UI, but if you use Mac, you want to use a different set of UI 
// But you are still using the same things, like Buttons and Dropdowns. What do you do here?

// You can make an abstract factory that you can then have a concrete version, depending on which OS is being used.
// Abstract Factory uses Abstract Types, Concrete Factories use Concrete Types

// In our case, our collection is a button and a dropdown
interface Buttons {
    abstract String getLabel();
    abstract void onClick();
}

interface Dropdowns {
    abstract String[] getList();
}

// Here is how windows would implement these buttons 
// {Using Labels, but could be things like style, logic and other stuff}
class WindowsButtons implements Buttons {
    public WindowsButtons(){}

    public String getLabel() {
        return "Windows Button";
    }

    public void onClick() {
        System.out.println("Clicked Windows Button");
    }
}

// How mac does the same thing
class MacButtons implements Buttons {
    public MacButtons(){}

    public String getLabel() {
        return "Mac Button";
    }

    public void onClick() {
        System.out.println("Clicked Mac Button");
    }
}

// Again with dropdowns...
class WindowsDropdown implements Dropdowns {
    public WindowsDropdown(){}

    public String[] getList(){
        String[] out = {"WindowList1"};
        return out;
    }
}

class MacDropdown implements Dropdowns {
    public MacDropdown(){}

    public String[] getList(){
        String[] out = {"MacList1"};
        return out;
    }
}

// Our factories would need to simple make a button or a dropdown. This abstract factory doesn't know. So it has to use abstract types...
interface UIFactory {
    abstract Buttons createButtons();
    abstract Dropdowns createDropdown();
}

// But, our concrete classes know what is going on....
class MacFactory implements UIFactory {
    public MacFactory(){}

    public MacButtons createButtons(){
        return new MacButtons();
    }

    public MacDropdown createDropdown(){
        return new MacDropdown();
    }
}

class WindowsFactory implements UIFactory {
    public WindowsFactory(){}

    public WindowsButtons createButtons(){
        return new WindowsButtons();
    }

    public WindowsDropdown createDropdown(){
        return new WindowsDropdown();
    }
}

// Finally, lets run a driver...
class Application {
    private UIFactory factory;

    // Driver doesn't care exactly what factory it gets...
    public Application(UIFactory factory){
        this.factory = factory;
    }

    // We can print and see what factories we are getting...
    public void checkFactory(){
        System.out.println(factory.createButtons().getLabel());
        factory.createButtons().onClick();
        System.out.println(factory.createDropdown().getList()[0]);

    }
}


public class AbstractFactory {
    public static void main(String[] args) {
        // make instances of everything, then check the factories.
        UIFactory mac = new MacFactory();
        UIFactory win = new WindowsFactory();

        Application windowsApp = new Application(win);
        Application macApp = new Application(mac);

        windowsApp.checkFactory();
        macApp.checkFactory();
    }
}

// It is possible to think of this as a matrix of possibly choices

//              WINDOWS    ||   MAC
// --------------------------------------------
// Button   |
// Dropdown |
// In this case, you can easliy make a factory
