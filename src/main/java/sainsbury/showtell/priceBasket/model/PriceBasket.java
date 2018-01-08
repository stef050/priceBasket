package sainsbury.showtell.priceBasket.model;

import java.util.ArrayList;
import java.util.List;

public class PriceBasket {

	private List<Item> basket;
	
	public PriceBasket(){
		this.basket = new ArrayList<Item>();
	}
	
	public List<Item> getBasket() {
		return basket;
	}

	public void setBasket(List<Item> basket) {
		this.basket = basket;
	}
}
