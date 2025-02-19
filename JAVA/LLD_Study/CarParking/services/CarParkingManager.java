package CarParking.services;

import java.util.ArrayList;
import java.util.HashMap;

import CarParking.models.Car; // Import the Car class

public class CarParkingManager {
    ArrayList<Car> cars = new ArrayList<Car>();
    int size;
    public CarParkingManager(int n){
        this.size=n;

    }
    public int getFreeSlot(){
        HashMap<Integer,Integer> filledslots = new HashMap<Integer,Integer>();
        for( Car car: cars){
            // System.out.print(car.getAttributes());
            filledslots.put(Integer.parseInt(car.getAttributes().get("slot")) ,1);
        }
        // System.out.print(filledslots);
        for (int i=0;i<this.size;i++){
            if (!filledslots.containsKey(i)){
                return i;
            }
        }
        return -1;
    }
    public void filterCars(String type){
        for(Car car:this.cars){
            if(car.getAttributes().get("color").contains(type)){
                car.printCar();
            }
        }

    }
    public void removeCar(String number , String color){
        int p=0;
        for(Car car:this.cars){
            HashMap<String,String> hm = car.getAttributes();
            if(hm.get("color").equals(color) && hm.get("number").equals(number)){
                break;
            }
            p+=1;
        }
        this.cars.remove(p);
        // System.out.print(p);
    }
    public void insertCar(String number , String color){
        int slot=this.getFreeSlot();
        if (slot == -1){
            System.out.print("No Slots Found !!!1");
            return ;
        }
        Car car = new Car(number,color,slot);
        cars.add(car);
    }
    public void printStatus(){
        for (Car car:cars){
            car.printCar();
        }
    }
}