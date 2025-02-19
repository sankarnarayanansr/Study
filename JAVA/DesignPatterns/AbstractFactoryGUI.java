// render windows style / mac style checkbox or inputbox without changing client code 

// making base classes 


interface UICheckbox{
    void renderCheckbox();
    
}
interface UIInputbox{
    void renderInputbox();
}

class WindowsCheckbox implements UICheckbox{
    @Override
    public void renderCheckbox() {
        System.out.println("Win checkbox");
        
    }
}
class MacCheckbox implements UICheckbox{
    @Override
    public void renderCheckbox() {
        System.out.println("Mac checkbox");
        
    }
}
class WindowsInputbox implements UIInputbox{
    @Override
    public void renderInputbox() {
        System.out.println("Win Inputbox");
        
    }
}
class MacInputbox implements UIInputbox{
    @Override
    public void renderInputbox() {
        System.out.println("Mac Inputbox");
        
    }
}

interface AbstractFactory {
    UICheckbox getCheckbox();
    UIInputbox geInputbox();
}

class WindowsFactory implements AbstractFactory{
    @Override
    public UICheckbox getCheckbox(){
        
            return new WindowsCheckbox();
    }

    @Override
    public UIInputbox geInputbox() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geInputbox'");
    }

    
}
class MacFactory implements AbstractFactory{
    @Override
    public UICheckbox getCheckbox(){
        
            return new MacCheckbox();
    }

    @Override
    public UIInputbox geInputbox() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geInputbox'");
    }
}

class FactoryProducer {

    public AbstractFactory getFactory(String type){
        if (type.equals("win")){
            return new WindowsFactory();
        }
        return new MacFactory();

    }
}
class Main{
    public static void main(String[] args) {
        FactoryProducer factory = new FactoryProducer();
        AbstractFactory winFactory = factory.getFactory("win");
        UICheckbox wincheckbox = winFactory.getCheckbox();
        wincheckbox.renderCheckbox();
        
    }
}