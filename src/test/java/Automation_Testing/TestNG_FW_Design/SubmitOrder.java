package Automation_Testing.TestNG_FW_Design;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbstractComponents.OrderPage;
import Automation_Testing.TestNG_FW_Design.PageObjects.CartPage;
import Automation_Testing.TestNG_FW_Design.PageObjects.CheckOutPage;
import Automation_Testing.TestNG_FW_Design.PageObjects.ConfirmationPage;
import Automation_Testing.TestNG_FW_Design.PageObjects.ProductCatalogue;
import Automation_Testing.TestNG_FW_Design.TestComponents.BaseTest;
import junit.framework.Assert;



	public class SubmitOrder extends BaseTest {
	
	String productName = "ZARA COAT 3";
	

@Test(dataProvider ="getData" , groups = {"Purchase"})
		public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
			
			ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), (input.get("password")));
	
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(input.get("productName"));
			CartPage cartPage = productCatalogue.goToCartPage();
	
			Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
			Assert.assertTrue(match); // validation can't move to page object files that's why assert is inside
	
			CheckOutPage checkOutPage = cartPage.checkOut();
			checkOutPage.selectCountry("India");
			ConfirmationPage confirmationPage = checkOutPage.submitOrder();
			String confirmMsg = confirmationPage.getConfirmationMsg();
	
			Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			
	
		}
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest(){
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("shruti123@gmail.com", "Abcd@1234");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}
	
	

	
	@DataProvider
	public Object[][] getData() throws IOException {
	    // Correct the file path construction
	    String filePath = System.getProperty("user.dir") + 
	    		"/src/test/java/Automation_Testing/TestNG_FW_Design/data/PurchaseOrder.json";
	    
	    List<HashMap<String, String>> data = getJsonDataToMap(filePath);
	    return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
		
	
}
	/* @DataProvider
	 * public object[][] getData()	{
	 * 	return new Object[][] {{"shruti123@gmail.com","Abcd@1234","ZARA COAT 3"},{"Monu@gmail.com","Monu@12345","ADIDAS ORIGINAL"}}; 
	 */
	/*HashMap<String, String> map = new HashMap<String, String>();
	map.put("email","shruti123@gmail.com");
	map.put("password","Abcd@1234");
	map.put("productName","ZARA COAT 3");
	
	HashMap<String, String> map1 = new HashMap<String, String>();
	map1.put("email","Monu@gmail.com");
	map1.put("password","Monu@12345");
	map1.put("productName", "ADIDAS ORIGINAL");*/