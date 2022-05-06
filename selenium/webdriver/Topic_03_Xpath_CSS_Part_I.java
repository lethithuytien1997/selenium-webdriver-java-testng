package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_CSS_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Register_01_Empty_Data() {
		// Mở trình duyệt
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Click vào button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Kiểm tra lổi hiển thị
		driver.findElement(By.id("txtFirstname-error")).getText();
		
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		// Mở trình duyệt
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Truyền dữ liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Le Tien");
		driver.findElement(By.id("txtEmail")).sendKeys("123@456@000");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@000");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
				
		// Click vào button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
				
		// Kiểm tra lổi hiển thị
				
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}

	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		// Mở trình duyệt
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		// Truyền dữ liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Le Tien");
		driver.findElement(By.id("txtEmail")).sendKeys("tienle@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tienle@outlook.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
						
		// Click vào button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
						
		// Kiểm tra lổi hiển thị
						
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	
	@Test
	public void Register_04_Password_Less_Than_6_Chars() {
		// Mở trình duyệt
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
					
		// Truyền dữ liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Le Tien");
		driver.findElement(By.id("txtEmail")).sendKeys("tienle@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tienle@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
								
		// Click vào button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
								
		// Kiểm tra lổi hiển thị
								
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
	}
	
	@Test
	public void Register_05_Incorrect_Confirm_Password() {
		// Mở trình duyệt
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
							
		// Truyền dữ liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Le Tien");
		driver.findElement(By.id("txtEmail")).sendKeys("tienle@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tienle@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
										
		// Click vào button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
									
		// Kiểm tra lổi hiển thị
										
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void Register_06_Invalid_PhoneNumber() {
		// Mở trình duyệt
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		// Truyền dữ liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Le Tien");
		driver.findElement(By.id("txtEmail")).sendKeys("tienle@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tienle@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("09876543");
		
		// Click vào button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// Kiểm tra lổi hiển thị
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
