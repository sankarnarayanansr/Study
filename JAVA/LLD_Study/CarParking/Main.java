package CarParking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import CarParking.services.CarParkingManager;
class Main{
    public static void main(String[] args) throws FileNotFoundException{
        CarParkingManager manager = new CarParkingManager(10);
        File inpFile = new File("input.txt");
        Scanner sc = new Scanner(inpFile);
        while (sc.hasNext()){
            String line = sc.nextLine();

            String[] command = line.split(" ");
            System.out.println(line);
            switch (command[0]) {
                case "park":
                    manager.insertCar(command[1], command[2]);
                    break;
                case "filter":
                    manager.filterCars(command[1]);
                    break;
                case "status":
                    manager.printStatus();
                    break;
                case "leave":
                    manager.removeCar(command[1], command[2]);
                    break;
                default:
                    break;
            }
        }
        

    }
}