package amazonAction;

import amazonPage.SigninPageElements;
import utils.CommonOperation;
import static utils.LogUtil.info;

public class SinginPageActions extends CommonOperation{
	
	SigninPageElements siginPageElems = new SigninPageElements();
	
	/**
	 * User Navigate to Register Page
	 */
	public void goToRegisterPage(){
		info("User Navigate to Register Page");
		click(siginPageElems.signUpLink);
	}

	/**
	 * User enter email and password, then click SignIn Button
	 */
	public void signIn(String email, String password){
		info("User enter email and password");
		type(siginPageElems.email, email, true);
		type(siginPageElems.password, password, true);
		info("User click SignIn Button");
		click(siginPageElems.signinButton);
	}
}
