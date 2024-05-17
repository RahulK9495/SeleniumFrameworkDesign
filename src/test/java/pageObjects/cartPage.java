package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent {

	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//div[@class='cart']//h3")
	List <WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutele;
	
	By Mycart= By.xpath("//div[@class='heading cf']");
	
	public Boolean VerifyProductDisplay(String productname)
	{	
		waitElementToAppear(Mycart);
		Boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	public CheckoutPage  goTocheckout()
	{
		checkoutele.click();
		return new CheckoutPage(driver);
	}

}
