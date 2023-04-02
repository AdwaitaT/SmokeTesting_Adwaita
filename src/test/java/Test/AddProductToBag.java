package Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import Pages.HomePage;
import Pages.ProductPage;

public class AddProductToBag {
	
	WebDriver driver;
	HomePage hp;
	ProductPage pp;
	Properties prop;
	FileInputStream fis;
	
	public AddProductToBag() throws IOException {
		prop=new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//details.properties");
		prop.load(fis);
		
	}
	
	@BeforeTest
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "..//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
	
	
	@Test
	public void searchProduct() {
		hp=new HomePage(driver);
		pp=new ProductPage(driver);
		driver.get("www.lego.com/en-gb");
		driver.manage().window().maximize();
		hp.HandleLandingPagePopUp();
		hp.HandleCookiesPopUp();
		Assert.assertTrue(hp.searchProduct(prop.getProperty("productName"))>0);
		String addedProduct=pp.AddToCart();
		Assert.assertTrue(driver.findElement(By.xpath("//h3[@data-test='product-title']")).getText().equalsIgnoreCase(addedProduct));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
