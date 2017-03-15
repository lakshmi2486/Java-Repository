package pages;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import base.GenericMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginFinanceTest extends GenericMethods {

	JavascriptExecutor exe = (JavascriptExecutor) driver;

	@Given("^Open Edge and navigate to login page finance application$")
	public void open_Edge_and_navigate_to_login_page_finance_application() throws Throwable {
		// // Microsoft Edge Browser
		// System.setProperty("webdriver.edge.driver",
		// "C:\\MicrosoftWebDriverEdge\\MicrosoftWebDriver.exe");
		// driver = new EdgeDriver();O

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://finance.corp.syntel.in/");
	}

	@When("^I enter valid userId and valid password$")
	public void i_enter_valid_userId_and_valid_password() throws Throwable {
		try {
			// Reading input username and password from excel sheet
			String FilePath = "C:/Users/Lakshmi/Desktop/seleniumLogin.xlsx";
			FileInputStream fis = new FileInputStream(FilePath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheetAt(0);

			String username = sh1.getRow(0).getCell(1).getStringCellValue();
			String password = sh1.getRow(1).getCell(1).getStringCellValue();

			System.out.println(username);

			wb.close();

			input(driver, username, "//*[@id='userid']");
			input(driver, password, "//*[@id='pwd']");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@When("^I click signIn button$")
	public void i_click_signIn_button() throws Throwable {
		try {
			// Click on SignIn button
			if (driver.findElement(By.name("Submit")).isDisplayed()) {
				System.out.println("Peoplesoft finance logged in");

				clickButton(driver, "//*[@id='bgimg']/div/div/div[2]/input[3]");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Then("^User should be able to login successfully and navigate to home page$")
	public void user_should_be_able_to_login_successfully_and_navigate_to_home_page() throws Throwable {
		System.out.println("Finance Page Login successfully");
	}

	@Then("^User should select to Main Menu$")
	public void user_should_select_to_Main_Menu() throws Throwable {
		// Main Menu
		clickButton(driver, "html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/a");

	}

	@Then("^User should select to Employee Service page$")
	public void user_should_select_to_Employee_Service_page() throws Throwable {
		try {
			// Employee Self Services
			clickButton(driver,
					"html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/div/div[2]/div[4]/ul/li[2]/a");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Then("^User should select to Gate Pass Request$")
	public void user_should_select_to_Gate_Pass_Request() throws Throwable {
		try {
			// Gate Pass Request
			clickButton(driver,
					"html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/div/div[2]/div[4]/ul/li[2]/div[3]/div[2]/div[2]/ul/li[10]/a");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Then("^User should select to Gate Pass$")
	public void user_should_select_to_Gate_Pass() throws Throwable {
		try {// Gate Pass
			clickButton(driver, "//*[@id='fldra_SY_SPZ_GP_FDR']");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Then("^User should select to Gate Pass Request Page$")
	public void user_should_select_to_Gate_Pass_Request_Page() throws Throwable {
		try {
			// Gate Pass Request Page
			clickButton(driver, "//*[@id='crefli_SY_LOC_MAP_CMP_GBL_1']/a");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Then("^User should able to select Mumbai location$")
	public void user_should_able_to_select_Mumbai_location() throws Throwable {
		try {

			driver.switchTo().defaultContent(); // you are now outside both frames
//			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='ptifrmcontent']")));
			driver.switchTo().frame("ptifrmtgtframe");
			
			clickRadio(driver, "//*[@id='SY_SPZ_GP_WRK_SY_LOCATION_MUM']");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
