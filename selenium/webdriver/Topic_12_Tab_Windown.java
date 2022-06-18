package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Tab_Windown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Switch_To_Windown_Basic() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Lấy ra ID của trang hiện tại
		String homePageID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindownByID(homePageID);
		sleepInsecond(2);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInsecond(2);
		switchToWindownByTitle("Facebook – log in or sign up");
		sleepInsecond(2);
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInsecond(2);
		switchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInsecond(2);
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		closeAllWithoutHomePage(homePageID);
	}
	
	//@Test
	public void TC_02_Cambridge() {
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.cssSelector("span.cdo-login-button")).click();
		sleepInsecond(2);
		switchToWindownByTitle("Login");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//span[@data-bound-to='loginID']")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//span[@data-bound-to='password']")).getText(), "This field is required");
		
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']")).sendKeys("Automation000***");
		sleepInsecond(3);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		switchToWindownByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("header#header span.cdo-username")).getText(), "Automation FC");
	}
	
	//@Test
	public void TC_03_KYNA() {
		driver.get("https://kyna.vn/");
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='k-footer']//a[@href='https://www.facebook.com/kyna.vn']")));
		switchToWindownByTitle("Kyna.vn - Home | Facebook");
		sleepInsecond(2);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Home | Facebook");
		
		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInsecond(2);
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='k-footer']//a[@href='https://www.youtube.com/user/kynavn']")));
		sleepInsecond(3);
		switchToWindownByTitle("Kyna.vn - YouTube");
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
	}
	
	@Test
	public void TC_04_NoComerce() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul//a[text()='Add to Compare']")).click();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul//a[text()='Add to Compare']")).click();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToWindownByTitle("Products Comparison List - Magento Commerce");
		sleepInsecond(2);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		switchToWindownByTitle("Mobile");
		sleepInsecond(2);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		alert = driver.switchTo().alert();
		alert.accept();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
		
	}
	
	public void switchToWindownByTitle(String expectedTitle) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String id : allWindowns) {
			driver.switchTo().window(id);
			String currentTitle = driver.getTitle();
			if(currentTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void switchToWindownByID(String currentWindownID)
	{
		Set<String> allWindowns = driver.getWindowHandles();
		for (String id : allWindowns) {
			if(!id.equals(currentWindownID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	public boolean closeAllWithoutHomePage(String homepageID) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String id : allWindowns) {
			if(!id.equals(homepageID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(homepageID);
		if(driver.getWindowHandles().size() == 1 )
			return true;
		else
			return false;
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
}