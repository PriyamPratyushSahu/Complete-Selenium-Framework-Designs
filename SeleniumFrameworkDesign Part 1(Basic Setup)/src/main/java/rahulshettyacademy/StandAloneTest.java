package rahulshettyacademy;


import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String chromeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "ChromeDriver" + File.separator + "chromedriver.exe";
		System.out.println(chromeDriverPath);

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		System.out.println("Title: " + driver.getTitle());
		System.out.println("URL: " + driver.getCurrentUrl());

		WebElement emailTxt = driver.findElement(By.xpath("//input[@id='userEmail']"));
		WebElement passwordTxt = driver.findElement(By.xpath("//input[@id='userPassword']"));
		WebElement loginBtn = driver.findElement(By.xpath("//input[@id='login']"));
		
		//##################### LOGIN PROCESS ############################
		emailTxt.sendKeys("priyam@gmail.com");
		passwordTxt.sendKeys("Priyam123");
		loginBtn.click();		
		
		//################################# ADD PRODUCT TO CART ############################################
		//Store all products list
		List<WebElement> productsList = driver.findElements(By.cssSelector(".mb-3"));
		//System.out.println(productsList);
		String productName = "ZARA COAT 3";
		WebElement findProduct =  productsList.stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		findProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Same as above
		//findProduct.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		
		//#####################################################################################################
		
		//############################## HANDLING TOAST MESSAGE ###############################################
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		
		//This works the same, but takes much time hence results in performance issue
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//######################################################################################################
		
		//############################### CART BUTTON ##########################################################
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		//######################################################################################################
		
		
		//######################## CHECKING PRODUCT ON CART PAGE ###############################################
		List<WebElement> cartItemList = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		//System.out.println(cartItemList);
		Boolean findMatch = cartItemList.stream().anyMatch(cartItem ->
		cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(findMatch);

		//CheckOut
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		//######################################################################################################
		
		//#################################### CHECKOUT PAGE ###################################################
		
		//Enter Country to country input
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		WebElement inputCountry = driver.findElement(By.xpath(" //input[@placeholder='Select Country']"));
		inputCountry.sendKeys("India");
		
		List<WebElement> options = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		for(WebElement i: options)
		{
			//For checking purpose Thread.sleep is used
			Thread.sleep(2000);
			
			if(i.getText().equalsIgnoreCase("India")) {
				i.click();
				break;
			}
		}
		
		Assert.assertTrue(inputCountry.getAttribute("value").equalsIgnoreCase("India"));		
		
		Thread.sleep(2000);
		//Place Order button
		//driver.findElement(By.cssSelector(".action__submit")).click();
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		System.out.println("Test run successful");
		Thread.sleep(2000);
		driver.close();

	}

}
