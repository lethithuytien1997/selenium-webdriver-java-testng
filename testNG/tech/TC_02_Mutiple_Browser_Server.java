package tech;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_02_Mutiple_Browser_Server {
	WebDriver driver;
	By email = By.xpath("//*[@id='email']");
	By pass = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");
	String projectPath = System.getProperty("user.dir");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();

			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();

			break;

		default:
			break;
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	@Parameters("server")
	@Test(invocationCount = 3)
	public void TC_01(String serverName) throws InterruptedException {
		String serverURL = getServerURL(serverName);
		driver.get("http://" + serverURL + "/index.php/customer/account/login/");

		driver.findElement(email).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(pass).sendKeys("111111");
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText()
				.contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title = 'Log Out']")).click();
	}

	// @Test
	public void Login() {
		System.out.println("Login Testcase");
	}

	private String getServerURL(String serverName) {
		switch (serverName) {
		case "LIVE":
			serverName = "live.techpanda.org";

			break;

		case "DEV":
			serverName = "dev.techpanda.org";

			break;

		default:
			break;
		}
		return serverName;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
