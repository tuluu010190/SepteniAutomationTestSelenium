package amazonAction;

import amazonPage.OrderPageElements;
import utils.CommonOperation;
import utils.Constant;
import static utils.LogUtil.info;

public class OrderpageActions extends CommonOperation{

	OrderPageElements orderPageElems = new OrderPageElements();
	
	/**
	 * User type search text and click search button
	 */
	public void searchProductInAllDepartment(String searchProduct){
		info("User type search text: " + searchProduct + " and click search button");
		type(orderPageElems.searchTextBox, searchProduct, true);
		click(orderPageElems.allDepartmentLink);
		click(orderPageElems.searchButton);
	}
	
	/**
	 * User click to choose a product in results list
	 */
	public void chooseProduct(String chooseProduct){
		info("User click to choose a product in results list");
		click(orderPageElems.getproductOrder(chooseProduct));
		
	}
	
	/**
	 * User click add to cart button
	 */
	public void addToCart(){
		info("User click add to cart button");
		click(orderPageElems.addToCartButton);
		if(waitForAndGetElement(orderPageElems.noThanksButton, 10000, 0) != null)
			click(orderPageElems.noThanksButton);
	}
	
	/**
	 * User Verify successfully message when added product
	 */
	public void verifyProductAddedSuccessfully(String number){
		info("User Verify successfully message when added product with " + number + " subtotal");
		waitForAndGetElement(orderPageElems.getcartSubtotal(number), Constant.DEFAULT_TIMEOUT, 1);
	}
}
