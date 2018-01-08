package sainsbury.showtell.priceBasket.rules;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import sainsbury.showtell.priceBasket.model.Discount;
import sainsbury.showtell.priceBasket.model.Item;
import sainsbury.showtell.priceBasket.model.PriceBasket;

@RunWith(SpringRunner.class)
public class RulesTest {

	 private KieServices kieServices;
	 private KieContainer kieContainer;
	 private KieSession kieSession;
	    
	
	@Before
    public void initialize() {
        if (kieSession != null) {
            kieSession.dispose();
        }
        this.kieServices = KieServices.Factory.get();
        this.kieContainer = kieServices.getKieClasspathContainer();
        this.kieSession = kieContainer.newKieSession("PriceBasketSession");
    }
	
	@Test
	public void testBOGOFApplesForOdd(){
		PriceBasket testBasket = new PriceBasket();
		
		ArrayList<Item> testItemList = new ArrayList<>();
		testItemList.add(Item.APPLES);
		testItemList.add(Item.APPLES);
		testItemList.add(Item.APPLES);
		
		testBasket.setBasket(testItemList);
		
		KieSession kieSession = kieContainer.newKieSession("PriceBasketSession");
        kieSession.insert(testBasket);
        kieSession.fireAllRules();
		
        ObjectFilter discountFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (Discount.class.equals(object.getClass())) return true;
                if (Discount.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };
        
        Collection<FactHandle> discountsCollection = kieSession.getFactHandles(discountFilter);
        assertTrue(discountsCollection.size() == 1);

        for(FactHandle fh : discountsCollection){
        	Discount discount = (Discount) kieSession.getObject(fh);
        	assertTrue(discount.getAmount() == 1.0);
        }
	}
	
	@Test
	public void testBOGOFApplesForEven(){
		PriceBasket testBasket = new PriceBasket();
		
		ArrayList<Item> testItemList = new ArrayList<>();
		testItemList.add(Item.APPLES);
		testItemList.add(Item.APPLES);
		testItemList.add(Item.APPLES);
		testItemList.add(Item.APPLES);
		
		testBasket.setBasket(testItemList);
		
		KieSession kieSession = kieContainer.newKieSession("PriceBasketSession");
        kieSession.insert(testBasket);
        kieSession.fireAllRules();
		
        ObjectFilter discountFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (Discount.class.equals(object.getClass())) return true;
                if (Discount.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };
        
        Collection<FactHandle> discountsCollection = kieSession.getFactHandles(discountFilter);
        assertTrue(discountsCollection.size() == 1);

        for(FactHandle fh : discountsCollection){
        	Discount discount = (Discount) kieSession.getObject(fh);
        	assertTrue(discount.getAmount() == 2.0);
        }
	}
	
	@Test
	public void testSoupAndBreadDiscount(){
		PriceBasket testBasket = new PriceBasket();
		
		ArrayList<Item> testItemList = new ArrayList<>();
		testItemList.add(Item.SOUP);
		testItemList.add(Item.BREAD);
		
		testBasket.setBasket(testItemList);
		
		KieSession kieSession = kieContainer.newKieSession("PriceBasketSession");
        kieSession.insert(testBasket);
        kieSession.fireAllRules();
		
        ObjectFilter discountFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (Discount.class.equals(object.getClass())) return true;
                if (Discount.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };
        
        Collection<FactHandle> discountsCollection = kieSession.getFactHandles(discountFilter);
        System.out.println(discountsCollection.size());
        assertTrue(discountsCollection.size() == 1);

        for(FactHandle fh : discountsCollection){
        	Discount discount = (Discount) kieSession.getObject(fh);
            System.out.println(discount.getAmount());
        	assertTrue(discount.getAmount() == (float) 0.65);
        }
	}
}
