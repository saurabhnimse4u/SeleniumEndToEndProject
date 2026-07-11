package Saurabh.StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Saurabh.Pages.CartPage;
import Saurabh.Pages.CheckoutPage;
import Saurabh.Pages.ConfirmationPage;
import Saurabh.Pages.LandingPage_1;
import Saurabh.Pages.ProductCatalogue;
import Saurabh.TestComponent.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BaseTest {
	
	Actions a = new Actions(driver);
	public LandingPage_1 lp;
	public ProductCatalogue pc;
	public CartPage cp;
	public ConfirmationPage confirm;
	
	
	
	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page()  throws IOException {
		lp= launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void login_username_password(String username, String password) {
		
		pc = lp.loginApp(username, password);
	}
	
	@When("^I add the prodcut (.+) to cart$")
	public void I_add_the_prodcut(String productName) throws InterruptedException {
		List<WebElement> products = pc.getProductList(); // Get all productlist
		a.scrollByAmount(0, 300).perform();
		pc.addProductToCart(productName);
		a.scrollByAmount(0, -300).perform();
	}
	
	@When("^Checkout (.+) and submit$")
	public void Checkoutandsubmit(String productName)
	{
		cp = pc.goToCartPage();
		Boolean match = cp.verifyProductDisplay(productName);
		System.out.println(match);
		Assert.assertTrue(match);
		a.scrollByAmount(0, 500).perform();
		CheckoutPage checkout = cp.goToCheckOut();
		checkout.selectCountry("india");
		 confirm = checkout.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message(String string) {
		
		String confirmMessage =  confirm.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
}
