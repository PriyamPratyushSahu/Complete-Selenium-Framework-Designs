package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import rahulshettyacademy.abstractComponents.AbstractComponent;

@SuppressWarnings("deprecation")
public class CheckOutPage_4 extends AbstractComponent{

	WebDriver driver;
	
	public CheckOutPage_4(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = " //input[@placeholder='Select Country']")
	WebElement inputCountry; //Equivalent to -> WebElement inputCountry = driver.findElement(By.xpath(" //input[@placeholder='Select Country']"));
	
	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> countryOptions; //Equivalent to -> List<WebElement> options = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
	
	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderButton; //Equivalent to -> WebElement placeOrderButton = driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));
	
	
	public void setCountry(String countryName)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		inputCountry.sendKeys(countryName);
		for(WebElement i: countryOptions)
		{
			if(i.getText().equalsIgnoreCase("India")) {
				i.click();
				break;
			}
		}
		Assert.assertTrue(inputCountry.getAttribute("value").equalsIgnoreCase("India"));
	}
	
	public ConfirmationPage_5 placeOrder()
	{
		placeOrderButton.click();
		return new ConfirmationPage_5(driver);
	}
}