package stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.cartPage;
import pageObjects.productCatalogue;
import rahulpractise.testcomponents.BaseTest;

public class Stepdefinationimpl extends BaseTest {

	public LandingPage landingpage;
	public productCatalogue productCatalogue;
	public ConfirmationPage confpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage=launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue=landingpage.LoginApplication(username,password);
	}
	
	@When ("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws Exception
	{
		List<WebElement> products=productCatalogue.getProductlist();
		productCatalogue.getProductByname(productName);
		productCatalogue.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submimit the order$")
	public void checkout_submit_order(String productName)
	{
		cartPage cartpage=productCatalogue.goToCartPage();
		
		Boolean match1=cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match1);
		CheckoutPage checkoutpage= cartpage.goTocheckout();
		checkoutpage.selectCountry("india");
		 confpage=checkoutpage.submitOrder();
	}
	
	@Then( "{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationpage(String string)
	{
		String conmsg=confpage.getconfirmationMessage();
		Assert.assertTrue(conmsg.equalsIgnoreCase(string));
		driver.close();
	}
}
