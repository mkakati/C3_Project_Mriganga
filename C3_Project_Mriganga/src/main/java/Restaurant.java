import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    // Given the current time, opening time and closing time, this method
    // returns if the restaurant is open or not
    public boolean isRestaurantOpen() {
        if(getCurrentTime().isAfter(this.openingTime ) && getCurrentTime().isBefore(this.closingTime)) {
            return true;
        }
        else {
            return false;
        }
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    // This method returns a List of items that are listed in the menu
    public List<Item> getMenu() {
        if (this.menu.size() > 0) {
            return this.menu ;
        }
        else {
            return null;
        }
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    /*Given  a list of itemName, this method finds out price of each item and
    returns the total order value*/
    public int calculateOrderValue(List<String> itemNames) {
        int totalOrderValue = 0;
        for (String name: itemNames){
            totalOrderValue +=  findItemPrice(name) ;
        }
        return totalOrderValue ;
    }

    /*Given  an itemName, this method returns the price for that item */
    public int findItemPrice(String itemName) {
        for(Item item: menu) {
            if(item.getName().equals(itemName)){
                return item.getPrice();
            }
        }
        return -1;
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
