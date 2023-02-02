package rahulshettyacademy;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import rahulshettyacademy.pageobjects.CartPage_3;
import rahulshettyacademy.pageobjects.CheckOutPage_4;
import rahulshettyacademy.pageobjects.ConfirmationPage_5;
import rahulshettyacademy.pageobjects.LandingPage_1;
import rahulshettyacademy.pageobjects.ProductCataloguePage_2;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String chromeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "ChromeDriver" + File.separator + "chromedriver.exe";
		System.out.println(chromeDriverPath);

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		LandingPage_1 Lp = new LandingPage_1(driver);
		ProductCataloguePage_2 Pc;
		CartPage_3 Cp;
		CheckOutPage_4 Co;
		ConfirmationPage_5 Cop;
		
		Lp.goTo("https://rahulshettyacademy.com/client");
		Pc = Lp.logApplication("priyam@gmail.com","Priyam123");
		
		List<WebElement> productsList = Pc.getProductList();
				
		String productName = "ZARA COAT 3";
		WebElement findProduct = Pc.getProductByName(productName);
		Pc.addProductToCart(productName);
		
		Cp = Pc.goToCartPage(); //From AbstractComponent.java
		
		
		List<WebElement> cartItemList =  Cp.getCartList();
		//System.out.println(cartItemList);
		Cp.VerifyProductDisplay(productName);
		

		//CheckOut
		Co = Cp.goToCheckOut();		
		Co.setCountry("India");
		
		Cop = Co.placeOrder();
		Cop.verifyConfirmMessage();
		
		System.out.println("Test run successful");
		driver.close();

	}

}
