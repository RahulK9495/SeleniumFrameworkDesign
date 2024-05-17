package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement elepassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public productCatalogue LoginApplication(String user, String password)
	{
		userEmail.sendKeys(user);
		elepassword.sendKeys(password);
		submit.click();
		productCatalogue productCatalogue=new productCatalogue(driver);
		return productCatalogue;
	}
	
	public void gotoURL()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrormesssage() {
		waitwebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
