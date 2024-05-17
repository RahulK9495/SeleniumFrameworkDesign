package rahulpractise.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.cartPage;
import pageObjects.productCatalogue;
import rahulpractise.testcomponents.BaseTest;

public class StandAloneTest extends BaseTest{
	String productname="ZARA COAT 3";
	
	@Test(dataProvider="getData")
	public void submitorder(HashMap <String,String> input) throws Exception
	{
		
		
		productCatalogue productCatalogue=landingpage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalogue.getProductlist();
		productCatalogue.getProductByname(input.get("productName"));
		productCatalogue.addProductToCart(input.get("productName"));
		cartPage cartpage=productCatalogue.goToCartPage();
		
		Boolean match1=cartpage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match1);
		CheckoutPage checkoutpage= cartpage.goTocheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confpage=checkoutpage.submitOrder();
			
		String conmsg=confpage.getconfirmationMessage();
		Assert.assertTrue(conmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitorder"})
	public void OrderHistoryTest()
	{
		productCatalogue productCatalogue=landingpage.LoginApplication("rahul.khot@gmail.com", "Rahul@123");
		OrderPage orderpage =productCatalogue.goToOrderpage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productname));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException

	{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email","rahul.khot@gmail.com");
//		map.put("password","Rahul@123");
//		map.put("productName","ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email","shetty@gmail.com");
//		map1.put("password","Iamking@000");
//		map1.put("productName","ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\purchaseorder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1)}};
	}
	

}
