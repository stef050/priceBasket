package sainsbury.showtell.priceBasket.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;

import sainsbury.showtell.priceBasket.model.Discount;
import sainsbury.showtell.priceBasket.model.Item;
import sainsbury.showtell.priceBasket.model.PriceBasket;

@Service
public class BasketCalculator {

	@Autowired
	private KieContainer kieContainer;
	
	public float calculateSubtotal(PriceBasket basket){
		float total = 0;
		for (Item item : basket.getBasket()){
        	total += item.getPrice();
        }
		return this.roundDouble(total);
	}
	
	public float calculateDiscount(PriceBasket basket){
		 KieSession kieSession = kieContainer.newKieSession("PriceBasketSession");
	        kieSession.insert(basket);
	        kieSession.fireAllRules();
	        
	        float discount = this.sumDiscounts(kieSession);
	        
	        kieSession.dispose();
	        
	        return discount;
	}
	
	public float sumDiscounts(KieSession kieSession){
		
		ObjectFilter discountFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (Discount.class.equals(object.getClass())) return true;
                if (Discount.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };
        
		float totalDiscount = 0;
		for (FactHandle handle : kieSession.getFactHandles(discountFilter)){
			Discount discount = (Discount) kieSession.getObject(handle);
			
			totalDiscount += discount.getAmount();
		}
		return this.roundDouble(totalDiscount);
	}
	
	public float calculateTotal(float subtotal, float discount){
		return this.roundDouble(subtotal - discount);
	}
	
	public float roundDouble(float num){
		float numMidRound = Math.round(num * 100);
		return numMidRound / 100;
	}
}
