package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//tr/td[2]")
	List <WebElement> productsintable;
	
	
	By YourOrders= By.xpath("////h1");
	
	public Boolean VerifyOrderDisplay(String productname)
	{	
		//waitElementToAppear(YourOrders);
		Boolean match=productsintable.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	


}
