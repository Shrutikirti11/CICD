package Automation_Testing.TestNG_FW_Design;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Automation_Testing.TestNG_FW_Design.PageObjects.CartPage;
import Automation_Testing.TestNG_FW_Design.PageObjects.ProductCatalogue;
import Automation_Testing.TestNG_FW_Design.TestComponents.BaseTest;
import Automation_Testing.TestNG_FW_Design.TestComponents.Retry;
import junit.framework.Assert;


public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

	
		landingPage.loginApplication("shrti123@gmail.com", "Abc@134");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());

	}
	

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("Monu@gmail.com", "Monu@12345");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}

	


}
