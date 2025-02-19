// create one factory to produce 2 different cars

/**
 * FActory
 */
public interface CarFactory {
    public void makeCar();
    
}


class FordFactory implements CarFactory{
    
    @Override
    public void makeCar() {
        System.out.println("Add Ford Engine");
        System.out.println("Add Ford Tyres");
        System.out.println("Add Ford Body");
        System.out.println("Add Ford Sticker");
        
    }


}
class HondaFactory implements CarFactory{
    @Override
    public void makeCar() {
        System.out.println("Add Honda Engine");
        System.out.println("Add Honda Tyres");
        System.out.println("Add Honda Body");
        System.out.println("Add Honda Sticker");
        
    }
}

class MyClass {
    public static void main(String[] args) {
        String start = "Honda";
        CarFactory factory = null;
        if (start.equals("Honda")){
            factory = new HondaFactory();
            
        }
        factory.makeCar();
}
}