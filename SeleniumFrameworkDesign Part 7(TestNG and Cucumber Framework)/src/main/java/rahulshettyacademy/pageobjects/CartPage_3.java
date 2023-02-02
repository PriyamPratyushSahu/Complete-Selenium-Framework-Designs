package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import rahulshettyacademy.abstractComponents.AbstractComponent;

@SuppressWarnings("deprecation")
public class CartPage_3 extends AbstractComponent{

	WebDriver driver;
	
	public CartPage_3(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartItemList; //Equivalent to -> List<WebElement> cartItemList = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOut; //Equivalent to -> WebElement checkOut = driver.findElement(By.xpath("//button[text()='Checkout']"));
	
	public List<WebElement> getCartList()
	{
		return cartItemList;
	}
	
	public void VerifyProductDisplay(String productName) {
		Boolean findMatch = getCartList().stream().anyMatch(cartItem ->cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(findMatch);
	}
	
	public CheckOutPage_4 goToCheckOut()
	{
		checkOut.click();
		return new CheckOutPage_4(driver);
	}
	
}