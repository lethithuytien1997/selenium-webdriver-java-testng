package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	// @Test
	public void TC_01_FindElement() {
		// - Có duy nhất 1 Element
		// Nếu Element xuất hiện ngay thì trả về Element đó mà không cần chờ hết timeout
		// 10s
		// Nếu Element chưa xuất hiện thì sau mỗi 0.5s sẽ tìm lại cho đến khi hết
		// timeout 10s
		System.out.println("Start time = " + getCurrentDateTime());
		driver.findElement(By.xpath("//input[@name='firstname']"));
		System.out.println("End time = " + getCurrentDateTime());

		// - Không tìm thấy Element nào hết
		// Tìm đi tìm lại cho đến khi hết timeout
		// Sau khi hết timeout thì đánh fail cả testcase này
		// Không chạy các bước còn lại
		System.out.println("Start time = " + getCurrentDateTime());
		driver.findElement(By.xpath("//input[@name='autoname']"));
		System.out.println("End time = " + getCurrentDateTime());

		// - Có nhiều hơn 1 Element
		// Trả về Element đầu tiên tìm được
		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a")).click();
	}

	//@Test
	public void TC_02_FindElements() {
		// - Có duy nhất 1 Element
		// - Có nhiều hơn 1 Element
		// Nếu Element xuất hiện ngay thì trả về Element đó mà không cần chờ hết timeout 10s
		// Nếu Element chưa xuất hiện thì sau mỗi 0.5s sẽ tìm lại cho đến khi hết timeout 10s
		int element = 0;
		
		element = driver.findElements(By.xpath("//input[@id='email']")).size();
		System.out.println("Tìm 1 element: " + element);
		
		element = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a")).size();
		System.out.println("Tìm nhiều element: " + element);
		
		// - Không tìm thấy Element nào hết
		//Sẽ tìm đi tìm lại cho đến khi hết timeout
		//Không đánh fail testcase
		//Chạy tiếp các bước còn lại
		System.out.println("Start time = " + getCurrentDateTime());
		element = driver.findElements(By.xpath("//input[@name='autoname']")).size();
		System.out.println("Không tìm thấy element: " + element);
		System.out.println("End time = " + getCurrentDateTime());
	}

	@Test
	public void TC_03_ImplicitWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
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

	public String getCurrentDateTime() {
		Date date = new Date();
		return date.toString();
	}
}