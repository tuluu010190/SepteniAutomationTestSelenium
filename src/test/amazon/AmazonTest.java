package amazon;

import org.testng.annotations.*;

import amazonAction.CartPageActions;
import amazonAction.HomePageActions;
import amazonAction.OrderpageActions;
import amazonAction.RegisterPageActions;
import amazonAction.SinginPageActions;
import amazonExcelDataFiles.ReadProductInformationExcelFile;
import amazonExcelDataFiles.ReadUserInformationExcelFile;

import utils.CommonOperation;
import utils.Constant;
import utils.PropertiesHandles;
import utils.instantBrowser;

public class AmazonTest extends CommonOperation{

	HomePageActions homepageActions;
	RegisterPageActions registerActions;
	SinginPageActions signinActions;
	OrderpageActions orderPageActions;
	CartPageActions cartPageActions;

	//Read Excel files
	ReadUserInformationExcelFile readUserInfo;
	public static String userInfoFilePath;
	ReadProductInformationExcelFile readProductInfo;
	public static String productInfoFilePath;
	
	// variables
	String userName;
	String email;
	String password;
	String confirmPassword;


	@BeforeClass
	public void BeforeClass(){
		instantBrowser.getProperties();
		instantBrowser.setPropertiesForEnvironment();
		
		userInfoFilePath = inputdataExcelFolder + PropertiesHandles.getKey("userInformation");
		readUserInfo = new ReadUserInformationExcelFile();
		readUserInfo.setData(userInfoFilePath, Constant.defaultShet);

		productInfoFilePath = inputdataExcelFolder + PropertiesHandles.getKey("productInformation");
		readProductInfo = new ReadProductInformationExcelFile();
		readProductInfo.setData(productInfoFilePath, Constant.defaultShet);

		homepageActions = new HomePageActions();
		registerActions = new RegisterPageActions();
		signinActions = new SinginPageActions();
		orderPageActions = new OrderpageActions();
		cartPageActions = new CartPageActions();
	}

	@BeforeMethod
	public void BeforeMethod (){
		instantBrowser.initiateDriver();
		driver.get(mainUrl);
	}

	@AfterMethod
	public void AfterMethod(){
		driver.close();
		driver.manage().deleteAllCookies();
	}

	/**
	 * Case ID:
	 * Test Case Name: Register Without Entering Any Field
	 * Description: Register Without Entering Any Field
	 * Pre-Condition: 
	 * Post-Condition: 
	 * @throws Exception 
	 */
	@Test
	public void TC01_RegisterWithoutEnteringAnyField(){
		/*Steps:
		1. Go to amazon site: http://amazon.com
		2. User Navigate to SignIn Page
		3. User Navigate to Register Page
		4. User Click Register Button
	-Expected result:
		4. System displays error messages for missing required fields*/
		homepageActions.goToSigninPage();
		signinActions.goToRegisterPage();
		registerActions.clickRegisterButton();
		registerActions.verifyErrorMissingMessages(true, true, true, false);
	}

	/**
	 * Case ID:
	 * Test Case Name: Register With Confirmation Password is not matched with Password
	 * Description: Register With Confirmation Password is not matched with Password
	 * Pre-Condition: 
	 * Post-Condition: 
	 * @throws Exception 
	 */
	@Test
	public void TC02_RegisterWithConfirmPasswordIsNotProper(){
		userName = readUserInfo.getUserNameByIndex(0) + getRandomString();
		email = readUserInfo.getEmailByIndex(0) + getRandomString() + "@gmail.com";
		password = readUserInfo.getPasswordByIndex(0);
		confirmPassword = readUserInfo.getConfirmPasswordByIndex(1);

		/*Steps:
		1. Go to amazon site: http://amazon.com
		2. User Navigate to SignIn Page
		3. User Navigate to Register Page
		4. User type User Name, Email, Password and confirmation password
		5. User Click Register Button
	-Expected result:
		5. System displays error message  Password miss matched*/
		homepageActions.goToSigninPage();
		signinActions.goToRegisterPage();
		registerActions.EnterRegisterForm(userName, email, password, confirmPassword);
		registerActions.clickRegisterButton();
		registerActions.verifyErrorMissingMessages(false, false, false, true);
	}

	@Test
	public void TC03_RegisterSuccessfully(){
		userName = readUserInfo.getUserNameByIndex(0) + getRandomString();
		email = readUserInfo.getEmailByIndex(0) + getRandomString() + "@gmail.com";
		password = readUserInfo.getPasswordByIndex(0);
		confirmPassword = readUserInfo.getConfirmPasswordByIndex(0);

		int i = userName.indexOf(' ');
		String firstName = userName.substring(0, i);

		/*Steps:
		1. Go to amazon site: http://amazon.com
		2. User Navigate to SignIn Page
		3. User Navigate to Register Page
		4. User type User Name, Email, Password and confirmation password
		5. User Click Register Button
	-Expected result:
		5. Verify Account Name is Presented in Home Page*/
		homepageActions.goToSigninPage();
		signinActions.goToRegisterPage();
		registerActions.EnterRegisterForm(userName, email, password, confirmPassword);
		registerActions.clickRegisterButton();
		homepageActions.verifyAccountName(firstName);
		homepageActions.signOut();
	}

	@Test
	public void TC04_MakeOrder(){
		String searchText = readProductInfo.getSearchTextByIndex(0);
		String chooseProduct = readProductInfo.getChooseProductByIndex(0);
		String numberOrder = readProductInfo.getTotalNumberOrderByIndex(0);
		int i = userName.indexOf(' ');
		String firstName = userName.substring(0, i);

		/*Steps:
		1. Go to amazon site: http://amazon.com
		2. User Navigate to SignIn Page
		3. User types email and password, then click SignIn Button
		4. User Type in text box search and click search Button
		5. User Click to choose a Product in results list
		6. User Click Add to cart button
	-Expected result:
		6.1. User Verify added successfully message: Cart subtotal (numberTotal items)
		6.2. User Verify Total Products in Cart */
		homepageActions.goToSigninPage();
		signinActions.signIn(email, password);
		homepageActions.verifyAccountName(firstName);
		orderPageActions.searchProductInAllDepartment(searchText);
		orderPageActions.chooseProduct(chooseProduct);
		orderPageActions.addToCart();
		orderPageActions.verifyProductAddedSuccessfully(numberOrder);
		homepageActions.verifyTotalCartProduct(numberOrder);
		homepageActions.signOut();
	}

	@Test
	public void TC05_DeleteProductInCart(){
		String numberOrder = readProductInfo.getTotalNumberOrderByIndex(0);
		int i = userName.indexOf(' ');
		String firstName = userName.substring(0, i);

		/*Steps:
		1. Go to amazon site: http://amazon.com
		2. User Navigate to SignIn Page
		3. User types email and password, then click SignIn Button
		4. User Verify Total Products in Cart is "<numberTotal>"
		5. User click to go to Cart
		6. User Delete Added product
	-Expected result:
		6.1. User Verify Total Products in Cart */
		homepageActions.goToSigninPage();
		signinActions.signIn(email, password);
		homepageActions.verifyAccountName(firstName);
		homepageActions.verifyTotalCartProduct(numberOrder);
		homepageActions.goToCart();
		cartPageActions.deleteCart();
		homepageActions.verifyTotalCartProduct(Integer.toString(Integer.parseInt(numberOrder) - 1));
		homepageActions.signOut();
	}
}
