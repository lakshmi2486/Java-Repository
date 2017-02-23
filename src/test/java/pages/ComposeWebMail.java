package pages;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import base.GenericMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ComposeWebMail extends GenericMethods {

	String parent;
	String child;

	@Given("^Login into webapp mail$")
	public void login_into_webapp_mail() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://webapp1.syntelinc.com/OWA/");

		// It will return the parent window name as a String
		parent = driver.getWindowHandle();

		// Reading input username and password from excel sheet
		String FilePath = "C:/Users/Lakshmi/Desktop/seleniumLogin.xlsx";
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh1 = wb.getSheetAt(0);

		String username = sh1.getRow(0).getCell(1).getStringCellValue();
		String password = sh1.getRow(1).getCell(1).getStringCellValue();

		System.out.println(username);

		wb.close();

		input(driver, username, "//*[@id='username']");
		input(driver, password, "//*[@id='password']");

		clickButton(driver, "//*[@id='tblMid']/tbody/tr[7]/td/table/tbody/tr[3]/td/input[1]");

	}

	@Then("^Click on the New Mail link$")
	public void click_on_the_New_Mail_link() throws Throwable {
		clickButton(driver, "//*[@id='newmsgc']/span");
	}

	@Then("^Enter the email address and save into the draft$")
	public void enter_the_email_address_and_save_into_the_draft() throws Throwable {
		// This will return the number of windows opened by Webdriver and will
		// return Set of Strings
		Set<String> s1 = driver.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			// Here we will compare if parent window is not equal to child
			// window then we will close
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
				input(driver, "Abhiram_Patil@syntelinc.com", "//*[@id='divTo']");
				clickButton(driver, "//*[@id='imgToolbarButtonIcon']");

			}
		}

	}

	@Then("^Close the compose mail child window$")
	public void close_the_compose_mail_child_window() throws Throwable {

		Thread.sleep(3000);
		driver.close();

		// once all pop up closed now switch to parent window
		driver.switchTo().window(parent);

		System.out.println("Closed the compose mail child window");

	}

	@Then("^Navigate to Draft menu and open the saved draft email$")
	public void navigate_to_Draft_menu_and_open_the_saved_draft_email() throws Throwable {

		clickButton(driver,
				"/html/body/div[13]/div[4]/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div[2]/div[2]/div/a/span/span[1]");
		// /html/body/div[23]/div[4]/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div/div/div[2]/div/div[1]/div[4]

		Thread.sleep(5000);

		// clickButton(driver, "//*[@id='divSubject']");
		// clickButton(driver,
		// "/html/body/div[23]/div[4]/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div/div/div[2]/div/div[1]/div[4]");

		// Double click
		doubleClickButton(driver,
				"/html/body/div[23]/div[4]/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div/div/div[2]/div/div[1]/div[4]");

		// action.moveToElement(driver.findElement(By
		// .xpath("/html/body/div[23]/div[4]/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[3]/div/div[3]/div[1]/div/div/div[2]/div/div[1]/div[4]")))
		// .doubleClick().build().perform();

		System.out.println("Navigate to Draft menu and open the saved draft email");
	}

}
