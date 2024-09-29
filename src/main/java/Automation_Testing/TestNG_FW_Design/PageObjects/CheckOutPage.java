package Automation_Testing.TestNG_FW_Design.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	@FindBy(xpath = "//div[@class='actions']//a[contains(text(), 'Place Order')]")
	WebElement submit;

	//By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// WebElement placeOrderButton = submit;
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);
	}

}