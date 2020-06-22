package ui.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;
import ui.junit.extensions.custom.CustomTestContext;
import ui.junit.extensions.custom.CustomTestWatcher;
import ui.webdriver.BrowserWaits;
import ui.webdriver.WebDriverManager;

@ExtendWith({CustomTestContext.class, CustomTestWatcher.class})
public class WebTest {

	 String existingUserEmail = "gk123@gk.com";
	 String existingUserPassword = "123456";

	 @Test
	 @Tags({@Tag("smoke")})
	 public void signInTest() {
		 WebDriver driver = WebDriverManager.getWebDriver();
		 WebDriverWait wait = BrowserWaits.waitFor(10);
		 driver.get(System.getProperty("site.base.url"));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		 String timestamp = String.valueOf(new Date().getTime());
		 String email = "gk_" + timestamp + "@gk" + timestamp.substring(7) + ".com";
		 String name = "Firstname";
		 String surname = "Lastname";
		 driver.findElement(By.id("email_create")).sendKeys(email);
		 driver.findElement(By.id("SubmitCreate")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();
		 driver.findElement(By.id("customer_firstname")).sendKeys(name);
		 driver.findElement(By.id("customer_lastname")).sendKeys(surname);
		 driver.findElement(By.id("passwd")).sendKeys("Qwerty");
		 Select select = new Select(driver.findElement(By.id("days")));
		 select.selectByValue("1");
		 select = new Select(driver.findElement(By.id("months")));
		 select.selectByValue("1");
		 select = new Select(driver.findElement(By.id("years")));
		 select.selectByValue("2000");
		 driver.findElement(By.id("company")).sendKeys("Company");
		 driver.findElement(By.id("address1")).sendKeys("Qwerty, 123");
		 driver.findElement(By.id("address2")).sendKeys("zxcvb");
		 driver.findElement(By.id("city")).sendKeys("Qwerty");
		 select = new Select(driver.findElement(By.id("id_state")));
		 select.selectByVisibleText("Colorado");
		 driver.findElement(By.id("postcode")).sendKeys("12345");
		 driver.findElement(By.id("other")).sendKeys("Qwerty");
		 driver.findElement(By.id("phone")).sendKeys("12345123123");
		 driver.findElement(By.id("phone_mobile")).sendKeys("12345123123");
		 driver.findElement(By.id("alias")).sendKeys("gk");
		 driver.findElement(By.id("submitAccount")).click();

		 WebElement heading =
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

		 /*assertEquals(heading.getText(), "MY ACCOUNT");
		 assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
		 assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		 assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		 assertTrue(driver.getCurrentUrl().contains("controller=my-account"));*/
	 }

	 @Test
	 @Tags({@Tag("smoke")})
	 public void logInTest() {
		 WebDriver driver = WebDriverManager.getWebDriver();
		 WebDriverWait wait = BrowserWaits.waitFor(10);
		 driver.get(System.getProperty("site.base.url"));
		 String fullName = "Ankit Nigam";
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		 /*driver.findElement(By.id("email")).sendKeys(existingUserEmail);
		 driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
		 driver.findElement(By.id("SubmitLogin")).click();
		 WebElement heading =
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));*/

		 /*assertEquals("MY ACCOUNT", heading.getText());
		 assertEquals(fullName, driver.findElement(By.className("account")).getText());
		 assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		 assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		 assertTrue(driver.getCurrentUrl().contains("controller=my-account"));*/
	 }

	 @Test
	 @Tags({@Tag("smoke")})
	public void checkoutTest() {
		 WebDriver driver = WebDriverManager.getWebDriver();
		 WebDriverWait wait = BrowserWaits.waitFor(10);
		 driver.get(System.getProperty("site.base.url"));
		 /*wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		 driver.findElement(By.id("email")).sendKeys(existingUserEmail);
		 driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
		 driver.findElement(By.id("SubmitLogin")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
		 driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		 driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(
				 By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']"))).click();


		 wait.until(ExpectedConditions.visibilityOfElementLocated(
				 By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"))).click();


		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();


		 driver.findElement(By.name("processCarrier")).click();


		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button")))
				 .click();
		 WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
*/
		 /*assertEquals("ORDER CONFIRMATION", heading.getText());


		 assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
		 assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
		 assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText()
				 .contains("Your order on My Store is complete."));
		 assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));*/
	 }

}
