package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_WebElement_Exercise_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_01_Check_IsDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		WebElement under18RadioButton = driver.findElement(By.id("under_18"));
		WebElement eduTexarea = driver.findElement(By.id("edu"));
		WebElement user5Name = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		
		CheckElementDisplay(emailTextbox, "Email textbox is Displayed", "Email textbox is not Displayed");
		emailTextbox.sendKeys("Automation Testing");
		
		CheckElementDisplay(under18RadioButton, "RadioButton Under 18 is Displayed", "RadioButton Under 18 is not Displayed");
		under18RadioButton.click();
		
		CheckElementDisplay(eduTexarea, "Textarea Education is Displayed", "Textarea Education is not Displayed");
		eduTexarea.sendKeys("Automation Testing");
		
		CheckElementDisplay(user5Name, "Name User 5 is Displayed", "Name User 5 is not Displayed");
	}
	
	//@Test
	public void TC_02_Check_IsEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		WebElement under18RadioButton = driver.findElement(By.id("under_18"));
		WebElement eduTexarea = driver.findElement(By.id("edu"));
		WebElement jobroleDropdown = driver.findElement(By.id("job1"));
		WebElement interestsCheckbox = driver.findElement(By.id("job2"));
		WebElement Slider01 = driver.findElement(By.id("slider-1"));
		
		WebElement passwordlTextbox = driver.findElement(By.id("disable_password"));
		WebElement disableRadioButton = driver.findElement(By.id("radio-disabled"));
		WebElement bioTexarea = driver.findElement(By.id("bio"));
		WebElement jobrole3Dropdown = driver.findElement(By.id("job3"));
		WebElement disableCheckbox = driver.findElement(By.id("check-disbaled"));
		WebElement Slider02 = driver.findElement(By.id("slider-2"));
		
		CheckElementEnable(emailTextbox, "Email textbox is Enabled", "Email textbox is Disabled");
		CheckElementEnable(under18RadioButton, "RadioButton Under 18 is Enabled", "RadioButton Under 18 is Disabled");
		CheckElementEnable(eduTexarea, "Textarea Education is Enabled", "Textarea Education is Disabled");
		CheckElementEnable(jobroleDropdown, "Dropdown Jobrole is Enabled", "Dropdown Jobrole is Disabled");
		CheckElementEnable(interestsCheckbox, "Checkbox Interest is Enabled", "Checkbox Interest is Disabled");
		CheckElementEnable(Slider01, "Slider 01 is Enabled", "Slider 01 is Disabled");
		
		CheckElementEnable(passwordlTextbox, "Password textbox is Enabled", "Password textbox is Disabled");
		CheckElementEnable(disableRadioButton, "RadioButton Disabled is Enabled", "RadioButton Disabled is Disabled");
		CheckElementEnable(bioTexarea, "Textarea BioGraphy is Enabled", "Textarea BioGraphy is Disabled");
		CheckElementEnable(jobrole3Dropdown, "Dropdown Jobrole 3 is Enabled", "Dropdown Jobrole 3 is Disabled");
		CheckElementEnable(disableCheckbox, "Checkbox is disabled is Enabled", "Checkbox is disabled is Disabled");
		CheckElementEnable(Slider02, "Slider 02 is Enabled", "Slider 02 is Disabled");
	}
	
	//@Test
	public void TC_03_Check_IsSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		WebElement javaCheckbox = driver.findElement(By.id("java"));
		
		under18Radio.click();
		javaCheckbox.click();
		
		CheckElementSelected(under18Radio, "Under 18 is Selected", "Under 18 is de-Selected");
		CheckElementSelected(javaCheckbox, "Java Checkbox is Selected", "Java Checkbox is de-Selected");
		
		under18Radio.click();
		javaCheckbox.click();
		
		CheckElementSelected(under18Radio, "Under 18 is Selected", "Under 18 is de-Selected");
		CheckElementSelected(javaCheckbox, "Java Checkbox is Selected", "Java Checkbox is de-Selected");
	}
	
	@Test
	public void TC_04_Register_Function_At_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("tien1234@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Tien Le");
		
		WebElement passTextbox = driver.findElement(By.id("new_password"));
		WebElement button = driver.findElement(By.id("create-account"));
		
		//Lowercase
		passTextbox.sendKeys("abc");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		
		//Uppercase
		passTextbox.clear();
		passTextbox.sendKeys("ABC");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		
		//Number
		passTextbox.clear();
		passTextbox.sendKeys("123");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		
		//Specialcase
		passTextbox.clear();
		passTextbox.sendKeys("&&&");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		
		//8-chars
		passTextbox.clear();
		passTextbox.sendKeys("12345678");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		
		//Combine
		passTextbox.clear();
		passTextbox.sendKeys("Abc123&&&");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
		sleepInsecond(5);
		driver.findElement(By.id("marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
		Assert.assertTrue(button.isEnabled());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInsecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CheckElementDisplay (WebElement element, String a, String b) {
		if (element.isDisplayed())
			System.out.println(a);
		else
			System.out.println(b);
	}
	
	public void CheckElementEnable (WebElement element, String a, String b) {
		if (element.isEnabled())
			System.out.println(a);
		else
			System.out.println(b);
	}
	
	public void CheckElementSelected (WebElement element, String a, String b) {
		if (element.isSelected())
			System.out.println(a);
		else
			System.out.println(b);
	}
}