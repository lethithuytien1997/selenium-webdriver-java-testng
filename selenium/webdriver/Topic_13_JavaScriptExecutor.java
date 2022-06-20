package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JavaScriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String email;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		email = "tienle" + RandomEmail() + "@vmail.net";
	}

	@Test
	public void TC_01_Basic_Function() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInsecond(5);

		String homepageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homepageDomain, "live.techpanda.org");

		String homepageUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homepageUrl, "http://live.techpanda.org/");

		clickToElementByJS("//a[text()='Mobile']");
		sleepInsecond(3);

		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInsecond(3);
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS("//a[text()='Customer Service']");
		sleepInsecond(3);

		String pageTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(pageTitle, "Customer Service");

		scrollToElementOnDown("//input[@id='newsletter']");
		getElement("//input[@id='newsletter']").sendKeys(email);
		getElement("//button[@title='Subscribe']").click();
		sleepInsecond(5);
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription"));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInsecond(5);
		String guruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(guruDomain, "demo.guru99.com");
	}

	@Test
	public void TC_02_HTML5_Validation_AutoFC() {
		driver.get("https://automationfc.github.io/html5/index.html");

		// Verify validation khi bỏ trống tất cả
		getElement("//input[@name='submit-btn']").click();
		Assert.assertEquals(getElementValidationMessage("//input[@name='fname']"), "Please fill out this field.");

		// Verify validation khi chỉ nhập mục Name
		getElement("//input[@name='fname']").sendKeys("Tuannnn");
		getElement("//input[@name='submit-btn']").click();
		Assert.assertEquals(getElementValidationMessage("//input[@name='pass']"), "Please fill out this field.");

		// Verify validation khi chỉ nhập mục Name và Pass
		getElement("//input[@name='fname']").sendKeys("Tuannnn");
		getElement("//input[@name='pass']").sendKeys("tuen123");
		getElement("//input[@name='submit-btn']").click();
		Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), "Please fill out this field.");

		// Verify validation khi chỉ nhập mục Name và Pass, Email sai định dạng
		getElement("//input[@name='fname']").sendKeys("Tuannnn");
		getElement("//input[@name='pass']").sendKeys("tuen123");
		getElement("//input[@name='em']").sendKeys("123!@$%^");
		getElement("//input[@name='submit-btn']").click();
		Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), "Please enter an email address.");

		// Verify validation khi chỉ nhập mục Name và Pass, Email sai định dạng
		getElement("//input[@name='fname']").sendKeys("Tuannnn");
		getElement("//input[@name='pass']").sendKeys("tuen123");
		getElement("//input[@name='em']").sendKeys("123@456");
		getElement("//input[@name='submit-btn']").click();
		Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), "Please enter an email address.");

		// Verify validation khi nhật hợp lệ, trừ mục Địa chỉ
		getElement("//input[@name='fname']").sendKeys("Tuannnn");
		getElement("//input[@name='pass']").sendKeys("tuen123");
		getElement("//input[@name='em']").sendKeys("tien@gmail.com");
		getElement("//input[@name='submit-btn']").click();
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
	}

	@Test
	public void TC_03_Validation() {
		// Trang 1
		driver.get("https://login.ubuntu.com/");
		sleepInsecond(2);
		getElement("//div[@class='actions']//button[@name='continue']").click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"), "Please fill out this field.");

		// Trang 2
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		getElement("//button[@value='Đăng ký']").click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"), "Please fill out this field.");

		// Trang 3
		driver.get("https://warranty.rode.com/");
		getElement("//button[contains(text(),'Register')]").click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");

		// Trang 4
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		clickToElementByJS("//button[contains(text(),'Tạo tài khoản mới')]");
		Assert.assertEquals(getElementValidationMessage("//input[@id='user_first_name']"), "Please fill out this field.");

		// Trang 5
		driver.get("https://www.orangehrm.com/open-source/register-to-download/?type=stable-zip");
		clickToElementByJS("//input[@name='action_submitRequest']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='Form_submitRequest_FirstName']"), "Please fill out this field.");
	}

	@Test
	public void TC_04_Create_Account() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInsecond(5);
		
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		sleepInsecond(3);
		getElement("//a[@class='button']").click();
		sleepInsecond(3);
		
		getElement("//input[@id='firstname']").sendKeys("Tien");
		getElement("//input[@id='lastname']").sendKeys("Lee");
		getElement("//input[@id='email_address']").sendKeys(email);
		getElement("//input[@id='password']").sendKeys("tien123456");
		getElement("//input[@id='confirmation']").sendKeys("tien123456");
		sleepInsecond(2);
		clickToElementByJS("//button[@title='Register']");
		sleepInsecond(5);
		
		Assert.assertEquals(getElement("//li[@class='success-msg']").getText(), "Thank you for registering with Main Website Store.");
		sleepInsecond(1);
		clickToElementByJS("//a[@title='Log Out']");
		sleepInsecond(8);
		Assert.assertEquals(driver.getTitle(), "Home page");
	}
	
	@Test
	public void TC_05_Image() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		Assert.assertTrue(isImageLoaded("//img[@alt='User Avatar 05']"));
		Assert.assertFalse(isImageLoaded("//img[@alt='broken_04']"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int RandomEmail() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public void sleepInsecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}