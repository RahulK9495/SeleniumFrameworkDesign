package rahulpractise.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class StandAloneTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingpage=new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("rahul.khot@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new  WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-containe")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
List<WebElement> cartproducts=driver.findElements(By.xpath("//div[@class=\"cart\"]//h3"));
		
		Boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//div[@class=\"subtotal cf ng-star-inserted\"]//button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String conmsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(conmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
