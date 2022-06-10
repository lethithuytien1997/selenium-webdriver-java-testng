package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Custom_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String autoITfirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Authentication_Alert_Trick() {
		// Selenium cho phép truyền Username và Password vào Url trước khi mở trình
		// duyệt
		String username = "admin";
		String pass = "admin";

		String url = "http://" + username + ":" + pass + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(url);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText()
				.contains("Congratulations! You must have the proper credentials."));
	}

	@Test
	public void TC_02_autoIT() throws IOException {
		String username = "admin";
		String pass = "admin";

		Runtime.getRuntime().exec(new String[] { autoITfirefox, username, pass });
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInsecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText()
				.contains("Congratulations! You must have the proper credentials."));
	}

	@Test
	public void TC_03_Navigate_From_Other_Page() {
		driver.get("http://the-internet.herokuapp.com/");
		
		String username = "admin";
		String pass = "admin";
		
		String basicLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		String[] linkArray = basicLink.split("//");
		
		String newLink = linkArray[0] + "//" + username + ":" + pass + "@" + linkArray[1];
		
		driver.get(newLink);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText()
				.contains("Congratulations! You must have the proper credentials."));
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