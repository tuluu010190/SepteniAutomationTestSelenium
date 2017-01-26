package amazonPage;

import org.openqa.selenium.By;

public class RegisterPageElements {

	// Controls
	public final By userName = By.id("ap_customer_name");
	public final By email = By.id("ap_email");
	public final By password = By.id("ap_password");
	public final By confirmPassword = By.id("ap_password_check");
	public final By createAccountButton = By.id("continue");
	
	// Error Messages
	public final By missingUserNameMessage = By.id("auth-customerName-missing-alert");
	public final By missingEmailMessage = By.id("auth-email-missing-alert");
	public final By missingPasswordMessage = By.id("auth-password-missing-alert");	
	public final By unmatchedPasswordMessage = By.id("auth-password-mismatch-alert");
}
