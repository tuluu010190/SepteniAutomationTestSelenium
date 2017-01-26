package amazonAction;

import amazonPage.CartPageElements;
import utils.CommonOperation;
import static utils.LogUtil.info;

public class CartPageActions extends CommonOperation{

	CartPageElements cartPageElements = new CartPageElements();
	
	/**
	 * User Click delete button to delete a product
	 */
	public void deleteCart(){
		info("User Click delete button to delete a product");
		click(cartPageElements.deleteCart);
	}
}
