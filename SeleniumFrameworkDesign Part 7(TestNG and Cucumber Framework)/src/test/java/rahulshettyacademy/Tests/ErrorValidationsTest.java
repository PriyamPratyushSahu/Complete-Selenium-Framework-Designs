package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage_3;
import rahulshettyacademy.pageobjects.CheckOutPage_4;
import rahulshettyacademy.pageobjects.ConfirmationPage_5;
import rahulshettyacademy.pageobjects.ProductCataloguePage_2;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException {

		Lp.logApplication("prim@gmail.com", "Pyam123");
		Assert.assertEquals("Incorrect email or password.",Lp.getErrorMessage());
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void ProductErrorValidation() throws IOException{
		String productName = "ZARA COAT 3";
		ProductCataloguePage_2 Pc = Lp.logApplication("priyam@gmail.com","Priyam123");	//Page 1
		List<WebElement> productsList = Pc.getProductList();
		Pc.addProductToCart(productName);
		CartPage_3 Cp = Pc.goToCartPage();
		Cp.VerifyProductDisplay(productName);
	}

}
