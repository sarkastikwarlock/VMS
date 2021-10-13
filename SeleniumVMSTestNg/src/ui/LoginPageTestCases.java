package ui;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


@Listeners(ListenerTest.class)
public class LoginPageTestCases {

	public WebDriver driver;
	public JavascriptExecutor js;
	public Actions builder;
	public Action seriesOfActions;
	
	@BeforeTest
	public void initialize() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\druml\\Downloads\\selenium-java-3.141.59\\chromedriver.exe");
		driver = new ChromeDriver();
	
		driver.get("http://localhost:4200");
		builder = new Actions(driver);
		js = (JavascriptExecutor)driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}
	
	//sign in as doctor
	@Test
	public void loginAsDoctor() {
		signInUser(driver, "G4405191H", "piL1FrGu34");
		WebElement doctorPage = driver.findElement(By.xpath("//h2[contains(text(),'Doctor Page')]"));
		String actualString = "Doctor Page";
		Assert.assertEquals(actualString, doctorPage.getText());
	}
	
	//sign in as admin
	@Test
	public void loginAsAdmin() {
		signInUser(driver, "admin", "admin123");
		WebElement adminPage = driver.findElement(By.xpath("//h2[contains(text(),'Admin Page')]"));
		String actualString = "Admin Page";
		Assert.assertEquals(actualString, adminPage.getText());
	}
	
	//sign in as admin
	@Test
	public void loginAsPatient() {
		signInUser(driver, "G5535348F", "WNp755z9e");
		WebElement patientPage = driver.findElement(By.xpath("//h2[contains(text(),'Patient Page')]"));
		String actualString = "Patient Page";
		Assert.assertEquals(actualString, patientPage.getText());
	}
	
	//sign in with no password
	@Test
	public void loginWithNoPassword() throws InterruptedException {
		signInUser(driver, "F0374513Q", "");
		String alertText = "User not found. You may have entered an incorrect NRIC/Password. Please try again.";
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alertText, alert.getText());
		alert.accept();

		driver.navigate().refresh();
	}
	
	//sign in with no id
	@Test
	public void loginWithNoId() throws InterruptedException {
		signInUser(driver, "", "NM1qdj");
		String alertText = "User not found. You may have entered an incorrect NRIC/Password. Please try again.";
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alertText, alert.getText());
		alert.accept();
		driver.navigate().refresh();
	}
	
	//sign in with no id and no password
	@Test
	public void loginWithNoIdAndNoPassword() throws InterruptedException {
		signInUser(driver, "", "");
		String alertText = "User not found. You may have entered an incorrect NRIC/Password. Please try again.";
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alertText, alert.getText());
		alert.accept();
		driver.navigate().refresh();
	}
	
	//test register button
	@Test
	public void registerButton() {
		driver.findElement(By.name("registerBtn")).click();
		String registrationText = "Registration Form";
		WebElement registrationPage = driver.findElement(By.xpath("//h1[contains(text(),'Registration Form')]"));
		Assert.assertEquals(registrationText, registrationPage.getText());
	}
	
	//forgot password
	@Test
	public void forgotPassword() throws InterruptedException {
		driver.findElement(By.linkText("Forgot Password")).click();
		String header = "Forgot password/id";
		Thread.sleep(2000);
		WebElement headerText = driver.findElement(By.xpath("//h5[text()='Forgot password/id']"));
		Assert.assertEquals(headerText.getText(), header);
	}
	
	//forgot Id
	@Test
	public void forgotId() {
		driver.findElement(By.linkText("Forgot ID")).click();
		String header = "Forgot password/id";
		WebElement headerText = driver.findElement(By.xpath("//h5[text()='Forgot password/id']"));
		Assert.assertEquals(header, headerText.getText());
	}
		
	//forgot Id with filled field
	@Test
	public void forgotIDWithFilledFields() throws InterruptedException {
		String content = "Please follow the instructions that have been sent to your email.";
		String content2 ="Email does not exist. Please re-enter the email that you have registered with.";
		
		driver.findElement(By.linkText("Forgot Password")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("123@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Send email']")).click();
		
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), content2);
		alert.accept();
		
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("cpreskett1@hp.com");
		driver.findElement(By.xpath("//button[text()='Send email']")).click();
		Thread.sleep(2000);
		
		WebElement contentText = driver.findElement(By.xpath("//label[contains(text(), 'Please follow')]"));
		Assert.assertEquals(content, contentText.getText());
		
		driver.findElement(By.xpath("//button[text()='Ok']")).click();
	}
	
	@Test
	public void forgotIDWithEmptyFields() throws InterruptedException{
		String content ="Email does not exist. Please re-enter the email that you have registered with.";
		
		driver.findElement(By.linkText("Forgot Password")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Send email']")).click();
		
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), content);
		alert.accept();
		
		driver.findElement(By.xpath("//button[text()='Cancel']")).click();
	}
	
	@Test
	public void forgotPasswordWithFilledFields() throws InterruptedException {
		String content = "Please follow the instructions that have been sent to your email.";
		String content2 ="Email does not exist. Please re-enter the email that you have registered with.";
		
		driver.findElement(By.linkText("Forgot Password")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("123@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Send email']")).click();
		
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), content2);
		alert.accept();
		
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("cpreskett1@hp.com");
		driver.findElement(By.xpath("//button[text()='Send email']")).click();
		Thread.sleep(2000);
		
		WebElement contentText = driver.findElement(By.xpath("//label[contains(text(), 'Please follow')]"));
		Assert.assertEquals(content, contentText.getText());
		
		driver.findElement(By.xpath("//button[text()='Ok']")).click();
	}
	
	@Test
	public void forgotPasswordWithEmptyFields() throws InterruptedException{
		String content ="Email does not exist. Please re-enter the email that you have registered with.";
		
		driver.findElement(By.linkText("Forgot Password")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Send email']")).click();
		
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), content);
		alert.accept();
		
		driver.findElement(By.xpath("//button[text()='Cancel']")).click();
	}
	
	@AfterMethod
	public void afterEachTest() throws InterruptedException {
		Thread.sleep(500);
		driver.navigate().to("http://localhost:4200");
	}
	
	@AfterTest
	public void end() {
		driver.quit();
	}
	
	public void signInUser(WebDriver driver, String id, String password) {
		driver.findElement(By.xpath("//input[@name='inputNric']")).sendKeys(id);
		driver.findElement(By.xpath("//input[@name='inputPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@name='signInBtn']")).click();		
	}
	
}
