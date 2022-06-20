package webdriver;

import java.io.File;
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

public class Topic_14_Upload_Files {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String blueFileName = "blue.jpg";
	String pinkFileName = "pink.jpg";
	String redFileName = "red.jpg";
	String whiteFileName = "white.jpg";
	
	String filePath = projectPath + File.separator + "Upload_Files" + File.separator;
	
	String blueFilePath = filePath + blueFileName;
	String pinkFilePath = filePath + pinkFileName;
	String redFilePath = filePath + redFileName;
	String whiteFilePath = filePath + whiteFileName;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
		// File cần upload phải để trong Project của thư mục Dự án luôn
	
	//@Test
	public void TC_01_JQuery_Demo() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	
		By uploadButton = By.xpath("//input[@type='file']");
		driver.findElement(uploadButton).sendKeys(blueFilePath);
		driver.findElement(uploadButton).sendKeys(pinkFilePath);
		driver.findElement(uploadButton).sendKeys(redFilePath);
		driver.findElement(uploadButton).sendKeys(whiteFilePath);
		sleepInsecond(3);
		
		List<WebElement> uploadStart = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadStart) {
			button.click();
			sleepInsecond(1);
		}
		sleepInsecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + blueFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + pinkFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + redFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + whiteFileName + "']")).isDisplayed());
	}
	
	@Test
	public void TC_02_GoFiles() {
		driver.get("https://gofile.io/uploadFiles");
		
		By uploadButton = By.xpath("//input[@type='file']");
		driver.findElement(uploadButton).sendKeys(blueFilePath);
		driver.findElement(uploadButton).sendKeys(pinkFilePath);
		driver.findElement(uploadButton).sendKeys(redFilePath);
		driver.findElement(uploadButton).sendKeys(whiteFilePath);
		sleepInsecond(8);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("i.text-success")).isDisplayed());
		String uploadLink = driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href");
		driver.get(uploadLink);
		sleepInsecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + blueFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + pinkFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + redFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + whiteFileName + "']")).isDisplayed());
	}
	
	@Test
	public void TC_03_() {
		
	}
	
	@Test
	public void TC_04_() {
		
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