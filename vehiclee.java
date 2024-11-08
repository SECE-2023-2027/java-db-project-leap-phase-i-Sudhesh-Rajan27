package javaa;

abstract class Carr {
    String Ftype;
    String Model;
    
    public abstract void display();
}
class Hatchback extends Carr {
    Hatchback() {
        Ftype = "CNG";
        Model = "Hatchback";
    }
    
    @Override
    public void display() {
        System.out.println(Ftype);
        System.out.println(Model);
    }
}

class Sedan extends Carr {
    Sedan() {
        Ftype = "Petrol";
        Model = "Sedan";
    }
    
    @Override
    public void display() {
        System.out.println(Ftype);
        System.out.println(Model);
    }
}
class XUV extends Carr {
    XUV() {
        Ftype = "Diesel";
        Model = "XUV";
    }
    
    @Override
    public void display() {
        System.out.println(Ftype);
        System.out.println(Model);
    }
}
public class vehiclee {
    public static void main(String[] args) {
        XUV ob = new XUV();
        ob.display();
        
        Sedan ob1 = new Sedan();
        ob1.display();
        
        Hatchback ob2 = new Hatchback();
        ob2.display();
    }
}
