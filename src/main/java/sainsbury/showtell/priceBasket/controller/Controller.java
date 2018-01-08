package sainsbury.showtell.priceBasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sainsbury.showtell.priceBasket.model.PriceBasket;
import sainsbury.showtell.priceBasket.model.Total;
import sainsbury.showtell.priceBasket.services.BasketCalculator;

@RestController
public class Controller {

	@Autowired
	private BasketCalculator basketCalculator;

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public Total checkout(@RequestBody PriceBasket priceBasket) {

		float subtotal = basketCalculator.calculateSubtotal(priceBasket);
		float discount = basketCalculator.calculateDiscount(priceBasket);
		float total = basketCalculator.calculateTotal(subtotal, discount);

		return new Total(subtotal, discount, total);
	}
}
