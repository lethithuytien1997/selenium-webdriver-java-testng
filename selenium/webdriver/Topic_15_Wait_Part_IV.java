package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_IV {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

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
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Loading_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		By loadingIcon = By.cssSelector("div#loading");

		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	// @Test
	public void TC_02_HelloWord_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		By h4 = By.xpath("//div[@id='finish']/h4");

		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(h4));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	// @Test
	public void TC_03_AJAX_Loading() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// Chờ Date Picker hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
		// Kiểm tra chưa chọn ngày
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"No Selected Dates to display.");

		// Kiểm tra phần tử có thể click chọn và click chọn
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='22']"))).click();

		// Wait cho đến khi Loading Icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		// Wait ngày 22 được chọn
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='22']")));

		// Kiểm tra khung kết quả
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"Wednesday, June 22, 2022");
	}

	@Test
	public void TC_04_Loading_File() {
		driver.get("https://gofile.io/uploadFiles");

		By uploadButton = By.xpath("//input[@type='file']");
		driver.findElement(uploadButton).sendKeys(blueFilePath + "\n" + pinkFilePath + "\n" + redFilePath + "\n" +whiteFilePath);
		
		List<WebElement> process = driver.findElements(By.cssSelector("div.progress"));
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(process));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i.text-success")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("i.text-success")).isDisplayed());
		String uploadLink = driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href");
		driver.get(uploadLink);
		sleepInsecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + blueFileName + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + pinkFileName + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + redFileName + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + whiteFileName + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
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