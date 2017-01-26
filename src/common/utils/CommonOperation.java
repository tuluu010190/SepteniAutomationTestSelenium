package utils;

import java.io.File;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CommonOperation extends instantBrowser{

	/**
	 * Pause
	 * @param timeInMillis
	 */
	public void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * checkCycling
	 * @param e
	 * @param loopCountAllowed
	 */
	public void checkCycling(Exception e, int loopCountAllowed) {
		if (Constant.loopCount > loopCountAllowed) {
			Assert.fail("Cycled: " + e.getMessage());
		}
		Constant.loopCount++;
	}

	/**
	 * check element displays or net
	 * 
	 * @param locator
	 * @return true if element displays false if element doesn't display
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try {
			if (e != null)
				bool = e.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(Constant.WAIT_INTERVAL);
			isDisplay(locator);
		} finally {
			Constant.loopCount = 0;
		}
		return bool;
	}

	/**
	 * Get element
	 * @param locator
	 * @param opParams
	 * @return an element
	 */
	public WebElement getElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator
				.toString());
		WebDriver wDriver;
		wDriver = (WebDriver) (opParams.length > 0 ? opParams[0] : driver);
		WebElement elem = null;
		try {
			elem = wDriver.findElement(by);
		} catch (NoSuchElementException e) {

		}
		return elem;
	}

	/**
	 * check an element display and get element
	 * @param locator
	 * @param opParams
	 * @return element
	 */
	public WebElement getDisplayedElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator
				.toString());

		WebDriver wDriver;
		wDriver = (WebDriver) (opParams.length > 0 ? opParams[0] : driver);
		WebElement e = null;
		try {
			if (by != null)
				e = wDriver.findElement(by);

			if (e != null) {
				if (isDisplay(by))
					return e;
			}
		} catch (NoSuchElementException ex) {
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(Constant.WAIT_INTERVAL);
			getDisplayedElement(locator);
		} finally {
			Constant.loopCount = 0;
		}
		return null;
	}

	/**
	 * Get element
	 * @param locator
	 *            locator of element
	 * @param opParams
	 *            opPram[0]: timeout opPram[1]: 0,1 0: No Assert 1: Assert
	 * @return an element
	 */
	public WebElement waitForAndGetElement(Object locator, Object... opParams) {
		WebElement elem = null;
		int timeout = (Integer) (opParams.length > 0 ? opParams[0]
				: Constant.DEFAULT_TIMEOUT);
		int isAssert = (Integer) (opParams.length > 1 ? opParams[1] : 1);
		int notDisplayE = (Integer) (opParams.length > 2 ? opParams[2] : 0);
		WebDriver wDriver;
		wDriver = (WebDriver) (opParams.length > 3 ? opParams[3] : driver);
		for (int tick = 0; tick < timeout / Constant.WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2) {
				elem = getElement(locator, wDriver);
			} else {
				elem = getDisplayedElement(locator, wDriver);
			}
			if (null != elem)
				return elem;
			pause(Constant.WAIT_INTERVAL);
		}
		if (isAssert == 1)
			assert false : ("Timeout after " + timeout
					+ "ms waiting for element present: " + locator);
		return null;
	}

	/**
	 * click action
	 * @param locator
	 * @param opParams
	 */
	public void click(Object locator, Object... opParams) {
		Actions actions = new Actions(driver);
		WebElement element = null;
		element = waitForAndGetElement(locator, Constant.DEFAULT_TIMEOUT, 1);
		actions.click(element).perform();
		pause(1000);
	}

	/**
	 * mouse over action
	 * @param locator
	 * @param safeToSERE
	 * @param opParams
	 */
	public void mouseOver(Object locator, boolean safeToSERE, Object...opParams) {
		WebElement element;
		Actions actions = new Actions(driver);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		element = waitForAndGetElement(locator, Constant.DEFAULT_TIMEOUT, 0, notDisplay);
		actions.moveToElement(element).perform();
	}

	/**
	 * type to text box
	 * @param locator
	 * @param value
	 * @param validate
	 * @param opParams
	 */
	public void type(Object locator, String value, boolean clearTextBox, Object...opParams) {	
		WebElement element = waitForAndGetElement(locator, Constant.DEFAULT_TIMEOUT, 1);		
		if (element != null){
			if (clearTextBox)  {
				element.clear();
			}
			element.sendKeys(value);
		}
	}

	/**
	 * get random string
	 * 
	 * @return random string
	 */
	public String getRandomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	public static String getDirFolder(String folderName) {
		String dir = folderName + File.separator;
		if (!PropertiesHandles.getKey("project.basedir").isEmpty()) {
			dir = PropertiesHandles.getKey("project.basedir") + File.separator + folderName + File.separator;
		}
		return dir;
	}
}
