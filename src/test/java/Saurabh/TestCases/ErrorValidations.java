package Saurabh.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
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
import Saurabh.Pages.ProductCatalogue;
import Saurabh.ReuseAbstractComponent.AbstractComponent;
import Saurabh.TestComponent.BaseTest;
import Saurabh.TestComponent.Retry;

public class ErrorValidations extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(groups={"ErrorHandling"},retryAnalyzer =Retry.class)
	public void submitOrder() throws InterruptedException, IOException {

		lp.loginApp("saurabh.wmwn@gmail.com", "Sau@9216"); // Enter username and password and click on login	
		System.out.println(lp.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());

	}
	
	@Test(groups="ErrorHandling")
	public void ProductErrorValidation() throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		Actions a = new Actions(driver);
		ProductCatalogue pc = lp.loginApp("saurabh.nimse@gmail.com", "Sau@1234");
		List<WebElement> products = pc.getProductList();
		a.scrollByAmount(0, 300).perform();
		pc.addProductToCart(productName);
		a.scrollByAmount(0, -300).perform();
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyProductDisplay("ZARA COAT 343");
		System.out.println(match);
		Assert.assertFalse(match);
		
	}
}
