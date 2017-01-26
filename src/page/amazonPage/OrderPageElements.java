package amazonPage;

import org.openqa.selenium.By;

public class OrderPageElements {

	public final By searchTextBox = By.id("twotabsearchtextbox");
	public final By searchButton = By.xpath("//*[@value='Go']");
	public final By allDepartmentLink = By.xpath(".//option[text()='All Departments']");
	public final String productOrder = "//*[contains(@data-attribute,'${product}')]";
	public final By addToCartButton = By.id("add-to-cart-button");
	public final By noThanksButton = By.id("siNoCoverage-announce");
	public final String cartSubtotal = "//*[text()='Cart subtotal']/../..//span[contains(text(),'${number} item')]";
	
	/**
	 * get Product Order
	 * @param chooseProduct
	 * @return
	 */
	public By getproductOrder(String chooseProduct){
		return By.xpath(productOrder.replace("${product}", chooseProduct));
	} 
	
	/**
	 * get Cart Sub total
	 * @param number
	 * @return
	 */
	public By getcartSubtotal(String number){
		return By.xpath(cartSubtotal.replace("${number}", number));
	}
}
