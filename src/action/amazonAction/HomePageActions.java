package amazonAction;

import org.testng.Assert;
import utils.CommonOperation;
import utils.Constant;
import amazonPage.HomePageElements;
import static utils.LogUtil.info;

public class HomePageActions extends CommonOperation{
	
	HomePageElements homePageElems = new HomePageElements();
	
	/**
	 * User Click To SignIn to navigate to SignIn Page
	 */
	public void goToSigninPage(){
		info("User Click To SignIn to navigate to SignIn Page");
		click(homePageElems.navigateSignupLink);
	}
	
	/**
	 * User Verify user name is displayed in home Page
	 * @param accountName
	 */
	public void verifyAccountName(String accountName){
		info("User Verify user name" + accountName + " is displayed in home Page");
		waitForAndGetElement(homePageElems.getaccountName(accountName), Constant.DEFAULT_TIMEOUT, 1);
	}
	
	/**
	 * User Sign out system
	 */
	public void signOut(){
		info("User mouse over to Navigate Signup Link");
		mouseOver(homePageElems.navigateSignupLink, true);
		info("User click Sign out button");
		click(homePageElems.signoutButton);
	}
	
	/**
	 * User Verify Total Products in Cart
	 * @param total
	 */
	public void verifyTotalCartProduct(String total){
		info("User Verify Total Products in Cart is: " + total);
		String totalProduct = waitForAndGetElement(homePageElems.totalCartProduct, Constant.DEFAULT_TIMEOUT, 1).getText();
		Assert.assertTrue(total.equals(totalProduct));
	}
	
	/**
	 * User Click to Cart Link
	 */
	public void goToCart(){
		info("User Click to Cart Link");
		click(homePageElems.cartLink);
	}
}
