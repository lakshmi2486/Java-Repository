package pages;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;

import base.GenericMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FileUploadTest extends GenericMethods {

	@Given("^Login into finance application$")
	public void login_into_finance_application() throws Throwable {
		try {

			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://finance.corp.syntel.in/");

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

			clickButton(driver, "//*[@id='bgimg']/div/div/div[2]/input[3]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^Navigate from main menu to travel expense$")
	public void navigate_from_main_menu_to_travel_expense() throws Throwable {
		try {
			// Main Menu
			clickButton(driver, "html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/a");

			// Employee Self Services
			clickButton(driver,
					"html/body/table[1]/tbody/tr[1]/td/div[2]/header/div[1]/div[1]/nav/div[1]/ul/li[3]/div/div[2]/div[4]/ul/li[2]/a");

			// Travel and Expense
			clickButton(driver, "//*[@id='crefli_EP_SC_SP_EMPLOYEE_TE_CENTER']");

			Thread.sleep(5000);
			driver.switchTo().defaultContent(); // you are now outside both
												// frames
			driver.switchTo().frame("ptifrmtgtframe");

			clickButton(driver,
					"html/body/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/table/tbody/tr/td/table/tbody/tr[1]/td[3]/h2/a");
			// "//*[@id='ptppscappnavtbl']/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/table/tbody/tr/td/table/tbody/tr[1]/td[3]/h2/a");

			clickButton(driver,
					"/html/body/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr/td[2]/a");
			// "//*[@id='ptppscappnavtbl']/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/table/tbody/tr/td/table/tbody/tr[1]/td[3]/h2/a");

			// clickButton(driver,
			// "//*[@id='ptppscappnavtbl']/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr/td[2]/a");

			Thread.sleep(3000);
			driver.switchTo().defaultContent(); // you are now outside both
												// frames
			driver.switchTo().frame("ptifrmtgtframe");

			// Search Button Click
			clickButton(driver, "//*[@id='#ICSearch']");

			// First Repot Button Click
			clickButton(driver, "/html/body/form/div[5]/div[4]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/a");

			Thread.sleep(3000);
			driver.switchTo().defaultContent(); // you are now outside both
												// frames
			driver.switchTo().frame("ptifrmtgtframe");

			clickButton(driver, "//*[@id='SY_EXP_ATTC_WRK_SY_ATTACH_LINK']");

			clickButton(driver, "//*[@id='SY_EXP_ATTACH$new$2$$0']");

			clickButton(driver, "//*[@id='SY_EXP_ATTCODE_SY_ADD_IMAGE$3']/img");
			
			Thread.sleep(5000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("ptModFrame_0");
			
			// Browse button click 
			clickButtonDirect(driver, "//*[@id='win0divPSTOOLSHIDDENS']/input");
			
			//AutoIT
			Runtime.getRuntime().exec("E:\\AutoIT\\FileUpload_x64.exe");			
			
			Thread.sleep(10000);
			
			//Upload button Click
			clickButtonDirect(driver, "//*[@id='Left']/span/input");
			
			Thread.sleep(5000);
			driver.switchTo().defaultContent(); // you are now outside both
												// frames

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^Click the attachment link to File upload$")
	public void click_the_attachment_link_to_File_upload() throws Throwable {
		System.out.println("File upload attachment link page");
	}

}
