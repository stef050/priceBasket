package sainsbury.showtell.priceBasket.model;

public class Total {

	private float subtotal;
	private float discount;
	private float total;
	
	public Total(){}
	
	public Total(float subtotal, float discount, float total) {
		this.subtotal = subtotal;
		this.discount = discount;
		this.total = total;
	}
	
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
