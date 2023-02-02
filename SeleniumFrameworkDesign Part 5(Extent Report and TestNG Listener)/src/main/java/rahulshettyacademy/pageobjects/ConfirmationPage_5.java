package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import rahulshettyacademy.abstractComponents.AbstractComponent;

@SuppressWarnings("deprecation")
public class ConfirmationPage_5 extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage_5(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement confirmMessage; //Equivalent to -> WebElement inputCountry = driver.findElement(By.xpath(" //input[@placeholder='Select Country']"));
	
	
	public void verifyConfirmMessage()
	{
		Assert.assertTrue(confirmMessage.getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}	
}