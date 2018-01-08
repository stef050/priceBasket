package sainsbury.showtell.priceBasket.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Item {

	SOUP ("soup", 0.65),
	BREAD ("bread", 0.8),
	MILK("milk", 1.3),
	APPLES ("apples", 1.0);
	
	private final String name;
	private final double price;
	private int quantity;
	
	Item(String name, double price){
		this.name = name;
		this.price = price;
		this.quantity = 0;
	}
	
	@JsonCreator 
	public static Item fromText(String text){
		for(Item item : Item.values()){
            if(item.getName().equals(text)){
                return item;
            }
        }
        throw new IllegalArgumentException();
	}

	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addOneQuantity(){
		this.quantity++;
	}
}