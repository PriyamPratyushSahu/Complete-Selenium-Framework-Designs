package rahulshettyacademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponent;

public class LandingPage_1 extends AbstractComponent{

	WebDriver driver;

	public LandingPage_1(WebDriver driver){
		
		super(driver);
		
		//Initialization
		this.driver = driver;
		
		PageFactory.initElements(driver,this);
		//initElements handles all the initialization as it takes two parameter
		//driver from base class and this to initialize all the elements present in the sub class
	}
	
	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement emailTxt; //Equivalent to -> WebElement emailTxt = driver.findElement(By.xpath("//input[@id='userEmail']"));
	
	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement passwordTxt; //Equivalent to ->	WebElement passwordTxt = driver.findElement(By.xpath("//input[@id='userPassword']"));
	
	@FindBy(xpath = "//input[@id='login']")
	WebElement loginBtn; //Equivalent to ->	WebElement loginBtn = driver.findElement(By.xpath("//input[@id='login']"));
	
	
	//Actions
	public void goTo(String Url)
	{
		driver.get(Url);
	}
	
	public ProductCataloguePage_2 logApplication(String email, String password)
	{
		emailTxt.sendKeys(email);
		passwordTxt.sendKeys(password);
		loginBtn.click();
		
		return new ProductCataloguePage_2(driver);
		
	}
}
