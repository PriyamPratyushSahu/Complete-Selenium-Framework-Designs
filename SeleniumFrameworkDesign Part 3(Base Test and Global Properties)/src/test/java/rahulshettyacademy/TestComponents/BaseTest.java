package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import rahulshettyacademy.pageobjects.LandingPage_1;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage_1 Lp;

	public WebDriver initializeDriver() throws IOException {
		
		String GlobalPropertiesPath = System.getProperty("user.dir") + File.separator + "src" + 
				File.separator + "main" + File.separator + "java" + File.separator + "rahulshettyacademy" + 
				File.separator + "resources" + File.separator + "GlobalData.properties";
				//C:\Users\priyamps\Downloads\Eclipse\Eclipse-Workspace\Selenium Udemy Rahul Shetty\SeleniumFrameworkDesign Part 3(Base Test and Global Properties)\src\main\java\rahulshettyacademy\resources
//		System.out.println(GlobalPropertiesPath);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(GlobalPropertiesPath);
		prop.load(fis);
		String browserName = prop.getProperty("Browser");
		
		
		switch(browserName) {
			case("Chrome"):
				String chromeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "ChromeDriver" + File.separator + "chromedriver.exe";
				System.out.println(chromeDriverPath);

				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver();
				break;
				
			default:
				System.out.println("Wrong Browser Selection.....\nCheck the GlobalData.properties");
				break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true) //To handle groups in testNg.xml
	public LandingPage_1 launchApplication() throws IOException {
		
		driver = initializeDriver();
		
		
		Lp = new LandingPage_1(driver);
		Lp.goTo("https://rahulshettyacademy.com/client");
		return Lp;
	}
	
	@AfterMethod(alwaysRun = true) //To handle groups in testNg.xml
	public void ClosingSession() {
		driver.close();
		System.out.println("Test run successful");
	}
}
