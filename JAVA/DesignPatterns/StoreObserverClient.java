
import java.util.ArrayList;

interface Notification{
    void sendEmailNotification(User user );
}
interface SendUpdates{
    void sendItemUpdates(ArrayList<User> users , Item item);
    void sendDiscountUpdates();
}


class Item{
    int id;
    int quantity;
    public Item(int id,int quantity) {
        this.id= id;
        this.quantity=quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    void addQuantity(int quantity){
        this.quantity+=quantity;
    }
}
class User{
    int userId ;
    ArrayList<Integer> lSubscribedItems;
    public User(int id){
        this.userId=id;
        this.lSubscribedItems = new ArrayList<>();
    }

    public void setlSubscribedItems(int item) {
        this.lSubscribedItems.add(item);
    }
}

class Store{
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    SendUpdates observer = new StoreObserver();
    int createUser(int userId){
        try{
            User user = new User(userId);
            this.users.add(user);
            return 1;
        }
        catch(Exception e){
            return 0;
        }
    }
    int addItem(int id , int quantity){
        try{
            this.items.add(new Item(id, quantity));
        return 1;
        }
        catch(Exception e){
            return 0;
        }
    }
    int subscribeUsersToItem(int userId , int itemId){
        for (User user:users){
            if (user.userId == userId){
                user.setlSubscribedItems(itemId);
                break;
            }
        }
        return 1;
    }
    int addQuantity(int id,int quantity){
        for (Item item:items){
            if (item.id == id){
                item.addQuantity(quantity);
                observer.sendItemUpdates(users, item);
                break;
            }
        }
        return 1;
    }
}

class StoreObserver implements SendUpdates {

    @Override
    public  void sendDiscountUpdates() {

        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void sendItemUpdates(ArrayList<User> users , Item item) {
        for (User user : users){
            
            if (user.lSubscribedItems.contains(Integer.valueOf(item.id))){
                Notification emailNotification = new EmailNotification();
                emailNotification.sendEmailNotification(user);
            }
        }
    }
    


}

class EmailNotification implements  Notification{
    @Override
    public void sendEmailNotification(User user){
        System.out.print("The Items you are looking for is available in our store");
    }

}
public class StoreObserverClient{
    public static void main(String[] args) {
        Store st=new Store();
        st.createUser(1234);
        st.addItem(1, 12);
        st.subscribeUsersToItem(1234,1);
        st.addQuantity(1, 12);
    }
}