package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage_3;
import rahulshettyacademy.pageobjects.CheckOutPage_4;
import rahulshettyacademy.pageobjects.ConfirmationPage_5;
import rahulshettyacademy.pageobjects.ProductCataloguePage_2;

public class SubmitOrder extends BaseTest{

	

	@Test(dataProvider = "getData")
	//public void submitOrder(String email,String password,String productName) throws IOException{
	public void submitOrder(HashMap<String, String> input) throws IOException{

		ProductCataloguePage_2 Pc;
		CartPage_3 Cp;
		CheckOutPage_4 Co;
		ConfirmationPage_5 Cop;
		
		Pc = Lp.logApplication(input.get("email"),input.get("password"));	//Page 1
		List<WebElement> productsList = Pc.getProductList();
				
		
		WebElement findProduct = Pc.getProductByName(input.get("productName"));	//Page 2
		Pc.addProductToCart(input.get("productName"));
		
		Cp = Pc.goToCartPage(); //From AbstractComponent.java
		List<WebElement> cartItemList =  Cp.getCartList();		//Page 3
		Cp.VerifyProductDisplay(input.get("productName"));
		
		Co = Cp.goToCheckOut();		//Page 4
		Co.setCountry("India");
		
		Cop = Co.placeOrder();		
		Cop.verifyConfirmMessage("THANKYOU FOR THE ORDER.");	//Page 5
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCataloguePage_2 Pc = Lp.logApplication("priyam@gmail.com","Priyam123");	//Page 1
		Pc.goToOrdersPage(); //Page 6
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		String jsonFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
				File.separator + "java" + File.separator + "rahulshettyacademy" + File.separator + "data" +
				File.separator + "PurchaseOrder.json"; 
				//C:\Users\priyamps\Downloads\Eclipse\Eclipse-Workspace\Selenium Udemy Rahul Shetty\SeleniumFrameworkDesign Part 4(Parameterization into Test)\src\test\java\rahulshettyacademy\data\PurchaseOrder.json
		
		List<HashMap<String, String>> data = getJsonDataToMap(jsonFilePath);
		return new Object[][] {{data.get(0)} , {data.get(1)}};
		
	}
}
