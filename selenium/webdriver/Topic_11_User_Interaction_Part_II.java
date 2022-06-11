package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_II {
	WebDriver driver;
	Actions action;
	Alert alert;
	JavascriptExecutor jsExecute;
	String projectPath = System.getProperty("user.dir");
	String jsFileContent;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecute = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Click_And_Hold_Multiple_Items() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Store all Elements in a LIST
		List<WebElement> allNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		Assert.assertEquals(allNumbers.size(), 12);

		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(10)).release().perform();

		// Verify after Click and Hold 9 Element

		allNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allNumbers.size(), 9);

	}

	// @Test
	public void TC_02_Click_And_Hold_Radom_Items() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Store all Elements in a LIST
		List<WebElement> allNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		Assert.assertEquals(allNumbers.size(), 12);

		action.keyDown(Keys.CONTROL).click(allNumbers.get(0)).click(allNumbers.get(4)).click(allNumbers.get(7))
				.click(allNumbers.get(10)).click(allNumbers.get(11)).keyUp(Keys.CONTROL).perform();

		// Verify after Click and Hold 5 Element

		allNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allNumbers.size(), 5);
	}

	// @Test
	public void TC_03_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		jsExecute.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//button[text()='Double click me']")));

		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	// @Test
	public void TC_04_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInsecond(3);

		action.moveToElement(driver.findElement(By.xpath("//span[text()='Delete']"))).perform();
		sleepInsecond(3);

		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[@class='context-menu-item context-menu-icon context-menu-icon-delete context-menu-hover context-menu-visible']"))
				.isDisplayed());

		action.click(driver.findElement(By.xpath("//span[text()='Delete']"))).perform();
		sleepInsecond(3);

		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: delete");
		alert.accept();
		sleepInsecond(3);

		Assert.assertFalse(driver.findElement(By.xpath(
				"//li[@class='context-menu-item context-menu-icon context-menu-icon-delete context-menu-hover context-menu-visible']"))
				.isDisplayed());
	}

	//@Test
	public void TC_05_Drag_And_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();

		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getCssValue("background-color").toLowerCase(),
				"rgb(3, 169, 244)");
	}

	@Test
	public void TC_06_Drag_And_Drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		jsFileContent = getContentFile(projectPath + "\\DragAndDrop\\drag_and_drop_helper.js");

		jsFileContent = jsFileContent + "$('div#column-a').simulateDragDrop({ dropTarget: 'div#column-b'});";

		jsExecute.executeScript(jsFileContent);
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "A");

		jsExecute.executeScript(jsFileContent);
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "B");
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

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}