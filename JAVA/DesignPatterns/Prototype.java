import java.util.HashMap;


abstract class PC implements Cloneable {
    String id = "";
    String type="";
    PC(String id , String type){
        this.id = id;
        this.type=type;
    }
    public void addMouse() {
        // TODO Auto-generated method stub
        System.out.println("default Mouse");
    }
    public void addKeyboard() {
        // TODO Auto-generated method stub
        System.out.println("default Keyboard");
        
    }
    public void addCoreBoard() {
        System.out.println("default Board");
        
    }
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
class WindowsPC extends PC  {
    String id = "";
    WindowsPC(String id){
        super(id,"windows");
        this.id=id;
        
    }
    @Override
    public void addCoreBoard() {
        System.out.println("WIN Board");
        
    }
    @Override
    public void addKeyboard() {
        // TODO Auto-generated method stub
        System.out.println("WIN Keyboard");
        
    }
    @Override
    public void addMouse() {
        // TODO Auto-generated method stub
        System.out.println("WIN Mouse");
    }
}
class MacPC extends PC  {
    String id = "";
    MacPC(String id){
        super(id, "MAC");
        this.id=id;
    }
    @Override
    public void addCoreBoard() {
        System.out.println("MAC Board");
        
    }
    @Override
    public void addKeyboard() {
        // TODO Auto-generated method stub
        System.out.println("MAC Keyboard");
        
    }
    @Override
    public void addMouse() {
        // TODO Auto-generated method stub
        System.out.println("MAC Mouse");
    }
    
}


interface PCOptions{
    PC getWindowsPC(String id);
    PC getMacPC(String id);

}
class PCHandler implements PCOptions{

    HashMap<String,PC> pcs = new HashMap<String,PC>();
    
    @Override
    public PC getWindowsPC(String id) {
        if (pcs.containsKey(id + "-" + "win")){
            return (PC) pcs.get(id + "-" + "win").clone();
        }
        PC win = new WindowsPC(id+"-"+"win");
        pcs.put(id+"-"+"win",(PC) win) ;

        return win ;
    }

    @Override
    public PC getMacPC(String id) {
        if (pcs.containsKey(id + "-" + "mac")){
            System.out.println("Cache hit " +  pcs.get(id + "-" + "mac"));
            return (PC) pcs.get(id + "-" + "mac").clone();
        }
        PC mac = new MacPC(id+"-"+"mac");
        pcs.put(id+"-"+"mac",(PC) mac) ;

        return mac ;
    }


}

class User{
    public static void main(String[] args) {
        PCOptions pch = new PCHandler();
        PC one = pch.getMacPC("mymac");
        PC two = pch.getMacPC("mymac");
        System.out.println(one);
        one.addKeyboard();
        System.out.println(one==two);


    }
}