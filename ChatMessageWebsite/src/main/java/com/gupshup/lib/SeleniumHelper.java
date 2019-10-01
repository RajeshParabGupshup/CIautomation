package com.gupshup.lib;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper {
	protected static WebDriver driver;

	static String driverPath = "/var/tmp/BrowserExecutable";

	protected WebDriver getDriver() {
		return driver;
	}

	public static boolean launchBrowser(String browserType) {

		boolean flagLaunchBrowser = true;
		try {
			switch (browserType) {
			case "chrome":
				driver = initChromeDriver("local");
				flagLaunchBrowser = true;
				break;
			case "firefox":
				driver = initFirefoxDriver("local");
				flagLaunchBrowser = true;
				break;
			case "remote_chrome":
				driver = initChromeDriver(browserType);
				flagLaunchBrowser = true;
				break;
			case "remote_firefox":
				driver = initFirefoxDriver(browserType);
				flagLaunchBrowser = true;
				break;
			default:
				System.out
						.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
				driver = initFirefoxDriver("local");
				flagLaunchBrowser = true;
			}

			driver.manage().timeouts().setScriptTimeout(3, TimeUnit.MINUTES);
			driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
			driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);

		} catch (Exception e) {
			flagLaunchBrowser = false;
		}
		return flagLaunchBrowser;
	}

	public static boolean setURL(String urlPath) {
		boolean flagSetURL;
		try {
			driver.get(urlPath);
			flagSetURL = true;
		} catch (Exception e) {
			flagSetURL = false;
		}
		return flagSetURL;
	}

	private static WebDriver initChromeDriver(String typeServer) throws MalformedURLException {
		WebDriver driver1 = null;
		if (typeServer.contains("remote")) {
			DesiredCapabilities caps = new DesiredCapabilities();

			caps.setBrowserName("chrome");
			// driver1 = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
			driver1 = new RemoteWebDriver(new URL("http://windowsqa.gupshup.io/wd/hub"), caps);

		} else {
			switch (OSUtil.getOS()) {
			case WINDOWS:
				System.setProperty("webdriver.chrome.driver",
						driverPath + File.separator + "chrome" + File.separator + "chromedriver.exe");
				break;
			case LINUX:
				System.setProperty("webdriver.chrome.driver",
						driverPath + File.separator + "chrome" + File.separator + "chromedriver");
				break;
			default:
				break;
			}

			ChromeOptions options = new ChromeOptions();

			// Disable extensions and hide infobars
			options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");

			Map<String, Object> prefs = new HashMap<>();

			// Enable Flash
			prefs.put("profile.default_content_setting_values.plugins", 1);
			prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);

			// Hide save credentials prompt
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			driver1 = new ChromeDriver(options);

		}

		driver1.manage().window().maximize();
		return driver1;
	}

	private static WebDriver initFirefoxDriver(String typeServer) throws MalformedURLException {
		WebDriver driver1 = null;
		if (typeServer.contains("remote")) {
			DesiredCapabilities caps = new DesiredCapabilities();

			caps.setBrowserName("firefox");
			// driver1 = new RemoteWebDriver(new URL("http://10.30.99.106:4447/wd/hub"),
			// caps);
			// driver1 = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
			driver1 = new RemoteWebDriver(new URL("http://windowsqa.gupshup.io/wd/hub"), caps);
		} else {

			switch (OSUtil.getOS()) {
			case WINDOWS:
				System.setProperty("webdriver.gecko.driver",
						driverPath + File.separator + "geckodriver" + File.separator + "geckodriver.exe");
				break;
			case LINUX:
				System.setProperty("webdriver.gecko.driver",
						driverPath + File.separator + "geckodriver" + File.separator + "geckodriver");
				break;
			default:
				break;
			}
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver1 = new FirefoxDriver();
		}
		return driver1;
	}

	public static void waitTillElementDisappear(By disappearedElement, int secs) {
		new WebDriverWait(driver, secs).until(ExpectedConditions.invisibilityOfElementLocated(disappearedElement));
	}

	public static void waitTillElementClickable(WebElement element, int secs) {
		new WebDriverWait(driver, secs).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitTillElementVisible(WebElement element, int secs) {
		new WebDriverWait(driver, secs).until(ExpectedConditions.visibilityOf(element));
	}

	public static void printWebElementAttribute(By webElement, String elementAttribute) {
		List<WebElement> colorElement = driver.findElements(webElement);
		for (WebElement element : colorElement) {
			System.out.println(element.getAttribute(elementAttribute));
		}
	}

	public static boolean isAlertPresent() {

		boolean presentFlag = false;

		try {
			Alert alert = driver.switchTo().alert();
			presentFlag = true;

		} catch (NoAlertPresentException ex) {
			presentFlag = false;
		}
		return presentFlag;
	}

	public static String getAlertText() {
		String textAlert = "";

		try {
			Alert alert = driver.switchTo().alert();
			textAlert = alert.getText();

		} catch (NoAlertPresentException ex) {
		}
		return textAlert;
	}

	public static void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void tearDown() {
		driver.close();
		driver.quit();
	}

	public void takeScreenShot(String methodName) throws IOException {
		String filePath = System.getProperty("user.dir") + File.separator + "Screenshot";

		// get the driver
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with test method name
		try {
			String fileName = filePath + File.separator + OSUtil.getUniqueName(methodName) + ".png";
			FileUtils.copyFile(scrFile, new File(fileName));
			TestNGLogger.logInfo("***Screenshot taken at " + fileName + " ***");

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
