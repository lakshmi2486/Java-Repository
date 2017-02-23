package base;

import static org.junit.Assume.assumeTrue;
//import static org.monte.media.FormatKeys.EncodingKey;
//import static org.monte.media.FormatKeys.FrameRateKey;
//import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
//import static org.monte.media.FormatKeys.MIME_AVI;
//import static org.monte.media.FormatKeys.MediaTypeKey;
//import static org.monte.media.FormatKeys.MimeTypeKey;
//import static org.monte.media.VideoFormatKeys.CompressorNameKey;
//import static org.monte.media.VideoFormatKeys.DepthKey;
//import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
//import static org.monte.media.VideoFormatKeys.QualityKey;
//import org.bouncycastle.asn1.x509.Time;
//import org.glassfish.jersey.client.ClientConfig;
//import org.monte.media.Format;
//import org.monte.media.FormatKeys.MediaType;
//import org.monte.media.math.Rational;
//import org.monte.screenrecorder.ScreenRecorder;
//import Features_Action.SpecializedScreenRecorder;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class GenericMethods {

	public static WebDriver driver;
	WebElement element;
	Boolean Bit_32;
	// private ScreenRecorder screenRecorder;

	// Variables for API-Data feeds
	public String[] myactualurl = null;
	public static WebTarget target;
	public static Response response;
	// public static ClientConfig config;
	public static Client client;
	public static String mytargetURI;
	public static String output = null;
	public static String uri;
	public static String starttime;
	public static String Endtime;
	public static String timetaken;
	public static int status = 0;

	Properties xpathProperties = new Properties();

	public void pressTAB(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		driver.findElement(By.xpath(xPath)).sendKeys(Keys.TAB);
	}

	public boolean getOSBitVersion() {

		String pFilesX86 = System.getenv("ProgramFiles(X86)");
		if (pFilesX86 != (null)) {
			// Put here the code to execute when Windows x64 are Detected
			System.out.println(" 64bit");
			Bit_32 = false;
		} else {
			// Put here the code to execute when Windows x32 are Detected
			System.out.println(" 32bit");
			Bit_32 = true;

		}
		return Bit_32;
	}

	private static boolean isSupportedPlatform() {

		Platform current = Platform.getCurrent();
		System.out.println("The Current OS Platform is \t:\t" + current);
		Reporter.log("<br />The Current OS Platform is \t:\t" + current);
		return Platform.WINDOWS.is(current);
	}

	/* SOLOMON--Downloading IE9 for some purpose */
	public void clickAndSaveFileIE9(WebDriver driver, String xpath) throws AWTException, InterruptedException {

		Robot robot = new Robot();
		element = driver.findElement(By.xpath(xpath));
		// Get the focus on the element..don't use click since it stalls the
		// driver
		element.sendKeys("");

		// simulate pressing enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Wait for the download manager to open
		Thread.sleep(30000);

		System.out.println("Going to Navigate Download Manager");
		Reporter.log("<br />Going to Navigate Download Manager");
		// Switch to download manager tray via Alt+N
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);

		System.out.println("Navigated Download Manager Successfully");
		Reporter.log("<br />Navigated Download Manager Successfully");
		// Press S key to save
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		System.out.println("Save the Downloaded File Successfully");
		Reporter.log("<br />Save the Downloaded File Successfully");
		Thread.sleep(5000);

		System.out.println("Going to Close Downloaded Manager");
		Reporter.log("<br />Going to Close Downloaded Manager");
		// Switch back to download manager tray via Alt+N
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);

		// Tab to X exit key
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		// Press Enter to close the Download Manager
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		System.out.println("Closing the Downloaded Manager Successfully");
		Reporter.log("<br />Closing the Downloaded Manager Successfully");

	}

	public void UserPreferenceSettings(WebDriver driver, String selectGroup, String locationType, String selectTimeZone,
			String locale, String language, String hourss, String measurement) throws Exception {
		Properties xpathProperties = new Properties();
		// Properties xpathMapper=new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));

		select(driver, selectGroup, xpathProperties.getProperty("settings_select_group"));
		if (locationType.equalsIgnoreCase("geo")) {
			clickTab(driver, xpathProperties.getProperty("settings_select_loc_geo"));
		} else {
			clickTab(driver, xpathProperties.getProperty("settings_select_loc_gps"));
		}
		select(driver, selectTimeZone, xpathProperties.getProperty("settings_select_timezone"));
		select(driver, locale, xpathProperties.getProperty("settings_select_localation"));
		select(driver, language, xpathProperties.getProperty("settings_select_language"));
		if (hourss.equalsIgnoreCase("12")) {
			clickTab(driver, xpathProperties.getProperty("settings_select_time_12"));
		} else {
			clickTab(driver, xpathProperties.getProperty("settings_select_time_24"));
		}
		select(driver, measurement, xpathProperties.getProperty("settings_select_measurements"));
		clickButton(driver, xpathProperties.getProperty("popup_save_button"));
		Thread.sleep(10000);

	}

	public WebDriver launchBrowser(String browser_type) throws IOException, InterruptedException {

		String path = System.getProperty("user.home") + "\\Desktop";
		assumeTrue(isSupportedPlatform());
		Boolean bitVersion = getOSBitVersion();
		String osVersion = System.getProperty("os.name");
		System.out.print("<br /> The Operating System's Version is \t:\t" + osVersion);
		Reporter.log("<br /> The Operating System's Version is \t:t" + osVersion);
		File directory = new File(".");
		File files = null;
		if (browser_type.equalsIgnoreCase("ie") || browser_type.equalsIgnoreCase("internetexplore")) {
			if (bitVersion)
				files = new File(directory.getCanonicalPath() + "\\resources\\IEDriverServer_32Bit.exe");
			else
				files = new File(directory.getCanonicalPath() + "\\resources\\IEDriverServer_64Bit.exe");
			System.setProperty("webdriver.ie.driver", files.getAbsolutePath());
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			// ieCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);
			// ieCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.ACCEPT);
			String version = ieCapabilities.getVersion().toString();
			System.out.println("The Version of the Browser is\t:\t" + version);
			Reporter.log("<br />The Version of the Browser is\t:\t" + version);

			driver = new InternetExplorerDriver(ieCapabilities);
		} else if (browser_type.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			String DownloadPath = "C:\\Users\\antons\\Desktop\\Soap";
			options.addArguments("test-type");
			options.addArguments("download.default_directory", DownloadPath);
			options.addArguments("start-maximized");
			options.addArguments("--disable-extensions");
			System.out.println(path);
			// files = new File(path+"\\Automation
			// Folder\\resources\\chromedriver.exe");//-New JFrame line
			files = new File(directory.getCanonicalPath() + "\\resources\\chromedriver.exe");

			System.setProperty("webdriver.chrome.driver", files.getAbsolutePath());
			DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
			chromeCapabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));// new
																												// line
																												// added
																												// for
																												// overriding
																												// the
																												// Client
																												// certificates....
			chromeCapabilities.setCapability("chrome.binary", files.getAbsolutePath());
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

			System.out.println(chromeCapabilities.getVersion());
			Reporter.log("<br />" + chromeCapabilities.getVersion());
			driver = new ChromeDriver(chromeCapabilities);

		}

		else if (browser_type.equalsIgnoreCase("firefox") || browser_type.equalsIgnoreCase("ff")) {

			driver = new FirefoxDriver();
		}

		else if (browser_type.equalsIgnoreCase("safari") || browser_type.equalsIgnoreCase("saf")) {

			SafariOptions options = new SafariOptions();
			options.setUseCleanSession(true);
			driver = new SafariDriver(options);
		}

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		System.out.println("The Browser Name \t:\t" + browserName);
		Reporter.log("<br />The Browser Name \t:\t" + browserName);
		String v = cap.getVersion().toString();
		System.out.println("The Version is  \t:\t" + v);
		Reporter.log("<br />The Version is  \t:\t" + v);
		// String version = ((RemoteWebDriver) driver).Capabilities.Version
		driver.manage().deleteAllCookies();
		// return driver;
		this.driver = driver;
		return this.driver;
	}

	public void navigateURL(WebDriver driver, String url) {

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);

	}

	public void mypageLoad(WebDriver driver) {

		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

	}

	public void ieKiller() throws Exception {
		final String KILL = "taskkill /F /IM ";
		String processName = "IEDriverServer_64Bit.exe"; // IE process

		Runtime.getRuntime().exec(KILL + processName + " /T");

		System.out.println("Killed All IEDriverServerer.exe in the Task Manager");
		Reporter.log("<br />Killed All IEDriverServerer.exe in the Task Manager");
		Thread.sleep(2000);
		/*
		 * processName = "iexplore.exe"; //IE process
		 * Runtime.getRuntime().exec(KILL + processName +" /T");
		 * System.out.println("Killed All IExplore.exe in the Task Manager");
		 * Reporter.log("<br />Killed All IExplore.exe in the Task Manager");
		 * Thread.sleep(2000); processName="IEDriverServer.exe";
		 * Runtime.getRuntime().exec(KILL + processName +" /T");
		 */

		processName = "chromedriver.exe"; // chrome driver process
		Runtime.getRuntime().exec(KILL + processName + " /T");
		System.out.println("Killed All chromedriver.exe in the Task Manager");
		Reporter.log("<br />Killed All chromedriver.exe in the Task Manager");
		Thread.sleep(2000);
		// processName = "chrome.exe"; //chrome process
		Runtime.getRuntime().exec(KILL + processName + " /T");
		System.out.println("Killed All chrome.exe in the Task Manager");
		Reporter.log("<br />Killed All chrome.exe in the Task Manager");
		Thread.sleep(2000);

		/*
		 * processName = "firefox.exe"; //firefox driver process
		 * Runtime.getRuntime().exec(KILL + processName +" /T");
		 * System.out.println("Killed All chromedriver.exe in the Task Manager"
		 * );
		 * Reporter.log("<br />Killed All chromedriver.exe in the Task Manager"
		 * );
		 */

	}

	protected boolean acceptNextAlert = true;

	public void enterURL(WebDriver driver, String url) {
		driver.get(url);
		// driver.get(url + "/");
	}

	private Logger logger = Logger.getLogger(GenericMethods.class);

	public GenericMethods() {
		System.out.println("Generic Method Constructor is called----------------");
		// PropertyConfigurator.configure("log4j.PROPERTIES");
	}

	public void pressTabKeys(WebDriver driver) throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public void input(WebDriver driver, String textToInput, String xPath) throws Exception {

		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		logger.info("XPath:" + xPath);
		Reporter.log("<br />" + Calendar.getInstance().getTime() + "\t:\t The Xpath of TextBox is \t:\t" + xPath);
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath(xPath)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(xPath)).clear();
		if (!(xPath.contains("search") || xPath.contains("3 Characters"))) {
			driver.findElement(By.xpath(xPath)).sendKeys(textToInput);
			TimeUnit.SECONDS.sleep(4);
		} else {
			char[] chr = textToInput.toCharArray();

			for (char c : chr) {
				driver.findElement(By.xpath(xPath)).sendKeys(Character.toString(c));
				TimeUnit.SECONDS.sleep(1);
			}
		}
		Reporter.log("<br />" + Calendar.getInstance().getTime() + "\t:\t The Input for these TextBox is \t:\t"
				+ textToInput);
	}

	public void loadProp() {

		// Properties xpathMapper=new Properties();
		try {
			xpathProperties.load(new FileInputStream("src\\Config\\OR_Azure.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickPreferenceIcon(WebDriver driver) {

		try {
			loadProp();
			Actions act = new Actions(driver);
			checkVisibilityOfElementLocated(driver, xpathProperties.getProperty("Home_Accountimage"));

			act.moveToElement(driver.findElement(By.xpath(xpathProperties.getProperty("Home_Accountimage")))).click()
					.perform();
			// Clicking on Preferences for Drop down
			checkVisibilityOfElementLocated(driver, xpathProperties.getProperty("Home_Preferences"));
			clickButton(driver, xpathProperties.getProperty("Home_Preferences"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pressUPKey(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		driver.findElement(By.xpath(xPath)).sendKeys(Keys.PAGE_UP);
	}

	public List<String> splitValues(WebDriver driver, String multipleValue) {

		String splitArray[] = new String[30];
		splitArray = multipleValue.split(",");
		List<String> splitted_List = Arrays.asList(splitArray);
		for (String each_splitterList : splitted_List) {
			System.out.println("Splitted Value : " + each_splitterList);
		}

		return splitted_List;
	}

	public void alertCheck(WebDriver driver, String condition) throws Exception {
		loadProp();
		if (Exists(driver, xpathProperties.getProperty("Alert"))) {
			System.out.println("Alert present");
			TimeUnit.SECONDS.sleep(3);
			getTextFromWebElement(driver, xpathProperties.getProperty("Alert_text"));
			if (condition.equalsIgnoreCase("accept")) {
				clickTab(driver, xpathProperties.getProperty("Alert_Yes"));
			} else if (condition.equalsIgnoreCase("dismiss")) {
				clickTab(driver, xpathProperties.getProperty("Alert_No"));
			}
		}
	}

	public void logout() throws Exception {
		loadProp();
		Actions act = new Actions(driver);
		checkVisibilityOfElementLocated(driver, xpathProperties.getProperty("Home_Accountimage"));
		act.moveToElement(driver.findElement(By.xpath(xpathProperties.getProperty("Home_Accountimage")))).click()
				.perform();
		checkVisibilityOfElementLocated(driver, xpathProperties.getProperty("Plweb_logout"));
		clickButton(driver, xpathProperties.getProperty("Plweb_logout"));
	}

	public void clearText(WebDriver driver, String xpath) {
		driver.findElement(By.xpath(xpath)).click();
		try {
			TimeUnit.SECONDS.sleep(2);
			driver.findElement(By.xpath(xpath)).clear();
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<String, Date> sortMap(Map<String, Date> unsortMap) {

		boolean ASC = true;
		boolean DESC = false;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// Creating dummy unsorted map

		System.out.println("Map size : " + unsortMap.size());
		System.out.println("Before sorting......");
		printMap(unsortMap);

		/*
		 * System.out.println("After sorting ascending order......");
		 * Map<String, Date> sortedMapAsc = sortByComparator(unsortMap, ASC);
		 * printMap(sortedMapAsc);
		 */

		System.out.println("After sorting descindeng order......");
		Map<String, Date> sortedMapDesc = sortByComparator(unsortMap, DESC);
		printMap(sortedMapDesc);

		return sortedMapDesc;

	}

	public void printMap(Map<String, Date> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		for (Entry<String, Date> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + sdf.format(entry.getValue()));
		}
	}

	public Map<String, Date> sortByComparator(Map<String, Date> unsortMap, final boolean order) {

		List<Entry<String, Date>> list = new LinkedList<Entry<String, Date>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Date>>() {
			public int compare(Entry<String, Date> o1, Entry<String, Date> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Date> sortedMap = new LinkedHashMap<String, Date>();
		for (Entry<String, Date> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public String changeformatDate(WebDriver driver, Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String formatDate = null;
		// Date todayDate=Calendar.getInstance().getTime();
		formatDate = df.format(date);
		System.out.println("Date = " + formatDate);
		return formatDate;

	}

	public void inputbyEachcharacter(WebDriver driver, String myserialno, String xpath) throws Exception {
		// Input serial character by character
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String submySerialNo = myserialno.substring(0, 3);
		driver.findElement(By.xpath(xpath)).click();
		driver.findElement(By.xpath(xpath)).sendKeys(submySerialNo);
		TimeUnit.SECONDS.sleep(7);
		int n = myserialno.length();
		for (int i = 3; i < n; i++) {
			char subChar = myserialno.charAt(i);
			String strsubChar = "" + subChar;
			driver.findElement(By.xpath(xpath)).click();
			driver.findElement(By.xpath(xpath)).sendKeys(strsubChar);
			TimeUnit.SECONDS.sleep(5);
		}
	}

	public void selectDateinNewCalender(WebDriver driver, String date) throws Exception {
		loadProp();
		// date="12/21/2016";
		String[] splitDate = date.split("/");
		WebElement element = driver.findElement(By.xpath(xpathProperties.getProperty("calender_datePicker")));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
		TimeUnit.SECONDS.sleep(1);

		// Select year
		element = driver.findElement(By.xpath(xpathProperties.getProperty("calender_datePicker")));
		action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
		action.moveToElement(driver
				.findElement(By.xpath(xpathChecker(driver, xpathProperties.getProperty("Replace_text"), splitDate[2]))))
				.click().perform();

		// Select Month
		int month_int = Integer.parseInt(splitDate[0]);
		String monthString = new DateFormatSymbols().getMonths()[month_int - 1];
		System.out.println("Month in text = " + monthString);
		action.moveToElement(driver
				.findElement(By.xpath(xpathChecker(driver, xpathProperties.getProperty("Replace_text"), monthString))))
				.click().perform();
		TimeUnit.SECONDS.sleep(1);
		// Select Date
		action.moveToElement(driver
				.findElement(By.xpath(xpathChecker(driver, xpathProperties.getProperty("Replace_text"), splitDate[1]))))
				.click().perform();

	}

	public void globalSearch(WebDriver driver, String serialno) throws Exception {
		loadProp();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(xpathProperties.getProperty("Home_globalLens")))).click()
				.perform();
		// methods.clickButton(driver,xmap(""));
		input(driver, serialno, xpathProperties.getProperty("Home_globalSearchinput"));
		TimeUnit.SECONDS.sleep(1);
		clickTab(driver, xpathProperties.getProperty("Home_GOButton"));
		TimeUnit.SECONDS.sleep(10);
	}

	public void navigateLink(WebDriver driver, String Mainpage, String Subpage) {
		try {
			loadProp();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			WebElement element = driver
					.findElement(By.xpath(xpathChecker(driver, xpathProperties.getProperty("Replace_text"), Mainpage)));
			actions.moveToElement(element).click().build().perform();

			WebElement element1 = driver
					.findElement(By.xpath(xpathChecker(driver, xpathProperties.getProperty("Replace_text"), Subpage)));
			actions.moveToElement(element1).click().build().perform();

			WebElement ele = driver.findElement(
					By.xpath(xpathChecker(driver, xpathProperties.getProperty("Replace_text"), "Product Link")));
			actions.moveToElement(ele).perform();
			// clickTab(driver,xpathChecker(driver,xpathProperties.getProperty("Replace_text")
			// ,"Product Link"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickButton(WebDriver driver, String xPath) throws Exception {
		logger.info("clickButton XPath:" + xPath);
		try {
			try {
				TimeUnit.SECONDS.sleep(3);
				new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
				WebElement elementToClick = driver.findElement(By.xpath(xPath));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
			} catch (StaleElementReferenceException serex) {
				System.out.println("I am in the Button Method \t :\tcatch serex");
				new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
				driver.findElement(By.xpath(xPath)).click();
			}
		} catch (Exception ex) {
			System.out.println("ex");
			TimeUnit.SECONDS.sleep(3);
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			WebElement elementToClick = driver.findElement(By.xpath(xPath));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);

		}
	}

	public void clickButtonDirect(WebDriver driver, String xPath) throws Exception {
		logger.info("clickButton XPath:" + xPath);
		try {
			// Thread.sleep(4000);
			System.out.println("I am in the TRY Block in the clickButtonDirect Method");

			driver.findElement(By.xpath(xPath)).click();
		} catch (Exception ex) {
			// Thread.sleep(2000);
			System.out.println("I am in the catch Block in the clickButtonDirect Method" + ex);
			driver.findElement(By.xpath(xPath)).click();
		}
	}

	public void doubleClickButton(WebDriver driver, String xPath) throws Exception {
		Actions action = new Actions(driver);
		logger.info("clickButton XPath:" + xPath);
		try {

			try {
				TimeUnit.SECONDS.sleep(3);
				new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
				WebElement elementToDoubleClick = driver.findElement(By.xpath(xPath));
				action.doubleClick(elementToDoubleClick).build().perform();
			} catch (StaleElementReferenceException serex) {
				System.out.println("I am in the Button Method \t :\tcatch serex");
				new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
				WebElement elementToDoubleClick = driver.findElement(By.xpath(xPath));
				action.moveToElement(elementToDoubleClick).doubleClick().perform();
			}
		} catch (Exception ex) {
			System.out.println("ex");
			TimeUnit.SECONDS.sleep(3);
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			WebElement elementToDoubleClick = driver.findElement(By.xpath(xPath));
			action.moveToElement(elementToDoubleClick).doubleClick().build().perform();
		}

	}

	public boolean checkLabelText(WebDriver driver, String textToVerify, String xPath) throws Exception {
		logger.info("checkLabelText XPath:" + xPath);
		try {
			logger.info(
					"Check Label Text " + driver.findElement(By.xpath(xPath)).getText() + "........" + textToVerify);
			// Thread.sleep(2000);
			Boolean res = driver.findElement(By.xpath(xPath)).getText().equalsIgnoreCase(textToVerify);
			System.out.println(driver.findElement(By.xpath(xPath)).getText() + "----" + textToVerify + "----"
					+ driver.findElement(By.xpath(xPath)).getText().equalsIgnoreCase(textToVerify));
			logger.info(res);
			return res;
		} catch (StaleElementReferenceException serex) {
			// Thread.sleep(2000);
			Boolean res = driver.findElement(By.xpath(xPath)).getText().equalsIgnoreCase(textToVerify);
			logger.info(res);
			return res;
		}
	}

	public String excelGetdata(String File, String sheetName, int row, int column)

	{
		String str = "";
		try {
			FileInputStream fis = new FileInputStream(File);
			org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
			str = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public void excelPutdata(String Filepath, String sheetname, int r1, int c1, String ms) throws Exception {
		FileInputStream f = new FileInputStream(Filepath);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(f);
		wb.getSheet(sheetname).getRow(r1).createCell(c1).setCellValue(ms);
		;

		FileOutputStream fos = new FileOutputStream(Filepath);

		wb.write(fos);

	}

	/* Solomon--Included for signing in with CWS login page */
	public void loginCWS(WebDriver driver, String un_xpath, String pwd_xpath, String Sbt_xpath, String username,
			String password)

	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath(un_xpath)).sendKeys(username);
		driver.findElement(By.xpath(pwd_xpath)).sendKeys(password);
		System.out.println(password);
		driver.findElement(By.xpath(Sbt_xpath)).click();

	}

	public void clickWithElement(WebDriver driver, WebElement element) throws InterruptedException {

		try {

			Thread.sleep(2000);
			System.out.println("try in \"clickWithElement\" Method");
			new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(element)).click();
			System.out.println("Clicked one time");
			// new
			// WebDriverWait(driver,100).until(ExpectedConditions.elementToBeClickable(element)).click();
			if (element.isDisplayed()) {
				element.click();
				System.out.println("Clicked 2nd time");
			}
			System.out.println("Functionality clicked");
		} catch (StaleElementReferenceException serex) {
			System.out.println("catch serex in the \"clickWithElement\" Method");
			element.click();

		}

	}

	public void clickTab(WebDriver driver, String xPath) throws InterruptedException {
		try {
			try {
				Thread.sleep(2000);
				System.out.println("try");
				WebElement elementToClick = driver.findElement(By.xpath(xPath));
				System.out.println("I am going to click " + xPath);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
			} catch (StaleElementReferenceException serex) {
				System.out.println("catch serex");
				System.out.println("I am going to click " + xPath);

				driver.findElement(By.xpath(xPath)).click();
			}
		} catch (Exception ex) {
			System.out.println("ex");
			WebElement elementToClick = driver.findElement(By.xpath(xPath));
			System.out.println("I am going to click " + xPath);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
		}
	}

	public void select(WebDriver driver, String textToSelect, String xPath) throws Exception {
		try {
			Thread.sleep(2000);
			System.out.println(xPath);
			System.out.println(textToSelect);
			// for (WebElement webElement:new
			// Select(driver.findElement(By.xpath(xPath))).getOptions()) {
			// System.out.println(webElement.getText()+"
			// "+webElement.getText().equals(textToSelect)
			// +webElement.getText().length()+"
			// "+webElement.getText().contains(textToSelect));
			// }
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			new Select(driver.findElement(By.xpath(xPath))).selectByVisibleText(textToSelect);
		} catch (StaleElementReferenceException serex) {
			System.out.println(xPath);
			System.out.println(textToSelect);
			Reporter.log("<br />" + xPath);
			System.out.println(textToSelect);
			new Select(driver.findElement(By.xpath(xPath))).selectByVisibleText(textToSelect);
		}
	}

	public boolean containsLabelText(WebDriver driver, String textToVerify, String xPath) {
		try {
			// System.out.println("Check Label Text
			// "+driver.findElement(By.xpath(xPath)).getText() +
			// "........"+textToVerify);
			return driver.findElement(By.xpath(xPath)).getText().contains(textToVerify);
		} catch (StaleElementReferenceException serex) {
			return driver.findElement(By.xpath(xPath)).getText().contains(textToVerify);
		}
	}

	public boolean verifyErrors(WebDriver driver, String errorString, String xPath) {
		System.out.println("check :" + driver.findElement(By.xpath(xPath)).getText() + "....." + errorString);
		return driver.findElement(By.xpath(xPath)).getText().contains(errorString);

	}

	public void DragandDrop(WebDriver driver, String sourceXpath, String targetXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		WebElement target = driver.findElement(By.xpath(targetXpath));

		Actions builder = new Actions(driver);
		builder.dragAndDrop(source, target).perform();

	}

	public void performDragandDrop(WebDriver driver, String sourceXpath, String targetXpath) {
		WebElement source = driver.findElement(By.xpath(sourceXpath));
		WebElement target = driver.findElement(By.xpath(targetXpath));

		Actions builder = new Actions(driver);
		builder.clickAndHold(source);
		Action action = builder.build();
		action.perform();

		builder.moveToElement(target);
		builder.release(target);
		action = builder.build();
		action.perform();
	}

	public boolean verifyList(WebDriver driver, Object[] dataList, String xPath) throws Exception {
		Thread.sleep(2000);
		List<WebElement> webElements = driver.findElements(By.xpath(xPath));
		StringBuffer listValue = new StringBuffer("");
		for (WebElement element : webElements) {
			try {
				try {
					listValue.append(element.getText());
				} catch (StaleElementReferenceException ex) {
					listValue.append(element.getText());
				}
			} catch (Exception e) {
				listValue.append(element.getText());
			}
		}
		String lstValue = listValue.toString();
		for (Object data : dataList) {
			System.out.println(" " + (String) data);
			if (data != null && data instanceof String && !data.equals("") && !lstValue.contains((String) data)) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyNotList(WebDriver driver, Object[] dataList, String xPath) throws Exception {
		Thread.sleep(2000);
		List<WebElement> webElements = driver.findElements(By.xpath(xPath));
		StringBuffer listValue = new StringBuffer("");
		for (WebElement element : webElements) {
			try {
				listValue.append(element.getText());
			} catch (StaleElementReferenceException ex) {
				listValue.append(element.getText());
			}
		}
		String lstValue = listValue.toString();
		for (Object data : dataList) {
			if (data != null && data instanceof String && !data.equals("") && lstValue.contains((String) data)) {
				return false;
			}
		}
		return true;
	}

	public void clickRadio(WebDriver driver, String xPath) {
		try {
			try {
				driver.findElement(By.xpath(xPath)).click();
			} catch (StaleElementReferenceException serex) {
				driver.findElement(By.xpath(xPath)).click();
			}
		} catch (Exception ex) {
			WebElement elementToClick = driver.findElement(By.xpath(xPath));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
		}
	}

	public boolean verifyDisplay(WebDriver driver, String xPath) throws Exception {
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath(xPath)).getText());
		return driver.findElement(By.xpath(xPath)).isDisplayed();

	}

	public boolean verifyNotDisplay(WebDriver driver, String xPath) throws Exception {
		try {

			Thread.sleep(2000);
			System.out.println(driver.findElement(By.xpath(xPath)).getAttribute("class"));
			System.out.println(!driver.findElement(By.xpath(xPath)).isDisplayed());
			System.out.println(!driver.findElement(By.xpath(xPath)).getAttribute("style").equals("height: auto;"));
			System.out.println(xPath + " " + driver.findElement(By.xpath(xPath)).getAttribute("style") + " "
					+ driver.findElement(By.xpath(xPath)).isDisplayed());
			if ((!driver.findElement(By.xpath(xPath)).getAttribute("style").equals("height: auto;"))
					&& (!driver.findElement(By.xpath(xPath)).isDisplayed())) {

				return true;
			}
			if (!driver.findElement(By.xpath(xPath)).getAttribute("class").contains("collapseContent in")) {

				return true;
			}
		} catch (NoSuchElementException ex) {
			return true;
		}
		return false;
	}

	public boolean clickButtonandCloseAlert(WebDriver driver, String data, String confirmation, String xPath)
			throws Exception {
		try {
			try {
				System.out.println(data + " " + xPath);
				Thread.sleep(5000);
				driver.findElement(By.xpath(xPath)).click();
				System.out.println("After ex " + data + " " + xPath);
				return closeAlertAndGetItsText(driver, confirmation).contains(data);
			} catch (StaleElementReferenceException serex) {
				System.out.println("Ex " + data + " " + xPath);
				driver.findElement(By.xpath(xPath)).click();
				return closeAlertAndGetItsText(driver, confirmation).contains(data);
			}
		} catch (Exception ex) {
			System.out.println("Verum EX " + data + " " + xPath);
			// Thread.sleep(3000);
			driver.findElement(By.xpath(xPath)).click();
			return closeAlertAndGetItsText(driver, confirmation).contains(data);
		}
	}

	public String closeAlertAndGetItsText(WebDriver driver, String confirmation) {
		try {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Alert alert = driver.switchTo().alert();
			boolean acceptNextAlert = false;
			String ret_value = alert.getText();
			if (confirmation.equalsIgnoreCase("ok") || confirmation.equalsIgnoreCase("yes")) {
				acceptNextAlert = true;
			}
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return ret_value;
		} finally {
			acceptNextAlert = true;
			System.out.println("I am here");
			driver.switchTo().defaultContent();
		}
	}

	@SuppressWarnings("unused")
	public void TakeScreenShotAndCloseAlert(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		if (wait != null) {
			Alert alert = driver.switchTo().alert();
			String loadaccmsg = alert.getText();
			System.out.println("The Class name of the Alert Pop UP box  is \t:\t" + alert.getClass());
			System.out.println("The ScreenShot Error message is \t:\t" + loadaccmsg);
			Reporter.log("<br />The ScreenShot Error message is \t:\t" + loadaccmsg);
			alert.accept();
			System.out.println("Clicked the The Class name of the Alert Pop UP box");
			Reporter.log("<br />Clicked the The Class name of the Alert Pop UP box");
		} else {
			System.out.println("There is no alert");
		}

	}

	public void TakeScreenShotAndCloseAlert(WebDriver driver, ITestNGMethod testMethod) throws Exception {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String myFileName;
		try {
			Thread.sleep(5000);
			new WebDriverWait(driver, 60).until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String loadaccmsg = alert.getText();
			System.out.println("The ScreenShot Error message is \t:\t" + loadaccmsg);
			Reporter.log("<br />The ScreenShot Error message is  \t:\t" + loadaccmsg);
			String fileName = testMethod.getTestClass().getRealClass().getSimpleName() + "_"
					+ testMethod.getMethodName();
			System.out.println("The File name is \t:\t" + fileName);
			myFileName = getFileName(fileName);
			BufferedImage image = null;
			Thread.sleep(10000);
			try {
				System.out.println("I am going to Create a Robot");
				image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			} catch (HeadlessException | AWTException e1) {
				e1.printStackTrace();
			}
			try {
				String filepath = null;
				ImageIO.write(image, "jpg", new File(getPath(fileName)));
				System.setProperty("org.uncommons.reportng.escape-output", "false");

				((DesiredCapabilities) ((RemoteWebDriver) driver).getCapabilities())
						.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				alert.accept();
				System.out.println("Clicked the The Class name of the Alert Pop UP box");
				Reporter.log("<br />Clicked the The Class name of the Alert Pop UP box");
				driver.switchTo().defaultContent();
				System.out.println("Clicked Ok Button in the Modualar POPUP Box");
				Reporter.log("Clicked Ok Button in the Modualar POPUP Box");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {

			this.takeScreenShoot(driver, testMethod);
		}

	}

	/*
	 * @SuppressWarnings("unused") public void
	 * TakeScreenShotAndCloseAlert(WebDriver driver,ITestNGMethod testMethod)
	 * throws IOException {
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 300);
	 * 
	 * if(wait.until(ExpectedConditions.alertIsPresent()) != null) {
	 * System.out.println("I am in AlertLine"); Alert alert =
	 * driver.switchTo().alert();
	 * System.out.println("AlertLineaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	 * String loadaccmsg = alert.getText();
	 * System.out.println("The Class name of the Alert Pop UP box  is \t:\t"
	 * +alert.getClass());
	 * System.out.println("The ScreenShot Error message is \t:\t"+loadaccmsg);
	 * Reporter.log("<br />The ScreenShot Error message is \t:\t"+loadaccmsg);
	 * 
	 * 
	 * WebDriver augmentedDriver = new Augmenter().augment((WebDriver) alert);
	 * File screenshot =
	 * ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	 * String nameScreenshot =
	 * testMethod.getTestClass().getRealClass().getSimpleName() + "_"
	 * +testMethod.getMethodName();
	 * Reporter.log("<br />The Name of ScreenShot is \t:\t"+nameScreenshot);
	 * System.out.println("The Name of ScreenShot is \t:\t"+nameScreenshot);
	 * String path = getPath(nameScreenshot); FileUtils.copyFile(screenshot, new
	 * File(path)); Reporter.log("<br />The file name is "+path);
	 * System.out.println("The file name is "+path);
	 * System.out.println("<a href=" + path + " target='_blank' >" +
	 * this.getFileName(nameScreenshot) + "</a>");
	 * 
	 * alert.accept();
	 * 
	 * System.out.println("Clicked the The Class name of the Alert Pop UP box");
	 * Reporter.log("<br />Clicked the The Class name of the Alert Pop UP box");
	 * } else { WebDriver augmentedDriver = new Augmenter().augment(driver);
	 * File screenshot =
	 * ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	 * String nameScreenshot =
	 * testMethod.getTestClass().getRealClass().getSimpleName() + "_"
	 * +testMethod.getMethodName();
	 * Reporter.log("<br />The Name of ScreenShot is \t:\t"+nameScreenshot);
	 * System.out.println("The Name of ScreenShot is \t:\t"+nameScreenshot);
	 * String path = getPath(nameScreenshot); FileUtils.copyFile(screenshot, new
	 * File(path)); Reporter.log("<br />The file name is "+path);
	 * System.out.println("The file name is "+path);
	 * System.out.println("<a href=" + path + " target='_blank' >" +
	 * this.getFileName(nameScreenshot) + "</a>"); }
	 * 
	 * }
	 */
	public boolean checkValue(WebDriver driver, String data, String xPath) throws Exception {
		try {
			// System.out.println("Check Label Text
			// "+driver.findElement(By.xpath(xPath)).getText() +
			// "........"+textToVerify);
			Thread.sleep(2000);
			System.out.println(driver.findElement(By.xpath(xPath)).getAttribute("value"));
			return driver.findElement(By.xpath(xPath)).getAttribute("value").equalsIgnoreCase(data);
		} catch (StaleElementReferenceException serex) {
			// Thread.sleep(2000);
			return driver.findElement(By.xpath(xPath)).getText().equalsIgnoreCase(data);
		}
	}

	public void check(WebDriver driver, String xPath) {
		try {
			try {
				// driver.findElement(By.xpath(xPath)).click();
				WebElement elementToClick = driver.findElement(By.xpath(xPath));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);

			} catch (StaleElementReferenceException serex) {
				driver.findElement(By.xpath(xPath)).click();
			}
		} catch (Exception ex) {
			WebElement elementToClick = driver.findElement(By.xpath(xPath));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
		}
	}

	public boolean verifyFilterListApplied(WebDriver driver, Object[] dataList, String xPath) throws Exception {
		System.out.println("**xpath**" + xPath);
		Thread.sleep(2000);
		List<WebElement> webElements = null;
		try {
			webElements = driver.findElements(By.xpath(xPath));
			if (webElements.size() <= 0) {
				System.out.println("...................");
				return false;
			}
			System.out.println("XPAth : " + xPath);
			System.out.println(webElements + " " + webElements.size());
			for (WebElement webElement : webElements) {
				String elementText = "";
				try {
					// Thread.sleep(3000);
					elementText = webElement.getText();
				} catch (StaleElementReferenceException ex) {
					// Thread.sleep(3000);
					System.out.println("staled..." + webElement.getText());
					elementText = webElement.getText();
					// continue;
				}
				// if(elementText.equals("")){
				// return false;
				// }
				int flag = 0;
				System.out.println("Text : " + elementText);
				for (Object data : dataList) {

					if (data instanceof String && (elementText.equals("") || data.equals(elementText)
							|| elementText.toLowerCase().contains((String) data)))
						flag = 1;

				}

				if (flag == 0) {
					System.out.println("returning false in flag=0");
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		System.out.println("returning true");
		return true;
	}

	public boolean isSelected(WebDriver driver, String xpath) {
		System.out.println(".iss*************el." + xpath);
		boolean b = driver.findElement(By.xpath(xpath)).isSelected();
		System.out.println(".boo." + b);
		return b;
	}

	public boolean isSelectedCombo(WebDriver driver, String data, String xpath) {
		Select select = new Select(driver.findElement(By.xpath(xpath)));
		String selectedValue = select.getFirstSelectedOption().getText();
		if (selectedValue.equals(data)) {
			return true;
		}
		return false;
	}

	public boolean isNotSelectedCombo(WebDriver driver, String data, String xpath) {
		Select select = new Select(driver.findElement(By.xpath(xpath)));
		String selectedValue = select.getFirstSelectedOption().getText();
		if (selectedValue.equals(data)) {
			return false;
		}
		return true;
	}

	public boolean createListGeoBoundaryAlert(WebDriver driver, Object[] dataList) throws Exception {
		try {
			Properties xpathProperties = new Properties();
			xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
			clickButton(driver, xpathProperties.getProperty("admin_telematicsUI"));
			clickButton(driver, xpathProperties.getProperty("admin_manage_alert_rule"));
			// Thread.sleep(2000);
			clickButton(driver, xpathProperties.getProperty("admin_alertrule_create"));
			String alertRule = "";
			String geoBoundaryName = "";
			String entryOrExit = "";
			List<String> assets = new ArrayList<String>();
			class Recipient {
				private String recipientName;
				private String recipientEmailid;
				private String recipientPhone;

				public void setRecipientName(String recipientName) {
					this.recipientName = recipientName;
				}

				public String getRecipientName() {
					return recipientName;
				}

				public void setRecipientEmailid(String recipientEmailid) {
					this.recipientEmailid = recipientEmailid;
				}

				public String getRecipientEmailid() {
					return recipientEmailid;
				}

				public void setRecipientPhone(String recipientPhone) {
					this.recipientPhone = recipientPhone;
				}

				public String getRecipientPhone() {
					return recipientPhone;
				}
			}
			List<Recipient> recipientList = new ArrayList<Recipient>();
			for (Object object : dataList) {
				if (object instanceof String) {
					String string = (String) object;
					if (string.contains("alertRule"))
						alertRule = string.substring("alertRule:".length(), string.length());
					if (string.contains("geoboundary"))
						geoBoundaryName = string.substring("geoboundary:".length(), string.length());
					if (string.contains("entryOrExit"))
						entryOrExit = string.substring("entryOrExit:".length(), string.length());
					if (string.contains("assetName")) {
						String assetName = string.substring("assetName:".length(), string.length());
						assets.add(assetName);
					}
					if (string.contains("recipient")) {
						Recipient recipient = new Recipient();
						String[] recipientItems = string.split("recipient");
						for (String recipientItem : recipientItems) {
							System.out.println(recipientItem);
							if (recipientItem.contains("name:"))
								recipient.setRecipientName(
										recipientItem.substring("name:".length(), recipientItem.length()));
							if (recipientItem.contains("phone:"))
								recipient.setRecipientPhone(
										recipientItem.substring("phone:".length(), recipientItem.length()));
							if (recipientItem.contains("email:"))
								recipient.setRecipientEmailid(
										recipientItem.substring("email:".length(), recipientItem.length()));
						}
						recipientList.add(recipient);

					}

				}
			}
			input(driver, alertRule, xpathProperties.getProperty("admin_alertrule_textbox"));
			clickRadio(driver,
					xpathProperties.getProperty("admin_alertrule_type").replace("**data1**", "Geo Boundary"));
			select(driver, geoBoundaryName, xpathProperties.getProperty("admin_alertrule_geoboundary"));
			if (entryOrExit.equalsIgnoreCase("entry"))
				check(driver, xpathProperties.getProperty("admin_alertrule_alertoptions_entryexit").replace("**data1**",
						"Entry"));
			else if (entryOrExit.equalsIgnoreCase("exit"))
				check(driver, xpathProperties.getProperty("admin_alertrule_alertoptions_entryexit").replace("**data1**",
						"Exit"));
			else {
				check(driver, xpathProperties.getProperty("admin_alertrule_alertoptions_entryexit").replace("**data1**",
						"Entry"));
				check(driver, xpathProperties.getProperty("admin_alertrule_alertoptions_entryexit").replace("**data1**",
						"Exit"));
			}
			for (String asset : assets) {
				performDragandDrop(driver, xpathProperties.getProperty("dragEquipsrc").replace("**data1**", asset),
						xpathProperties.getProperty("dropEquipdst"));
			}
			clickButton(driver, xpathProperties.getProperty("admin_alertrule_addeditrecip"));
			for (Recipient recipient : recipientList) {
				input(driver, recipient.getRecipientName(), xpathProperties.getProperty("admin_alertrule_recip_name"));
				if (recipient.getRecipientPhone() != null && !recipient.getRecipientPhone().equals(""))
					input(driver, recipient.getRecipientPhone(),
							xpathProperties.getProperty("admin_alertrule_phonenumber"));
				if (recipient.getRecipientEmailid() != null && !recipient.getRecipientEmailid().equals(""))
					input(driver, recipient.getRecipientEmailid(),
							xpathProperties.getProperty("admin_alertrule_emailid"));
				clickButton(driver, xpathProperties.getProperty("admin_alertrule_recip_add"));
				clickButton(driver, xpathProperties.getProperty("admin_alertrule_recip_save"));
			}
			clickButton(driver, xpathProperties.getProperty("admin_create_save"));
			// Thread.sleep(2000);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean createListComplexAsset(WebDriver driver, Object[] dataList) throws Exception {
		try {
			Properties xpathProperties = new Properties();
			xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
			clickButton(driver, xpathProperties.getProperty("admin_telematicsUI"));
			clickButton(driver, xpathProperties.getProperty("admin_onboarding"));
			// Thread.sleep(3000);
			clickButton(driver, xpathProperties.getProperty("admin_onboarding_create"));
			String name = "", manu = "", sn = "", icon = "", model = "", prodfam = "";
			List<String> assets = new ArrayList<String>();
			for (Object object : dataList) {
				if (object instanceof String) {
					String string = (String) object;
					if (string.contains("name"))
						name = string.substring("name:".length(), string.length());
					if (string.contains("manu"))
						manu = string.substring("manu:".length(), string.length());
					if (string.contains("sn"))
						sn = string.substring("sn:".length(), string.length());
					if (string.contains("assetName")) {
						String assetName = string.substring("assetName:".length(), string.length());
						assets.add(assetName);
					}
					if (string.contains("icon"))
						icon = string.substring("icon:".length(), string.length());
					if (string.contains("model"))
						model = string.substring("model:".length(), string.length());
					if (string.contains("prodfam"))
						prodfam = string.substring("prodfam:".length(), string.length());
				}

			}
			input(driver, name, xpathProperties.getProperty("admin_onboarding_name"));
			input(driver, sn, xpathProperties.getProperty("admin_onboarding_sn"));
			input(driver, manu, xpathProperties.getProperty("admin_onboarding_manu"));
			if (!model.equals("")) {
				input(driver, model, xpathProperties.getProperty("admin_onboarding_model"));
			}
			if (!prodfam.equals("")) {
				input(driver, prodfam, xpathProperties.getProperty("admin_onboarding_prodfam"));
			}

			for (String asset : assets) {
				performDragandDrop(driver, xpathProperties.getProperty("dragEquipsrc").replace("**data1**", asset),
						xpathProperties.getProperty("dropEquipdst"));
			}
			clickRadio(driver, xpathProperties.getProperty("admin_onboarding_icon").replace("**data1**", icon));
			clickButton(driver, xpathProperties.getProperty("admin_create_save"));
			clickButtonDirect(driver, xpathProperties.getProperty("admin_create_save"));
			// Thread.sleep(3000);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public void createListGroup(WebDriver driver, Object[] dataList) throws Exception {
		Properties xpathProperties = new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
		clickButton(driver, xpathProperties.getProperty("admin_telematicsUI"));
		clickButton(driver, xpathProperties.getProperty("admin_manage_group"));
		waitForLoadingIcon(driver, xpathProperties.getProperty("loading_icon"));
		clickButton(driver, xpathProperties.getProperty("admin_group_create"));
		clickButton(driver, xpathProperties.getProperty("admin_group_create"));
		String name = "", privacy = "";
		List<String> assets = new ArrayList<String>();
		for (Object object : dataList) {
			if (object instanceof String) {
				String string = (String) object;
				if (string.contains("name"))
					name = string.substring("name:".length(), string.length());

				if (string.contains("assetName")) {
					String assetName = string.substring("assetName:".length(), string.length());
					assets.add(assetName);
				}
				if (string.contains("privacy"))
					privacy = string.substring("privacy:".length(), string.length());
			}
		}
		input(driver, name, xpathProperties.getProperty("admin_group_name"));
		String privacyXpath = xpathProperties.getProperty("admin_group_create_privacy");
		System.out.println("***" + privacyXpath);
		clickRadio(driver, privacyXpath.replace("**data1**", privacy));
		System.out.println(Arrays.asList(dataList));
		for (String asset : assets) {
			performDragandDrop(driver, xpathProperties.getProperty("dragEquipsrc").replace("**data1**", asset),
					xpathProperties.getProperty("dropEquipdst"));
		}
		// Thread.sleep(2000);
		clickButton(driver, xpathProperties.getProperty("admin_create_save"));
	}

	public void createListGroups(WebDriver driver, Object[] dataList) throws Exception {
		Properties xpathProperties = new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
		clickButton(driver, xpathProperties.getProperty("admin_telematicsUI"));
		clickButton(driver, xpathProperties.getProperty("admin_manage_group"));
		clickButton(driver, xpathProperties.getProperty("admin_group_create"));
		clickButton(driver, xpathProperties.getProperty("admin_group_create"));
		String privacy = xpathProperties.getProperty("admin_group_create_privacy");
		clickRadio(driver, privacy.replace("**data1**", (String) dataList[1]));
		input(driver, (String) dataList[0], xpathProperties.getProperty("admin_group_name"));

		System.out.println(Arrays.asList(dataList));
		for (int i = 2; i < dataList.length; i++) {
			if (dataList[i] != null && !((String) dataList[i]).equals("")) {
				String sourceXpath = xpathProperties.getProperty("dragEquipsrc");
				String targetXpath = xpathProperties.getProperty("dropEquipdst");
				performDragandDrop(driver, sourceXpath.replace("**data1**", (String) dataList[i]), targetXpath);
			}
		}
		clickButton(driver, xpathProperties.getProperty("admin_create_save"));
	}

	public void CaptureScreenShots(WebDriver driver, String ts) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("C:/Users/shanmk4/Desktop/" + ts + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickShowFilters(WebDriver driver, String xpath) throws Exception {
		WebElement webElement = driver.findElement(By.xpath(xpath));
		try {
			// Thread.sleep(4000);
			if (webElement.getText().contains("show filters")) {
				clickButton(driver, xpath);
				if (webElement.getText().contains("show filters")) {
					clickButton(driver, xpath);
				}
			}
		} catch (StaleElementReferenceException serex) {
			if (webElement.getText().contains("show filters")) {
				clickButton(driver, xpath);
				if (webElement.getText().contains("show filters")) {
					clickButton(driver, xpath);
				}
			}
		}
	}

	public boolean obtainDataFromTable(WebDriver driver, String query, String colHeader, String rowHeader, String xpath)
			throws Exception {
		Thread.sleep(2000);
		System.out.println(xpath + " " + colHeader + " " + rowHeader);
		List<WebElement> rowWebElements = driver.findElements(By.xpath(xpath));
		List<WebElement> headerWebElements = rowWebElements.get(0).findElements(By.tagName("th"));
		for (int j = 1; j < rowWebElements.size(); j++) {
			WebElement rowElement = rowWebElements.get(j);

			System.out.println("RowElement " + rowElement.findElement(By.tagName("td")) + " ");

			if (rowElement.findElement(By.tagName("td")) != null
					&& rowElement.findElement(By.tagName("td")).getText().equals(rowHeader)) {
				int i = 0;
				for (WebElement headerElement : headerWebElements) {

					System.out.println("Header " + headerElement.getText() + " "
							+ headerElement.getText().equals(colHeader) + " " + colHeader);

					if (headerElement.getText() != null && headerElement.getText().equals(colHeader)) {
						List<WebElement> individualElementsforRow = rowElement.findElements(By.tagName("td"));
						String data = executeQuery(query);

						if (individualElementsforRow.get(i) != null) {
							System.out.println("Dei Data da " + individualElementsforRow.get(i).getText() + " " + data);
						}

						if (individualElementsforRow.get(i) != null
								&& data.contains(individualElementsforRow.get(i).getText())) {
							return true;
						}
					}
					i++;
				}
			}
		}
		return false;
	}

	private String executeQuery(String query) {
		try {

			String connectionURL = "jdbc:oracle:thin:@z8qrora.corp.cat.com:1521:z8qr";
			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "seetha", "Oct08!2013");
			statement = connection.createStatement();
			System.out.println("......................." + query);
			rs = statement.executeQuery(query);
			String data = "";
			while (rs.next()) {
				data = rs.getString(1);
				System.out.println(rs.getString(1) + " ");
			}
			if (data == null || data.length() == 0) {
				data = "0";
				return data;
			}
			rs.close();
			statement.close();
			connection.close();
			try {
				data = data.replace(".000000000 PM", " PM");
				data = data.replace("-13 ", "-2013 ");
				System.out.println(data);
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = (Date) formatter.parse(data);
				System.out.println(date);
				String returndata = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa").format(date);
				System.out.println(returndata);
				return returndata;
			} catch (Exception ex) {
				try {
					if (!data.contains(".")) {
						System.out.println("No decimal sir. Can be returned with no problem");
						return data;
					}
					double precision = 10.0;
					if (query.contains("eng_hr"))
						precision = 100.0;
					Double dat = Math.round(Double.parseDouble(data) * precision) / precision;
					System.out.println(dat);
					return BigDecimal.valueOf(dat).toPlainString();
				} catch (Exception exe) {
					return data;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Unable to connect to database.");
		}
		return "";
	}

	public boolean executeQueryandCheckLabel(WebDriver driver, String query, String xPath) throws Exception {
		// Thread.sleep(4000);
		String dataFromDB = executeQuery(query);
		System.out.println(dataFromDB + " " + xPath);
		return checkLabelText(driver, dataFromDB, xPath);
	}

	public boolean checkAttribute(WebDriver driver, String data, String xPath) {
		WebElement webElement = driver.findElement(By.xpath(xPath));
		System.out.println(webElement.getAttribute("style") + " " + webElement.getAttribute("class"));
		if (webElement.getAttribute("style").contains(data))
			return true;
		if (webElement.getAttribute("class").contains(data))
			return true;
		return false;
	}

	public void wait(WebDriver driver, String data) throws Exception {
		// Thread.sleep(Integer.parseInt(data));
	}

	public void waitForLoadingIcon(WebDriver driver, final String xPath) {
		try {

			new WebDriverWait(driver, 100).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return !d.findElement(By.xpath(xPath)).isDisplayed();
				}
			});
		} catch (Exception ex) {

		}
	}

	public void waitloading_icon(WebDriver driver, String xPath) throws InterruptedException {
		try {
			Thread.sleep(2000);
			if (!(driver.findElement(By.xpath(xPath)).isDisplayed())) {
				Thread.sleep(3000);
				System.out.println("I am here in the wait load");

			}
			if (driver.findElement(By.xpath(xPath)).isDisplayed()) {

				System.out.println("WebElement is present and waiting to inactivate the webElement");
				// Reporter.log("<br />WebElement is present and waiting to
				// inactivate the webElement");
				new WebDriverWait(driver, 100).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
				// Reporter.log("<br />WebElement \"Loading icon\" is
				// inactivated");
			}
		} catch (Exception ex) {
			// new
			// WebDriverWait(driver,100).until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(xpathMapper.getProperty("loading_icon")))));

			System.out.println("The Exception is  in the \"waitloading_icon\" method");
			// Reporter.log("<br />The Exception is in the \"waitloading_icon\"
			// method");
		}
	}

	/*
	 * public void waitloading_icon(WebDriver driver,String xPath) throws
	 * InterruptedException { try{
	 * if(driver.findElement(By.xpath(xPath)).isDisplayed()) { System.out.
	 * println("WebElement is present and waiting to inactivate the webElement"
	 * ); Reporter.
	 * log("<br />WebElement is present and waiting to inactivate the webElement"
	 * ); new WebDriverWait(driver,10).until(ExpectedConditions.
	 * invisibilityOfElementLocated(By.xpath(xPath)));
	 * Reporter.log("<br />WebElement \"Loading icon\" is inactivated"); } }
	 * catch(Exception ex) { //new
	 * WebDriverWait(driver,100).until(ExpectedConditions.stalenessOf(driver.
	 * findElement(By.xpath(xpathMapper.getProperty("loading_icon"))))); //new
	 * WebDriverWait(driver,100).until(ExpectedConditions.
	 * invisibilityOfElementLocated(By.xpath(xPath)));
	 * System.out.println("The Exception is  in the \"waitloading_icon\" method"
	 * );
	 * Reporter.log("<br />The Exception is  in the \"waitloading_icon\" method"
	 * ); } }
	 */

	public void selectDateinCalendar(WebDriver driver, String fromDate, String toDate, String sourceXPath,
			String targetXPath) throws Exception {
		Properties xpathProperties = new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
		System.out.println("date arrow select");
		clickButton(driver, xpathProperties.getProperty("datearrow_select"));
		// Thread.sleep(4000);
		clickButton(driver, xpathProperties.getProperty("date_range_select").replace("**data1**", "Today"));
		System.out.println("today");
		waitForLoadingIcon(driver, xpathProperties.getProperty("loading_icon"));
		System.out.println("loading icon");
		clickButton(driver, xpathProperties.getProperty("datearrow_select"));
		// Thread.sleep(4000);
		System.out.println("date arrow select");
		clickButton(driver, xpathProperties.getProperty("date_range_select").replace("**data1**", "Custom Range"));
		System.out.println("custom range");
		String[] dateTime = extractDateAndTime(fromDate);
		List<Comparable> returnParameters = selectDate(dateTime[0]);
		String xPath = (String) returnParameters.get(0);
		String dateXPath = (String) returnParameters.get(1);
		int noOfClicks = (Integer) returnParameters.get(2);

		for (int i = 0; i < noOfClicks; i++) {
			clickButton(driver, sourceXPath + xPath);
		}
		clickButton(driver, sourceXPath + dateXPath);
		System.out.println(xPath + " " + dateXPath + " " + noOfClicks);
		if (dateTime.length > 1) {
			String startTime = dateTime[1];
			System.out.println(startTime);
			input(driver, startTime, xpathProperties.getProperty("datefilter_starttime"));
			if (dateTime.length > 2) {
				String ampm = dateTime[2];
				System.out.println(ampm);
				if (ampm.equals("AM")) {
					clickRadio(driver, xpathProperties.getProperty("datefilter_start_am"));
				} else if (ampm.equals("PM")) {
					clickRadio(driver, xpathProperties.getProperty("datefilter_start_pm"));
				}
			}
		}
		dateTime = extractDateAndTime(toDate);
		returnParameters = selectDate(dateTime[0]);
		xPath = (String) returnParameters.get(0);
		dateXPath = (String) returnParameters.get(1);
		noOfClicks = (Integer) returnParameters.get(2);
		for (int i = 0; i < noOfClicks; i++) {
			clickButton(driver, targetXPath + xPath);
		}
		clickButton(driver, targetXPath + dateXPath);
		System.out.println(xPath + " " + dateXPath + " " + noOfClicks);
		if (dateTime.length > 1) {
			String endTime = dateTime[1];
			System.out.println(endTime);
			input(driver, endTime, xpathProperties.getProperty("datefilter_endtime"));
			if (dateTime.length > 2) {
				String ampm = dateTime[2];
				System.out.println(ampm);
				if (ampm.equals("AM")) {
					clickRadio(driver, xpathProperties.getProperty("datefilter_end_am"));
				} else if (ampm.equals("PM")) {
					clickRadio(driver, xpathProperties.getProperty("datefilter_end_pm"));
				}
			}
		}
		clickButton(driver, xpathProperties.getProperty("calendar_apply"));
	}

	private String[] extractDateAndTime(String date) throws Exception {
		String[] str = date.split(" ");
		return str;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Comparable> selectDate(String date) throws Exception {
		List<Comparable> returnParameters = new ArrayList<Comparable>();
		String[] date_class = date.split("/");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		int currentYear = cal.get(Calendar.YEAR);
		int dayDate = new Integer(date_class[0]).intValue();
		int monthDate = new Integer(date_class[1]).intValue();
		int yearDate = new Integer(date_class[2]).intValue();
		int noOfClicks = 0;
		String navXpath = "";
		Properties xpathProperties = new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
		String prevXPath = xpathProperties.getProperty("calendar_previous");
		String nextXPath = xpathProperties.getProperty("calendar_next");
		String dateXpath = xpathProperties.getProperty("calendar_day");
		if (dateXpath.contains("**data1**")) {
			dateXpath = dateXpath.replace("**data1**", ((Integer) dayDate).toString());
			System.out.println("Date " + dateXpath);
		}
		if (monthDate > 0 && monthDate <= 12 && dayDate > 0 && dayDate <= 31) {
			if (currentYear > yearDate) {
				navXpath = navXpath + prevXPath;
				noOfClicks = (12 - monthDate) + currentMonth + (currentYear - yearDate) * 12;
				System.out.println("currYear great " + noOfClicks + " " + navXpath);
			} else if (currentYear < yearDate) {
				navXpath = navXpath + nextXPath;
				noOfClicks = (12 - monthDate) + currentMonth + (yearDate - currentYear) * 12;
				System.out.println("currYear less " + noOfClicks + " " + navXpath);
			} else if (currentYear == yearDate) {
				if (currentMonth > monthDate) {
					navXpath = navXpath + prevXPath;
					noOfClicks = currentMonth - monthDate;
					System.out.println("currMonth great " + noOfClicks + " " + navXpath);
				} else if (currentMonth < monthDate) {
					navXpath = navXpath + nextXPath;
					noOfClicks = monthDate - currentMonth;
					System.out.println("currMonth less " + noOfClicks + " " + navXpath);
				}
			}
		}
		returnParameters.add(navXpath);
		returnParameters.add(dateXpath);
		returnParameters.add(noOfClicks);
		return returnParameters;
	}

	public boolean verifyWithinBounds(WebDriver driver, String data, String xPath) throws Exception {
		// Thread.sleep(4000);
		String obtainedData = driver.findElement(By.xpath(xPath)).getText();
		obtainedData = obtainedData.replaceAll(".[^0-9.]", "");
		if (obtainedData.endsWith(".")) {
			obtainedData = obtainedData.substring(0, obtainedData.length() - 1);
		}

		double value = 0;
		try {
			value = Double.parseDouble(obtainedData);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		double bound = Integer.parseInt(data);
		if (value > bound) {
			return false;
		}
		return true;
	}

	public boolean verifyValueinColumnListWithinBounds(WebDriver driver, Object[] data, String xPath) throws Exception {
		// Thread.sleep(4000);
		List<WebElement> webElementList = driver.findElements(By.xpath(xPath));
		int i = 0;
		for (WebElement webElement : webElementList) {
			String obtainedData = webElement.getText();
			obtainedData = obtainedData.replaceAll("[^*0-9.]", "");
			if (obtainedData.endsWith(".")) {
				obtainedData = obtainedData.substring(0, obtainedData.length() - 1);
			}
			if (obtainedData.equals("") || obtainedData.length() != 0) {
				continue;
			}
			double value = 0;
			try {
				value = Double.parseDouble(obtainedData);
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
			double bound = Integer.parseInt(data[i].toString());
			if (value > bound) {
				return false;
			}
		}
		return true;
	}

	public boolean verifySelectList(WebDriver driver, Object[] list, String xPath) throws Exception {
		WebElement selectWebElement = driver.findElement(By.xpath(xPath));
		for (WebElement webElement : new Select(selectWebElement).getOptions()) {
			String selectOptionsText = webElement.getText();
			int i = 0;
			for (Object obj : list) {
				if (obj instanceof String && ((String) obj).equals(selectOptionsText)) {
					i = 1;
				}
			}
			if (i == 0) {
				System.out.println("Not in excel list:" + selectOptionsText);
				return false;
			}
		}
		return true;
	}

	public boolean isNotSelected(WebDriver driver, String xpath) {
		return !(driver.findElement(By.xpath(xpath)).isSelected());
	}

	public boolean checkAllFieldsListinAvatar(WebDriver driver, Object[] list, String xPath) throws Exception {
		WebElement webElement = driver.findElement(By.xpath(xPath));
		String icon = "", sn = "", make = "", model = "";
		for (Object obj : list) {
			if (obj instanceof String) {
				String str = (String) obj;
				if (str.startsWith("icon")) {
					icon = str.substring("icon:".length(), str.length());
				}
				if (str.startsWith("make")) {
					make = str.substring("make:".length(), str.length());
				}
				if (str.startsWith("model")) {
					model = str.substring("model:".length(), str.length());
				}
				if (str.startsWith("sn")) {
					sn = str.substring("sn:".length(), str.length());
				}
			}
		}
		try {
			webElement.findElement(By.xpath("//div[contains(@class,'" + icon.toLowerCase() + "')]"));
		} catch (Exception ex) {
			return false;
		}
		System.out.println(icon + " " + make + " " + model + " " + sn + " "
				+ webElement.findElement(By.xpath("//*[contains(@data-name,'SerialNumber')]")).getText() + " "
				+ webElement.findElement(By.xpath("//*[contains(@data-name,'make')]")).getText() + " ");
		if (!webElement.findElement(By.xpath("//*[contains(@data-name,'SerialNumber')]")).getText().equals(sn)) {
			return false;
		}
		if (!webElement.findElement(By.xpath("//*[contains(@data-name,'make')]")).getText().equals(make)) {
			return false;
		}
		if (!webElement.findElement(By.xpath("//*[contains(@data-name,'model')]")).getText().equals(model)) {
			return false;
		}
		return true;

	}

	private String queryConstructor(Object[] dataList) throws Exception {
		String type = "";
		List<String> assets = new ArrayList<String>();
		List<String> fields = new ArrayList<String>();
		String startDate = "", endDate = "";
		String fieldValues = "";
		// String filter="";
		String operator = "";
		List<String> filterList = new ArrayList<String>();
		List<String> sortList = new ArrayList<String>();
		for (Object object : dataList) {
			if (object instanceof String) {
				String string = (String) object;
				if (string.contains("type"))
					type = string.substring("type:".length(), string.length());
				if (string.contains("fieldName")) {
					String field = string.substring("fieldName:".length(), string.length());
					fields.add(field);
				}
				if (string.contains("assetName")) {
					String assetName = string.substring("assetName:".length(), string.length());
					assets.add(assetName);
				}
				// if(string.contains("sort"))
				// sortField=string.substring("sort:".length(),
				// string.length());
				if (string.contains("startDate"))
					startDate = string.substring("startDate:".length(), string.length());
				if (string.contains("endDate"))
					endDate = string.substring("endDate:".length(), string.length());
				if (string.contains("fieldsToBeTaken"))
					fieldValues = string.substring("fieldsToBeTaken:".length(), string.length());
				// if(string.contains("filter"))
				// filter=string.substring("filter:".length(), string.length());
				if (string.contains("filter")) {
					String filterName = string.substring("filter:".length(), string.length());
					filterList.add(filterName);
				}
				if (string.contains("sort")) {
					String sortName = string.substring("sort:".length(), string.length());
					sortList.add(sortName);
				}
				if (string.contains("operator"))
					operator = string.substring("operator:".length(), string.length());
			}

		}
		String query = "";
		Properties sqlQueryBindings = new Properties();
		sqlQueryBindings.load(new FileInputStream("src\\Config\\sqlbindings.properties"));
		Properties sqlTableBindings = new Properties();
		sqlTableBindings.load(new FileInputStream("src\\Config\\sqlquery.properties"));

		/**/
		if (!operator.equals("")) {
			if (operator.equals("difference")) {
				if (assets.size() > 0) {
					for (int i = 0; i < assets.size(); i++) {
						String query1 = "", query2 = "";
						if (!startDate.equals("")) {
							query1 = "select ";
							if (!fieldValues.equals("")) {
								fieldValues = sqlQueryBindings.getProperty(fieldValues);
								fields = Arrays.asList(fieldValues.split(","));
							}
							for (int j = 0; j < fields.size(); j++) {
								query1 += sqlQueryBindings.getProperty(fields.get(j));
								if (j < fields.size() - 1) {
									query1 += ", ";
								}
							}
							query1 += " from " + sqlTableBindings.getProperty(type);
							query1 += " where ";
							query1 += sqlQueryBindings.getProperty("asset") + " = ";
							query1 += "'" + assets.get(i) + "'";
							if (!startDate.equals("")) {
								if (assets.size() > 0)
									query1 += " and ";
								query1 += sqlQueryBindings.getProperty(type + "_date") + " = '" + startDate + "'";
							}
						}
						System.out.println("Query1 " + query1);
						if (!endDate.equals("")) {
							query2 = "select ";
							if (!fieldValues.equals("")) {
								fieldValues = sqlQueryBindings.getProperty(fieldValues);
								fields = Arrays.asList(fieldValues.split(","));
							}
							for (int j = 0; j < fields.size(); j++) {
								query2 += sqlQueryBindings.getProperty(fields.get(j));
								if (j < fields.size() - 1) {
									query2 += ", ";
								}
							}
							query2 += " from " + sqlTableBindings.getProperty(type);
							query2 += " where ";
							query2 += sqlQueryBindings.getProperty("asset") + " = ";
							query2 += "'" + assets.get(i) + "'";
							if (!startDate.equals("")) {
								if (assets.size() > 0)
									query2 += " and ";
								query2 += sqlQueryBindings.getProperty(type + "_date") + " = '" + endDate + "'";
							}
						}
						System.out.println("Query2 " + query2);
						query = "select (" + query2 + ") - (" + query1 + ") from dual";
						return query;
					}
				}
			}
		}
		/**/

		query = "select ";
		sqlQueryBindings = new Properties();
		sqlQueryBindings.load(new FileInputStream("src\\Config\\sqlbindings.properties"));
		if (!fieldValues.equals("")) {
			fieldValues = sqlQueryBindings.getProperty(fieldValues);
			fields = Arrays.asList(fieldValues.split(","));
		}
		for (int i = 0; i < fields.size(); i++) {
			query += sqlQueryBindings.getProperty(fields.get(i));
			if (i < fields.size() - 1) {
				query += ", ";
			}
		}
		sqlTableBindings = new Properties();
		sqlTableBindings.load(new FileInputStream("src\\Config\\sqlquery.properties"));
		query += " from " + sqlTableBindings.getProperty(type);

		if (assets.size() > 0 || !startDate.equals("") || !endDate.equals("") || filterList.size() > 0) {
			query += " where ";
			if (assets.size() > 0) {
				query += sqlQueryBindings.getProperty("asset") + " in (";
				for (int i = 0; i < assets.size(); i++) {
					query += "'" + assets.get(i) + "'";
					if (i < assets.size() - 1) {
						query += ",";
					} else
						query += ")";
				}
			}
			if (!startDate.equals("")) {
				if (assets.size() > 0)
					query += " and ";
				query += sqlQueryBindings.getProperty(type + "_date") + " between '" + startDate + "' and '" + endDate
						+ "'";
			}

			if (filterList.size() > 0) {
				if (assets.size() > 0 || !startDate.equals(""))
					query += " and ";
				for (int i = 0; i < filterList.size(); i++) {
					String filterParam = filterList.get(i).split("::")[0];
					String[] filterdata = filterList.get(i).split("::")[1].split(",");
					query += sqlQueryBindings.getProperty("filter_" + filterParam) + " in ";
					// query+="("+filterdata+")";
					query += "(";
					for (int j = 0; j < filterdata.length; j++) {
						query += "'" + filterdata[j] + "'";
						if (j < filterdata.length - 1) {
							query += ",";
						} else {
							query += ")";
							if (i < filterList.size() - 1)
								query += " and ";
						}
					}
				}
			}
			// if(!filter.equals("")) {
			// String filterParam=filter.split("::")[0];
			// String [] filterdata=filter.split("::")[1].split(",");
			// query+=" and
			// "+sqlQueryBindings.getProperty("filter_"+filterParam)+" in ";
			//// query+="("+filterdata+")";
			// query+="(";
			// for(int i=0;i<filterdata.length;i++) {
			// query+="'"+filterdata[i]+"'";
			// if(i<filterdata.length-1) {
			// query+=",";
			// } else
			// query+=")";
			// }
			// }
		}
		if (sortList.size() > 0) {
			query += " order by ";
			for (int i = 0; i < sortList.size(); i++) {
				String orderField = sortList.get(i).split("::")[0];
				orderField = sqlQueryBindings.getProperty(orderField);
				String orderBy = sortList.get(i).split("::")[1];
				query += orderField + " " + orderBy;
				if (i < sortList.size() - 1) {
					query += ",";
				}
			}
		}
		// if(!sortField.equals("")) {
		// String orderField=sortField.split("::")[0];
		// orderField=sqlQueryBindings.getProperty(orderField);
		// String orderBy=sortField.split("::")[1];
		// query+=" order by "+orderField+" "+orderBy ;
		// }
		System.out.println(query);
		return query;
	}

	public void homePageAlertsFeed(WebDriver driver, String srcxpath, String dstxpath) throws Exception {
		// Thread.sleep(6000);
		Properties xpathProperties = new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
		driver.findElement(By.xpath(".//div[text()='GBE00107']//ancestor::tr//th[text()='Date/Time Occurred']"))
				.click();
		List<WebElement> weblist = driver.findElements(By.xpath(srcxpath));
		System.out.println("size" + weblist.size());
		List<String> listValue = new ArrayList<String>();

		for (WebElement element : weblist) {
			try {
				try {
					listValue.add(element.getText());
				} catch (StaleElementReferenceException ex) {
				}
			} catch (Exception e) {
			}
		}
		int i = 0;
		for (; i < listValue.size(); i++)
			System.out.println("listValue contains " + listValue.get(i));

		System.out.println("click on home page tab");

		driver.findElement(By.xpath(xpathProperties.getProperty("home_telematicsUI"))).click();
		// Thread.sleep(3000);
		driver.findElement(By.xpath(xpathProperties.getProperty("homepage_alerts_fault"))).click();

		System.out.println("fetch alert data in home page");
		// Thread.sleep(4000);
		List<WebElement> homeweblist = driver.findElements(By.xpath(dstxpath));

		System.out.println("destination xpath" + dstxpath);

		System.out.println("size" + homeweblist.size());
		System.out.println("---" + homeweblist.get(0).getText());
		List<String> homeValue = new ArrayList<String>();
		WebElement element = null;
		for (int m = 0; m < homeweblist.size(); m++) {
			try {
				try {
					System.out.println(m);
					element = homeweblist.get(m);
					System.out.println(element.getText());
					homeValue.add(homeweblist.get(m).getText());
				} catch (StaleElementReferenceException ex) {
				}
			} catch (Exception e) {
			}
		}
		System.out.println(homeValue.size());
		int h = 0;
		for (; h < homeValue.size(); h++)
			System.out.println("homeValue contains " + homeValue.get(h));
		int k = 1, g = 0;
		for (int j = 0; j < homeValue.size() / 3; j++)
			for (int q = 0; q < 3; q++, k++, g++) {
				if (homeValue.get(g).contains(listValue.get(k))) {
					System.out.println("equals");
				} else {
					System.out.println("not equals");
				}
			}
	}

	public boolean checkAttributeList(WebDriver driver, Object[] dataList, String xPath) {

		int flag = 0;
		List<WebElement> webElements = driver.findElements(By.xpath(xPath));

		StringBuffer strbuf = new StringBuffer("");
		String finalstr;

		if (webElements.size() <= 0) {
			return true;
		}
		for (Object data : dataList) {
			if (data != null)
				strbuf.append((String) data);
		}
		finalstr = strbuf.toString();
		System.out.println(finalstr);
		for (WebElement webElement : webElements) {
			flag = 0;
			System.out.println(webElement.getAttribute("class"));
			if (webElement.getAttribute("class").contains(finalstr))
				flag = 1;
			if (flag == 0)
				return false;
		}
		return true;

	}

	void typeKeyCombination(WebDriver driver, String xPath) {
		try {
			Process p = Runtime.getRuntime().exec("cd C:\\sendkeys");
			Process p1 = Runtime.getRuntime().exec("WinSendKeyKeys force.com Send('!s')");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception {
		String[][] tabArray = null;

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName);
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();

		Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);

		endRow = tableEnd.getRow();
		endCol = tableEnd.getColumn();
		System.out.println(
				"startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol + ", endCol=" + endCol);
		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;

		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}

		return (tabArray);
	}

	public void takeScreenShoot(WebDriver driver, ITestNGMethod testMethod) throws Exception {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameScreenshot = testMethod.getTestClass().getRealClass().getSimpleName() + "_"
				+ testMethod.getMethodName();
		String path = getPath(nameScreenshot);
		FileUtils.copyFile(screenshot, new File(path));
		System.out.println("The file name is " + path);
		System.out.println("<a href=" + path + " target='_blank' >" + this.getFileName(nameScreenshot) + "</a>");
		String mydestination = "\\html-report\\TestNG-report\\html";
		File directory = new File(".");
		String destinationpath = directory.getCanonicalPath() + mydestination;
		FileUtils.copyFileToDirectory(new File(path), new File(destinationpath));
		String myFileName = getFileName(nameScreenshot);
		System.out.println("MyFile Name is \t:\t " + myFileName);
		Reporter.log("<br />MyFile Name is \t:\t " + myFileName);
		Reporter.log("<br /><img src=\"" + myFileName + "\">");

	}

	/*
	 * public void takeScreenShoot(WebDriver driver, ITestNGMethod testMethod)
	 * throws Exception { try { new
	 * WebDriverWait(driver,30).until(ExpectedConditions.alertIsPresent());
	 * Alert alert = driver.switchTo().alert(); String loadaccmsg =
	 * alert.getText();
	 * System.out.println("The Class name of the Alert Pop UP box  is \t:\t"
	 * +alert.getClass());
	 * System.out.println("The ScreenShot Error message is \t:\t"+loadaccmsg);
	 * Reporter.log("<br />The ScreenShot Error message is \t:\t"+loadaccmsg);
	 * alert.accept();
	 * System.out.println("Clicked the The Class name of the Alert Pop UP box");
	 * Reporter.log("<br />Clicked the The Class name of the Alert Pop UP box");
	 * } catch(NoAlertPresentException ex) { WebDriver augmentedDriver = new
	 * Augmenter().augment(driver); File screenshot =
	 * ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	 * String nameScreenshot =
	 * testMethod.getTestClass().getRealClass().getSimpleName() + "_"
	 * +testMethod.getMethodName();
	 * Reporter.log("<br />The Name of ScreenShot is \t:\t"+nameScreenshot);
	 * System.out.println("The Name of ScreenShot is \t:\t"+nameScreenshot);
	 * String path = getPath(nameScreenshot); FileUtils.copyFile(screenshot, new
	 * File(path)); Reporter.log("<br />The file name is "+path);
	 * System.out.println("The file name is "+path);
	 * System.out.println("<a href=" + path + " target='_blank' >" +
	 * this.getFileName(nameScreenshot) + "</a>"); } }
	 * 
	 */

	public void takeScreenShoot(WebDriver driver) throws Exception {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameScreenshot = "MyTestcase" + 2;
		// Reporter.log("<br />The Name of ScreenShot is \t:\t"+nameScreenshot);
		// System.out.println("The Name of ScreenShot is \t:\t"+nameScreenshot);
		String path = getPath(nameScreenshot);
		FileUtils.copyFile(screenshot, new File(path));
		Reporter.log("<br />The file name is " + path);
		// Reporter.log("<br /><a href=" + path + " target='_blank' >" +
		// this.getFileName(nameScreenshot) + "</a>");
		System.out.println("The file name is " + path);
		System.out.println("<a href=" + path + " target='_blank' >" + this.getFileName(nameScreenshot) + "</a>");
	}

	private String getFileName(String nameTest) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		Date date = new Date();
		// Reporter.log("<br />The File Name of the ScreenShot is
		// \t:\t"+dateFormat.format(date) + "_" + nameTest + ".jpg");
		System.out.println(
				"The File Name of the ScreenShot is \t:\t" + dateFormat.format(date) + "_" + nameTest + ".jpg");
		return dateFormat.format(date) + "_" + nameTest + ".jpg";
	}

	private String getPath(String nameTest) throws IOException {
		File directory = new File(".");
		String newFileNamePath = directory.getCanonicalPath() + "\\target\\MyReports\\screenShots\\"
				+ getFileName(nameTest);
		Reporter.log("<br />The Failure ScreenShot File Directory Location is \t:\t" + newFileNamePath);
		System.out.println("The Failure ScreenShot File Directory Location is \t:\t" + newFileNamePath);
		return newFileNamePath;
	}

	public boolean checkVisibilityOfElementLocated(WebDriver driver, String xPath) {
		boolean b1 = false;
		try {
			b1 = new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)))
					.isDisplayed();

			// new WebDriverWait(driver,
			// 200).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
			System.out.println("The WebElement is visible to the Webpage\t:\t" + xPath);
			Reporter.log("<br />" + Calendar.getInstance().getTime()
					+ "\t:\t The WebElement is visible to the Webpage\t:\t" + xPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b1;
	}

	public void checkVisibilityOfElementLocated_ByElement(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.visibilityOf(element));
		System.out.println("The WebElement is visible to the Webpage\t:\t" + element);
		Reporter.log("<br />" + Calendar.getInstance().getTime() + "\t:\t The WebElement is visible to the Webpage\t:\t"
				+ element);
	}

	public void scrollUp(WebDriver driver) throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_HOME);
		robot.keyRelease(KeyEvent.VK_HOME);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void scrollDown(WebDriver driver) throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public boolean checkElementIsPresentorNot(WebDriver driver, String xPath) {

		Boolean status = false;

		if (driver.findElement(By.xpath(xPath)).isDisplayed()) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	public boolean isElementPresent(WebDriver driver, String xPath) {
		try {
			element = driver.findElement(By.xpath(xPath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clickHyperlink_FromTable(WebDriver driver, String xPath, String texttoVerify) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		List<WebElement> hyperlinkList = getListElement(driver, xPath);

		for (int i = 0; i <= hyperlinkList.size(); i++) {
			if (hyperlinkList.get(i).getText().equalsIgnoreCase(texttoVerify)) {
				hyperlinkList.get(i).click();
				break;
			}
		}

	}

	public void clickHyperlink(WebDriver driver, String link) {
		try {
			try {
				new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.linkText(link)));
				WebElement elementToClick = driver.findElement(By.linkText(link));
				elementToClick.click();
			} catch (StaleElementReferenceException serex) {
				new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.linkText(link)));
				System.out.println("catch serex");
				driver.findElement(By.linkText(link)).click();
			}
		} catch (Exception ex) {
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.linkText(link)));
			System.out.println("ex");
			// Thread.sleep(2000);
			WebElement elementToClick = driver.findElement(By.linkText(link));
			elementToClick.click();
		}
		Reporter.log("<br />" + Calendar.getInstance().getTime() + "\t:\t The Link \t:\t" + link + "\t:\t is clicked");
	}

	public void checkAssertionTrue(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		Assert.assertTrue(driver.findElement(By.xpath(xPath)).isDisplayed());
		System.out.println("The WebElement is displayed in the webpage\t:\t" + xPath);
		Reporter.log("<br />" + Calendar.getInstance().getTime()
				+ "\t:\t The WebElement is displayed in the webpage\t:\t" + xPath);
	}

	public void checkElementisEnabled(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		Assert.assertTrue(driver.findElement(By.xpath(xPath)).isEnabled());
		System.out.println("The WebElement is Enabled in the webpage\t:\t" + xPath);
		Reporter.log("<br />" + Calendar.getInstance().getTime() + "\t:\t The WebElement is Enabled in the webpage\t:\t"
				+ xPath);
	}

	public boolean checkisEnabled(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		Boolean status = driver.findElement(By.xpath(xPath)).isEnabled();
		Assert.assertTrue(driver.findElement(By.xpath(xPath)).isEnabled());
		System.out.println("The WebElement is Enabled in the webpage\t:\t" + xPath);
		Reporter.log("<br />" + Calendar.getInstance().getTime() + "\t:\t The WebElement is Enabled in the webpage\t:\t"
				+ xPath);
		return status;
	}

	public void checkElementisDisabled(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		Assert.assertFalse(driver.findElement(By.xpath(xPath)).isEnabled());
		System.out.println("The WebElement is disabled in the webpage\t:\t" + xPath);
		Reporter.log("<br />" + Calendar.getInstance().getTime()
				+ "\t:\t The WebElement is disabled in the webpage\t:\t" + xPath);
	}

	public String getDefaultTextFromWebElement(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		String value = driver.findElement(By.xpath(xPath)).getAttribute("placeholder").toString().trim();
		return value;
	}

	public String getTextFromWebElement(WebDriver driver, String xPath) {
		String value = "";
		try {
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

			value = driver.findElement(By.xpath(xPath)).getText().toString().trim();
			// String
			// value=driver.findElement(By.xpath(xPath)).getAttribute("value");
			System.out.println("Value = " + value);
		} catch (Exception e) {

		}
		return value;
	}

	public String getTextFromWebElement_Attr(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

		// String
		// value=driver.findElement(By.xpath(xPath)).getText().toString().trim();
		String value = driver.findElement(By.xpath(xPath)).getAttribute("value");
		return value;
	}

	public void waitUntilEleIsDisplayed(WebDriver driver, String xPath) throws InterruptedException {
		try {
			new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		} catch (StaleElementReferenceException staleex) {
			System.out.println("The exception is " + staleex);
			new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		}

	}

	public void checkAssertionEquals(WebDriver driver, String expected, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		String actual = driver.findElement(By.xpath(xPath)).getText().trim();
		Assert.assertEquals(expected, actual);
	}

	public void complete_loading(int seconds, final String xPath) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		ExpectedCondition<Boolean> conditionToCheck = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return (driver.findElement(By.id(xPath)).isDisplayed());
			}
		};
		wait.until(conditionToCheck);
	}

	public void elementwait(WebDriver driver, String xPath) throws InterruptedException {

		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		System.out.println("Checks the WebElement \t:\t" + xPath + "\t:\t is Present or not");
		element = driver.findElement(By.xpath(xPath));
		while (true) {
			if (!(element.isDisplayed() && element.isEnabled())) {
				Thread.sleep(5000);
			}
			if (element.isDisplayed() && element.isEnabled()) {
				System.out.println("The WebElement \t:\t" + xPath + "\t:\t is Present,it displayed and enabled");
				break;
			}
		}

	}

	public void expandallelements(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		System.out.println("Checks the WebElement \t:\t" + xPath + "\t:\t is Present or not");
		List<WebElement> expandall = driver.findElements(By.xpath(xPath));
		for (int i = 0; i < expandall.size(); i++) {

			System.out.println("The Value for the list of expand\t:\t" + expandall.get(i).getAttribute("data-isopen")
					+ "\t:\t The value is" + expandall.get(i).getAttribute("data-id"));
			// expandall.get(i).click();
		}

	}

	public void mouseOverElement(WebDriver driver, String xPath) throws InterruptedException {
		Actions action = new Actions(driver);
		element = driver.findElement(By.xpath(xPath));
		// action.moveToElement(element).build().perform();
		action.moveToElement(element).click();
		// action.build();
		action.perform();
		Reporter.log("<br />Performed Mouse Over on the \t:\t" + xPath + "\t\t");

	}

	public void FaultcodeDropdown1(WebDriver driver, String xPath) {
		Actions actions = new Actions(driver);
		WebElement ele1 = driver.findElement(By.xpath(xPath));
		ele1.click();
		ele1.click();
		// WebElement ele1=driver.findElement(By.xpath(xPath));
		actions.moveToElement(ele1).click();
		// actions.moveToElement(ele1).click();
		actions.moveToElement(ele1).build().perform();

		System.out.println("I clicked");
		WebElement element = driver.findElement(By.xpath(".//div[@id='selectOptions']/ul/li/span[@class='faultCode']"));
		new WebDriverWait(driver, 500).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(".//div[@id='selectOptions']/ul/li/span[@class='faultCode']")));
		WebElement elementToClick = driver.findElement(By.xpath(xPath));
		System.out.println("I clicked 2");
		// List<WebElement> openBets =
		// element.findElements(By.className("faultCode"));
		WebElement openBets = driver
				.findElement(By.xpath("(.//div[@id='selectOptions']/ul/li/span[@class='faultCode'])[2]"));
		System.out.println("I clicked 3");
		System.out.println("Iam here");
		openBets.click();

		System.out.println("finished");
	}

	public String getCSSValuesFontColor(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		String color = driver.findElement(By.xpath(xPath)).getCssValue("color");
		System.out.println("The Color of the webElement is" + color);
		String rgb[] = color.replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
		String hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])),
				toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
		System.out.println("The FontColor of the webElement is in Hexadecimal is" + hex);
		Reporter.log("<br />The FontColor of the webElement \t:\t" + xPath + "\t:\tis in Hexadecimal is\t:\t" + hex);
		return hex;
	}

	public String getCSSValuesBackgroundColor(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		String backgroundcolor = driver.findElement(By.xpath(xPath)).getCssValue("background-color");
		System.out.println("The Color of the webElement is" + backgroundcolor);
		String rgb[] = backgroundcolor.replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
		String hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])),
				toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
		System.out.println("The BackgroundColor of the webElement is in Hexadecimal is" + hex);
		Reporter.log(
				"<br />The BackgroundColor of the webElement \t:\t" + xPath + "\t:\tis in Hexadecimal is\t:\t" + hex);
		return hex;
	}

	public String getCSSValuesBorderTopColor(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		String BorderTopColor = driver.findElement(By.xpath(xPath)).getCssValue("border-top-color");
		System.out.println("The Color of the webElement is" + BorderTopColor);
		String rgb[] = BorderTopColor.replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
		String hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])),
				toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
		System.out.println("The BorderTop Color of the webElement is in Hexadecimal is" + hex);
		Reporter.log(
				"<br />The BorderTop Color of the webElement \t:\t" + xPath + "\t:\tis in Hexadecimal is\t:\t" + hex);
		return hex;
	}

	public String getCSSColor(WebDriver driver, String xPath, String csscolorvalue) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		String BorderTopColor = driver.findElement(By.xpath(xPath)).getCssValue(csscolorvalue);
		System.out.println("The Color of the webElement is\t:\t" + BorderTopColor);
		String rgb[] = BorderTopColor.replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
		String hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])),
				toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
		System.out.println("The BorderTop Color of the webElement is in Hexadecimal is" + hex);
		Reporter.log(
				"<br />The BorderTop Color of the webElement \t:\t" + xPath + "\t:\tis in Hexadecimal is\t:\t" + hex);
		return hex;
	}

	public String getCSSColor_Element(WebDriver driver, WebElement element, String csscolorvalue) {

		String BorderTopColor = element.getCssValue(csscolorvalue);
		System.out.println("The Color of the webElement is" + BorderTopColor);
		String rgb[] = BorderTopColor.replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
		String hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])),
				toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
		System.out.println("The BorderTop Color of the webElement is in Hexadecimal is" + hex);
		Reporter.log(
				"<br />The BorderTop Color of the webElement \t:\t" + element + "\t:\tis in Hexadecimal is\t:\t" + hex);
		return hex;
	}

	public String getCSSValues(WebDriver driver, String xPath, String CSSValue) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		String value = driver.findElement(By.xpath(xPath)).getCssValue(CSSValue);
		System.out.println("The CSS Value for this element \t:\t" + xPath + "\t:\t is \t:\t" + value);
		Reporter.log("<br />The CSS Value for this element \t:\t" + xPath + "\t:\t is \t:\t" + value);
		return value;
	}

	public String getCSSValues_Element(WebDriver driver, WebElement element, String CSSValue) {
		String value = element.getCssValue(CSSValue);
		System.out.println("The CSS Value for this element \t:\t" + element + "\t:\t is \t:\t" + value);
		Reporter.log("<br />The CSS Value for this element \t:\t" + element + "\t:\t is \t:\t" + value);
		return value;
	}

	public static String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}

	public String[] getTableHeaders(WebDriver driver, String xPath) {

		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

		List<WebElement> mytable = driver.findElements(By.xpath(xPath));
		int columns = mytable.size();
		String[] tableHeader = new String[columns];
		for (int i = 0; i < columns; i++) {
			tableHeader[i] = mytable.get(i).getText();

		}

		return tableHeader;
	}

	public List<WebElement> getListElement(WebDriver driver, String xPath) {
		List<WebElement> myList = null;
		try {
			new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			myList = driver.findElements(By.xpath(xPath));
		} catch (Exception e) {
			System.out.println("No Element found--------------");
		}
		// System.out.println(myList);
		return myList;
	}

	public void selectItems_From_Table(WebDriver driver, String xPath, String textToVerify)
			throws InterruptedException {
		List<WebElement> mytable = getListElement(driver, xPath);

		int rows_count = mytable.size();

		for (int i = 0; i < rows_count; i++) {
			System.out.println("The Content in each Row is \t:\t" + mytable.get(i).getText());
			Reporter.log("<br />The Content in each Row is \t:\t" + mytable.get(i).getText());

		}

		for (int i = 0; i < rows_count; i++) {
			if (mytable.get(i).getText().trim().equalsIgnoreCase(textToVerify)) {
				System.out.println("Going to Select the Alert Type is \t:\t" + mytable.get(i).getText());
				Reporter.log("<br />Going to Select the Alert Type is \t:\t" + mytable.get(i).getText());
				// highlightElement_WebElement(driver,mytable.get(i));
				mytable.get(i).click();
				String sub_section_header = ".//div[@class='sel_subHeader_txt]";
				getTextFromWebElement(driver, sub_section_header);
				System.out.println("The sub Header part of the selected alert type is \t:\t"
						+ getTextFromWebElement(driver, sub_section_header));
				Reporter.log("<br />The sub Header part of the selected alert type is \t:\t"
						+ getTextFromWebElement(driver, sub_section_header));
				break;
			}
		}

		int returnedAlertTypeNumber = getAlertType(textToVerify);
		mytable.get(returnedAlertTypeNumber).click();

		Thread.sleep(10000);
		String sub_section_header = ".//div[@class='sel_subHeader_txt]";
		getTextFromWebElement(driver, sub_section_header);
		System.out.println("The sub Header part of the selected alert type is \t:\t"
				+ getTextFromWebElement(driver, sub_section_header));
		Reporter.log("<br />The sub Header part of the selected alert type is \t:\t"
				+ getTextFromWebElement(driver, sub_section_header));

	}

	public void selectContainer_FromTable(WebDriver driver, String xPath, String textToVerify)
			throws InterruptedException {

		String expectedValue = textToVerify.toLowerCase().trim();
		expectedValue = StringEscapeUtils.escapeHtml4(expectedValue);
		String actualValue = null;

		List<WebElement> mycontainers_text = getListElement(driver, xPath);

		int mycontainers_count = mycontainers_text.size();

		/*
		 * for(int i=0;i<mycontainers_count;i++) {
		 * System.out.println("The Text in that Section \t:\t"+mycontainers_text
		 * .get(i).getText());
		 * Reporter.log("<br />The Text in that Section \t:\t"+mycontainers_text
		 * .get(i).getText());
		 * 
		 * }
		 */

		for (int i = 0; i < mycontainers_count; i++) {
			actualValue = mycontainers_text.get(i).getText().toString().toLowerCase().trim();
			actualValue = StringEscapeUtils.escapeHtml4(actualValue);
			if (actualValue.equalsIgnoreCase(expectedValue)) {
				mycontainers_text.get(i).click();
				/*
				 * System.out.
				 * println("Selected container from that Section Successfully");
				 * Reporter.
				 * log("<br />Selected container from that Section Successfully"
				 * );
				 */
				break;
			}

		}

	}

	public int getCSSValue_FromContainer(WebDriver driver, String xPath) throws InterruptedException {
		int default_value = 0;
		List<WebElement> mycontainers_CSS = getListElement(driver, xPath);
		int mycontainers_CSS_count = mycontainers_CSS.size();
		for (int i = 1; i <= mycontainers_CSS_count; i++) {
			System.out.println("The Text in that Section \t:\t" + mycontainers_CSS.get(i).getCssValue("display"));
			Reporter.log("<br />The Text in that Section \t:\t" + mycontainers_CSS.get(i).getCssValue("display"));

		}
		for (int i = 1; i <= mycontainers_CSS_count; i++) {
			if (mycontainers_CSS.get(i).getCssValue("display").equalsIgnoreCase("block")) {
				default_value = i;
				System.out.println(
						"The default CSS Display value is\t:\t" + mycontainers_CSS.get(i).getCssValue("display"));
				Reporter.log(
						"<br />The default CSS Display value is\t:\t" + mycontainers_CSS.get(i).getCssValue("display"));
				break;
			}

		}
		return default_value;
	}

	public int getAlertType(String AlertTypeName) {

		int alertTypeNumber = 0;

		if (AlertTypeName == null) {
			return alertTypeNumber;
		}

		switch (AlertTypeName.toLowerCase()) {
		case "fault codes":
			alertTypeNumber = 1;

			break;
		case "geo boundary":
			alertTypeNumber = 2;
			break;
		case "engine status":
			alertTypeNumber = 3;
			break;
		case "device status":
			alertTypeNumber = 4;
			break;
		case "battery voltage threshold":
			alertTypeNumber = 5;
			break;
		case "power loss":
			alertTypeNumber = 6;
			break;
		default:
			alertTypeNumber = 1;
			break;
		}

		return alertTypeNumber;
	}

	public void Switchframe(WebDriver driver) {
		try {
			driver.wait(2000);
			driver.switchTo().frame(0);
		} catch (Exception e) {
			System.out.println("no frames found");
		}
	}

	public void getCheckbox_From_Table(WebDriver driver, String xPath) throws InterruptedException {
		List<WebElement> mytable = getListElement(driver, xPath);
		String mycheckboxxpath = ".//div[@id='alertTyperightPaneDetails']//input[@type='checkbox']";
		int rows_count = mytable.size();
		int j = 0;
		int checkboxcount = 0;
		for (int i = 0; i < rows_count; i++) {
			System.out.println("The Content in each Row is \t:\t" + mytable.get(i).getText());
			Reporter.log("<br />The Content in each Row is \t:\t" + mytable.get(i).getText());

		}

		for (int i = 0; i < rows_count; i++) {

			System.out.println("Going to Select the Alert Type is \t:\t" + mytable.get(i).getText());
			Reporter.log("<br />Going to Select the Alert Type is \t:\t" + mytable.get(i).getText());
			mytable.get(i).click();
			Thread.sleep(10000);
			List<WebElement> mycheckbox = getListElement(driver, mycheckboxxpath);

			checkboxcount = mycheckbox.size();
			System.out.println("The count of Checkboxes in this Alert Type \t:\t" + mytable.get(i).getText()
					+ "\t:\t is \t:\t" + checkboxcount);
			Reporter.log("<br />The count of Checkboxes in this Alert Type \t:\t" + mytable.get(i).getText()
					+ "\t:\t is \t:\t" + checkboxcount);
			for (j = 0; j < checkboxcount; j++) {
				System.out.println("The Value of Each checkbox in this Alert Type \t:\t" + mytable.get(i).getText()
						+ "\t:\t is \t:\t" + mycheckbox.get(j).getAttribute("value"));
				Reporter.log("<br />The Value of Each checkbox in this Alert Type \t:\t" + mytable.get(i).getText()
						+ "\t:\t is \t:\t" + mycheckbox.get(j).getAttribute("value"));
			}

			// highlightElement_WebElement(driver,mytable.get(i));

			System.out.println("++++++++++++++++++++++++");
		}
	}

	public void highlightElement(WebDriver driver, String xPath) {
		WebElement res = driver.findElement(By.xpath(xPath));

		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", res,
					"color: ; border: 4px solid red;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", res, "");
		}

	}

	public void highlightElement_WebElement(WebDriver driver, WebElement element) throws InterruptedException {
		WebElement res = element;

		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", res,
					"color: ; border: 4px solid red;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", res, "");

		}
		Reporter.log("<br />The Highligted webElement is \t:\t" + element);
		System.out.println("The Highligted webElement is \t:\t" + element);
	}

	public void ImageSizeVerification(WebDriver driver, WebElement element, int expectedheight, int expectedwidth) {
		/*
		 * List<WebElement> images=driver.findElements(By.xpath(xpath));
		 * 
		 * int numberofimages=images.size(); for(int i=0;i<numberofimages;i++) {
		 * height=images.get(i).getSize().getHeight();
		 * width=images.get(i).getSize().getWidth();
		 * System.out.println("The Height of the image is \t:\t"+height);
		 * System.out.println("The width of the image is \t:\t"+width);
		 * Assert.assertEquals(expectedheight, height);
		 * Assert.assertEquals(expectedwidth, width); }
		 */
		// height = driver.findElement(By.xpath(xpath)).getSize().getHeight();
		// width = driver.findElement(By.xpath(xpath)).getSize().getWidth();
		int height = element.getSize().getHeight();
		int width = element.getSize().getWidth();
		System.out.println("The Height of the image is \t:\t" + height);
		System.out.println("The width of the image is \t:\t" + width);
		Reporter.log("<br />The Height of the image is \t:\t" + height);
		Reporter.log("<br />The width of the image is \t:\t" + width);
		if (height == 42) {
			System.out.println("The Height of the image is reduced to 50%");
			Reporter.log("<br />The Height of the image is reduced to 50%");
		} else {
			System.out.println("The Height of the image is not reduced to 50%");
			Reporter.log("<br />The Height of the image is not reduced to 50%");
		}

		if (width == 53) {
			System.out.println("The width of the image is reduced to 50%");
			Reporter.log("<br />The width of the image is reduced to 50%");
		} else {
			System.out.println("The width of the image is not reduced to 50%");
			Reporter.log("<br />The width of the image is not reduced to 50%");
		}

		System.out.println("++++++++++++++++++++++");
		Reporter.log("<br />++++++++++++++++++++++");
	}

	public String alertisPresent_clickAccept(WebDriver driver) {
		Alert myAlert = new WebDriverWait(driver, 100).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert();
		String alertText = myAlert.getText();
		System.out.println("The Message in the Alert PopUP is \t:\t" + alertText);
		myAlert.accept();
		return alertText;

	}

	public void alertisPresent_clickDismiss(WebDriver driver) {
		Alert myAlert = new WebDriverWait(driver, 100).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert();
		myAlert.dismiss();

	}

	public String getClassname(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		element = driver.findElement(By.xpath(xPath));
		String classname = element.getAttribute("class");
		return classname;
	}

	public String getDataValueFromSelectedDataType(WebDriver driver, String xPath, String datatype) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		element = driver.findElement(By.xpath(xPath));
		String datatype_value = element.getAttribute(datatype);
		return datatype_value;
	}

	public String getToolTipText(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

		String text = driver.findElement(By.xpath(xPath)).getAttribute("title");
		return text;
	}

	public void killdriver(WebDriver driver) {
		try {
			driver.wait(2000);
		} catch (Exception e) {
		}
		driver.close();
		driver.quit();
	}

	public String xpathChecker(WebDriver driver, String xPath, String datatoVerify) {
		if (xPath.contains("**data**")) {
			xPath = xPath.replaceAll("\\*\\*data\\*\\*", datatoVerify);
			System.out.println(xPath);
		}
		return xPath;
	}

	public void getTableData(WebDriver driver, String xPath) {

		/*
		 * .//div[contains(@id,'list') or @id='operationsHistoryDay' and
		 * contains(@style,'block')]//div[@class='tel-head-light']/div[contains(
		 * 
		 * @class,'tel-th') and not(contains(@style,'none'))]
		 * .//div[contains(@id,'list') or @id='operationsHistoryDay' and
		 * contains(@style,'block')]//div[@class='tel-tbody']/div[@class='tel-
		 * tr' or contains(@style,'none')]
		 */
		String columnsxpath = null;
		String rowssxpath = null;
		String spanxpath = null;
		String absoultpath = null;
		if (driver.findElement(By.xpath(xPath)).getAttribute("id").equalsIgnoreCase("utilization-list")) {

			columnsxpath = xPath
					+ "//div[@class='tel-head-light']/div[contains(@class,'tel-th') and not(contains(@style,'none'))]";
			rowssxpath = xPath + "//div[@class='tel-tbody']/div[@class='tel-tr' or contains(@style,'none')]";
			spanxpath = rowssxpath + "//span[not(contains(@style,'none'))]";
			// ".//div[contains(@id,'list') or @id='operationsHistoryDay' and
			// contains(@style,'block')]//div[@class='tel-tbody']/div[@class='tel-tr'
			// or
			// contains(@style,'none')][4]//span[not(contains(@style,'none'))]
			List<WebElement> rowsss = getListElement(driver, rowssxpath);
			List<WebElement> spanElements = getListElement(driver, columnsxpath);
			int spanElements_count = spanElements.size();
			for (int i = 1; i <= rowsss.size(); i++) {
				for (int j = 1; j <= spanElements_count; j++) {
					String myspanxpath = rowssxpath + "[" + i + "]//span[not(contains(@style,'none'))][" + j + "]";
					System.out.println(getTextFromWebElement(driver, myspanxpath));
				}
			}

		}

		else {
			columnsxpath = xPath + "//div[@class='tel-head']//*[contains(@class,'sortable')]";
			rowssxpath = xPath + "//div[@class='tel-tbody']/div[@class='tel-tr' or contains(@style,'none')]";

			List<WebElement> columnsss = getListElement(driver, columnsxpath);
			List<WebElement> rowsss = getListElement(driver, rowssxpath);
			int rows_counts = rowsss.size();
			int columns_counts = columnsss.size();
			System.out.println("The Column Size is \t:\t" + columns_counts);
			System.out.println("The Row Size is \t:\t" + rows_counts);
			String[][] myarray = new String[rows_counts][columns_counts];

			for (int i = 1; i <= rows_counts; i++) {
				for (int j = 1; j <= columns_counts; j++) {
					int k = i - 1;
					int l = j - 1;
					absoultpath = rowssxpath + "[" + i + "]/div[contains(@class,'tel-td')][" + j + "]";
					myarray[k][l] = getTextFromWebElement(driver, absoultpath);
				}
			}
			for (int i = 0; i < rows_counts; i++) {
				for (int j = 0; j < columns_counts; j++) {
					System.out.print("\t" + myarray[i][j] + "\t|\t");
				}
				System.out.println();
			}

			for (int i = 0; i < rows_counts; i++) {
				for (int j = 0; j < columns_counts; j++) {
					// System.out.print("\t"+myarray[i][j]+"\t|\t");
					Reporter.log("The Header Value is\t:\t" + columnsss.get(j).getText()
							+ "\t:\t and the Data for this Equipment is \t:\t" + myarray[i][j]);
				}
				Reporter.log("\n");
			}

		}
	}

	public boolean verifygetTextorder(WebDriver driver, String xPath) throws Exception {
		List<String> input = new ArrayList<String>();
		// Thread.sleep(4000);
		boolean test = false;
		String firstLetter = "";
		logger.info("Verify display XPath:" + xPath);
		Properties xpathProperties = new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR_Azure.properties"));
		List<WebElement> string1 = driver.findElements(By.xpath(xPath));
		// int y=0;
		for (int z = 0; z < string1.size(); z++) {
			int k = z + 1;
			String mydata = "(" + xPath + ")[" + k + "]";
			System.out.println("The Value is \t:\t" + mydata);
			System.out.println("The Text of the value is present in this \t:\t" + z + " is \t:\t"
					+ driver.findElement(By.xpath(mydata)).getText());
			String mydata1 = driver.findElement(By.xpath(mydata)).getText();
			if (!(mydata1.equals(""))) {
				firstLetter = String.valueOf(mydata1.charAt(0));
				input.add(firstLetter);
			} else {
				System.out.println("No Data Displayed");
			}

		}
		// verifygetText(driver,xPath);
		int i = 0, j = 0;

		for (; i < input.size() - 1; i++) {
			if (input.get(i).compareToIgnoreCase(input.get(i + 1)) > 0) {
				System.out.println("The text order is not sorted in ascending order.");
				for (; j < input.size() - 1; j++) {
					if (input.get(j).compareToIgnoreCase(input.get(j + 1)) < 0) {
						System.out.println("The text order is not sorted in descending order.");
						return test;

					}
				}
				if (j == input.size() - 1) {
					System.out.println("The text order is sorted in descending order");
					test = true;
					return test;
				}
				break;
			}
		}
		if (i == input.size() - 1) {
			System.out.println("The text order is sorted in ascending order");
			test = true;
			return test;
		}

		logger.info(driver.findElement(By.xpath(xPath)).isDisplayed());
		logger.info(test);
		// return driver.findElement(By.xpath(xPath)).isDisplayed();
		return test;

	}

	public boolean verifygetTextorderfordate(WebDriver driver, String xPath) throws Exception {
		List<String> input = new ArrayList<String>();
		Thread.sleep(4000);
		boolean test = false;
		String firstLetter = "";
		logger.info("Verify display XPath:" + xPath);
		Properties xpathProperties = new Properties();
		xpathProperties.load(new FileInputStream("src\\Config\\OR.properties"));
		List<WebElement> string1 = driver.findElements(By.xpath(xPath));
		// int y=0;
		for (int z = 0; z < string1.size(); z++) {
			int k = z + 1;
			String mydata = "(" + xPath + ")[" + k + "]";
			System.out.println("The Value is \t:\t" + mydata);
			System.out.println("The Text of the value is present in this \t:\t" + z + " is \t:\t"
					+ driver.findElement(By.xpath(mydata)).getText());
			String mydata1 = driver.findElement(By.xpath(mydata)).getText().trim();
			firstLetter = String.valueOf(mydata1.charAt(9));
			input.add(firstLetter);

		}

		int i = 0, j = 0;

		for (; i < input.size() - 1; i++) {
			if (input.get(i).compareToIgnoreCase(input.get(i + 1)) > 0) {
				System.out.println("The text order is not sorted in ascending order.");
				for (; j < input.size() - 1; j++) {
					if (input.get(j).compareToIgnoreCase(input.get(j + 1)) < 0) {
						System.out.println("The text order is not sorted in descending order.");
						return test;

					}
				}
				if (j == input.size() - 1) {
					System.out.println("The text order is sorted in descending order");
					test = true;
					return test;
				}
				break;
			}
		}
		if (i == input.size() - 1) {
			System.out.println("The text order is sorted in ascending order");
			test = true;
			return test;
		}

		logger.info(driver.findElement(By.xpath(xPath)).isDisplayed());
		logger.info(test);
		// return driver.findElement(By.xpath(xPath)).isDisplayed();
		return test;

	}

	/*
	 * public void getTableData(WebDriver driver, String xPath) { String
	 * columnsxpath=xPath+
	 * "/div[@class='tel-head']//*[contains(@class,'sortable')]"; String
	 * rowssxpath=
	 * xPath+"/div[@class='tel-tbody']/div[@class='tel-tr' or contains(@style,'none')]"
	 * ; List<WebElement> columnsss=getListElement(driver, columnsxpath);
	 * List<WebElement> rowsss=getListElement(driver, rowssxpath); int
	 * rows_counts= rowsss.size(); int columns_counts= columnsss.size();
	 * String[][] myarray= new String[rows_counts][columns_counts]; for(int
	 * i=1;i<=rows_counts;i++) { for(int j=1;j<=columns_counts;j++) { int k=i-1;
	 * int l=j-1; String
	 * absoultpath=rowssxpath+"["+i+"]/div[contains(@class,'tel-td')]["+j+"]";
	 * myarray[k][l]=getTextFromWebElement(driver, absoultpath); } } for(int
	 * i=0;i<rows_counts;i++) { for(int j=0;j<columns_counts;j++) {
	 * System.out.print("\t"+myarray[i][j]+"\t|\t");
	 * Reporter.log("\t"+myarray[i][j]+"\t|\t"); } System.out.println();
	 * Reporter.log("\n"); }
	 * 
	 * }
	 */

	public void checkAssertionfalse(WebDriver driver, String xPath) {

		// new WebDriverWait(driver,
		// 100).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
		Assert.assertFalse(driver.findElement(By.xpath(xPath)).isDisplayed());
		Reporter.log(" WebElement is not present\t:\t" + xPath);
		System.out.println(" WebElement is not present\t:\t" + xPath);
	}

	public void checkInvisiblityofElement(WebDriver driver, String xPath) {
		List<WebElement> element = driver.findElements(By.xpath(xPath));
		for (int i = 0; i < element.size(); i++) {
			new WebDriverWait(driver, 100).until(ExpectedConditions.stalenessOf(element.get(i)));
			System.out.println("The element" + element.get(i).getAttribute("class"));
		}
	}

	public void checkLazyLoadingDisplay(WebDriver driver, String xPath) throws InterruptedException {
		List<WebElement> element = driver.findElements(By.xpath(xPath));
		System.out.println("The Size of the LazyLoading items\t:\t" + element.size());
		/*
		 * for(int i=1;i<=element.size();i++) { String
		 * myprogressIcon="("+xPath+")["+i+"]"; //
		 * System.out.println("The ProgessIcon\t:\t"+myprogressIcon); new
		 * WebDriverWait(driver,
		 * 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * myprogressIcon))); new WebDriverWait(driver,
		 * 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * myprogressIcon))); // System.out.println("The ProgessIcon \t:\t"
		 * +myprogressIcon+"\t:\t is displayed successfully"); }
		 */
		try {
			Thread.sleep(2000);
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xPath)));
		} catch (StaleElementReferenceException ex) {
			System.out.println("ex in the checkLazyloading");
			Thread.sleep(2000);
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xPath)));
		}

	}

	public void scrollAutomatically(WebDriver driver, String xPath1, String xPath2, String xPath3,
			int totalEquipmentcount) throws InterruptedException {
		System.out.println("The Total No of Equipment is \t: \t:" + totalEquipmentcount);
		Reporter.log("The Total No of Equipment is \t: \t:" + totalEquipmentcount);
		// int
		// height=driver.findElement(By.xpath(xPath1)).getSize().getHeight();
		/*
		 * for (int second = 0;; second++) {
		 * System.out.println(" The Second value is \t:\t"+second);
		 * element=driver.findElement(By.xpath(xPath1)); Actions actions = new
		 * Actions(driver); actions.moveToElement(element); actions.click();
		 * actions.sendKeys(Keys.PAGE_DOWN); actions.build().perform();
		 * checkLazyLoadingDisplay(driver,xPath2); Thread.sleep(5000);
		 * System.out.println("Waited for 3 seconds"); if( second >= 24) {
		 * System.out.println("Going to come out from the Loop"); break; }
		 * System.out.println("Came out from the Loop"); }
		 */
		if (totalEquipmentcount > 20) {

			double a = totalEquipmentcount / 20;
			int noofTimeLoop = (int) Math.ceil(a);
			System.out.println(Calendar.getInstance().getTime() + "\t:\tThe noofTimeLoop \t:\t " + noofTimeLoop);
			for (int i = 0; i < noofTimeLoop; i++) {
				System.out.println(Calendar.getInstance().getTime());

				/*
				 * for(int j=0;j<=1;j++) {
				 * element=driver.findElement(By.xpath(xPath1)); Actions actions
				 * = new Actions(driver); actions.moveToElement(element);
				 * actions.click(); actions.sendKeys(Keys.PAGE_DOWN);
				 * actions.build().perform(); System.out.
				 * println("The Initial Time taken after dragdown\t:\t"+Calendar
				 * .getInstance().getTime());
				 * checkLazyLoadingDisplay(driver,xPath2); System.out.
				 * println("The Actual Time taken after dragdown\t:\t"+Calendar.
				 * getInstance().getTime()); //System.out.
				 * println("I am inside the lazy loading value\t:\t"+j); }
				 */
				System.out.println("The Initial Time taken after dragdown\t:\t" + Calendar.getInstance().getTime());
				List<WebElement> com_stand_assets = driver.findElements(By.xpath(xPath3));
				int comp_stand_alone_count = com_stand_assets.size();
				System.out.println(
						"Complex Asset Count for the \t:\t" + i + "\t th time is :\t " + comp_stand_alone_count);
				if (comp_stand_alone_count < totalEquipmentcount) {
					element = driver.findElement(By.xpath(xPath1));
					Actions actions = new Actions(driver);
					actions.moveToElement(element);
					actions.click();
					actions.sendKeys(Keys.END);
					actions.build().perform();
					checkLazyLoadingDisplay(driver, xPath2);
					Thread.sleep(3000);

					System.out.println("The Actual Time taken after dragdown\t:\t" + Calendar.getInstance().getTime());
				}
				if (comp_stand_alone_count == totalEquipmentcount) {
					System.out.println("Complex Asset Count equals to the Total Equipment \t:\t" + i
							+ "\t th time is :\t " + comp_stand_alone_count);
					break;
				}
				System.out.println("-------------------------\t:\t" + Calendar.getInstance().getTime());
			}
		} else {
			checkLazyLoadingDisplay(driver, xPath2);
			Thread.sleep(2000);
		}

		System.out.println("Came out from the Loop");
	}

	public void lazyloadingdelete(WebDriver driver, String xPath) {
		List<WebElement> element = driver.findElements(By.xpath(xPath));
		for (int i = 1; i <= element.size(); i++) {
			String myprogressIcon = "(" + xPath + ")[" + i + "]";
			WebElement myelement = driver.findElement(By.xpath(myprogressIcon));
			System.out.println("The ProgessIcon\t:\t" + myprogressIcon);
			new WebDriverWait(driver, 30).until(ExpectedConditions.stalenessOf(myelement));
			System.out.println("The ProgessIcon \t:\t" + myprogressIcon + "\t:\t is deleted successfully");
		}
	}

	public String setDateFormat(String formats) {
		Date now = new Date();
		// String dateformat = new
		// SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(now);
		String dateformat = new SimpleDateFormat(formats).format(now);
		return dateformat;
	}

	public String[] mySplit(String text, String delemeter) {
		java.util.List<String> parts = new java.util.ArrayList<String>();

		text += delemeter;

		for (int i = text.indexOf(delemeter), j = 0; i != -1;) {
			String temp = text.substring(j, i);
			if (temp.trim().length() != 0) {
				parts.add(temp);
			}
			j = i + delemeter.length();
			i = text.indexOf(delemeter, j);
		}

		return parts.toArray(new String[0]);
	}

	// public void startRecording() throws Exception {
	// File directory = new File(".");
	// File files = null;
	// files = new File(directory.getCanonicalPath() + "\\Videos");
	// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	// int width = screenSize.width;
	// int height = screenSize.height;
	// Rectangle captureSize = new Rectangle(0, 0, width, height);
	// GraphicsConfiguration gc =
	// GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
	// .getDefaultConfiguration();
	// this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
	// new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
	// new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
	// ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	// CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24,
	// FrameRateKey,
	// Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
	// new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
	// FrameRateKey, Rational.valueOf(30)),
	// null, files, "Video");
	// this.screenRecorder.start();
	//
	// }

	// public void stopRecording() throws Exception {
	// this.screenRecorder.stop();
	// }

	public void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}

	public String getDatafromwebElement(WebDriver driver, String xPath) {
		String value = driver.findElement(By.xpath(xPath)).getAttribute("value");
		return value;
	}

	public String getRandomString(int length) {

		String randomStr = UUID.randomUUID().toString();
		while (randomStr.length() < length) {
			randomStr += UUID.randomUUID().toString();
		}
		return randomStr.substring(0, length);
	}

	public void StaleObjectsRemoval(WebDriver driver, String xPath) {
		WebElement elementToClick = driver.findElement(By.xpath(xPath));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (StaleElementReferenceException ex) {
			new WebDriverWait(driver, 100).until(ExpectedConditions.stalenessOf(elementToClick));
		}
		try {
			if (elementToClick.isDisplayed())
				new WebDriverWait(driver, 100).until(ExpectedConditions.stalenessOf(elementToClick));
		} catch (StaleElementReferenceException ex) {
			new WebDriverWait(driver, 100).until(ExpectedConditions.stalenessOf(elementToClick));
		}
		/*
		 * else { new WebDriverWait(driver,
		 * 100).until(ExpectedConditions.stalenessOf(elementToClick)); }
		 */
	}

	public void pressEnd(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		/*
		 * driver.findElement(By.xpath(xPath)).click();
		 * driver.findElement(By.xpath(xPath)).sendKeys(Keys.END);
		 */
		element = driver.findElement(By.xpath(xPath));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(Keys.PAGE_DOWN);
		actions.build().perform();

	}

	public boolean checkLabelPlaceHolderText(WebDriver driver, String textToVerify, String xPath) throws Exception {
		logger.info("checkLabelText XPath:" + xPath);
		try {
			logger.info("Check Label Text "
					+ driver.findElement(By.xpath(xPath)).getAttribute("placeholder").toString().trim() + "........"
					+ textToVerify);
			// Thread.sleep(2000);
			Boolean res = driver.findElement(By.xpath(xPath)).getText().equalsIgnoreCase(textToVerify);
			System.out.println(driver.findElement(By.xpath(xPath)).getAttribute("placeholder").toString().trim()
					+ "----" + textToVerify + "----"
					+ driver.findElement(By.xpath(xPath)).getAttribute("placeholder").equalsIgnoreCase(textToVerify));
			logger.info(res);
			return res;
		} catch (StaleElementReferenceException serex) {
			// Thread.sleep(2000);
			Boolean res = driver.findElement(By.xpath(xPath)).getAttribute("placeholder").toString().trim()
					.equalsIgnoreCase(textToVerify);
			logger.info(res);
			return res;
		}
	}

	public String getTitleattribute(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		// new WebDriverWait(driver,
		// 200).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
		System.out.println("The WebElement is visible to the Webpage\t:\t" + xPath);
		String value = driver.findElement(By.xpath(xPath)).getAttribute("title");
		System.out.println("The value is " + value);
		Reporter.log("<br />" + Calendar.getInstance().getTime() + "\t:\t The WebElement is visible to the Webpage\t:\t"
				+ xPath);
		return value;
	}

	public String keyword_executor(WebDriver driver, String xpathMapping, List<String> dataList) throws Exception {
		String temp_Return;
		temp_Return = "Pass";
		String[] xPaths = new String[1];
		String sourceXPath = "", targetXPath = "";
		if (xpathMapping != null) {
			xPaths = xpathMapping.split("::");
			sourceXPath = driver.findElement(By.xpath(xPaths[0])).toString();
			if (dataList.size() > 0) {
				List<String> targetDataList = new ArrayList<String>(dataList);
				if (sourceXPath != null) {
					int size = dataList.size();
					for (int i = 0; i < size; i++) {
						if (sourceXPath.contains("**data" + (i) + "**")) {
							sourceXPath = sourceXPath.replaceAll("\\*\\*data" + (i) + "\\*\\*", dataList.get(0));
							sourceXPath.trim();
							dataList.remove(0);
						} else {
							break;
						}
					}
				}
				if (xPaths.length > 1) {
					System.out.println("target data list value is" + targetDataList.get(0));
					targetXPath = driver.findElement(By.xpath(xPaths[1])).toString();
					if (targetXPath != null) {
						int size = targetDataList.size();
						for (int i = 0; i < size; i++) {
							if (targetXPath.contains("**data" + (i) + "**")) {
								targetXPath = targetXPath.replaceAll("\\*\\*data" + (i) + "\\*\\*",
										targetDataList.get(0));
								targetXPath.trim();
								targetDataList.remove(0);
							} else {
								break;
							}
						}
					}
				}
			}
		}

		return temp_Return;

	}

	public void datechecker(WebDriver driver, String IST, String UTC, String hourss) {

		/*
		 * String dateStart = "11/04/13 01:00:00 AM";
		 *
		 * 11/04/13 01:00:00 AM 02/04/2015 09:24:31 PM String dateStop =
		 * "11/04/14 02:00:00 PM";
		 */

		// Custom date format
		if (hourss.equalsIgnoreCase("12")) {
			System.out.println("12 hours format");
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a");

			Date d1 = null;
			Date d2 = null;
			try {
				d1 = format.parse(IST);
				d2 = format.parse(UTC);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Get msec from each, and subtract.
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000);
			System.out.println("Time in seconds: " + diffSeconds + " seconds.");
			System.out.println("Time in minutes: " + diffMinutes + " minutes.");
			System.out.println("Time in hours: " + diffHours + " hours.");
			if (diffHours == -5 && diffMinutes == -30) {
				Assert.assertTrue(true);
				System.out.println("As Per UTC -5:30 time variance is working fine");
				Reporter.log("<br />" + Calendar.getInstance().getTime()
						+ "\t:\t The WebElement is visible to the Webpage\t:\t" + IST + UTC);
			} else {
				Reporter.log("<br />" + Calendar.getInstance().getTime()
						+ "\t:\t The WebElement is visible to the Webpage\t:\t" + IST + UTC);
				Assert.assertFalse(true, "As Per UTC -5:30 time variance is not working fine");
				System.out.println("Fail");
			}
		} else {
			System.out.println("24 hours format");
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");

			Date d1 = null;
			Date d2 = null;
			try {
				d1 = format.parse(IST);
				d2 = format.parse(UTC);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Get msec from each, and subtract.
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000);
			System.out.println("Time in seconds: " + diffSeconds + " seconds.");
			System.out.println("Time in minutes: " + diffMinutes + " minutes.");
			System.out.println("Time in hours: " + diffHours + " hours.");
			if (diffHours == -5 && diffMinutes == -30) {
				Assert.assertTrue(true);
				System.out.println("As Per UTC -5:30 time variance is working fine");
				Reporter.log("<br />" + Calendar.getInstance().getTime()
						+ "\t:\t The WebElement is visible to the Webpage\t:\t" + IST + UTC);
			} else {
				Reporter.log("<br />" + Calendar.getInstance().getTime()
						+ "\t:\t The WebElement is visible to the Webpage\t:\t" + IST + UTC);
				Assert.assertFalse(true, "As Per UTC -5:30 time variance is not working fine");
				System.out.println("Fail");
			}
		}

	}

	public long TimeDifference(WebDriver driver, Date VBenchTime, String MMS_msg) throws Exception {

		// HH converts hour in 24 hours format (0-23), day calculation

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		long diffMinutes = 0;

		String Vbench = format1.format(VBenchTime);
		System.out.println(Vbench);

		String datetime = MMS_msg;
		String[] parts = datetime.split("T");
		String part1 = parts[0];
		String part2 = parts[1];
		String[] parts1 = part2.split("Z");
		String part3 = parts1[0];
		String part4 = part1 + " " + part3;
		String part8 = part4.replaceAll("-", "/");
		d2 = format.parse(part8);
		// String dateFormatUIfromMMS=format1.format(d1);
		// System.out.println("the MMS date as per UI view " +
		// dateFormatUIfromMMS);

		try {
			d1 = format1.parse(Vbench);
			d2 = format1.parse(part8);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return diffMinutes;

	}

	public String SuiteTimeDifference(Date OnStart, Date onFinish) throws Exception {

		// HH converts hour in 24 hours format (0-23), day calculation

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		long diffMinutes = 0;

		String OnStart_str = format1.format(OnStart);
		System.out.println(OnStart_str);
		String onFinish_str = format1.format(onFinish);

		try {
			d1 = format1.parse(OnStart_str);
			d2 = format1.parse(onFinish_str);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.println("The Total Time Taken is: ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return diffMinutes + "";

	}

	public Date MMSTime_Split(WebDriver driver, String MMS_msg) throws Exception {

		// HH converts hour in 24 hours format (0-23), day calculation

		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;

		// Spliiting the MMS Time
		String datetime = MMS_msg;
		String[] parts = datetime.split("T");
		String part1 = parts[0];
		String part2 = parts[1];
		String[] parts1 = part2.split("Z");
		String part3 = parts1[0];
		String part4 = part1 + " " + part3;
		String part8 = part4.replaceAll("-", "/");
		d1 = format.parse(part8);
		// d1 = (Date)format1.parse(part8);

		try {

			d1 = format.parse(part8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return d1;

	}

	public String Timecustomization(Date today) {
		today = new Date();

		System.out.println(today);

		// If you print Date, you will get un formatted output
		System.out.println("Today is : " + today);

		// formatting date in Java using SimpleDateFormat
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		String date = DATE_FORMAT.format(today);
		System.out.println("Today in dd-MM-yyyy format : " + date);

		// Another Example of formatting Date in Java using SimpleDateFormat
		DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		date = DATE_FORMAT.format(today);
		System.out.println("Today in dd/MM/yy pattern : " + date);

		return date;

	}

	public double DGBpercent(String fuelstartvalue, String fuelendvalue, String totalfuel_wo_Subs_start,
			String totalfuel_wo_Subs_end) {

		totalfuel_wo_Subs_start = totalfuel_wo_Subs_start.split(" ")[0].toString();
		totalfuel_wo_Subs_end = totalfuel_wo_Subs_end.split(" ")[0].toString();

		double totalfuelused = (Double.parseDouble(fuelendvalue)) - (Double.parseDouble(fuelstartvalue));
		double unsubstitutedfuel = (Double.parseDouble(totalfuel_wo_Subs_end))
				- (Double.parseDouble(totalfuel_wo_Subs_start));
		double DGB = (unsubstitutedfuel - totalfuelused) / (unsubstitutedfuel);

		return DGB;
	}

	public double fuelratecalc(String fuelstartvalue, String fuelendvalue, String Starthours, String Endhours) {

		double unsubstitutedfuel = (Double.parseDouble(fuelendvalue)) - (Double.parseDouble(fuelstartvalue));
		double totalfuelused = (Double.parseDouble(Endhours)) - (Double.parseDouble(Starthours));
		double fuelrate = (unsubstitutedfuel / totalfuelused);

		return fuelrate;
	}

	public double decimalplaces(Double db, String format) {
		DecimalFormat df = new DecimalFormat(format);
		db = Double.valueOf(df.format(db));
		return db;
	}

	public String ISTtoUTCconversion(String date) throws ParseException {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = f.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d1);
		cal.add(Calendar.HOUR_OF_DAY, 5);
		cal.add(Calendar.MINUTE, 30);

		date = f.format(cal.getTime());
		System.out.println(f.format(cal.getTime()));

		return date;
	}

	public String xpathReplace(WebDriver driver, String xPath, String data1, String data2) {
		if (xPath.contains("**data1**") && xPath.contains("**data2**")) {
			xPath = xPath.replaceAll("\\*\\*data1\\*\\*", data1);
			xPath = xPath.replaceAll("\\*\\*data2\\*\\*", data2);
		}
		System.out.println(xPath);
		return xPath;
	}

	public void checkVisibilityOfElementLocatedE2E(WebDriver driver, String xPath) {
		try {
			new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			// new WebDriverWait(driver,
			// 200).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
			System.out.println("The WebElement is visible to the Webpage\t:\t" + xPath);
			Reporter.log("<br />" + Calendar.getInstance().getTime()
					+ "\t:\t The WebElement is visible to the Webpage\t:\t" + xPath);
		} catch (NoSuchElementException ex) {
			System.out.println("element not found");
		}
	}

	public boolean Exists(WebDriver driver, String xPath) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
		boolean exists = driver.findElements(By.xpath(xPath)).size() != 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		System.out.println(xPath + " Exists in the page is ++++:" + exists);

		return exists;
	}

	public double Double(String value) {
		Double db = null;
		try {
			db = Double.parseDouble(value);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(value + " : NAN");
			db = null;

		}

		return db;

	}

	public String DateCheck(String MMS_msg, String target) throws Exception {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat targetformat = new SimpleDateFormat(target);

		MMS_msg = MMS_msg.replaceFirst("T", " ");
		MMS_msg = MMS_msg.replaceFirst("Z", " ");
		MMS_msg = MMS_msg.trim();
		System.out.println(MMS_msg);

		Date finalDate = df.parse(MMS_msg);
		MMS_msg = targetformat.format(finalDate);

		System.out.println(MMS_msg);

		return MMS_msg;

	}

	public void scrollHorizontally(WebDriver driver, String xpath) throws Exception {
		try {

			TimeUnit.SECONDS.sleep(5);
			JavascriptExecutor scroll = (JavascriptExecutor) driver;
			scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(xpath)));
			System.out.println("Page scrolled");
		} catch (Exception e) {
			Reporter.log("Error : " + e);
			System.out.println("Error : " + e);
			Assert.assertFalse(true);
		}
	}

	// Sathya
	// Used in HTML Reporter file
	public WebDriver getDriverInstance() throws IOException, InterruptedException {

		return driver;
	}

	public boolean getlistoftextindiv(WebDriver driver, String xPath, String xPath1) {
		String result[] = mySplit(xPath1, ",");
		List<WebElement> openBets = driver.findElements(By.xpath(xPath));
		int size = openBets.size();
		System.out.println("fetching the size of list");

		int count = 0;
		boolean value = false;
		for (WebElement we : openBets) {
			for (int i = 0; i < result.length; i++) {
				if (we.getText().toLowerCase().equalsIgnoreCase(result[i].toLowerCase())) {
					String text = we.getText();
					count++;
					System.out.println("the text is : " + text);
				}

			}
		}
		if (size == result.length) {
			System.out.println("matched");
			value = true;
		} else {
			System.out.println("Doesn't match the expectation, we have a problem. in size");
		}

		if (count == result.length) {
			System.out.println("matched");
			value = true;
		} else {
			System.out.println("Doesn't match the expectation, we have a problem. in value(not matched as per excel");
		}

		return value;

	}

	public boolean getlistoftextindiv1(WebDriver driver, String xPath, String xPath1, String xPath2) {
		String result[] = mySplit(xPath1, ",");
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> arrayList1 = new ArrayList<String>();
		ArrayList<String> arrayList2 = new ArrayList<String>();

		List<WebElement> openBets = driver.findElements(By.xpath(xPath));
		int size = openBets.size();
		System.out.println("fetching the size of list");

		for (int i = 0; i < result.length; i++) {
			arrayList1.add(result[i]);

		}

		for (WebElement addele : openBets) {
			String text = addele.getText();
			arrayList2.add(text);
		}

		int count = 0;
		boolean value = false;
		for (WebElement we : openBets) {
			for (int i = 0; i < result.length; i++) {
				// arrayList2.add(result[i]);
				if (we.getText().toLowerCase().equalsIgnoreCase(result[i].toLowerCase())) {
					String text = we.getText();
					count++;
					arrayList.add(result[i]);
					System.out.println("the subscription is in both Excel and UI : " + text);
				}
			}
		}
		if (size == result.length) {
			System.out.println("matched");
			value = true;
		} else {
			System.out.println("Doesn't match the expectation, we have a problem. in size");
		}

		if (count == result.length) {
			System.out.println("matched");
			value = true;
		} else {
			System.out.println("Doesn't match the expectation, we have a problem. in value");
		}
		System.out.println("The mismatch of sequence in asset : " + xPath2);

		// The comparision of values with excel from DSP UI
		if (arrayList1.size() >= arrayList.size()) {
			for (int i = 0; i < arrayList1.size(); i++) {

				if (!(arrayList.contains(arrayList1.get(i)))) {

					System.out.println("The Values were not found from excel : " + arrayList1.get(i));
					// System.out.println("..." + arrayList1.get(i) + "..." +
					// arrayList.get(i));

				}
			}
		}

		else {
			System.out.println("The values are matching perfectly ");
		}

		// The comparision of values with UI from Excel
		if (arrayList2.size() >= arrayList1.size()) {
			for (int i = 0; i < arrayList2.size(); i++) {

				if (!(arrayList1.contains(arrayList2.get(i)))) {

					System.out.println("The Values were not found from UI : " + arrayList2.get(i));
					// System.out.println("..." + arrayList1.get(i) + "..." +
					// arrayList.get(i));

				}
			}
		}

		else {
			int count1 = 0;
			for (int i = 0; i < arrayList1.size(); i++) {

				if (!(arrayList2.contains(arrayList1.get(i)))) {
					if (arrayList2.size() <= count1) {
						break;
					}

					System.out.println("The Values were not found from UI : " + arrayList2.get(i));

					// System.out.println("..." + arrayList1.get(i) + "..." +
					// arrayList.get(i));

				}
				count1++;
			}
		}

		ArrayList<String> al3 = new ArrayList<String>();
		for (String temp : arrayList1) {

			al3.add(arrayList.contains(temp) ? "Yes" : "No");
		}
		System.out.println(al3);

		// Storing the comparison output in ArrayList<Integer>
		ArrayList<Integer> al4 = new ArrayList<Integer>();
		for (String temp2 : arrayList1)
			al4.add(arrayList.contains(temp2) ? 1 : 0);
		System.out.println(al4);

		return value;

	}

	public void selecteachtextindiv(WebDriver driver, String xPath, String xPath1, String xPath2, String xPath3,
			String xPath4, String xPath5, String xPath6) throws InterruptedException {
		String result[] = mySplit(xPath1, ",");

		ArrayList<String> dealer = new ArrayList<String>();
		ArrayList<String> catess = new ArrayList<String>();

		List<WebElement> openBets = driver.findElements(By.xpath(xPath));

		System.out.println("fetching the size of list");

		int customersubcount = openBets.size();
		customersubcount = customersubcount - 1;

		// for (WebElement we : openBets) {

		for (int i = customersubcount; i > 0; i--) {
			openBets = driver.findElements(By.xpath(xPath));
			WebElement we = openBets.get(i);
			driver.findElement(By.xpath(xPath6)).click();
			System.out.println("printing the value of result[i] " + result[i]);

			if (we.getText().toLowerCase().equalsIgnoreCase(result[i].toLowerCase())) {
				String ab = we.getText();
				System.out.println(ab);
				ab = ab.concat("(Inherited)");
				openBets.get(i).click();

				Thread.sleep(2000);
				// To get all the options present in the dealer subscription
				driver.findElement(By.xpath(xPath4)).click();
				driver.findElement(By.xpath(xPath4)).getText();

				List<WebElement> dealersub = driver.findElements(By.xpath(xPath2));
				for (WebElement webElement : dealersub) {
					String text = webElement.getText().toLowerCase().toString();
					System.out.println(webElement.getText());
					dealer.add(text);
				}
				dealersub.get(0).click();
				driver.findElement(By.xpath(xPath5)).click();
				// To get all the options present in the cat subcription
				List<WebElement> cat = driver.findElements(By.xpath(xPath3));
				for (WebElement webElement : cat) {
					String text = webElement.getText().toLowerCase().toString();
					System.out.println(webElement.getText());
					catess.add(text);
				}
				cat.get(0).click();

				// comparing the both subscription
				for (int k = 0; k < dealer.size(); k++) {
					if (!dealer.get(k).equals(catess.get(k))) {
						Assert.assertFalse(true);
					}
				}
				System.out.println("dealer and cat subscription are same as expected");
				driver.findElement(By.xpath(".//div[@class='close_img']")).click();

			}
		}
		// }
	}

	public void clickbuttondropdowndiv(WebDriver driver, String xPath, String xPath1) {

		List<WebElement> openBets = driver.findElements(By.xpath(xPath));
		System.out.println("fetching the size of div");

		for (int i = 0; i < openBets.size(); i++) {
			System.out.println(openBets.get(i) + "===" + openBets.get(i).getText());
			if (openBets.get(i).getText().trim().equalsIgnoreCase(xPath1)) {
				System.out.println("Iam here");
				openBets.get(i).click();
				break;
			}
		}

	}

	public String getTextFromWebElementE2E(WebDriver driver, String xPath) throws Exception {
		String value = "null";
		try {

			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			WebElement element = driver.findElement(By.xpath(xPath));
			value = element.getAttribute("textContent").toString().trim();
			System.out.println(value);
			return value;
		} catch (NoSuchElementException ex) {
			System.out.println("element not found");
		}
		System.out.println("element  found");
		return value;
	}

	public int getSizefromElement(WebDriver driver, String xpath) {

		try {
			List<WebElement> element = driver.findElements(By.xpath(xpath));

			int size = element.size();
			return size;
		} catch (Exception e) {
			return 0;
		}

	}

	public String getAttributeFromWebElement(WebDriver driver, String xPath, String attribute) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));

		// String
		// value=driver.findElement(By.xpath(xPath)).getText().toString().trim();
		String value = driver.findElement(By.xpath(xPath)).getAttribute(attribute);
		return value;
	}

	public String MinutesAdding(WebDriver driver, String myTime, int minutes) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d = df.parse(myTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, minutes);
		String newTime = df.format(cal.getTime());
		return newTime;

	}

	public String DatesAdding(WebDriver driver, String myDate, int days) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = df.parse(myDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, days);
		String newTime = df.format(cal.getTime());
		return newTime;

	}

	public String DatesFilterAdding(WebDriver driver, String myDate, int days) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date d = df.parse(myDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, days);
		String newTime = df.format(cal.getTime());
		return newTime;

	}

	public List<String> HashSplits(List<String> keys) {
		Set<String> dateset = new HashSet<String>();

		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String keyString1[] = keys.get(i).split(" ");
			String keyString = keyString1[0];
			System.out.println(keyString);
			dateset.add(keyString);
		}

		keys.removeAll(keys);
		keys.addAll(dateset);
		Collections.sort(keys);

		return keys;
	}

	public List<String> getTextListElement(WebDriver driver, String xPath) {
		new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		List<WebElement> myList = driver.findElements(By.xpath(xPath));
		// System.out.println(myList);
		List<String> textlist = new ArrayList<String>();

		for (int i = 0; i < myList.size(); i++) {
			textlist.add(myList.get(i).getText());
		}

		return textlist;
	}

	public Boolean test_TimeConfirmation(String MMSTime, Date LowerBound, Date UpperBound) throws Throwable {

		Date dte = MMSTime_Split(driver, MMSTime);
		System.out.println(dte);

		Boolean result = null;

		if ((dte.compareTo(LowerBound) >= 0) && dte.compareTo(UpperBound) <= 0) {
			System.out.println("True");
			result = true;
		} else {
			System.out.println("false");
			result = false;
		}

		return result;

	}

	public String Timezoneconversion(String DailyReportTime, String Format, String TimezoneFrom, String TimeZoneTo)
			throws ParseException {
		SimpleDateFormat sdf_current = new SimpleDateFormat(Format);
		sdf_current.setTimeZone(TimeZone.getTimeZone(TimezoneFrom));
		// String newdate=sdf_current.format(DailyReportTime);
		Date dtes = sdf_current.parse(DailyReportTime);

		// TimeZone Times=TimeZone.getTimeZone("America/Los_Angeles");
		TimeZone TimesTo = TimeZone.getTimeZone(TimeZoneTo);

		System.out.println(TimesTo);

		// Date currenttime=Calendar.getInstance().getTime();

		SimpleDateFormat sdf = new SimpleDateFormat(Format);
		sdf.setTimeZone(TimesTo);
		String theResult = sdf.format(dtes);
		System.out.println(theResult);

		return theResult;
	}

	// public String sendGetResponse(String URL, String connectionType) throws
	// ParseException, Exception {
	//
	// System.getProperties().put("https.proxyHost", "proxy.cat.com");
	// System.getProperties().put("https.proxyPort", "80");
	//
	// config = new ClientConfig();
	// client = ClientBuilder.newClient(config);
	//
	// // client.register(new HttpBasicAuthFilter(username, password));
	//
	// target = client.target(URL);
	//
	// response = target.request().accept(connectionType).get();
	// System.out.println("response.getStatus:" + response.getStatus());
	//
	// int status = response.getStatus();
	//
	// if (status != 200) {
	// System.out.println("Failed : HTTP error code : " + response.getStatus());
	// // Reporter.log("Failed : HTTP error code : " +
	// // response.getStatus());
	// }
	// output = response.readEntity(String.class);
	// System.out.println("Output from Server .... \n");
	// System.out.println(output);
	// Reporter.log(output);
	// System.out.println("HTTP status code : " + response.getStatus());
	//
	// // Paring the Response and getting only the Timezone value from here
	// // using HashMap method*************************
	// JSONParser parser = new JSONParser();
	//
	// Object obj = parser.parse(output);
	//
	// JSONObject jsonObject = (JSONObject) obj;
	// System.out.println("************Total Objects returned in the JSon array
	// are: " + jsonObject.size());
	//
	// String Zoneid = (String) jsonObject.get("timeZoneId");
	// System.out.println("**************timeZoneId: " + Zoneid);
	//
	// String Zonename = (String) jsonObject.get("timeZoneName");
	// System.out.println("**************timeZoneName: " + Zonename);
	//
	// return Zoneid;
	// }

	public Double SubtractingDouble(Double from, Double to) {
		Double difference = 0.0;
		try {
			difference = from - to;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return difference;
	}

	// HandlingXML file
	public void handleXMLData(String fuelused, String operatinghours, String Idlefuel, String IdleHours) {
		Double db_fuelused = this.Double(fuelused);
		Double db_operatinghours = this.Double(operatinghours);
		Double db_Idlefuel = this.Double(Idlefuel);
		Double db_IdleHours = this.Double(IdleHours);

		String xmlpath = "Path to XML file";

		try {
			File fXmlFile = new File(xmlpath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// Adding Required node to List
			List<String> nodeList = new ArrayList<String>();
			nodeList.add("j1939_map");
			nodeList.add("j1939_public");

			List<String> paramList = new ArrayList<String>();
			paramList.add("J1939CAT_Engine_toh_0107");
			paramList.add("TotalFuel #3_map");
			paramList.add("TotalIdleFuel #3_map");
			paramList.add("TotalIdleTime #3_map");

			String data = null;
			String mainParam = null;
			Double temp = null;
			String new_data = null;
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			for (String each_nodeList : nodeList) {
				NodeList node1 = doc.getElementsByTagName(each_nodeList);
				System.out.println("Length = " + node1.getLength());
				for (int i = 0; i <= node1.getLength(); i++) {
					Node node = node1.item(i);
					if (node instanceof Element) {
						mainParam = ((Element) node).getAttribute("param");
						for (String each_paramList : paramList) {
							temp = null;
							if (mainParam.equalsIgnoreCase("J1939CAT_Engine_toh_0107")) {
								temp = db_operatinghours;
								break;
							} else if (mainParam.equalsIgnoreCase("TotalFuel #3_map")) {
								temp = db_fuelused;
								break;
							} else if (mainParam.equalsIgnoreCase("TotalIdleFuel #3_map")) {
								temp = db_Idlefuel;
								break;
							} else if (mainParam.equalsIgnoreCase("TotalIdleTime #3_map")) {
								temp = db_IdleHours;
								break;
							}
							System.out.println("---------ParamList = " + each_paramList);
						}

						if (!(temp == null)) {

							// System.out.println("---------ParamList =
							// "+each_paramList);
							data = ((Element) node).getAttribute("value");
							System.out.println("Current value in XML = " + data);
							// temp=Integer.parseInt(data);
							NamedNodeMap attr = node.getAttributes();
							Node nodeAttr = attr.getNamedItem("value");
							// For fuel values we are adding 50 to the retrieved
							// values****************************************************************
							if (mainParam.contains("Fuel")) {
								new_data = (temp + 50) + "";
							}
							// For Hour values we are adding 12 to the retrieved
							// values***************************************************************
							else {
								new_data = (temp + 12) + "";
							}

							System.out.println("New value to be set to XML = " + new_data);
							nodeAttr.setTextContent(new_data);
						}

					}
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlpath));
			transformer.transform(source, result);
			System.out.println("XML Updated");
		} catch (Exception e) {
			Reporter.log("Error : " + e);
			System.out.println("Error : " + e);
			Assert.assertFalse(true);
		}
	}

	public String getCurrentTime() {
		String Date = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss").format(Calendar.getInstance().getTime());
		System.out.println("The current Date and Time is: " + Date);
		return Date;
	}

}
