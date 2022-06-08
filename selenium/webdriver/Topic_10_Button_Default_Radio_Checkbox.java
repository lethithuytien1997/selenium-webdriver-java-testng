package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Default_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInsecond(3);

		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		Assert.assertFalse(loginButton.isEnabled());

		driver.findElement(By.id("login_username")).sendKeys("tien@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInsecond(3);

		Assert.assertTrue(loginButton.isEnabled());
		Assert.assertEquals(loginButton.getCssValue("background-color").toLowerCase(), "rgb(201, 33, 39)");

	}

	//@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// I - Click (Chọn) - Select

		WebElement check1 = driver.findElement(By.xpath("//input[@value='Cancer']"));
		WebElement check2 = driver.findElement(By.xpath("//input[@value='Hepatitis']"));
		WebElement check3 = driver.findElement(By.xpath("//input[@value='Emphysema']"));
		WebElement rad1 = driver.findElement(By.xpath("//input[@value='I have a loose diet']"));
		WebElement rad2 = driver.findElement(By.xpath("//input[@value=\"I don't drink\"]"));
		WebElement rad3 = driver.findElement(By.xpath("//input[@value=\"I don't use caffeine\"]"));

		// 1 - Checkbox
		// Cancer
		check1.click();
		// Hepatitis
		check2.click();
		// Emphysema
		check3.click();

		// 2 - Radio
		// I have a loose diet
		rad1.click();
		// I don't drink
		rad2.click();
		// I don't use caffeine
		rad3.click();

		// Verify
		Assert.assertTrue(check1.isSelected());
		Assert.assertTrue(check2.isSelected());
		Assert.assertTrue(check3.isSelected());
		
		Assert.assertTrue(rad1.isSelected());
		Assert.assertTrue(rad2.isSelected());
		Assert.assertTrue(rad3.isSelected());

		// II - unClick (Bỏ Chọn) - de-Select

		// 1 - Checkbox
		// Cancer
		check1.click();
		// Hepatitis
		check2.click();
		// Emphysema
		check3.click();

		// 2 - Radio
		// I have a loose diet
		rad1.click();
		// I don't drink
		rad2.click();
		// I don't use caffeine
		rad3.click();

		// Verify
		Assert.assertFalse(check1.isSelected());
		Assert.assertFalse(check2.isSelected());
		Assert.assertFalse(check3.isSelected());
		
		Assert.assertTrue(rad1.isSelected());
		Assert.assertTrue(rad2.isSelected());
		Assert.assertTrue(rad3.isSelected());
	}

	//@Test
	public void TC_03_Select_All_Checkboxes() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected())
			{
				checkbox.click();
			}
			Assert.assertTrue(checkbox.isSelected());
		}
		
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.isSelected())
			{
				checkbox.click();
			}
			Assert.assertFalse(checkbox.isSelected());
		}
	}

	@Test
	public void TC_04_Checkbox_Excercise() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInsecond(5);
		
		WebElement checkbox = 	driver.findElement(By.id("eq5"));
		checkbox.click();
		Assert.assertTrue(checkbox.isSelected());
		
		checkbox.click();
		Assert.assertFalse(checkbox.isSelected());
	}
	
	@Test
	public void TC_05_Radio_Excercise() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		sleepInsecond(5);
		
		WebElement rad = 	driver.findElement(By.id("engine3"));
		if(!rad.isSelected()) {
		rad.click();
		Assert.assertTrue(rad.isSelected());
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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