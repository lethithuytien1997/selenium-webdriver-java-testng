package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_CSS_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Demo tìm chuỗi My Account dùng Parent-Tagname
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
		driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		
		// Demo tìm theo giá trị Tuyệt đối (=) và Tương đối (,)
		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Desktops ']"));
		driver.findElement(By.xpath("//span[text()='Samsung Galaxy was added to your shopping cart.']"));
		driver.findElement(By.xpath("//div[contains(@class,'page-title')]"));
		
		// Demo với Starts-with
		driver.findElement(By.xpath("//div[starts-with(@class,'page-title')]"));
		driver.findElement(By.xpath("//input[starts-with(@data-spm-anchor-id,'a2o4n.login_signup')]"));
		driver.findElement(By.xpath("//div[contains(@class,'page-title')]"));
		
		// Làm việc với Text()
		driver.findElement(By.xpath("//legend[text()='XPath Tip and Trick']"));
		driver.findElement(By.xpath("//span[text()='0']"));
		driver.findElement(By.xpath("//span[text()=' @04:45 PM ']"));
		
		// Làm việc với Contains()
		driver.findElement(By.xpath("//h5[contains(text(),'Hello')]"));
		driver.findElement(By.xpath("//h5[contains(text(),\"I'm a Hacker\")]"));
		driver.findElement(By.xpath("//h5[contains(.,'living in Viet Nam')]"));
		driver.findElement(By.xpath("//h5[contains(.,'18 years old')]"));
		
		driver.findElement(By.xpath("//h5[contains(string(),\"I'm a Hacker\")]"));
		driver.findElement(By.xpath("//h5[contains(string(),'living in Viet Nam')]"));
		driver.findElement(By.xpath("//h5[contains(string(),'18 years old')]"));
		
		// Làm việc với hàm Concat()
		driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]"));
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}