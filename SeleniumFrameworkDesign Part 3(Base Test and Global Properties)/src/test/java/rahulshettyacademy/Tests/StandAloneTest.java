package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage_3;
import rahulshettyacademy.pageobjects.CheckOutPage_4;
import rahulshettyacademy.pageobjects.ConfirmationPage_5;
import rahulshettyacademy.pageobjects.ProductCataloguePage_2;

public class StandAloneTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	

	@Test
	public void submitOrder() throws IOException{

		ProductCataloguePage_2 Pc;
		CartPage_3 Cp;
		CheckOutPage_4 Co;
		ConfirmationPage_5 Cop;
		
		Pc = Lp.logApplication("priyam@gmail.com","Priyam123");	//Page 1
		List<WebElement> productsList = Pc.getProductList();
				
		
		WebElement findProduct = Pc.getProductByName(productName);	//Page 2
		Pc.addProductToCart(productName);
		
		Cp = Pc.goToCartPage(); //From AbstractComponent.java
		List<WebElement> cartItemList =  Cp.getCartList();		//Page 3
		Cp.VerifyProductDisplay(productName);
		
		Co = Cp.goToCheckOut();		//Page 4
		Co.setCountry("India");
		
		Cop = Co.placeOrder();		
		Cop.verifyConfirmMessage();	//Page 5
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCataloguePage_2 Pc = Lp.logApplication("priyam@gmail.com","Priyam123");	//Page 1
		Pc.goToOrdersPage(); //Page 6
		
		
	}
}
