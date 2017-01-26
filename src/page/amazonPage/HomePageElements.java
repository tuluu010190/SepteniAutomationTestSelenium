package amazonPage;

import org.openqa.selenium.By;

public class HomePageElements {

	public final By navigateSignupLink = By.id("nav-link-accountList");
	public final String accountName = ".//*[@id='nav-link-accountList']//*[contains(text(), 'Hello, ${name}')]";
	public final By signoutButton = By.id("nav-item-signout");
	public final By totalCartProduct = By.id("nav-cart-count");
	public final By cartLink = By.id("nav-cart");
	
	/**
	 * get account Name
	 * @param firstName
	 * @return
	 */
	public By getaccountName(String firstName) {
		return By.xpath(accountName.replace("${name}", firstName));
	}		
}
