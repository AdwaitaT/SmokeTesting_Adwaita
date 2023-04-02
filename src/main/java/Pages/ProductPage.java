package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage{
	
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@FindBy(xpath="//span[text()='Add to Bag']")
	WebElement AddToCartBtn;
	
	@FindBy(xpath="//h2[text()='Added to My Bag']")
	WebElement ConfirmationMgs;
	
	@FindBy(xpath="//a[text()='View My Bag']")
	WebElement ViewBag;
	
	public String AddToCart() {
//		Thread.sleep(2000);
		AddToCartBtn.click();
//		WaitForVisibility(ConfirmationMgs);
		String AddedProduct=driver.findElement(By.xpath("//div[@data-test='added-item']/div[2]/p[1]/span")).getText();
		ViewBag.click();
		return AddedProduct;
	}
}
