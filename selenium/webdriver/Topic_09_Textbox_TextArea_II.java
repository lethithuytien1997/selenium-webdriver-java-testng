package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Textbox_TextArea_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String employeeID, firstName, lastName, firstNameNew, lastNameNew;
	WebElement employeeIDtext, firstNametext, lastNametext;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		employeeID = "";
		firstName = "Tien";
		lastName = "Le";
		firstNameNew = "Hun";
		lastNameNew = "Kim";
	}

	@Test
	public void TC_01_Login() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");;
		driver.findElement(By.id("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='head']")).getText(), "Dashboard");
	}

	@Test
	public void TC_02_Add_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		System.out.println(employeeID);
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		
		driver.findElement(By.id("btnSave")).click();
		
		firstNametext = driver.findElement(By.id("personal_txtEmpFirstName"));
		lastNametext = driver.findElement(By.id("personal_txtEmpLastName"));
		employeeIDtext = driver.findElement(By.id("personal_txtEmployeeId"));
		
		Assert.assertFalse(firstNametext.isEnabled());
		Assert.assertFalse(lastNametext.isEnabled());
		Assert.assertFalse(employeeIDtext.isEnabled());
		
		Assert.assertEquals(firstNametext.getAttribute("value"), firstName);
		Assert.assertEquals(lastNametext.getAttribute("value"), lastName);
		Assert.assertEquals(employeeIDtext.getAttribute("value"), employeeID);
	}

	@Test
	public void TC_03_Edit_Employee() {
		driver.findElement(By.id("btnSave")).click();
		
		firstNametext.clear();
		lastNametext.clear();
		
		Assert.assertTrue(firstNametext.isEnabled());
		Assert.assertTrue(lastNametext.isEnabled());
		
		firstNametext.sendKeys(firstNameNew);
		lastNametext.sendKeys(lastNameNew);
		
		driver.findElement(By.id("btnSave")).click();
		
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), firstNameNew);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), lastNameNew);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmployeeId")).getAttribute("value"), employeeID);
		
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int RandomEmail() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public void sleepInsecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}