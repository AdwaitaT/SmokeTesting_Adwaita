package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage{
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@FindBy(xpath="//span[text()='Open search field']")
	WebElement SearchBtn;
	
	@FindBy(id="desktop-search-search-input")
	WebElement SearchBox;
	
	@FindBy(xpath="//span[@data-test='result-count']")
	WebElement ProdCount;
	
	@FindBy(xpath="//div[contains(@class,'AgeGatestyles__Panel-xudtvj-3')]")
	WebElement LandingPagePopUP;
	
	@FindBy(xpath="//button[@data-test='age-gate-grown-up-cta']")
	WebElement ContBtn;
	
	@FindBy(xpath="//div[@data-test='cookie-modal-content']")
	WebElement CookieWindow;
	
	@FindBy(xpath="//button[@data-test='cookie-necessary-button']")
	WebElement NecessaryCookies;
	
	@FindBy(xpath="//span[text()='Add to Bag']")
	WebElement AddToCartBtn;
	
	@FindBy(xpath="//h2[text()='Added to My Bag']")
	WebElement ConfirmationMgs;
	
	@FindBy(xpath="//a[text()='View My Bag']")
	WebElement ViewBag;
	
	public void HandleLandingPagePopUp() {
		if(LandingPagePopUP.isDisplayed()) {
			ContBtn.click();
		}
	}
	
	public void HandleCookiesPopUp() {
		if(CookieWindow.isDisplayed()) {
			NecessaryCookies.click();
		}
	}

	public int searchProduct(String prodName) {
		int num;
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",SearchBtn);
		SearchBox.sendKeys("Yoda");
		SearchBox.sendKeys(Keys.ENTER);
		String num1=ProdCount.getText().split(" ")[1];
		num=Integer.parseInt(num1);
		return num;
	}
	
	public String AddToBag() {
		AddToCartBtn.click();
		String AddedProduct=driver.findElement(By.xpath("//div[@data-test='added-item']/div[2]/p[1]/span")).getText();
		ViewBag.click();
		return AddedProduct;
	}
}
