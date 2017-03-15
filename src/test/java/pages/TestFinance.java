package pages;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestFinance {

	WebDriver driver = null;

	@BeforeTest
	public void SetBrowser() {

		// Firefox
		// System.setProperty("webdriver.gecko.driver",
		// "C:\\geckodriver-v0.13.0-win64\\geckodriver.exe");
		// driver = new FirefoxDriver();
		// driver.get("https://finance.corp.syntel.in/");
		// driver.manage().window().maximize();

		// driver.get("https://www.facebook.com/");
		// driver.manage().window().maximize();
		// Thread.sleep(3000);
		// Select mnt = new Select(driver.findElement(By.id("month")));
		// mnt.selectByVisibleText("Apr");
		// Thread.sleep(3000);
		// driver.quit();

		// Edge
//		System.setProperty("webdriver.edge.driver", "C:\\MicrosoftWebDriverEdge\\MicrosoftWebDriver.exe");
//		driver = new EdgeDriver();
//		driver.get("https://finance.corp.syntel.in/");
//		driver.manage().window().maximize();

		// Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://finance.corp.syntel.in/");
		driver.manage().window().maximize();

		// initialize IE driver
		// File ieFile = new
		// File("C:\\IEDriverServer_x64_3.0.0\\IEDriverServer.exe");
		// System.setProperty("webdriver.ie.driver", ieFile.getAbsolutePath());
		// DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
		// ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
		// "https://finance.corp.syntel.in/");
		// WebDriver driver = new InternetExplorerDriver(ieCaps);
		// driver.manage().window().maximize();

	}

	@Test
	public void testnglogin() {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 30);

			String FilePath = "C:/Users/Lakshmi/Desktop/seleniumLogin.xlsx";
			FileInputStream fis = new FileInputStream(FilePath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheetAt(0);

			String username = sh1.getRow(0).getCell(1).getStringCellValue();
			String password = sh1.getRow(1).getCell(1).getStringCellValue();

			System.out.println(username);

			wb.close();

			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
			driver.findElement(By.id("userid")).sendKeys(username);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
			driver.findElement(By.id("pwd")).sendKeys(password);

			if (driver.findElement(By.name("Submit")).isDisplayed()) {
				System.out.println("Peoplesoft finance logged in");

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
				WebElement submit = driver.findElement(By.name("Submit"));

				JavascriptExecutor exe = (JavascriptExecutor) driver;
				exe.executeScript("arguments[0].click();", submit);

				// by link text - system profile page
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My
				// System Profile")));
				// WebElement systemProfile = driver.findElement(By.linkText("My
				// System Profile"));
				// exe.executeScript("arguments[0].click();", systemProfile);

				// by absolute xpath - system profile page
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"/html/body/table[1]/tbody/tr[2]/td/table/tbody/tr/td[1]/ul/li[1]/table/tbody/tr[2]/td/div/nav/div[2]/ul/li[7]/a")));
				WebElement systemProfile = driver.findElement(By.xpath(
						"/html/body/table[1]/tbody/tr[2]/td/table/tbody/tr/td[1]/ul/li[1]/table/tbody/tr[2]/td/div/nav/div[2]/ul/li[7]/a"));
				exe.executeScript("arguments[0].click();", systemProfile);

				// Employee Self Service
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By
				// .xpath("html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/a")));
				// WebElement selfService = driver.findElement(By
				// .xpath("html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/a"));
				// exe.executeScript("arguments[0].click();", selfService);
				//
				// // Gate Pass Request
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				// "html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/div/div[2]/div[4]/ul/li[2]/a")));
				// WebElement mainMenu = driver.findElement(By.xpath(
				// "html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/div/div[2]/div[4]/ul/li[2]/a"));
				// exe.executeScript("arguments[0].click();", mainMenu);
				//
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				// "html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/div/div[2]/div[4]/ul/li[2]/div[3]/div[2]/div[2]/ul/li[10]/a")));
				// WebElement gatePassReq = driver.findElement(By.xpath(
				// "html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/div/div[2]/div[4]/ul/li[2]/div[3]/div[2]/div[2]/ul/li[10]/a"));
				// exe.executeScript("arguments[0].click();", gatePassReq);
				//
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fldra_SY_SPZ_GP_FDR']")));
				// WebElement gatePass =
				// driver.findElement(By.xpath("//*[@id='fldra_SY_SPZ_GP_FDR']"));
				// exe.executeScript("arguments[0].click();", gatePass);
				//
				// wait.until(ExpectedConditions
				// .visibilityOfElementLocated(By.xpath("//*[@id='crefli_SY_LOC_MAP_CMP_GBL_1']/a")));
				// WebElement gatePassInnerReq =
				// driver.findElement(By.xpath("//*[@id='crefli_SY_LOC_MAP_CMP_GBL_1']/a"));
				// exe.executeScript("arguments[0].click();", gatePassInnerReq);
				//
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				// "html/body/form/div[5]/table/tbody/tr/td/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div/input")));
				// WebElement location =
				// driver.findElement(By.xpath("//*[@id='SY_SPZ_GP_WRK_SY_LOCATION_MUM']"));
				// //
				// "html/body/form/div[5]/table/tbody/tr/td/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div/input"));
				// exe.executeScript("arguments[0].click();", location);

				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fldra_SY_GP_REVIEW']")));
				// WebElement viewGatePass =
				// driver.findElement(By.xpath("//*[@id='fldra_SY_GP_REVIEW']"));
				// exe.executeScript("arguments[0].click();", viewGatePass);
				//
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='crefli_SY_GP_DAILY_RV_GBL']/a")));
				// WebElement dailyPass =
				// driver.findElement(By.xpath("//*[@id='crefli_SY_GP_DAILY_RV_GBL']/a"));
				// exe.executeScript("arguments[0].click();", dailyPass);

				// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/form/div[5]/div[3]/label/span/input")));
				// if (
				// !driver.findElement(By.xpath("html/body/form/div[5]/div[3]/label/span/input")).isSelected()
				// ){
				// System.out.println("Match Case start");
				// driver.findElement(By.xpath("html/body/form/div[5]/div[3]/label/span/input")).click();
				// System.out.println("Match Case end");
				// }
				// WebElement caseSensitive =
				// exe.executeScript("arguments[0].click();", caseSensitive);

				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SY_GP_DAILY_RV_REQ_ID$op']")));
				// driver.findElement(By.id("SY_GP_DAILY_RV_REQ_ID$op")).sendKeys("between");
				// WebElement reqId =
				// exe.executeScript("arguments[0].click();", reqId);

				/**
				 * // other Navigation links
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Employee
				 * Self-Service"))); WebElement systemProfile =
				 * driver.findElement(By.linkText("Employee Self-Service"));
				 * exe.executeScript("arguments[0].click();", systemProfile);
				 * 
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ptppscappnavtbl']/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[3]/table/tbody/tr/td/table/tbody/tr[1]/td[3]/h2/a")));
				 * WebElement systemProfile1 =
				 * driver.findElement(By.xpath("//*[@id='ptppscappnavtbl']/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[3]/table/tbody/tr/td/table/tbody/tr[1]/td[3]/h2/a"));
				 * exe.executeScript("arguments[0].click();", systemProfile1);
				 * 
				 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("SOAP
				 * Deputation Request"))); WebElement systemProfile2 =
				 * driver.findElement(By.linkText("SOAP Deputation Request"));
				 * exe.executeScript("arguments[0].click();", systemProfile2);
				 */

				// by id - select dropdown
				// new
				// Select(driver.findElement(By.xpath("//*[@id='PSOPRDEFN_LANGUAGE_CD']"))).selectByVisibleText("French");

				Thread.sleep(5000);
				driver.switchTo().defaultContent(); // you are now outside both frames
				driver.switchTo().frame("ptifrmtgtframe");

				Select selectOption = new Select(driver.findElement(By.xpath(
						"html/body/form/div[5]/table/tbody/tr[1]/td/div/table/tbody/tr[6]/td[2]/table/tbody/tr[3]/td[2]/div/select")));
				selectOption.selectByValue("ARA");
				driver.switchTo().defaultContent(); // you are now outside both
													// frames

				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("option")));
				// List<WebElement> option =
				// driver.findElements(By.tagName("option"));
				// System.out.println(option.size());

				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("select")));
				// WebElement mySelectElement1 = (WebElement)
				// driver.findElement(By.xpath("//*[@id='PSOPRDEFN_LANGUAGE_CD']"));
				// int mySelectElement1 =
				// driver.findElements(By.tagName("iframe")).size();
				// System.out.println(mySelectElement1);

				// int aaa = driver.findElements(By.id("frame")).size();
				// System.out.println(aaa);
				// driver.switchTo().defaultContent();
				//
				// driver.switchTo().window(driver.getWindowHandle());
				// System.out.println("hi");
				//
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				// "html/body/form/div[5]/table/tbody/tr[1]/td/div/table/tbody/tr[6]/td[2]/table/tbody/tr[3]/td[2]/div/select")));
				// System.out.println("language");
				// WebElement select = driver.findElement(By.xpath(
				// "html/body/form/div[5]/table/tbody/tr[1]/td/div/table/tbody/tr[6]/td[2]/table/tbody/tr[3]/td[2]/div/select"));
				// System.out.println("English");
				// Select dropDown = new Select(select);
				// dropDown.getFirstSelectedOption().getText();
				// dropDown.selectByVisibleText("French");

				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='PSOPRDEFN_LANGUAGE_CD']")));
				// WebElement mySelectElement1 = (WebElement)
				// driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='PSOPRDEFN_LANGUAGE_CD']")));
				// Select dropdown1 = new Select(mySelectElement1);
				// dropdown1.selectByValue("French");

				// // by name - select dropdown
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("PSOPRDEFN_LANGUAGE_CD")));
				// WebElement mySelectElement2 =
				// driver.findElement(By.name("PSOPRDEFN_LANGUAGE_CD"));
				// Select dropdown2= new Select(mySelectElement2);
				// dropdown2.selectByValue("UK English");
				//
				// by xpath- select dropdown
				// System.out.println("language");
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='PSOPRDEFN_LANGUAGE_CD']")));
				// System.out.println("hi");
				// WebElement mySelectElement =
				// driver.findElement(By.xpath("//*[@id='PSOPRDEFN_LANGUAGE_CD']"));
				// exe.executeScript("arguments[0].click();", mySelectElement);
				// System.out.println("welcome");
				// Select dropdown = new Select(mySelectElement);
				// System.out.println("hello");
				// dropdown.selectByVisibleText("French");
				// System.out.println("world");

				// by absolute xpath with different approach
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/form/div[5]/table/tbody/tr[1]/td/div/table/tbody/tr[6]/td[2]/table/tbody/tr[3]/td[2]/div/select")));
				// System.out.println("language");
				// WebElement select =
				// driver.findElement(By.xpath("html/body/form/div[5]/table/tbody/tr[1]/td/div/table/tbody/tr[6]/td[2]/table/tbody/tr[3]/td[2]/div/select"));
				// System.out.println("English");
				// Select dropDown = new Select(select);
				// String selected =
				// dropDown.getFirstSelectedOption().getText();
				// if(selected.equals("English")){
				// System.out.println("English Selected");
				// //already selected;
				// //do stuff
				// }
				// List<WebElement> Options = dropDown.getOptions();
				// for(WebElement option:Options){
				// if(option.getText().equals("Arabic")) {
				// option.click(); //select option here;
				// }
				// }

				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pthdr2logout")));
				// driver.findElement(By.id("pthdr2logout")).click();

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
