//Parent class of all page classes

package rahulshettyacademy.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage_3;
import rahulshettyacademy.pageobjects.OrdersPage_6;

public class AbstractComponent {
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader; //Equivalent to -> WebElement cartHeader =  driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersHeader; 
	
	
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this); //To use @FindBy()
	}

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
}
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public CartPage_3 goToCartPage() {
		cartHeader.click();
		return new CartPage_3(driver);
	}
	
	public OrdersPage_6 goToOrdersPage() {
		ordersHeader.click();
		return new OrdersPage_6(driver);
	}

}
