package amazonAction;

import amazonPage.RegisterPageElements;
import utils.CommonOperation;
import utils.Constant;
import static utils.LogUtil.info;

public class RegisterPageActions extends CommonOperation{

	RegisterPageElements registerPageElems = new RegisterPageElements();

	/**
	 * User click register button
	 */
	public void clickRegisterButton(){
		info("User click register button");
		click(registerPageElems.createAccountButton);
	}

	/**
	 * User Type to register form
	 */
	public void EnterRegisterForm(String userName, String email, String password, String confirmPassword){
		info("User Type to register form");
		type(registerPageElems.userName, userName, true);
		type(registerPageElems.email, email, true);
		type(registerPageElems.password, password, true);
		type(registerPageElems.confirmPassword, confirmPassword, true);
	}

	/**
	 * System displays error messages for missing required fields and Password miss matched
	 */
	public void verifyErrorMissingMessages(boolean verifyUserMessage, boolean verifyEmailMessage, boolean verifyPasswordMessage,
			boolean verifyUnmatchPasswordMessage){
		if(verifyUserMessage) {
			info("Verify system displays error message when missing user name");
			waitForAndGetElement(registerPageElems.missingUserNameMessage, Constant.DEFAULT_TIMEOUT, 1);
		}
		if(verifyEmailMessage){
			info("Verify system displays error message when missing email");
			waitForAndGetElement(registerPageElems.missingEmailMessage, Constant.DEFAULT_TIMEOUT, 1);
		}
		if(verifyPasswordMessage) {
			info("Verify system displays error message when missing password");
			waitForAndGetElement(registerPageElems.missingPasswordMessage, Constant.DEFAULT_TIMEOUT, 1);
		}
		if(verifyUnmatchPasswordMessage) {
			info("Verify system displays error message when password missmatched");
			waitForAndGetElement(registerPageElems.unmatchedPasswordMessage, Constant.DEFAULT_TIMEOUT, 1);
		}
	}
}
