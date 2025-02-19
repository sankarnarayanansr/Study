package CarParking.models;
import java.lang.String;
import java.util.HashMap;
public class Car{
     String number;
     String color;
     int slot;
    public Car(String number , String color , int slot){
        this.number=number;
        this.color=color;
        this.slot=slot;
    }
    public HashMap<String,String> getAttributes(){
        HashMap<String,String> dAtts = new HashMap<String,String>();
        dAtts.put("number",this.number);
        dAtts.put("color",this.color);
        dAtts.put("slot",Integer.toString(this.slot));
        // System.out.println(dAtts);
        return dAtts;
    }
    public void printCar(){
        System.out.println("Car : "+ this.number + " " + this.color + " " + Integer.toString(this.slot));
    }
}