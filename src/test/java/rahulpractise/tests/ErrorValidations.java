package rahulpractise.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.cartPage;
import pageObjects.productCatalogue;
import rahulpractise.testcomponents.BaseTest;

public class ErrorValidations extends BaseTest{

	@Test
	public void submitorder() throws Exception
	{
		String productname="ZARA COAT 3";
		productCatalogue productCatalogue=landingpage.LoginApplication("rahul.khot@gmail.com", "Rahul123");
		landingpage.getErrormesssage();
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrormesssage());
		
		
	}
	
	@Test
	public void productErrorvalidation() throws Exception
	{
		String productname="ZARA COAT 3";
		
		productCatalogue productCatalogue=landingpage.LoginApplication("ankshika@gmail.com", "Iamki000");
		List<WebElement> products=productCatalogue.getProductlist();
		productCatalogue.getProductByname(productname);
		productCatalogue.addProductToCart(productname);
		cartPage cartpage=productCatalogue.goToCartPage();
		
		Boolean match1=cartpage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertFalse(match1);
					
	}

}
