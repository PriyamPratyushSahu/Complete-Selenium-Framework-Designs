package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.AbstractComponent;

public class ProductCataloguePage_2 extends AbstractComponent{

	WebDriver driver;

	public ProductCataloguePage_2(WebDriver driver){
		
		super(driver);
		
		this.driver = driver;		
		PageFactory.initElements(driver,this);

	}
	
	//Store all products list
	@FindBy(css = ".mb-3")
	List<WebElement> productsList; //Equivalent to -> List<WebElement> productsList = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css = ".ng-animating")
	WebElement spinner; //Equivalent to -> driver.findElement(By.cssSelector(".ng-animating"))
	
	//By
	//By addToCart = By.cssSelector(".card-body button:lastof-type");
	By addToCart = By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]");
	By ToastMessage = By.xpath("//div[@id='toast-container']");
	
	//Actions
	public List<WebElement> getProductList()
	{
		return productsList;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement findProduct =  getProductList().stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return findProduct;
		
	}
	
	public void addProductToCart(String productName) {
		
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(ToastMessage);
		waitForElementToDisappear(spinner);
	}
	
	

	
	


}
