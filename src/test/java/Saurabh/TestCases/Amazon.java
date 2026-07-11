package Saurabh.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) {
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		WebElement menu= driver.findElement(By.xpath("//div[@class='nav-line-1-container']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menu).perform();
		
		menu.findElement(By.xpath("//a[@data-nav-role='signin']")).click();
		
	}

}
