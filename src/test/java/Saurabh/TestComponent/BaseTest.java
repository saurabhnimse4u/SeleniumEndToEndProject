package Saurabh.TestComponent;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Saurabh.Pages.LandingPage_1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage_1 lp;

	public WebDriver initializeDriver() throws IOException {

		// Set up properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\Automation 2026\\AutomationFramework\\src\\main\\java\\Saurabh\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		// Logic for If browser property we use in maven command then execute 1st part {mvn -test -P Regression -D browser=edge} if not provided then 
		// execute second part so browser open in proprties file
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	
	// Logic for to get JSON format data 
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;
	}

	// ScreenShot

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);

		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	// Here before any test we launch the application
	@BeforeMethod(alwaysRun = true)
	public LandingPage_1 launchApplication() throws IOException {
		driver = initializeDriver();
		lp = new LandingPage_1(driver);
		lp.goTo();
		return lp;
	}
   // Here after any test we close the browser
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
