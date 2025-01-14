package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class productCatalogue extends AbstractComponent{

WebDriver driver;
	
	public productCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	By productBy=By.cssSelector(".mb-3");
	By animation=By.cssSelector(".ng-animating");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	
	
	public List<WebElement> getProductlist()
	{
		waitElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByname(String productname)
	{
		WebElement prod=getProductlist().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productname) throws Exception
	{
		WebElement prod=getProductByname(productname);
		prod.findElement(addToCart).click();
		waitElementToDisappear(animation);
		
	}

	
}
