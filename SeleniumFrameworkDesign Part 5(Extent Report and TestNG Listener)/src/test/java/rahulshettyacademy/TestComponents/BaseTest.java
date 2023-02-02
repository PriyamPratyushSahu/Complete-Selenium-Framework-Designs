package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage_1;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage_1 Lp;

	public WebDriver initializeDriver() throws IOException {
		
		String GlobalPropertiesPath = System.getProperty("user.dir") + File.separator + "src" + 
				File.separator + "main" + File.separator + "java" + File.separator + "rahulshettyacademy" + 
				File.separator + "resources" + File.separator + "GlobalData.properties";
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
	
	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {	
		
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver testdriver) throws IOException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");  
		 Date date = new Date();
		 String dateVal = formatter.format(date);
		 String imagePath = System.getProperty("user.dir")+ File.separator + "Screenshots" + File.separator + dateVal +  File.separator +testdriver.getTitle()+ ".png";
		 
		TakesScreenshot scrShot =((TakesScreenshot)testdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File file = new File(imagePath);
		FileUtils.copyFile(SrcFile,file);
		return imagePath;
		
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
