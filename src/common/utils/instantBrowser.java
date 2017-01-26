package utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class instantBrowser {

	public static final String FOLDER_DRIVER = "resources\\driver";

	//driver
	public static WebDriver driver;
	public static String browser;
	public static String mainUrl;
	public static String passWord;
	public static String inputdataExcelFolder;


	
	/**
	 * 
	 * @return
	 */
	public static void getProperties() {
		browser = System.getProperty("browser"); 
		if (browser == null || browser == "") browser = PropertiesHandles.getKey("default.browser"); 
	}

	/**
	 * 
	 * @return
	 */
	public static void setPropertiesForEnvironment(){
		mainUrl = PropertiesHandles.getKey("main.url");
		inputdataExcelFolder = System.getProperty("user.dir") + PropertiesHandles.getKey("input.data.excel");
		System.out.println("inputdataExcelFolder" + inputdataExcelFolder);
	}

	/**
	 * 
	 * @return
	 */
	public static String getIeDriver() {
		String path = System.getProperty("user.dir") + File.separator + FOLDER_DRIVER + File.separator + "IEDriverServer.exe";
		System.out.println(path);
		try {
			File driverIe = new File(path);
			if (driverIe.exists()) {
				return driverIe.getAbsolutePath();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static String getChromeDriver() {
		String path = System.getProperty("user.dir") + File.separator + FOLDER_DRIVER + File.separator + "chromedriver.exe";
		System.out.println("path is: " + path);
		try {
			File driverChrome = new File(path);
			if (driverChrome.exists()) {
				return driverChrome.getAbsolutePath();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param browser
	 * @return
	 */
	public static WebDriver initiateDriver() {
		getProperties();
		setPropertiesForEnvironment();
		if (browser.equalsIgnoreCase("firefox")) {
			try {
				driver = null;
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				profile.setPreference(FirefoxProfile.PORT_PREFERENCE, 7056);
				driver = new FirefoxDriver(profile);
				driver.manage().deleteAllCookies();
			} catch (WebDriverException e) {
			}
			driver.manage().timeouts().pageLoadTimeout(Constant.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("chrome")) {
			driver = null;
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches","--disable-extensions");
			System.setProperty("webdriver.chrome.driver", instantBrowser.getChromeDriver());
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capability);
			driver.manage().timeouts().pageLoadTimeout(Constant.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			return driver;
		} else if (browser.equalsIgnoreCase("ie")) {
			driver = null;
			System.setProperty("webdriver.ie.driver", instantBrowser.getIeDriver());
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(caps);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Constant.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		}
		return driver;
	}
}
