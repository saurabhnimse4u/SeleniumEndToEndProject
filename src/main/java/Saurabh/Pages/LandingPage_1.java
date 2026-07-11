package Saurabh.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Saurabh.ReuseAbstractComponent.AbstractComponent;

public class LandingPage_1 extends AbstractComponent  {

	WebDriver driver;

	public LandingPage_1(WebDriver driver) {
	    super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApp(String email, String pass) {
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		login.click();
		return new ProductCatalogue(driver);
		
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
