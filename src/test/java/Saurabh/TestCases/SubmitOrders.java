package Saurabh.TestCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Saurabh.Pages.CartPage;
import Saurabh.Pages.CheckoutPage;
import Saurabh.Pages.ConfirmationPage;
import Saurabh.Pages.LandingPage_1;
import Saurabh.Pages.OrderPage;
import Saurabh.Pages.ProductCatalogue;
import Saurabh.ReuseAbstractComponent.AbstractComponent;
import Saurabh.TestComponent.BaseTest;

public class SubmitOrders extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		Actions a = new Actions(driver);
		ProductCatalogue pp = lp.loginApp(input.get("email"), input.get("pass")); // Enter username and password and click
																				// on login
		List<WebElement> products = pp.getProductList(); // Get all productlist
		a.scrollByAmount(0, 300).perform();
		pp.addProductToCart(input.get("product"));
		a.scrollByAmount(0, -300).perform();
		CartPage cp = pp.goToCartPage();
		Boolean match = cp.verifyProductDisplay(input.get("product"));
		System.out.println(match);
		Assert.assertTrue(match);
		a.scrollByAmount(0, 500).perform();
		CheckoutPage checkout = cp.goToCheckOut();
		checkout.selectCountry("india");
		ConfirmationPage confirm = checkout.submitOrder();
		String confirmationMessage = confirm.getConfirmationMessage();
		System.out.println(confirmationMessage);
		AssertJUnit.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

// Create new test method with dependancy attribute based on Test strategy desing
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistroy() {

		ProductCatalogue pp = lp.loginApp("saurabh.n@gmail.com", "Sau@169216");
		OrderPage op = pp.goToOrderHistory();
		Assert.assertTrue(op.verifyOrderDisplay(productName));

	}
// Using HashMap 	
//	@DataProvider
//	public Object[][] getData(){
//		HashMap<String,String> map1 = new HashMap<String, String>();
//		map1.put("email", "saurabh.n@gmail.com");
//		map1.put("pass", "Sau@169216");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map2 = new HashMap<String, String>();
//		map2.put("email", "saurabh.nimse@gmail.com");
//		map2.put("pass", "Sau@1234");
//		map2.put("product", "ZARA COAT 3");
//		return new Object[][] {{map1},{map2}};
//	}
	
	
// Using multidimentional 	
//		@DataProvider
//		public Object[][] getData(){
//			return new Object[][] {{"saurabh.n@gmail.com", "Sau@169216","ADIDAS ORIGINAL"},{"saurabh.nimse@gmail.com","Sau@1234","ZARA COAT 3"}};
//		}
	// Using HashMap 	
		@DataProvider
		public Object[][] getData() throws IOException{
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Saurabh//Data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
		// ScreenShots
		
		
}
