package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourc(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlert(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAler(WebDriver driver) {
		waitForAlert(driver).accept();
	}
	
	public void canceltAler(WebDriver driver) {
		waitForAlert(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlert(driver).getText();
	}
	
	public void senkeyAlert(WebDriver driver, String textToAlert) {
		waitForAlert(driver).sendKeys(textToAlert);
	}
	
	public void switchToWindownByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String id : allWindowns) {
			driver.switchTo().window(id);
			String currentTitle = driver.getTitle();
			if(currentTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void switchToWindownByID(WebDriver driver, String currentWindownID)
	{
		Set<String> allWindowns = driver.getWindowHandles();
		for (String id : allWindowns) {
			if(!id.equals(currentWindownID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	public boolean closeAllWithoutHomePage(WebDriver driver, String homepageID) {
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
	
	public By getXpathLocator(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getXpathLocator(locator));
	}
	
	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getXpathLocator(locator));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	
	public void senkeyToElement(WebDriver driver, String locator, String textToElement) {
		WebElement element = getElement(driver, locator);
		element.clear();
		element.sendKeys(textToElement);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locator, String itemValue) {
		Select select = new Select(getElement(driver, locator));
		select.selectByValue(itemValue);
	}
	
	public String getFirstSelectedItemText(WebDriver driver, String locator, String itemValue) {
		Select select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMutiple(WebDriver driver, String locator) {
		Select select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}
	
	public void sleepInsecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectItem (WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		getElement(driver, parentLocator).click();
		sleepInsecond(1);
		
		List<WebElement> allDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpathLocator(childLocator)));
		
		for (WebElement item : allDropdownItems) {
			String itemText = item.getText();
			System.out.println(itemText);
			
			// Duyệt qua từng giá trị, getText() từng cái, đến khi tới giá trị mong muốn thì dừng lại
			if(itemText.equals(expectedItem)) {
				JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
				jsExecute.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInsecond(1);
				
				item.click();
				sleepInsecond(1);
				break;
			}
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementCSSValue(WebDriver driver, String locator, String propertyName) {
		return getElement(driver, locator).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}
	
	public void checkToCheckboxRadio(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplay(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	
	public void switchToFrameIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void moveToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getXpathLocator(locator)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXpathLocator(locator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getXpathLocator(locator)));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getXpathLocator(locator)));
	}
}


