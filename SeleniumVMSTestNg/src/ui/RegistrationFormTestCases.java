package ui;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerTest.class)
public class RegistrationFormTestCases {
		
	public WebDriver driver;
	public JavascriptExecutor js;
	public Actions builder;
	
	@BeforeTest
	public void initialize() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\druml\\Downloads\\selenium-java-3.141.59\\chromedriver.exe");
		driver = new ChromeDriver();
	
		driver.get("http://localhost:4200");
		builder = new Actions(driver);
		js = (JavascriptExecutor)driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//button[text()='Register']")).click();
	}
	
	//test navigation to registration form page
	@Test
	public void assertPage() {
		String header = "Registration Form";
		WebElement headerText = driver.findElement(By.xpath("//h1[text()='Registration Form']"));
		Assert.assertEquals(headerText.getText(), header);
	}
	
	// test the register as ddl
	@Test
	public void registerSelect() {
		Boolean patientFields=false;
		Boolean doctorFields=false;
		Boolean centreFields=false;
		
		WebElement registerAs = driver.findElement(By.cssSelector("#registerType"));
		Select selectRegisterAs = new Select(registerAs);
		selectRegisterAs.selectByVisibleText("Patient");
		
		WebElement fullname = driver.findElement(By.xpath("//input[@name='inputFullname']"));
		WebElement email = driver.findElement(By.xpath("//input[@name='inputEmail']"));
		WebElement password = driver.findElement(By.xpath("//input[@name='inputPassword']"));
		WebElement reTypePassword = driver.findElement(By.xpath("//input[@name='reTypePassword']"));
		WebElement id = driver.findElement(By.xpath("//input[@name='inputId']"));
		WebElement dob = driver.findElement(By.xpath("//input[@name='inputDOB']"));
		WebElement phone = driver.findElement(By.xpath("//input[@name='inputPhone']"));
		
		if(fullname.isDisplayed()&&email.isDisplayed()&&password.isDisplayed()&&reTypePassword.isDisplayed()
				&&id.isDisplayed()&&dob.isDisplayed()&&phone.isDisplayed()) {
			patientFields = true;
		}
		
		selectRegisterAs.selectByVisibleText("Doctor");

		WebElement field = driver.findElement(By.xpath("//input[@name='inputField']"));
		WebElement accreditation = driver.findElement(By.xpath("//input[@name='inputAccreditation']"));
		WebElement gp = driver.findElement(By.cssSelector("#Gp"));
		
		if(patientFields && field.isDisplayed()&&accreditation.isDisplayed()&&gp.isDisplayed()) {
			doctorFields = true;
		}
		
		selectRegisterAs.selectByVisibleText("Vaccination Centre");

		WebElement cName = driver.findElement(By.xpath("//input[@name='inputCName']"));
		WebElement cEmail = driver.findElement(By.xpath("//input[@name='inputCEmail']"));
		WebElement cAddress = driver.findElement(By.cssSelector("#centreAddressText"));
		WebElement cPhone = driver.findElement(By.xpath("//input[@name='inputCPhone']"));
		WebElement cHours = driver.findElement(By.xpath("//registration-form//label[contains(text(), 'Hours')]"));
		WebElement cVaccine = driver.findElement(By.xpath("//registration-form//label[contains(text(), 'Vaccine')]"));

		if(cName.isDisplayed()&&cEmail.isDisplayed()&&cAddress.isDisplayed()&&cPhone.isDisplayed()&&cHours.isDisplayed()&&cVaccine.isDisplayed()) {
			centreFields = true;
		}
		
		Assert.assertTrue(patientFields);
		Assert.assertTrue(doctorFields);
		Assert.assertTrue(centreFields);
	}
	
	//register as patient with filled fields
	@Test
	public void registerAsPatientWithFilledField() throws InterruptedException {
		String content = "Registration successful. Return to login page";
		
		WebElement registerAs = driver.findElement(By.cssSelector("#registerType"));
		Select selectRegisterAs = new Select(registerAs);
		selectRegisterAs.selectByVisibleText("Patient");
		
		driver.findElement(By.xpath("//input[@name='inputFullname']")).sendKeys("Hamizan K");
		driver.findElement(By.xpath("//input[@name='inputEmail']")).sendKeys("hamizank@gmail.com");
		driver.findElement(By.xpath("//input[@name='inputPassword']")).sendKeys("hamizank");
		driver.findElement(By.xpath("//input[@name='reTypePassword']")).sendKeys("hamizank");
		driver.findElement(By.xpath("//input[@name='inputId']")).sendKeys("s98765432a");
		driver.findElement(By.xpath("//input[@name='inputDOB']")).sendKeys("17/12/1993");
		driver.findElement(By.xpath("//input[@name='inputPhone']")).sendKeys("91234568");
		
		driver.findElement(By.xpath("//div[@class='btnGroup']//button[text()='Submit']")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), content);
		alert.accept();
		
	}
	
	//register as patient with empty field
	@Test
	public void registerAsPatientWithEmptyField() throws InterruptedException {
		String content = "Registration failed. Please reload page";
		
		WebElement registerAs = driver.findElement(By.cssSelector("#registerType"));
		Select selectRegisterAs = new Select(registerAs);
		selectRegisterAs.selectByVisibleText("Patient");
		
		driver.findElement(By.xpath("//input[@name='inputFullname']"));
		driver.findElement(By.xpath("//input[@name='inputEmail']"));
		driver.findElement(By.xpath("//input[@name='inputPassword']"));
		driver.findElement(By.xpath("//input[@name='reTypePassword']"));
		driver.findElement(By.xpath("//input[@name='inputId']"));
		driver.findElement(By.xpath("//input[@name='inputDOB']"));
		driver.findElement(By.xpath("//input[@name='inputPhone']"));
		
		driver.findElement(By.xpath("//div[@class='btnGroup']//button[text()='Submit']")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), content);
		alert.accept();
	}
	
	//register as doctor with filled fields
	@Test
	public void registerAsDoctorWithFilledField() throws InterruptedException {
		String content = "Registration successful. Return to login page";
		
		WebElement registerAs = driver.findElement(By.cssSelector("#registerType"));
		Select selectRegisterAs = new Select(registerAs);
		selectRegisterAs.selectByVisibleText("Doctor");
		
		driver.findElement(By.xpath("//input[@name='inputFullname']")).sendKeys("Hamizan K");
		driver.findElement(By.xpath("//input[@name='inputEmail']")).sendKeys("hamizank@gmail.com");
		driver.findElement(By.xpath("//input[@name='inputPassword']")).sendKeys("hamizank");
		driver.findElement(By.xpath("//input[@name='reTypePassword']")).sendKeys("hamizank");
		driver.findElement(By.xpath("//input[@name='inputId']")).sendKeys("s98765432a");
		driver.findElement(By.xpath("//input[@name='inputDOB']")).sendKeys("17/12/1993");
		driver.findElement(By.xpath("//input[@name='inputPhone']")).sendKeys("91234568");
		
		driver.findElement(By.xpath("//input[@name='inputField']")).sendKeys("Family Doctor");
		driver.findElement(By.xpath("//input[@name='inputAccreditation']")).sendKeys("X235527-x99p");
		driver.findElement(By.cssSelector("#Gp")).click();
		
		driver.findElement(By.xpath("//div[@class='btnGroup']//button[text()='Submit']")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), content);
		alert.accept();
	}
	
	//register as doctor with empty field
	@Test
	public void registerAsDoctorWithEmptyField() throws InterruptedException {
		String content = "Registration failed. Please reload page";
		
		WebElement registerAs = driver.findElement(By.cssSelector("#registerType"));
		Select selectRegisterAs = new Select(registerAs);
		selectRegisterAs.selectByVisibleText("Doctor");
		
		driver.findElement(By.xpath("//input[@name='inputFullname']"));
		driver.findElement(By.xpath("//input[@name='inputEmail']"));
		driver.findElement(By.xpath("//input[@name='inputPassword']"));
		driver.findElement(By.xpath("//input[@name='reTypePassword']"));
		driver.findElement(By.xpath("//input[@name='inputId']"));
		driver.findElement(By.xpath("//input[@name='inputDOB']"));
		driver.findElement(By.xpath("//input[@name='inputPhone']"));
		
		driver.findElement(By.xpath("//input[@name='inputField']"));
		driver.findElement(By.xpath("//input[@name='inputAccreditation']"));
		driver.findElement(By.cssSelector("#Gp"));
		
		driver.findElement(By.xpath("//div[@class='btnGroup']//button[text()='Submit']")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), content);
		alert.accept();
	}
	
	@Test
	public void registerAsCentreWithEmptyField() throws InterruptedException {
		String content = "Registration failed. Please reload page";
		
		WebElement registerAs = driver.findElement(By.cssSelector("#registerType"));
		Select selectRegisterAs = new Select(registerAs);
		selectRegisterAs.selectByVisibleText("Vaccination Centre");
		
		driver.findElement(By.xpath("//input[@name='inputCName']")).sendKeys("My House");
		driver.findElement(By.xpath("//input[@name='inputCEmail']")).sendKeys("myHouse@gmail.com");
		driver.findElement(By.cssSelector("#centreAddressText")).sendKeys("400351");
		driver.findElement(By.xpath("//input[@name='inputCPhone']")).sendKeys("62410251");
		driver.findElement(By.xpath("//input[@name='inputCOpening']")).sendKeys("07:00");
		driver.findElement(By.xpath("//input[@name='inputCClosing']")).sendKeys("18:00");
		driver.findElement(By.xpath("//div[@class='vaccineCheckbox']//input[@value='Moderna']")).click();
		
		driver.findElement(By.xpath("//div[@class='btnGroup']//button[text()='Submit']")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), content);
		alert.accept();
	}
	
	@AfterMethod
	public void afterEachTest() throws InterruptedException {
		Thread.sleep(500);
		driver.navigate().to("http://localhost:4200/register");
	}
	
	@AfterTest
	public void end() {
		driver.quit();
	}
}
