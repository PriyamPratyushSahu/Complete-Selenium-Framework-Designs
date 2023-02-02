package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import io.cucumber.java.en.Then;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.LandingPage_1;
import rahulshettyacademy.pageobjects.ProductCataloguePage_2;
import rahulshettyacademy.pageobjects.CartPage_3;
import rahulshettyacademy.pageobjects.CheckOutPage_4;
import rahulshettyacademy.pageobjects.ConfirmationPage_5;

public class StepDefinationImpl extends BaseTest{
	
	public LandingPage_1 Lp;
	public ProductCataloguePage_2 Pc;
	public CartPage_3 Cp;
	public CheckOutPage_4 Co;
	public ConfirmationPage_5 Cop;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		Lp = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$") //(.+) -> To match any number of string
	public void Logged_in_with_username_and_password(String username, String password) {
		
		Pc = Lp.logApplication(username,password);	//Page 1
	}
	
    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) {
    	List<WebElement> productsList = Pc.getProductList();		
		Pc.addProductToCart(productName);
    }
    
    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName){
    	Cp = Pc.goToCartPage(); //From AbstractComponent.java
		List<WebElement> cartItemList =  Cp.getCartList();		//Page 3
		Cp.VerifyProductDisplay(productName);
		
		Co = Cp.goToCheckOut();		//Page 4
		Co.setCountry("India");
		
		Cop = Co.placeOrder();
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String stringMessage) {
    	Cop.verifyConfirmMessage(stringMessage);
    	
    	driver.close();
    }
    
    @Then("{string} message is displayed")
    public void error_message_is_displayed(String stringMessage) {
    	Assert.assertEquals(stringMessage,Lp.getErrorMessage());
    	driver.close();
    }
}
