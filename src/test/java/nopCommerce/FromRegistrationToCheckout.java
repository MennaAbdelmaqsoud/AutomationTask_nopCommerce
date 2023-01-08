package nopCommerce;



import static org.testng.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class FromRegistrationToCheckout {

	ChromeDriver driver;
	String Input_FirstName = "Mennatullah";
	String Input_LastName = "Adelmaqsoud";
	String Input_Day = "19";
	String Input_Month = "March";
	String Input_Year = "1996";
	String Input_Email = "mennatullahabdelmaqsoud@gmail.com";
	String Input_Password = "123456";
	String Input_SortBy= "Price: Low to High";
	String Input_Country = "Egypt";
	String Input_City = "Alexandria";
	String Input_Address1 = "Sedi Besher";
	String Input_PostalCode = "12345";
	String Input_PhoneNumber = "01234567899";

	@BeforeTest
	public void OpenURL()
	{
		String ChromeDriverPath= System.getProperty("user.dir")+"\\Resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
	}

	@Test(priority = 0,enabled = true)
	public void RegisterNewAccount() {

		//Open Registration  Page
		if (isElementPresent(By.className("ico-register"))) {
			WebElement Registration_page = driver.findElement(By.className("ico-register"));
			Registration_page.click();
		}else {
			org.testng.Assert.fail("Element is not founded");
		}

		// Select gender Female 

		WebElement SelectGender_Female = driver.findElement(By.id("gender-female"));
		SelectGender_Female.click();

		//Enter First Name
		WebElement FirstName = driver.findElement(By.id("FirstName"));
		FirstName.sendKeys(Input_FirstName);

		//Enter Last Name
		WebElement LastName = driver.findElement(By.id("LastName"));
		LastName.sendKeys(Input_LastName);

		//Select DateOfBirthDay
		//DAY
		Select DaySelection = new Select(driver.findElement(By.name("DateOfBirthDay")));
		DaySelection.selectByValue(Input_Day);

		//MONTH
		Select MonthSelection = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		MonthSelection.selectByVisibleText(Input_Month);

		//YEAR
		Select YearSelection = new Select(driver.findElement(By.name("DateOfBirthYear")));
		YearSelection.selectByValue(Input_Year);

		//Enter Email
		WebElement Email = driver.findElement(By.id("Email"));
		Email.sendKeys(Input_Email);

		//Enter Password
		WebElement Password = driver.findElement(By.id("Password"));
		Password.sendKeys(Input_Password);

		//Confirm Password
		WebElement ConfirmPassword = driver.findElement(By.id("ConfirmPassword"));
		ConfirmPassword.sendKeys(Input_Password);

		//Click on Register Button
		WebElement Register_BTN = driver.findElement(By.id("register-button"));
		Register_BTN.click();

		//Click to Continue  
		WebElement Continue_BTN = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[2]/a"));
		Continue_BTN.click();
		


	}

	@Test(priority = 1)
	public void LoginPage() {

		//Continue to Login
		if (isElementPresent(By.className("ico-login"))) {
			WebElement ContinueToLogin = driver.findElement(By.className("ico-login"));
			ContinueToLogin.click();
		}else {
			org.testng.Assert.fail("Element is not founded");
		}

		//Enter Email
		WebElement Email = driver.findElement(By.id("Email"));
		Email.sendKeys(Input_Email);

		//Enter Password
		WebElement Password = driver.findElement(By.id("Password"));
		Password.sendKeys(Input_Password);
		//Enter to Login
		Password.sendKeys(Keys.ENTER);

	}

	@Test(priority = 2)
	public void BuyBook() throws InterruptedException  {

		//Select Book Tab
		WebElement SelectBookTab = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[5]/a"));
		SelectBookTab.click();

		//Sort by                           
		Select SortByList = new Select(driver.findElement(By.id("products-orderby")));
		SortByList.selectByVisibleText(Input_SortBy);

		//Select the second item 
		//WebElement SelectSecondItem = driver.findElement(By.cssSelector(".button-2.add-to-wishlist-button"));
		//SelectSecondItem.click();

		try {
			WebElement SelectSecondItem = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div[3]/div[2]/button[1]"));
			SelectSecondItem.click();
		}
		catch (org.openqa.selenium.StaleElementReferenceException ex)
		{
			WebElement SelectSecondItem = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/div[2]/div[3]/div[2]/button[1]"));
			SelectSecondItem.click();
		}

		Thread.sleep(5000);
		//Close Notifications 
		//WebElement CloseNotifications = driver.findElement(By.xpath("//*[@id=\"bar-notification\"]/div/span"));
		//CloseNotifications.click();

		//Go to Shopping Cart
		WebElement ClickOnShoppingCart = driver.findElement(By.xpath("//*[@id=\"topcartlink\"]/a/span[1]"));
		ClickOnShoppingCart.click();

		//Agree terms  Conditions 
		WebElement TermsConditions = driver.findElement(By.id("termsofservice"));
		if(!TermsConditions.isSelected()) {
			TermsConditions.click();
		}
		assertTrue(TermsConditions.isSelected());

		//Click on Checkout 
		WebElement Checkout_BTN = driver.findElement(By.id("checkout"));
		Checkout_BTN.click();

		//Billing Address
		//Check if the address is already selected
		//Check the edit Button is displayed or not
		WebElement Edit_BTN = driver.findElement(By.id("edit-billing-address-button"));
		if (!Edit_BTN.isDisplayed())
		{

			Thread.sleep(1000);
			//Country
			Select Country = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
			Country.selectByVisibleText(Input_Country);

			//City
			WebElement City = driver.findElement(By.id("BillingNewAddress_City"));
			City.sendKeys(Input_City);

			//Address1 
			WebElement Address1 = driver.findElement(By.id("BillingNewAddress_Address1"));
			Address1.sendKeys(Input_Address1);

			//Zip postal Code
			WebElement PostalCode = driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));
			PostalCode.sendKeys(Input_PostalCode);

			//Phone number
			WebElement PhoneNumber = driver.findElement(By.id("BillingNewAddress_PhoneNumber"));
			PhoneNumber.sendKeys(Input_PhoneNumber);

			//Click to Continue  
			WebElement ContinueAfterBilling = driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]"));
			ContinueAfterBilling.click();  

			Thread.sleep(2000);
		}else {
			Thread.sleep(2000);
			WebElement ContiuewithoutAddress = driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]"));
			ContiuewithoutAddress.click();
			Thread.sleep(2000);                                            
		}


		//Shipping Method 
		//Continue with default Method "Ground"
		WebElement ContinuewithShippingMethod = driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button"));
		ContinuewithShippingMethod.click();

		Thread.sleep(2000);

		//Payment Method  
		//Continue with Payment Method
		WebElement ContinuewithPaymentMethod = driver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/button"));
		ContinuewithPaymentMethod.click();

		Thread.sleep(2000);

		//Payment Informations 
		//Continue with Payment Information
		WebElement ContinuewithPaymentInformation = driver.findElement(By.xpath("//*[@id=\"payment-info-buttons-container\"]/button"));
		ContinuewithPaymentInformation.click();

		Thread.sleep(2000);

		//Confirm Order
		WebElement ConfirmOrder_TN = driver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/button"));
		ConfirmOrder_TN.click();


	}

	@AfterTest
	public void CloseBrowser() {
		//driver.quit();
	}

	private boolean isElementPresent (By by) {
		try {

			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}


