package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.OrderPage;
import pageObjects.cartPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement myorders;
	
	public void waitElementToAppear(By findBy)
	{
	WebDriverWait wait=new  WebDriverWait(driver, Duration.ofSeconds(7));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	public void waitwebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait=new  WebDriverWait(driver, Duration.ofSeconds(7));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}
	
	public cartPage goToCartPage()
	{
		cartHeader.click();
		cartPage cartpage=new cartPage(driver);
		return cartpage;
	}
	
	public void waitElementToDisappear(By findBy) throws Exception
	{
	Thread.sleep(1000);
	//WebDriverWait wait=new  WebDriverWait(driver, Duration.ofSeconds(7));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	
	}
	public OrderPage goToOrderpage()
	{
		myorders.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
	
}
