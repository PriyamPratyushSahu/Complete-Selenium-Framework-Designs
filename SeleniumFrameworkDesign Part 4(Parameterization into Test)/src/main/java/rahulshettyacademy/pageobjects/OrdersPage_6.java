package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import rahulshettyacademy.abstractComponents.AbstractComponent;

@SuppressWarnings("deprecation")
public class OrdersPage_6 extends AbstractComponent{

	WebDriver driver;
	
	public OrdersPage_6(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//tbody/tr/td[2]")
	//Equivalent css -> tr td:nth-child(3)
	List<WebElement> OrderNameList; 
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOut; //Equivalent to -> WebElement checkOut = driver.findElement(By.xpath("//button[text()='Checkout']"));
	

	
	public List<WebElement> getOrderNametList()
	{
		return OrderNameList;
	}
	
	public void VerifyOrderDisplay(String productName) {
		Boolean findMatch = getOrderNametList().stream().anyMatch(cartItem ->cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(findMatch);
	}
	
	
	
}