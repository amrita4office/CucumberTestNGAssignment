package StepDef;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef {
	Random ran = new Random();
	Integer int1 = ran.nextInt(1000);
	By SignUp = By.xpath("//a[text()=' Sign up! ']");
	
	WebDriver driver;
		@Given("Open the required URL")
		public void open_the_required_url() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		    driver.get("http://elearningm1.upskills.in/");
			}
 
		@When("Click on the SignUP link")
			public void click_on_the_sign_up_link() throws InterruptedException {
			   // driver.findElement(By.xpath("//a[text()=' Sign up! ']")).click();
			    driver.findElement(SignUp).click();
			    Thread.sleep(5000); 
			}
		
		@When("Fill in the User Details in registration screen as {string},{string},{string},{string}")
			public void fill_in_the_user_details_in_registration_screen_as(String fName, String lName, String eMail, String Pwd) throws InterruptedException {
				String FirstName = fName+int1;
				String Email = FirstName+eMail;
				String UserName = FirstName+lName;
				driver.findElement(By.id(("registration_firstname"))).sendKeys(FirstName);
			    driver.findElement(By.id(("registration_lastname"))).sendKeys(lName);
			    driver.findElement(By.id(("registration_email"))).sendKeys(Email);
			    driver.findElement(By.id(("username"))).sendKeys(UserName);
			    driver.findElement(By.id(("pass1"))).sendKeys(Pwd);
			    driver.findElement(By.id(("pass2"))).sendKeys(Pwd);
			    driver.findElement(By.id(("registration_submit"))).click();
			    Thread.sleep(5000);
			}
		
		@Then("Validate the registration {string},{string},{string}")
		public void validate_the_registration_and(String fName, String lName, String eMail) throws InterruptedException {
			 String FirstName = fName+int1;
			 String Email = FirstName+eMail;
			 String Actual = driver.findElement(By.xpath("//*[@class='col-xs-12 col-md-12']/p[1]")).getText();
			 String Expected  = "Dear "+FirstName+" "+lName;  // "Dear U62 test"
			 Integer len = Expected.length();
			 String a1 = Actual.substring(0,len);
			 Assert.assertEquals(a1, Expected);
			 System.out.println("The Registration is successful with the given message:"+a1);
			 //email id verification
			 driver.findElement(By.xpath("//*[@class='dropdown avatar-user']")).click();
			 Thread.sleep(3000);
			 String ActualEmail = driver.findElement(By.xpath("//*[@class='user-header']/div/p")).getText();
			 String ExpEmail = Email;
			 Assert.assertEquals(ActualEmail, ExpEmail);
			 System.out.println("The Email verification Passed");
			 //compose email
			 driver.findElement(By.xpath("//a[@title='Inbox']")).click();
			 Thread.sleep(3000);
			//email sending functionality
			 driver.findElement(By.xpath("//*[@title='Compose message']")).click();
			 Thread.sleep(3000);
			 driver.findElement(By.xpath("//*[@class='select2-search__field']")).click(); 
			 driver.findElement(By.xpath("//*[@class='select2-search__field']")).sendKeys("U2.test@test.com");
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//*[@class='select2-search__field']")).sendKeys(Keys.ENTER);
			 Thread.sleep(3000);
			 //press enter here
			 driver.findElement(By.xpath("//*[@id='compose_message_title']")).sendKeys("Testing Email Subject");
			//press tab here
			// driver.findElement(By.xpath("//*[@id='compose_message_title']")).sendKeys(Keys.TAB);
			 Thread.sleep(3000);
			 //iframe selction object
			 WebElement ifrm = driver.findElement(By.xpath("//*[@class='cke_wysiwyg_frame cke_reset']"));
			 driver.switchTo().frame(0);
			 driver.findElement(By.xpath("//body")).click();
			 driver.findElement(By.xpath("//body")).sendKeys("test");
			 driver.switchTo().defaultContent();
			 Thread.sleep(5000);
			 driver.findElement(By.xpath("//*[@id='compose_message_compose']")).click();
			 Thread.sleep(5000);
				if(driver.findElement(By.xpath("//*[@class='alert alert-success']")).isDisplayed())
				System.out.println("Email notification is Visible");
				else
				System.out.println("Email notification is not Visible");
				
			driver.quit();
			 
			}



}
