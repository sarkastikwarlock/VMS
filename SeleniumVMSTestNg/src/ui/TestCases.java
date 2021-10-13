package ui;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(groups="UI")
@Listeners(ListenerTest.class)
public class TestCases {
	
	public WebDriver driver;
	public JavascriptExecutor js;
	public Actions builder;
	public Action seriesOfActions;
	public SoftAssert softassert;
	public SignIn signIn;
	public User user;
	
	@BeforeTest
	public void initialize() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\druml\\Downloads\\selenium-java-3.141.59\\chromedriver.exe");
		driver = new ChromeDriver();
		builder = new Actions(driver);
		js = (JavascriptExecutor) driver;
		softassert = new SoftAssert();
		signIn = new SignIn();
		driver.get("http://localhost:4200");
		builder = new Actions(driver);
		js = (JavascriptExecutor)driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		Thread.sleep(1000);
		
		signIn.signInUser(driver, user.getId(), user.getPassword());
	}
	
	@BeforeSuite(groups="patient")
	public void beforeTestForPatient() throws InterruptedException{
		System.out.println("Before suite");
		user = new User("s9876543c","Nurhamizan kamsani","hamizan@gmail.com","password123","1999-06-16","98989898","Bedok Polyclinic", "16:00", "2021-12-20", "Bedok Polyclinic", "11:00", "2022-01-28", false, false);
	}
	
	@BeforeSuite(groups="admin")
	public void beforeTestForAdmin() throws InterruptedException{
		user= new User("admin", "admin123", "admin@admin.com");
	}
	
	@Test(priority=1,groups="patient")
	public void checkSessionPatient() throws InterruptedException {

		String userName = user.getFullname().substring(0, 10);
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement smallSessionText = driver.findElement(By.xpath("//small"));
		smallSessionText.getText();
		
		softassert.assertTrue(smallSessionText.getText().contains(userName)," Login session failed.");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=2,groups="patient")
	public void checkPatientPagePositiveCase() throws InterruptedException {
		
		String headerText = "Patient Page";
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement header = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[1]/h2[1]\r\n"));
		
		softassert.assertEquals(header.getText(), headerText,"Navigate to the wrong page.");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=3,groups="patient")
	public void checkPatientPageNegativeCase() throws InterruptedException {
		
		String headerText = "Pant Page";
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement header = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[1]/h2[1]\r\n"));
		
		softassert.assertNotEquals(header.getText(), headerText);
		
		softassert.assertAll();
	}
	
	@Test(priority=4,groups="patient")
	public void checkPersonalInformationPagePositiveCase() throws InterruptedException {

		String userName = user.getFullname();
		String userEmail = user.getEmail();
		String userId = user.getId().substring(0,1)+"****"+user.getId().substring(5);
		String userPhone = "****"+user.getPhone().substring(4);
		String userDOB = "**"+user.getDob().substring(2, 5)+"**"+user.getDob().substring(7);
		
		Thread.sleep(1000);
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement userNameElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/span[1]"));
		WebElement userIdElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/span[1]"));
		WebElement userEmailElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/span[1]"));
		WebElement userPhoneElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/span[1]"));
		WebElement userDOBElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/span[1]"));

		softassert.assertEquals(userNameElement.getText(), userName,"Fullname is not the same.");
		softassert.assertEquals(userIdElement.getText(), userId,"NRIC is not the same.");
		softassert.assertEquals(userEmailElement.getText(), userEmail,"Email is not the same.");
		softassert.assertEquals(userPhoneElement.getText(), userPhone,"Phone is not the same.");
		softassert.assertEquals(userDOBElement.getText(), userDOB,"DOB is not the same.");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=5,groups="patient")
	public void checkPersonalInformationPageNegativeCase() throws InterruptedException {

		String userName = "Nurhamizankamsani";
		String userEmail = "hamizan@";
		String userId = "S****5";
		String userPhone = "****98";
		String userDOB = "**99-**";
		
		Thread.sleep(1000);
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement userNameElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/span[1]"));
		WebElement userIdElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/span[1]"));
		WebElement userEmailElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/span[1]"));
		WebElement userPhoneElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/span[1]"));
		WebElement userDOBElement = driver.findElement(By.xpath("//div[@class='tabcontent']/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/span[1]"));

		softassert.assertNotEquals(userNameElement.getText(), userName);
		softassert.assertNotEquals(userIdElement.getText(), userId);
		softassert.assertNotEquals(userEmailElement.getText(), userEmail);
		softassert.assertNotEquals(userPhoneElement.getText(), userPhone);
		softassert.assertNotEquals(userDOBElement.getText(), userDOB);
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=6,groups="patient")
	public void checkEditPatientModalContentPositiveCase() throws InterruptedException {

		String userName = user.getFullname();
		String userEmail = user.getEmail();
		String userId = user.getId();
		String userPhone = user.getPhone();
		String userDOB = user.getDob();
				
		String fullname ="Fullname:";
		String email = "E-mail:";
		String password = "Password:";
		String retypePassword = "Re-Type Password:";
		String id = "NRIC:";
		String dob = "Date of birth:";
		String phone = "Phone Number:"; 
		
		String btnTextSave="Save Changes";
		String btnTextCancel = "Cancel";
		
		Thread.sleep(1000);
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement editBtnElement = driver.findElement(By.xpath("//i[contains(@class,'fa-pencil')]"));
		editBtnElement.click();
		
		Thread.sleep(1000);
				
		WebElement inputTextName = driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement inputTextEmail = driver.findElement(By.id("patientEmail"));
		WebElement inputTextId = driver.findElement(By.id("patientId"));
		WebElement inputTextPhone = driver.findElement(By.id("patientPhone"));
		WebElement inputTextDOB = driver.findElement(By.id("patientDOB"));
		
		WebElement labelTextName = driver.findElement(By.xpath("//label[text()='Fullname: ']"));
		WebElement labelTextEmail = driver.findElement(By.xpath("//label[text()='E-mail:']"));
		WebElement labelTextPassword = driver.findElement(By.xpath("//label[text()='Password:']"));
		WebElement labelTextRetypePassword = driver.findElement(By.xpath("//label[contains(text(),'Re-Type Password:')]"));
		WebElement labelTextId = driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/label[1]"));
		WebElement labelTextDOB = driver.findElement(By.xpath("//label[contains(text(),'Date of birth:')]"));
		WebElement labelTextPhone = driver.findElement(By.xpath("//label[contains(text(),'Phone Number:')]"));
		
		WebElement btnSave = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		WebElement btnCancel = driver.findElement(By.xpath("//button[text()='Cancel']"));

		Thread.sleep(1000);
		
		softassert.assertEquals(inputTextName.getAttribute("ng-reflect-model"), userName,"Fullname is not the same.");
		softassert.assertEquals(inputTextEmail.getAttribute("ng-reflect-model"), userEmail,"Email is not the same.");
		softassert.assertEquals(inputTextId.getAttribute("ng-reflect-model"), userId,"NRIC is not the same.");
		softassert.assertEquals(inputTextPhone.getAttribute("ng-reflect-model"), userPhone,"Phone is not the same.");
		softassert.assertEquals(inputTextDOB.getAttribute("ng-reflect-model"), userDOB,"DOB is not the same.");
		
		softassert.assertEquals(labelTextName.getText(), fullname,"Fullname label is not found.");
		softassert.assertEquals(labelTextEmail.getText(), email,"Email label is not found.");
		softassert.assertEquals(labelTextPassword.getText(), password,"Password label is not found.");
		softassert.assertEquals(labelTextRetypePassword.getText(), retypePassword,"Retype password label is not found.");
		softassert.assertEquals(labelTextId.getText(), id,"NRIC label is not found.");
		softassert.assertEquals(labelTextDOB.getText(), dob,"DOB label is not found.");
		softassert.assertEquals(labelTextPhone.getText(), phone,"Phone label is not found.");
		
		softassert.assertEquals(btnSave.getText(), btnTextSave,"Save button is not found.");
		softassert.assertEquals(btnCancel.getText(), btnTextCancel,"Cancel is not found.");
		
		softassert.assertAll();
		
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=7,groups="patient")
	public void checkEditPatientModalContentNegativeCase() throws InterruptedException {

		String userName = "Nurhamizmsani";
		String userEmail = "hamizal.com";
		String userId = "S983C";
		String userPhone = "98898";
		String userDOB = "196-16";
				
		String fullname ="Fulme:";
		String email = "E-m:";
		String password = "Pasrd:";
		String retypePassword = "Re-Tyssword:";
		String id = "N:";
		String dob = "Date orth:";
		String phone = "Phone er:"; 
		
		String btnTextSave="Save ges";
		String btnTextCancel = "cel";
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement editBtnElement = driver.findElement(By.xpath("//i[contains(@class,'fa-pencil')]"));
		editBtnElement.click();
		
		Thread.sleep(1000);
		
		WebElement inputTextName = driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement inputTextEmail = driver.findElement(By.id("patientEmail"));
		WebElement inputTextId = driver.findElement(By.id("patientId"));
		WebElement inputTextPhone = driver.findElement(By.id("patientPhone"));
		WebElement inputTextDOB = driver.findElement(By.id("patientDOB"));
		
		WebElement labelTextName = driver.findElement(By.xpath("//label[text()='Fullname: ']"));
		WebElement labelTextEmail = driver.findElement(By.xpath("//label[text()='E-mail:']"));
		WebElement labelTextPassword = driver.findElement(By.xpath("//label[text()='Password:']"));
		WebElement labelTextRetypePassword = driver.findElement(By.xpath("//label[contains(text(),'Re-Type Password:')]"));
		WebElement labelTextId = driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/label[1]"));
		WebElement labelTextDOB = driver.findElement(By.xpath("//label[contains(text(),'Date of birth:')]"));
		WebElement labelTextPhone = driver.findElement(By.xpath("//label[contains(text(),'Phone Number:')]"));
		
		WebElement btnSave = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		WebElement btnCancel = driver.findElement(By.xpath("//button[text()='Cancel']"));

		Thread.sleep(1000);
		
		softassert.assertNotEquals(inputTextName.getAttribute("ng-reflect-model"), userName);
		softassert.assertNotEquals(inputTextEmail.getAttribute("ng-reflect-model"), userEmail);
		softassert.assertNotEquals(inputTextId.getAttribute("ng-reflect-model"), userId);
		softassert.assertNotEquals(inputTextPhone.getAttribute("ng-reflect-model"), userPhone);
		softassert.assertNotEquals(inputTextDOB.getAttribute("ng-reflect-model"), userDOB);
		
		softassert.assertNotEquals(labelTextName.getText(), fullname);
		softassert.assertNotEquals(labelTextEmail.getText(), email);
		softassert.assertNotEquals(labelTextPassword.getText(), password);
		softassert.assertNotEquals(labelTextRetypePassword.getText(), retypePassword);
		softassert.assertNotEquals(labelTextId.getText(), id);
		softassert.assertNotEquals(labelTextDOB.getText(), dob);
		softassert.assertNotEquals(labelTextPhone.getText(), phone);
		
		softassert.assertNotEquals(btnSave.getText(), btnTextSave);
		softassert.assertNotEquals(btnCancel.getText(), btnTextCancel);
		
		softassert.assertAll();
		
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=8,groups="patient")
	public void checkEditPatientClickable() throws InterruptedException {

		String editBtnHeader = "Edit Patient";
		
		Thread.sleep(1000);WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement editBtnElement = driver.findElement(By.xpath("//i[contains(@class,'fa-pencil')]"));
		editBtnElement.click();
		
		WebElement editBtnHeaderElement = driver.findElement(By.xpath("//h5[text()='Edit Patient']"));
		System.out.println("EditBtnHeader "+ editBtnHeaderElement.getText());
		softassert.assertEquals(editBtnHeaderElement.getText(), editBtnHeader,"Opened the wrong modal.");
		
		softassert.assertAll();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=9,groups="patient")
	public void checkEditPatientInfomationPatternValidation() throws InterruptedException {

		Thread.sleep(1000);
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement editBtnElement = driver.findElement(By.xpath("//i[contains(@class,'fa-pencil')]"));
		editBtnElement.click();
		
		Thread.sleep(1000);
		
		WebElement inputTextName = driver.findElement(By.id("patientFullname"));
		WebElement inputTextEmail = driver.findElement(By.id("patientEmail"));
		WebElement inputTextPassword = driver.findElement(By.id("patientPassword"));
		WebElement inputTextRetypePassword = driver.findElement(By.id("patientReTypePassword"));
		WebElement inputTextId = driver.findElement(By.id("patientId"));
		WebElement inputTextPhone = driver.findElement(By.id("patientPhone"));
		WebElement inputTextDOB = driver.findElement(By.id("patientDOB"));
		
		inputTextName.clear();
		inputTextName.sendKeys("90210");
		
		inputTextEmail.clear();
		inputTextEmail.sendKeys("hello world");
		
		inputTextPassword.clear();
		inputTextPassword.sendKeys("123");
		
		inputTextRetypePassword.clear();
		inputTextRetypePassword.sendKeys("13");
		
		inputTextId.clear();
		inputTextId.sendKeys("w77");
		
		inputTextDOB.clear();
		inputTextDOB.sendKeys("08/09/2021");
		
		inputTextPhone.clear();
		inputTextPhone.sendKeys("wow");
		
		Thread.sleep(1000);
		
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Name may only contain alphabets.')]"))!=null,"Pattern validation for name is not shown.");
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Please enter the proper e-mail format. Eg.john.doe')]"))!=null,"Pattern validation for email is not shown.");
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Password have to be at least 8 characters long.')]"))!=null,"Pattern validation for password is not shown.");
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Password is not matching.')]"))!=null,"Pattern validation for retype password is not shown.");
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'NRIC should start with either S,T,F,G contains 7 d')]"))!=null,"Pattern validation for nric is not shown.");
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'You have to be at least 12 year old to register fo')]"))!=null,"Pattern validation for age is not shown.");
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Phone number should be 8 digits long and start wit')]"))!=null,"Pattern validation for phone is not shown.");

		softassert.assertAll();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=10,groups="patient")
	public void checkEditPatientInformationIsFilledValidationWithSaveChangesNegativeCase() throws InterruptedException {
		
		String alertText="Update failed. Please refresh page.";
		
		Thread.sleep(1000);
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement editBtnElement = driver.findElement(By.xpath("//i[contains(@class,'fa-pencil')]"));
		editBtnElement.click();
		
		Thread.sleep(1000);
		
		WebElement inputTextName = driver.findElement(By.id("patientFullname"));
		WebElement inputTextEmail = driver.findElement(By.id("patientEmail"));
		WebElement inputTextPassword = driver.findElement(By.id("patientPassword"));
		WebElement inputTextRetypePassword = driver.findElement(By.id("patientReTypePassword"));
		WebElement inputTextId = driver.findElement(By.id("patientId"));
		WebElement inputTextPhone = driver.findElement(By.id("patientPhone"));
		WebElement inputTextDOB = driver.findElement(By.id("patientDOB"));
		
		inputTextName.clear();
		inputTextEmail.clear();
		inputTextPassword.clear();
		inputTextRetypePassword.clear();
		inputTextId.clear();
		inputTextPhone.clear();
		inputTextDOB.clear();
		
		Thread.sleep(1000);
		
		WebElement btnSave = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		btnSave.click();
		
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText,"Wrong alert.");
		
		softassert.assertAll();
		
		alert.accept();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
		btnCancel.click();
		
		Thread.sleep(2000);		
	}
	
	@Test(priority=11,groups="patient")
	public void checkEditPatientSaveChangePositiveCase() throws InterruptedException {

		String userName = user.getFullname();
		String retypePassword=user.getPassword();
		String alertText = "Patient information have been updated.";
		
		Thread.sleep(1000);
		
		WebElement personalInfo = driver.findElement(By.xpath("//button[contains(text(),'Personal Information')]"));
		personalInfo.click();
		
		WebElement editBtnElement = driver.findElement(By.xpath("//i[contains(@class,'fa-pencil')]"));
		editBtnElement.click();
		
		WebElement inputTextName = driver.findElement(By.id("patientFullname"));
		WebElement inputTextEmail = driver.findElement(By.id("patientEmail"));
		WebElement inputTextPassword = driver.findElement(By.id("patientPassword"));
		WebElement inputTextRetypePassword = driver.findElement(By.id("patientReTypePassword"));
		WebElement inputTextId = driver.findElement(By.id("patientId"));
		WebElement inputTextPhone = driver.findElement(By.id("patientPhone"));
		WebElement inputTextDOB = driver.findElement(By.id("patientDOB"));
		
		inputTextName.clear();
		inputTextName.sendKeys(userName);
		
		inputTextRetypePassword.sendKeys(retypePassword);
		
		Thread.sleep(1000);
				
		WebElement btnSave = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		btnSave.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText,"Wrong pop up");
		
		softassert.assertAll();
		
		alert.accept();
		
		Thread.sleep(1000);
		
		WebElement inputTextNameEdited = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/span[1]"));
		
		softassert.assertEquals(inputTextNameEdited.getText(), userName,"Fullname is not the same.");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=12,groups="patient")
	public void checkNavigateToBookPage() throws InterruptedException {

		String bookPageText = "Book a slot";
		
		Thread.sleep(1000);
		
		WebElement bookPageBtn= driver.findElement(By.xpath("//button[contains(text(),'Book a Slot')]"));
		bookPageBtn.click();
		
		WebElement bookPageHeader= driver.findElement(By.xpath("//h3"));
		
		softassert.assertEquals(bookPageHeader.getText(), bookPageText,"Navigate to the wrong page");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=13,groups="patient")
	public void checkBookPageContent() throws InterruptedException {

		String fDLocationText= "First Dose Location:";
		String fDDateText= "Date:";
		String sDLocationText= "Second Dose Location:";
		String sDDateText= "Date:";
		String submitBtnText= "Submit";
		
		Thread.sleep(1000);
		
		WebElement bookPageBtn= driver.findElement(By.xpath("//button[contains(text(),'Book a Slot')]"));
		bookPageBtn.click();
		
		Thread.sleep(1000);
		
		js.executeScript("window.scrollBy(0,100)");
		
		Thread.sleep(1000);
		
		WebElement fDLocationElement= driver.findElement(By.xpath("//label[contains(text(),'First Dose Location:')]"));
		WebElement fDDateElement= driver.findElement(By.xpath("//label[@for='firstDoseDate']"));
		WebElement sDLocationElement= driver.findElement(By.xpath("//label[contains(text(),'Second Dose Location:')]"));
		WebElement sDDateElement= driver.findElement(By.xpath("//label[@for='secondDoseDate']"));
		WebElement submitBtnElement= driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		
		softassert.assertEquals(fDLocationElement.getText(), fDLocationText,"First dose label is not the same.");
		softassert.assertEquals(fDDateElement.getText(), fDDateText,"First data label is not the same.");
		softassert.assertEquals(sDLocationElement.getText(), sDLocationText,"Second dose label is not the same.");
		softassert.assertEquals(sDDateElement.getText(), sDDateText,"Second date label is not the same.");
		softassert.assertEquals(submitBtnElement.getText(), submitBtnText,"Submit button text is not the same.");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=14,groups="patient")
	public void checkSubmitBookSlotNegativeCase() throws InterruptedException {

		Thread.sleep(1000);
		
		WebElement bookPageBtn= driver.findElement(By.xpath("//button[contains(text(),'Book a Slot')]"));
		bookPageBtn.click();
		
		Thread.sleep(500);
		
		WebElement fDLocation= driver.findElement(By.xpath("//select[@id='bookFirstDose']"));
		Select selectFLocation = new Select(fDLocation);
		selectFLocation.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(100);

		WebElement submitBtn= driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		submitBtn.click();
		
		Thread.sleep(1000);
		
		softassert.assertTrue(submitBtn.getAttribute("disabled")!=null,"Submit button is not disabled.");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=15,groups="patient")
	public void checkSubmitBookSlotPositiveCase() throws InterruptedException {

		String alertText="Vaccination schedule have been updated.";
		
		Thread.sleep(1000);
		
		WebElement bookPageBtn= driver.findElement(By.xpath("//button[contains(text(),'Book a Slot')]"));
		bookPageBtn.click();
		
		WebElement fDLocation= driver.findElement(By.xpath("//select[@id='bookFirstDose']"));
		WebElement fDDate= driver.findElement(By.xpath("//input[@id='firstDateBook']"));
		WebElement sDLocation= driver.findElement(By.xpath("//select[@id='bookSecondDose']"));
		WebElement sDDate= driver.findElement(By.xpath("//input[@id='secondDateBook']"));

		Thread.sleep(1000);
		
		Select selectFirstLocation = new Select(fDLocation);
		selectFirstLocation.selectByVisibleText("Bedok Polyclinic");
		
		fDDate.sendKeys("20/12/2021");
		
		js.executeScript("window.scrollBy(0,100)");
		
		WebElement fDTime= driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[4]/div[8]"));
		WebElement fDTimeChild = fDTime.findElement(By.xpath(".//*"));
		fDTimeChild.click();
		
		Thread.sleep(1000);
		
		Select selectSecondLocation = new Select(sDLocation);
		selectSecondLocation.selectByVisibleText("Bedok Polyclinic");
		
		sDDate.sendKeys("28/01/2022");
		
		js.executeScript("window.scrollBy(0,200)");

		Thread.sleep(2000);
		
		WebElement sDTime= driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[8]/div[3]"));
		WebElement sDTimeChild = sDTime.findElement(By.xpath(".//*"));
		sDTimeChild.click();
		
		Thread.sleep(1000);

		WebElement submitBtn= driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		submitBtn.click();
		
		Thread.sleep(1000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText,"Wrong pop up.");
		
		softassert.assertAll();
		
		alert.accept();
	}
	
	@Test(priority=16,groups="patient")
	public void checkSubmitBookSlotWithValidationNegativeCase() throws InterruptedException {
		
		String alertText="Failed to book slots. Please refresh page.";
		String dateIntervalText = "Dates between your first & second does have to at least be 30 days apart.";
		String diffCText ="*Note: Location for first and second vaccination is different.";
		
		Thread.sleep(1000);
		
		WebElement bookPageBtn= driver.findElement(By.xpath("//button[contains(text(),'Book a Slot')]"));
		bookPageBtn.click();
		
		WebElement fDLocation= driver.findElement(By.xpath("//select[@id='bookFirstDose']"));
		WebElement fDDate= driver.findElement(By.xpath("//input[@id='firstDateBook']"));
		WebElement sDLocation= driver.findElement(By.xpath("//select[@id='bookSecondDose']"));
		WebElement sDDate= driver.findElement(By.xpath("//input[@id='secondDateBook']"));

		Thread.sleep(1000);
		Select selectFirstLocation = new Select(fDLocation);
		selectFirstLocation.selectByVisibleText("Bedok Polyclinic");
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fdateString = formatter.format(date);
		
		fDDate.sendKeys(fdateString);
		
		js.executeScript("window.scrollBy(0,100)");
		
		WebElement fDTime= driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[4]/div[8]"));
		WebElement fDTimeChild = fDTime.findElement(By.xpath(".//*"));
		fDTimeChild.click();
		
		Thread.sleep(1000);
		
		Select selectSecondLocation = new Select(sDLocation);
		selectSecondLocation.selectByVisibleText("Acumed Medical (Taman Jurong)");
		
		Thread.sleep(1000);
		
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'*Note: Location for first and second vaccination i')]\r\n"))!=null);
		
		WebElement locationValidation= driver.findElement(By.xpath("//small[contains(text(),'*Note: Location for first and second vaccination i')]\r\n"));
		
		softassert.assertEquals(locationValidation.getText(), diffCText);
		
		calendar.add(Calendar.DATE, -10);
		date = calendar.getTime();
		String sdateString = formatter.format(date);
		
		sDDate.sendKeys(sdateString);
		
		js.executeScript("window.scrollBy(0,200)");

		Thread.sleep(1000);
		
		WebElement sDTime= driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[8]/div[3]"));
		WebElement sDTimeChild = sDTime.findElement(By.xpath(".//*"));
		sDTimeChild.click();
		
		softassert.assertTrue(driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/small[1]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[6]/small[1]"))!=null);
		
		WebElement dateValidation1= driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/small[1]"));
		WebElement dateValidation2= driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/book-vaccine-slot[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[6]/small[1]"));

		softassert.assertEquals(dateValidation1.getText(), dateIntervalText);
		softassert.assertEquals(dateValidation2.getText(), dateIntervalText);
		
		Thread.sleep(1000);

		WebElement submitBtn= driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		submitBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		softassert.assertEquals(alert.getText(), alertText);
		softassert.assertAll();
		alert.accept();
	}
	
	@Test(priority=17,groups="patient")
	public void checkViewVaccinationContent() throws InterruptedException {
		
		String userFVcentre =user.getfCentre();
		String userFDTime= user.getfDate()+", "+user.getfTime();
		String userFDose= user.getfDose().toString();
		String userSVcentre =user.getsCentre();
		String userSDTime=user.getsDate()+", "+user.getsTime();
		String userSDose=user.getsDose().toString();
		
		String fVcentre ="First Vaccination Centre:";
		String fDTime="First Date & Time:";
		String fDose="First Dose:";
		String sVcentre ="Second Vaccination Centre:";
		String sDTime="Second Second Date & Time:";
		String sDose="Second Dose:";
		
		Thread.sleep(1000);
		
		WebElement viewVScheduleBtn = driver.findElement(By.xpath("//button[contains(text(),'View Vaccination Schedule')]"));
		viewVScheduleBtn.click();
		
		WebElement userFVcentreElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/span[1]"));
		WebElement userFDTimeElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/span[1]"));
		WebElement userFDoseElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/span[1]"));
		WebElement userSVcentreElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/div[1]/span[1]"));
		WebElement userSDTimeElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[6]/div[1]/span[1]"));
		WebElement userSDoseElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[7]/div[1]/span[1]"));
		
		WebElement fVcentreElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/label[1]"));
		WebElement fDTimeElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/label[1]"));
		WebElement fDoseElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/label[1]"));
		WebElement sVcentreElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/div[1]/label[1]"));
		WebElement sDTimeElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[6]/div[1]/label[1]"));
		WebElement sDoseElement = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[7]/div[1]/label[1]"));

		Thread.sleep(1000);
		
		softassert.assertEquals(userFVcentreElement.getText(), userFVcentre,"First centre is not the same.");
		softassert.assertEquals(userFDTimeElement.getText(), userFDTime,"First time is not the same.");
		softassert.assertEquals(userFDoseElement.getText(), userFDose,"First dose is not the same.");
		softassert.assertEquals(userSVcentreElement.getText(), userSVcentre,"Second centre is not the same.");
		softassert.assertEquals(userSDTimeElement.getText(), userSDTime,"Second time is not the same.");
		softassert.assertEquals(userSDoseElement.getText(), userSDose,"Second dose is not the same.");
		
		softassert.assertEquals(fVcentreElement.getText(), fVcentre,"First centre label is not the same.");
		softassert.assertEquals(fDTimeElement.getText(), fDTime,"First tim label is not the same.");
		softassert.assertEquals(fDoseElement.getText(), fDose,"First dose label is not the same.");
		softassert.assertEquals(sVcentreElement.getText(), sVcentre,"Second centre label is not the same.");
		softassert.assertEquals(sDTimeElement.getText(), sDTime,"Second time label is not the same.");
		softassert.assertEquals(sDoseElement.getText(), sDose,"Second dose label is not the same.");
		
		WebElement iconElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[7]/div[2]/button[1]/span[1]/i[2]"));
		
		softassert.assertTrue(iconElement != null,"Icon is missing");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=18,groups="patient")
	public void checkEditScheduleContent() throws InterruptedException {
		
		String editPatientHeader = "Edit Patient Schedule";
		String fVCentre = "First Vaccination Centre:";
		String fVDate = "First Vaccination Date:";
		String fVTime = "First Vaccination Time:";
		String sVCentre = "Second Vaccination Centre:";
		String sVDate = "Second Vaccination Date:";
		String sVTime = "Second Vaccination Time:";
		String saveChangesBtn = "Save Changes";
		String cancelBtn = "Cancel";
		
		Thread.sleep(1000);
		
		WebElement viewVScheduleBtn = driver.findElement(By.xpath("//button[contains(text(),'View Vaccination Schedule')]"));
		viewVScheduleBtn.click();
		
		WebElement iconElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[7]/div[2]/button[1]/span[1]/i[2]"));
		iconElement.click();
		
		Thread.sleep(500);
		
		WebElement editPatientHeaderElement = driver.findElement(By.xpath("//h5[@id='staticBackdropLabel']"));
		WebElement fVCentreElement = driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/label[1]"));
		WebElement fVDateElement = driver.findElement(By.xpath("//label[contains(text(),'First Vaccination Date:')]"));
		WebElement fVTimeElement = driver.findElement(By.xpath("//label[contains(text(),'First Vaccination Time:')]"));
		WebElement sVCentreElement = driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/label[1]"));
		WebElement sVDateElement = driver.findElement(By.xpath("//label[contains(text(),'Second Vaccination Date:')]"));
		WebElement sVTimeElement = driver.findElement(By.xpath("//label[contains(text(),'Second Vaccination Time:')]"));
		WebElement saveChangesBtnElement = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		WebElement cancelBtnElement = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		
		softassert.assertEquals(editPatientHeaderElement.getText(), editPatientHeader,"Header is not the same.");
		softassert.assertEquals(fVCentreElement.getText(), fVCentre,"First centre is not the same.");
		softassert.assertEquals(fVDateElement.getText(), fVDate,"First date is not the same.");
		softassert.assertEquals(fVTimeElement.getText(), fVTime,"Firs time is not the same.");
		softassert.assertEquals(sVCentreElement.getText(), sVCentre,"Secnond centre is not the same.");
		softassert.assertEquals(sVDateElement.getText(), sVDate,"Secnond date is not the same.");
		softassert.assertEquals(sVTimeElement.getText(), sVTime,"Second time is not the same.");
		softassert.assertEquals(saveChangesBtnElement.getText(), saveChangesBtn,"Save button text is not the same.");
		softassert.assertEquals(cancelBtnElement.getText(), cancelBtn,"Cancel button text is not the same.");
		
		softassert.assertAll();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=19,groups="patient")
	public void checkEditModalPositiveCase() throws InterruptedException {

		String alertText = "Patient schedule have been updated.";
		
		Thread.sleep(1000);
		
		WebElement viewVScheduleBtn = driver.findElement(By.xpath("//button[contains(text(),'View Vaccination Schedule')]"));
		viewVScheduleBtn.click();
		
		WebElement iconElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[7]/div[2]/button[1]/span[1]/i[2]"));
		iconElement.click();
		
		WebElement fVCentre = driver.findElement(By.xpath("//select[@id='bookFirstCentre']"));
		WebElement fVDate = driver.findElement(By.xpath("//input[@name='patientFirstDoseDate']"));
		WebElement fVTime = driver.findElement(By.xpath("//input[@name='patientFirstDoseTime']"));
		WebElement sVCentre = driver.findElement(By.xpath("//select[@id='bookSecondCentre']"));
		WebElement sVDate = driver.findElement(By.xpath("//input[@name='patientSecondDoseDate']"));
		WebElement sVTime = driver.findElement(By.xpath("//input[@name='patientSecondDoseTime']"));

		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		
		Select selectFirstLocation = new Select(fVCentre);
		selectFirstLocation.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(500);
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fdateString = formatter.format(date);
		
		fVDate.sendKeys(fdateString);
		
		fVTime.sendKeys("13:00");
		
		WebElement fVTimeElement = driver.findElement(By.xpath("//label[contains(text(),'First Vaccination Time:')]"));
		fVTimeElement.click();
				
		Select selectSecondLocation = new Select(sVCentre);
		selectSecondLocation.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(500);
		
		calendar.add(Calendar.DATE, 34);
		date = calendar.getTime();
		String sdateString = formatter.format(date);
		
		sVDate.sendKeys(sdateString);
		
		sVTime.sendKeys("15:00");
		
		WebElement sVTimeElement = driver.findElement(By.xpath("//label[contains(text(),'Second Vaccination Time:')]"));
		sVTimeElement.click();
		
		Thread.sleep(1000);
		
		saveChangesBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
	
		softassert.assertEquals(alert.getText(), alertText,"Wrong pop up.");
		
		softassert.assertAll();
		
		alert.accept();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=20,groups="patient")
	public void checkEditModalWithValidationNegativeCase() throws InterruptedException {

		String alertText = "Update failed. Please refresh page.";
		String dateIntervalText = "Dates between your first & second does have to at least be 30 days apart.";
		String diffCText ="*Note: Location for first and second vaccination is different.";
		
		Thread.sleep(1000);
		
		WebElement viewVScheduleBtn = driver.findElement(By.xpath("//button[contains(text(),'View Vaccination Schedule')]"));
		viewVScheduleBtn.click();
		
		WebElement iconElement = driver.findElement(By.xpath("//body/app-root[1]/patient-page[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[7]/div[2]/button[1]/span[1]/i[2]"));
		iconElement.click();
		
		WebElement  fVCentre = driver.findElement(By.xpath("//select[@id='bookFirstCentre']"));
		WebElement  fVDate = driver.findElement(By.xpath("//input[@name='patientFirstDoseDate']"));
		WebElement  fVTime = driver.findElement(By.xpath("//input[@name='patientFirstDoseTime']"));
		WebElement  sVCentre = driver.findElement(By.xpath("//select[@id='bookSecondCentre']"));
		WebElement  sVDate = driver.findElement(By.xpath("//input[@name='patientSecondDoseDate']"));
		WebElement  sVTime = driver.findElement(By.xpath("//input[@name='patientSecondDoseTime']"));

		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		
		Select selectFirstLocation = new Select(fVCentre);
		selectFirstLocation.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(500);
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fdateString = formatter.format(date);
		
		fVDate.sendKeys(fdateString);
		
		fVTime.sendKeys("14:00");
		
		WebElement fVTimeElement = driver.findElement(By.xpath("//label[contains(text(),'First Vaccination Time:')]"));
		fVTimeElement.click();
		
		Thread.sleep(500);
		
		Select selectSecondLocation = new Select(sVCentre);
		selectSecondLocation.selectByVisibleText("Acumed Medical (Taman Jurong)");
		
		Assert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'*Note: Location for first and second vaccination i')]"))!=null);

		WebElement locationValidation= driver.findElement(By.xpath("//small[contains(text(),'*Note: Location for first and second vaccination i')]"));
		
		softassert.assertEquals(locationValidation.getText(), diffCText);
		
		calendar.add(Calendar.DATE, 20);
		date = calendar.getTime();
		String sdateString = formatter.format(date);
		
		sVDate.sendKeys(sdateString);
		
		softassert.assertTrue(driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/small[1]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/small[1]"))!=null);
		
		WebElement dateValidation1= driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/small[1]"));
		WebElement dateValidation2= driver.findElement(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/small[1]"));
		
		softassert.assertEquals(dateValidation1.getText(), dateIntervalText);
		softassert.assertEquals(dateValidation2.getText(), dateIntervalText);
		
		softassert.assertAll();
		
		sVTime.sendKeys("16:00");
		
		WebElement sVTimeElement = driver.findElement(By.xpath("//label[contains(text(),'Second Vaccination Time:')]"));
		sVTimeElement.click();
		
		Thread.sleep(1000);
		
		saveChangesBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
	
		softassert.assertEquals(alert.getText(), alertText);
		
		softassert.assertAll();
		
		alert.accept();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=21,groups="admin")
	public void checkSessionAdmin() throws InterruptedException {
		
		String userName = user.getId();
		
		Thread.sleep(1000);
		
		WebElement smallSessionText = driver.findElement(By.xpath("//small"));
		smallSessionText.getText();
		
		softassert.assertTrue(smallSessionText.getText().contains(userName),"Wrong session");
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=22,groups="admin")
	public void checkAdminPagePositiveCase() throws InterruptedException {
		
		String headerText = "Admin Page";
		
		Thread.sleep(1000);
		
		WebElement header = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[1]/h2[1]"));
		
		softassert.assertEquals(header.getText(), headerText,"Navigate to the wrong page.");
		
		softassert.assertAll();
	}
	
	@Test(priority=23,groups="admin")
	public void checkAdminPageNegativeCase() throws InterruptedException {
		
		String headerText = "in Page";
		
		Thread.sleep(1000);
		
		WebElement header = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[1]/h2[1]"));
		
		softassert.assertNotEquals(header.getText(), headerText);
		
		softassert.assertAll();
	}
	
	@Test(priority=24,groups="admin")
	public void checkAdminTab() throws InterruptedException {

		String patientTabText= "Patients";
		String doctorTabText = "Doctors";
		String centreTabText = "Vaccination Centres";
		String feedbackTabText = "Feedbacks";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		WebElement doctorTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[2]"));
		WebElement centreTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[3]"));
		WebElement feedbackTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[4]"));
		
		patientTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement patientTabHeader= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/h3[1]"));
		
		softassert.assertEquals(patientTabHeader.getText(), patientTabText,"Navigate to the wrong tab.");
		
		doctorTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement doctorTabHeader= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/h3[1]"));
		
		softassert.assertEquals(doctorTabHeader.getText(), doctorTabText,"Navigate to the wrong tab.");

		centreTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement centreTabHeader= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/h3[1]"));
		
		softassert.assertEquals(centreTabHeader.getText(), centreTabText,"Navigate to the wrong tab.");

		feedbackTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement feedbackTabHeader= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/h3[1]"));
		
		softassert.assertEquals(feedbackTabHeader.getText(), feedbackTabText,"Navigate to the wrong tab.");
		
		softassert.assertAll();
		
		Thread.sleep(1000);	
	}
	
	@Test(priority=25,groups="admin")
	public void checkPatientTabContent() throws InterruptedException {
		
		String fullname = "Fullname";
		String email = "Email";
		String phone = "Phone";
		String dob = "Date of Birth";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement fullnameHeader= driver.findElement(By.xpath("//thead//tr//th//span[contains(text(),'Fullname')]"));
		WebElement emailHeader= driver.findElement(By.xpath("//thead//tr//th//span[contains(text(),'Email')]"));
		WebElement phoneHeader= driver.findElement(By.xpath("//thead//tr//th//span[contains(text(),'Phone')]"));
		WebElement dobHeader= driver.findElement(By.xpath("//thead//tr//th//span[contains(text(),'Date of')]"));
		
		Thread.sleep(1000);
		
		softassert.assertEquals(fullnameHeader.getText(), fullname,"Fullname header is missing.");
		softassert.assertEquals(emailHeader.getText(), email,"Email header is missing.");
		softassert.assertEquals(phoneHeader.getText(), phone,"Phone header is missing.");
		softassert.assertEquals(dobHeader.getText(), dob,"Dob header is missing.");
				
		softassert.assertTrue(driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[1]/span[1]/i[2]"))!=null,"Details icon is missing.");
		softassert.assertTrue(driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[2]/span[1]/i[2]"))!=null,"Edit icon is missing.");
		softassert.assertTrue(driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[3]/span[1]/i[2]"))!=null,"Delete icon is missing.");
		
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,500)");	
		
		softassert.assertAll();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=26,groups="admin")
	public void checkDetailsIconClickable() throws InterruptedException {
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();
		
		WebElement iconDetails= driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[1]/span[1]/i[2]"));
		iconDetails.click();
		
		Thread.sleep(1000);
		
		WebElement closeBtn= driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
		closeBtn.click();
	}
	
	@Test(priority=27,groups="admin")
	public void checkDetailsContentWithVaccCentre() throws InterruptedException{
		
		String detailsHeaderText = "Patients Details";
		String fullnameText = "Fullname:";
		String idText = "NRIC:";
		String emailText = "E-mail:";
		String phoneText = "Phone:";
		String dobText = "Date of Birth:";
		String fDoseText = "First Dose:";
		String fDDateText = "First Dose Date:";
		String fDTimeText = "First Dose Time:";
		String sDoseText = "Second Dose:";
		String sDDateText = "Second Dose Date:";
		String sDTimeText = "Second Dose Time:";
		String fDIsDoneText = "First Dose is Done:";
		String sDIsDoneText = "Second Dose is Done:";
		
		
		// data is already in database
		String fullname = "Constanta Preskett";
		String id = "G5535348F";
		String email = "cpreskett1@hp.com";
		String phone = "88910803";
		String dob = "1998-01-01";
		String fDose = "Bedok Polyclinic";
		String fDDate = "2021-06-24";
		String fDTime= "13:00";
		String sDose= "Bedok Polyclinic";
		String sDDate= "2021-07-24";
		String sDTime= "20:00";
		String fDIsDone= "true";
		String sDIDone= "true";
		
		String closeBtn= "Close";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();
		
		WebElement iconDetails= driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[1]/span[1]/i[2]"));
		iconDetails.click();
		
		Thread.sleep(2000);
		
		WebElement detailsHeaderLabel=driver.findElement(By.xpath("//h5[@id='staticBackdropLabel']"));
		WebElement fullnameLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/label[1]"));
		WebElement idLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/label[1]"));
		WebElement emailLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/div[1]/label[1]"));
		WebElement phoneLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/div[2]/label[1]"));
		WebElement dobLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[3]/div[1]/label[1]"));
		WebElement fDoseLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/div[1]/label[1]"));
		WebElement fDDateLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/div[1]/label[1]"));
		WebElement fDTimeLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[6]/div[1]/label[1]"));
		WebElement sDoseLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[7]/div[1]/label[1]"));
		WebElement sDDateLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[8]/div[1]/label[1]"));
		WebElement sDTimeLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[9]/div[1]/label[1]"));
		WebElement fDIsDoneLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[10]/div[1]/label[1]"));
		WebElement sDIsDoneLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[11]/div[1]/label[1]"));
		
		WebElement fullnameElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/span[1]"));
		WebElement idElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/span[1]"));
		WebElement emailElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/div[1]/span[1]"));
		WebElement phoneElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/div[2]/span[1]"));
		WebElement dobElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[3]/div[1]/span[1]"));
		WebElement fDoseElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/div[1]/span[1]"));
		WebElement fDDateElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/div[1]/span[1]"));
		WebElement fDTimeElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[6]/div[1]/span[1]"));
		WebElement sDoseElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[7]/div[1]/span[1]"));
		WebElement sDDateElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[8]/div[1]/span[1]"));
		WebElement sDTimeElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[9]/div[1]/span[1]"));
		WebElement fDIsDoneElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[10]/div[1]/span[1]"));
		WebElement sDIDoneElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[11]/div[1]/span[1]"));
		
		WebElement closeBtnElement = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
		
		softassert.assertEquals(detailsHeaderLabel.getText(), detailsHeaderText);
		softassert.assertEquals(fullnameLabel.getText(), fullnameText);
		softassert.assertEquals(idLabel.getText(), idText);
		softassert.assertEquals(emailLabel.getText(), emailText);
		softassert.assertEquals(phoneLabel.getText(), phoneText);
		softassert.assertEquals(dobLabel.getText(), dobText);
		softassert.assertEquals(fDoseLabel.getText(), fDoseText);
		softassert.assertEquals(fDDateLabel.getText(), fDDateText);
		softassert.assertEquals(fDTimeLabel.getText(), fDTimeText);
		softassert.assertEquals(sDoseLabel.getText(), sDoseText);
		softassert.assertEquals(sDDateLabel.getText(), sDDateText);
		softassert.assertEquals(sDTimeLabel.getText(), sDTimeText);
		softassert.assertEquals(fDIsDoneLabel.getText(), fDIsDoneText);
		softassert.assertEquals(sDIsDoneLabel.getText(), sDIsDoneText);
		
		softassert.assertEquals(fullnameElement.getText(), fullname);
		softassert.assertEquals(idElement.getText(), id);
		softassert.assertEquals(emailElement.getText(), email);
		softassert.assertEquals(phoneElement.getText(), phone);
		softassert.assertEquals(dobElement.getText(), dob);
		softassert.assertEquals(fDoseElement.getText(), fDose);
		softassert.assertEquals(fDDateElement.getText(), fDDate);
		softassert.assertEquals(fDTimeElement.getText(), fDTime);
		softassert.assertEquals(sDoseElement.getText(), sDose);
		softassert.assertEquals(sDDateElement.getText(), sDDate);
		softassert.assertEquals(sDTimeElement.getText(), sDTime);
		softassert.assertEquals(fDIsDoneElement.getText(), fDIsDone);
		softassert.assertEquals(sDIDoneElement.getText(), sDIDone);
		
		softassert.assertEquals(closeBtnElement.getText(), closeBtn);
		
		softassert.assertAll();
		
		WebElement btnClose = driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
		btnClose.click();
		
		Thread.sleep(1000);
		
	}
	
	@Test(priority=28,groups="admin")
	public void checkEditIconeClickable() throws InterruptedException {
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconEdit= driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[2]/span[1]/i[2]"));
		iconEdit.click();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=29,groups="admin")
	public void checkEditContent() throws InterruptedException {
		
		String detailsHeaderText = "Edit Patient";
		String fullnameText = "Fullname:";
		String idText = "NRIC:";
		String emailText = "E-mail:";
		String passwordText = "Password:";
		String retypeText = "Re-Type Password:";
		String phoneText = "Phone Number:";
		String dobText = "Date of birth:";
		String fVCentreText = "First Vaccination Centre:";
		String fVDateText = "First Vaccination Date:";
		String fVTimeText = "First Vaccination Time:";
		String sVCentreText = "Second Vaccination Centre:";
		String sVDateText = "Second Vaccination Date:";
		String sVTimeText = "Second Vaccination Time:";
		String fDoseText = "First Dose: ";
		String sDoseText = "Second Dose: ";
		
		String saveChangesBtnText = "Save Changes";
		String cancelBtnText = "Cancel";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconEdit= driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[2]/span[1]/i[2]"));
		iconEdit.click();
		
		Thread.sleep(1000);
		
		WebElement detailsHeaderLabel=driver.findElement(By.xpath("//h5[@id='staticBackdropLabel']"));
		WebElement fullnameLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/label[1]"));
		WebElement idLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/label[1]"));
		WebElement emailLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/label[1]"));
		WebElement passwordLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[3]/label[1]"));
		WebElement retypeLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/label[1]"));
		WebElement phoneLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[7]/label[1]"));
		WebElement dobLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[6]/label[1]"));
		WebElement fVCentreLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[8]/label[1]"));
		WebElement fVDateLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[9]/label[1]"));
		WebElement fVTimeLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[10]/label[1]"));
		WebElement sVCentreLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[11]/label[1]"));
		WebElement sVDateLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[12]/label[1]"));
		WebElement sVTimeLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[13]/label[1]"));
		WebElement fDoseLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[14]/label[1]"));
		WebElement sDoseLabel=driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[14]/label[3]"));
		
		WebElement submitChangesBtn = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
		WebElement cancelBtn = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[2]"));
		
		softassert.assertEquals(detailsHeaderLabel.getText(), detailsHeaderText);
		softassert.assertEquals(fullnameLabel.getText(), fullnameText);
		softassert.assertEquals(idLabel.getText(), idText);
		softassert.assertEquals(emailLabel.getText(), emailText);
		softassert.assertEquals(passwordLabel.getText(), passwordText);
		softassert.assertEquals(retypeLabel.getText(), retypeText);
		softassert.assertEquals(phoneLabel.getText(), phoneText);
		softassert.assertEquals(dobLabel.getText(), dobText);
		softassert.assertEquals(fVCentreLabel.getText(), fVCentreText);
		softassert.assertEquals(fVDateLabel.getText(), fVDateText);
		softassert.assertEquals(fVTimeLabel.getText(), fVTimeText);
		softassert.assertEquals(sVCentreLabel.getText(), sVCentreText);
		softassert.assertEquals(sVDateLabel.getText(), sVDateText);
		softassert.assertEquals(sVTimeLabel.getText(), sVTimeText);
		softassert.assertEquals(fDoseLabel.getText(), fDoseText);
		softassert.assertEquals(sDoseLabel.getText(), sDoseText);
		
		softassert.assertEquals(submitChangesBtn.getText(), saveChangesBtnText);
		softassert.assertEquals(cancelBtn.getText(), cancelBtnText);
		
		softassert.assertAll();
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		btnCancel.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=30,groups="admin")
	public void checkEditInformationWithFilledPositiveCase() throws InterruptedException {
		
		String alertText="Patient has been updated.";
		
		String userNameChanged="Con D'Orianno";
		String userEmailChanged="conWillOfD2@wikimedia.org";
		String userPhoneChanged="98726544";
		
		
		//data is already in database
		String userNameOri = "Creighton O'Corren";
		String userEmailOri = "cocorren2@wikimedia.org";
		String userPhoneOri = "94904820";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconDetails= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[1]/span[1]/i[2]"));
		iconDetails.click();
		
		Thread.sleep(1000);
		
		WebElement closeBtn = driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
		closeBtn.click();
		
		Thread.sleep(1000);
		
		WebElement iconEdit= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[2]/span[1]/i[2]"));
		iconEdit.click();
		
		Thread.sleep(1000);
		
		WebElement nameElement = driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement emailElement = driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement phoneElement = driver.findElement(By.xpath("//input[@id='patientPhone']"));
		WebElement retypeElement = driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));

		nameElement.clear();
		nameElement.sendKeys(userNameChanged);
		
		emailElement.clear();
		emailElement.sendKeys(userEmailChanged);
		
		phoneElement.clear();
		phoneElement.sendKeys(userPhoneChanged);
		
		retypeElement.sendKeys("5nqhLZzZKSI");
		
		Thread.sleep(2000);
		
		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		saveChangesBtn.click();

		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText,"Wrong pop up.");
		
		softassert.assertAll();
		
		alert.accept();
		
		Thread.sleep(1000);
		
		
		WebElement iconDetailsAfter= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[1]/span[1]/i[2]"));
		iconDetailsAfter.click();
		
		Thread.sleep(3000);
		
		WebElement nameDetailsAfter = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/span[1]"));
		WebElement emailDetailsAfter = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/div[1]/span[1]"));
		WebElement phoneDetailsAfter = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/div[2]/span[1]"));

		Assert.assertNotEquals(nameDetailsAfter.getText(),userNameOri);
		Assert.assertNotEquals(emailDetailsAfter.getText(),userEmailOri);
		Assert.assertNotEquals(phoneDetailsAfter.getText(),userPhoneOri);
		
		//setting details to orginal details
		WebElement closeBtnAfter = driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
		closeBtnAfter.click();
		
		WebElement iconEditAfter= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[2]/span[1]/i[2]"));
		iconEditAfter.click();
		
		Thread.sleep(2000);
		
		WebElement nameElementAfter = driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement emailElementAfter = driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement phoneElementAfter = driver.findElement(By.xpath("//input[@id='patientPhone']"));
		WebElement retypeElementAfter = driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));

		nameElementAfter.clear();
		nameElementAfter.sendKeys(userNameOri);
		
		emailElementAfter.clear();
		emailElementAfter.sendKeys(userEmailOri);
		
		phoneElementAfter.clear();
		phoneElementAfter.sendKeys(userPhoneOri);
		
		retypeElementAfter.sendKeys("5nqhLZzZKSI");
		
		WebElement saveChangesBtnAfter = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		saveChangesBtnAfter.click();
		
		Thread.sleep(3000);
		
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
		 
		Thread.sleep(3000);
	}
	
	@Test(priority=31,groups="admin")
	public void checkEditSchedulePositiveCase() throws InterruptedException {
		
		String alertText="Patient has been updated.";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconEdit= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[2]/span[1]/i[2]"));
		iconEdit.click();
		
		Thread.sleep(1000);
		
		WebElement fVCentre= driver.findElement(By.xpath("//select[@id='bookFirstDose']"));
		WebElement fVDate= driver.findElement(By.xpath("//input[@name='patientFirstDate']"));
		WebElement fVTime= driver.findElement(By.xpath("//input[@name='patientFirstTime']"));
		
		WebElement sVCentre= driver.findElement(By.xpath("//select[@id='patientSecondCentre']"));
		WebElement sVDate= driver.findElement(By.xpath("//input[@name='patientSecondDate']"));
		WebElement sVTime= driver.findElement(By.xpath("//input[@name='patientSecondTime']"));
		
		Select selectFCentre = new Select(fVCentre);
		selectFCentre.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(1000);
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 5);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String firstDate = formatter.format(date);
		
		fVDate.sendKeys(firstDate);
		fVTime.sendKeys("12:00");
		
		Select selectSCentre = new Select(sVCentre);
		selectSCentre.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(1000);
		
		calendar.add(Calendar.DATE, 35);
		date = calendar.getTime();
		String secondDate = formatter.format(date);
		
		sVDate.sendKeys(secondDate);
		sVTime.sendKeys("14:00");
		
		WebElement retypeElement = driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		retypeElement.sendKeys("5nqhLZzZKSI");
		
		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		saveChangesBtn.click();

		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText,"Wrong pop up.");
		
		softassert.assertAll();
		
		alert.accept();
		
		Thread.sleep(1000);
		
		//setting back the centre location
		
		WebElement iconEditAfter= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[2]/span[1]/i[2]"));
		iconEditAfter.click();
		
		Thread.sleep(2000);
		
		WebElement fVCentreAfter= driver.findElement(By.xpath("//select[@id='bookFirstDose']"));
		WebElement fVDateAfter= driver.findElement(By.xpath("//input[@name='patientFirstDate']"));
		WebElement fVTimeAfter= driver.findElement(By.xpath("//input[@name='patientFirstTime']"));
		
		WebElement sVCentreAfter= driver.findElement(By.xpath("//select[@id='patientSecondCentre']"));
		WebElement sVDateAfter= driver.findElement(By.xpath("//input[@name='patientSecondDate']"));
		WebElement sVTimeAfter= driver.findElement(By.xpath("//input[@name='patientSecondTime']"));
		
		Select selectFCentreAfter = new Select(fVCentreAfter);
		selectFCentreAfter.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(1000);
		
		fVDateAfter.sendKeys("14/11/2021");
		fVTimeAfter.sendKeys("14:00");
		
		Select selectSCentreAfter = new Select(sVCentreAfter);
		selectSCentreAfter.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(1000);
		
		sVDateAfter.sendKeys("15/12/2021");
		sVTimeAfter.sendKeys("16:00");
		
		WebElement retypeElementAfter = driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		retypeElementAfter.sendKeys("5nqhLZzZKSI");
		
		WebElement saveChangesBtnAfter = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		saveChangesBtnAfter.click();

		Thread.sleep(3000);
		
		Alert alertAfter = driver.switchTo().alert();
				
		alertAfter.accept();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=32,groups="admin")
	public void checkEditInfomationWithValidationEmptyNegativeCase() throws InterruptedException {
		
		String alertText="Update failed.Please refresh page.";
		
		String nameRequired = "Name is required.";
		String emailRequired = "E-mail is required.";
		String passwordRequired = "Password is required.";
		String retypeRequired = "Please re-enter the password.";
		String idRequired = "NRIC is required.";
		String dobRequired = "Date of birth is required.";
		String phoneRequired = "Phone is required.";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconEdit= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[2]/span[1]/i[2]"));
		iconEdit.click();
		
		Thread.sleep(2000);
		
		WebElement nameElement = driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement emailElement = driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement phoneElement = driver.findElement(By.xpath("//input[@id='patientPhone']"));
		WebElement passwordElement = driver.findElement(By.xpath("//input[@id='patientPassword']"));
		WebElement retypeElement = driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		WebElement idElement = driver.findElement(By.xpath("//input[@id='patientId']"));
		WebElement dobElement = driver.findElement(By.xpath("//input[@id='patientDOB']"));

		nameElement.clear();
		emailElement.click();
		emailElement.clear();
		phoneElement.clear();
		passwordElement.clear();
		retypeElement.clear();
		idElement.clear();
		dobElement.clear();
		
		idElement.click();
		
		Thread.sleep(2000);
		
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Name is required.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'E-mail is required.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Password is required.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Please re-enter the password.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'NRIC is required.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Date of birth is required.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Phone is required.')]"))!=null);
		
		WebElement smallName = driver.findElement(By.xpath("//small[contains(text(),'Name is required.')]"));
		WebElement smallEmail = driver.findElement(By.xpath("//small[contains(text(),'E-mail is required.')]"));
		WebElement smallPassword = driver.findElement(By.xpath("//small[contains(text(),'Password is required.')]"));
		WebElement smallRetype = driver.findElement(By.xpath("//small[contains(text(),'Please re-enter the password.')]"));
		WebElement smallId = driver.findElement(By.xpath("//small[contains(text(),'NRIC is required.')]"));
		WebElement smallDob = driver.findElement(By.xpath("//small[contains(text(),'Date of birth is required.')]"));
		WebElement smallPhone = driver.findElement(By.xpath("//small[contains(text(),'Phone is required.')]"));
		
		softassert.assertEquals(smallName.getText(), nameRequired);
		softassert.assertEquals(smallEmail.getText(), emailRequired);
		softassert.assertEquals(smallPassword.getText(), passwordRequired);
		softassert.assertEquals(smallRetype.getText(), retypeRequired);
		softassert.assertEquals(smallId.getText(), idRequired);
		softassert.assertEquals(smallDob.getText(), dobRequired);
		softassert.assertEquals(smallPhone.getText(), phoneRequired);
		
		softassert.assertAll();
		
		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		saveChangesBtn.click();

		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		
		softassert.assertAll();
		
		alert.accept();		
		
		WebElement btnCancel = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		btnCancel.click();
		
	}
	
	@Test(priority=33,groups="admin")
	public void checkEditScheduleWithValidationNegativeCase() throws InterruptedException {
		
		String alertText="Update failed.Please refresh page.";
		
		String centreWarning = "*Note: Location for first and second vaccination is different.";
		String dateValidation = "Dates between your first & second does have to at least be 30 days apart.";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconEdit= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[2]/span[1]/i[2]"));
		iconEdit.click();
		
		Thread.sleep(2000);
		
		WebElement fVCentre= driver.findElement(By.xpath("//select[@id='bookFirstDose']"));
		WebElement fVDate= driver.findElement(By.xpath("//input[@name='patientFirstDate']"));
		WebElement fVTime= driver.findElement(By.xpath("//input[@name='patientFirstTime']"));
		
		WebElement sVCentre= driver.findElement(By.xpath("//select[@id='patientSecondCentre']"));
		WebElement sVDate= driver.findElement(By.xpath("//input[@name='patientSecondDate']"));
		WebElement sVTime= driver.findElement(By.xpath("//input[@name='patientSecondTime']"));
		
		Select selectFCentre = new Select(fVCentre);
		selectFCentre.selectByVisibleText("Acumed Medical (Woodlands)");
		
		Thread.sleep(1000);
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 5);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String firstDate = formatter.format(date);
		
		fVDate.sendKeys(firstDate);
		fVTime.sendKeys("12:00");
		
		Select selectSCentre = new Select(sVCentre);
		selectSCentre.selectByVisibleText("Bedok Polyclinic");
		
		Thread.sleep(1000);
		
		calendar.add(Calendar.DATE, 10);
		date = calendar.getTime();
		String secondDate = formatter.format(date);
		
		sVDate.sendKeys(secondDate);
		sVTime.sendKeys("14:00");
		
		softassert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[9]/small[1]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'*Note: Location for first and second vaccination i')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[12]/small[1]"))!=null);
		
		WebElement dateInvalid1 = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[9]/small[1]"));
		WebElement centreDiff = driver.findElement(By.xpath("//small[contains(text(),'*Note: Location for first and second vaccination i')]"));
		WebElement dateInvalid2 = driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[12]/small[1]"));
		
		softassert.assertEquals(dateInvalid1.getText(), dateValidation);
		softassert.assertEquals(centreDiff.getText(), centreWarning);
		softassert.assertEquals(dateInvalid2.getText(), dateValidation);
		
		WebElement retypeElement = driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		retypeElement.sendKeys("5nqhLZzZKSI");
		
		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		saveChangesBtn.click();

		softassert.assertAll();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		softassert.assertAll();
		
		alert.accept();
		
		Thread.sleep(1000);
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=34,groups="admin")
	public void checkEditInformationWithValidationDiffInputNegativeCase() throws InterruptedException {
		
		String alertText="Update failed.Please refresh page.";
		
		String patternName = "Name may only contain alphabets.";
		String patternEmail = "Please enter the proper e-mail format. Eg.john.doe@gmail.com";
		String patternPassword = "Password have to be at least 8 characters long.";
		String patternRetype = "Password is not matching.";
		String patternId = "NRIC should start with either S,T,F,G contains 7 digits in between and ends with an alphabet.";
		String patternDob = "You have to be at least 12 year old to register for a vaccine.";
		String patternPhone = "Phone number should be 8 digits long and start with either 8, 9 or 6 (for landline).";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconEdit= driver.findElement(By.xpath("//tbody/tr[2]/td[5]/button[2]/span[1]/i[2]"));
		iconEdit.click();
		
		Thread.sleep(1000);
		
		WebElement nameElement = driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement emailElement = driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement phoneElement = driver.findElement(By.xpath("//input[@id='patientPhone']"));
		WebElement passwordElement = driver.findElement(By.xpath("//input[@id='patientPassword']"));
		WebElement retypeElement = driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		WebElement idElement = driver.findElement(By.xpath("//input[@id='patientId']"));
		WebElement dobElement = driver.findElement(By.xpath("//input[@id='patientDOB']"));
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String yesterDate = formatter.format(date);
		
		nameElement.sendKeys("123");
		emailElement.sendKeys("hello world");
		phoneElement.sendKeys("yue2");
		passwordElement.clear();
		passwordElement.sendKeys("123");
		retypeElement.sendKeys("23");
		idElement.sendKeys("XR33");
		dobElement.sendKeys(yesterDate);
		
		idElement.click();
		
		Thread.sleep(2000);
		
		WebElement smallElemName = driver.findElement(By.xpath("//small[contains(text(),'Name may only contain alphabets.')]"));
		WebElement smallElemEmail = driver.findElement(By.xpath("//small[contains(text(),'Please enter the proper e-mail format. Eg.john.doe')]"));
		WebElement smallElemPassword = driver.findElement(By.xpath("//small[contains(text(),'Password have to be at least 8 characters long.')]"));
		WebElement smallElemRetype = driver.findElement(By.xpath("//small[contains(text(),'Password is not matching.')]"));
		WebElement smallElemId = driver.findElement(By.xpath("//small[contains(text(),'NRIC should start with either S,T,F,G contains 7 d')]"));
		WebElement smallElemDob = driver.findElement(By.xpath("//small[contains(text(),'You have to be at least 12 year old to register fo')]"));
		WebElement smallElemPhone = driver.findElement(By.xpath("//small[contains(text(),'Phone number should be 8 digits long and start wit')]"));
		
		softassert.assertEquals(smallElemName.getText(), patternName);
		softassert.assertEquals(smallElemEmail.getText(), patternEmail);
		softassert.assertEquals(smallElemPassword.getText(), patternPassword);
		softassert.assertEquals(smallElemRetype.getText(), patternRetype);
		softassert.assertEquals(smallElemId.getText(), patternId);
		softassert.assertEquals(smallElemDob.getText(), patternDob);
		softassert.assertEquals(smallElemPhone.getText(), patternPhone);
		
		softassert.assertAll();
		
		Thread.sleep(1000);
		
		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[contains(text(),'Save Changes')]"));
		saveChangesBtn.click();

		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		softassert.assertAll();
		
		alert.accept();
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(1000);
		
	}
	
	@Test(priority=35,groups="admin")
	public void checkAddPatientClickable() throws InterruptedException{
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement addPatientBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/button[1]"));
		addPatientBtn.click();
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(2000);
	}
	
	@Test(priority=36,groups="admin")
	public void checkAddPatientContent() throws InterruptedException{
		
		String patientHeader="Add Patient";
		String fullname="Fullname:";
		String email="E-mail:";
		String password="Password:";
		String retype="Re-Type Password:";
		String id="NRIC:";
		String dob="Date of birth:";
		String phone="Phone Number:";
		String cancelBtnText="Cancel";
		String addPatientText="Add Patient";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement addPatientBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/button[1]"));
		addPatientBtn.click();
		
		Thread.sleep(1000);
		
		WebElement pHeaderElement= driver.findElement(By.xpath("//h5[@id='staticBackdropLabel']"));
		WebElement nameElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/label[1]"));
		WebElement emailElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[2]/label[1]"));
		WebElement passwordElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[3]/label[1]"));
		WebElement retypeElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/label[1]"));
		WebElement idElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[5]/label[1]"));
		WebElement dobElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[6]/label[1]"));
		WebElement phoneElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[7]/label[1]"));
		WebElement addPBtnElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]"));
		
		
		WebElement cancelBtnElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[2]"));

		softassert.assertEquals(pHeaderElement.getText(), patientHeader);
		softassert.assertEquals(nameElement.getText(), fullname);
		softassert.assertEquals(emailElement.getText(), email);
		softassert.assertEquals(passwordElement.getText(), password);
		softassert.assertEquals(retypeElement.getText(), retype);
		softassert.assertEquals(idElement.getText(), id);
		softassert.assertEquals(dobElement.getText(), dob);
		softassert.assertEquals(phoneElement.getText(), phone);
		softassert.assertEquals(addPBtnElement.getText(), addPatientText);
		softassert.assertEquals(cancelBtnElement.getText(), cancelBtnText);
		
		softassert.assertAll();
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=37, groups="admin")
	public void addPatientPositiveCase() throws InterruptedException {
		
		String alertText = "Patient has been added.";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement addPatientBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/button[1]"));
		addPatientBtn.click();
		
		Thread.sleep(1000);
		
		WebElement fullname= driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement email= driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement password= driver.findElement(By.xpath("//input[@id='patientPassword']"));
		WebElement retype= driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		WebElement id= driver.findElement(By.xpath("//input[@id='patientId']"));
		WebElement dob= driver.findElement(By.xpath("//input[@id='patientDOB']"));
		WebElement phone= driver.findElement(By.xpath("//input[@id='patientPhone']"));
		
		
		//data is not present in database
		fullname.sendKeys("Nurhamizan");
		email.sendKeys("nurhamizan@hotmail.com");
		password.sendKeys("password123");
		retype.sendKeys("password123");
		id.sendKeys("S9123456C");
		dob.sendKeys("19/01/1991");
		phone.sendKeys("87126354");
		
		WebElement addPBtn= driver.findElement(By.xpath("//button[text()='Add Patient']"));
		addPBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		
		alert.accept();
		
		Thread.sleep(1000);
		
		softassert.assertTrue(driver.findElement(By.xpath("//*[text()='nurhamizan@hotmail.com']"))!=null);
	
		softassert.assertAll();
	}
	
	@Test(priority=38,groups="admin", dependsOnMethods="addPatientPositiveCase")
	public void addPatientExistingPatientNegativeCase() throws InterruptedException{
		
		String alertText = "Patient has already exist. Please enter a patient with a different Nric.";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement addPatientBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/button[1]"));
		addPatientBtn.click();
		
		Thread.sleep(1000);
		
		WebElement fullname= driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement email= driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement password= driver.findElement(By.xpath("//input[@id='patientPassword']"));
		WebElement retype= driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		WebElement id= driver.findElement(By.xpath("//input[@id='patientId']"));
		WebElement dob= driver.findElement(By.xpath("//input[@id='patientDOB']"));
		WebElement phone= driver.findElement(By.xpath("//input[@id='patientPhone']"));
		
		fullname.sendKeys("Nurhamizan Kamsani");
		email.sendKeys("hamizan@hotmail.com");
		password.sendKeys("password123");
		retype.sendKeys("password123");
		id.sendKeys("S9876543C");
		dob.sendKeys("19/01/1991");
		phone.sendKeys("87126354");
		
		Thread.sleep(1000);
		
		WebElement addPBtn= driver.findElement(By.xpath("//button[text()='Add Patient']"));
		addPBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		
		softassert.assertAll();
		
		alert.accept();
		
		Thread.sleep(2000);
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=39,groups="admin")
	public void checkAddPatientPatternValidationNegativeCase()throws InterruptedException{
		
		String alertText="Please check the registration form.";
		
		String patternName = " Name may only contain alphabets.";
		String patternEmail = "Please enter the proper e-mail format. Eg.john.doe@gmail.com";
		String patternPassword = "Password have to be at least 8 characters long.";
		String patternRetype = "Password is not matching. Please re-enter the password.";
		String patternId = "NRIC should start with either S,T,F,G contains 7 digits in between and ends with an alphabet.";
		String patternDob = "You have to be at least 12 year old to register for a vaccine.";
		String patternPhone = "Phone number should be 8 digits long and start with either 8, 9 or 6 (for landline).";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("//button[contains(text(),'Patients')]"));
		patientTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement addPatientBtn= driver.findElement(By.xpath("//button[@class='addBtn']"));
		addPatientBtn.click();
		
		Thread.sleep(1000);
		
		WebElement fullname= driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement email= driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement password= driver.findElement(By.xpath("//input[@id='patientPassword']"));
		WebElement retype= driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		WebElement id= driver.findElement(By.xpath("//input[@id='patientId']"));
		WebElement dob= driver.findElement(By.xpath("//input[@id='patientDOB']"));
		WebElement phone= driver.findElement(By.xpath("//input[@id='patientPhone']"));
		
		fullname.sendKeys("123");
		email.sendKeys("hello world");
		password.sendKeys("pa3");
		retype.sendKeys("ord123");
		id.sendKeys("XR8765");
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -20);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = formatter.format(date);
		
		dob.sendKeys(dateString);
		phone.sendKeys("576354");
		id.click();
		
		Thread.sleep(1000);
		
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Name may only contain alphabets.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Please enter the proper e-mail format. Eg.john.doe')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Password have to be at least 8 characters long.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Password is not matching.')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'NRIC should start with either S,T,F,G contains 7 d')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'You have to be at least 12 year old to register fo')]"))!=null);
		softassert.assertTrue(driver.findElement(By.xpath("//small[contains(text(),'Phone number should be 8 digits long and start wit')]"))!=null);
	
		WebElement smallElemName = driver.findElement(By.xpath("//small[contains(text(),'Name may only contain alphabets.')]"));
		WebElement smallElemEmail = driver.findElement(By.xpath("//small[contains(text(),'Please enter the proper e-mail format. Eg.john.doe')]"));
		WebElement smallElemPassword = driver.findElement(By.xpath("//small[contains(text(),'Password have to be at least 8 characters long.')]"));
		WebElement smallElemRetype = driver.findElement(By.xpath("//small[contains(text(),'Password is not matching.')]"));
		WebElement smallElemId = driver.findElement(By.xpath("//small[contains(text(),'NRIC should start with either S,T,F,G contains 7 d')]"));
		WebElement smallElemDob = driver.findElement(By.xpath("//small[contains(text(),'You have to be at least 12 year old to register fo')]"));
		WebElement smallElemPhone = driver.findElement(By.xpath("//small[contains(text(),'Phone number should be 8 digits long and start wit')]"));
		
		softassert.assertEquals(smallElemName.getText(), patternName);
		softassert.assertEquals(smallElemEmail.getText(), patternEmail);
		softassert.assertEquals(smallElemPassword.getText(), patternPassword);
		softassert.assertEquals(smallElemRetype.getText(), patternRetype);
		softassert.assertEquals(smallElemId.getText(), patternId);
		softassert.assertEquals(smallElemDob.getText(), patternDob);
		softassert.assertEquals(smallElemPhone.getText(), patternPhone);
		
		softassert.assertAll();
		
		Thread.sleep(1000);
		
		WebElement addPBtn= driver.findElement(By.xpath("//button[text()='Add Patient']"));
		addPBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		
		softassert.assertAll();
		
		alert.accept();
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=40,groups="admin")
	public void checkAddPatientWithEmptyFieldNegativeCase()throws InterruptedException{
		
		String alertText="Please check the registration form.";
	
		Thread.sleep(2000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();
		
		Thread.sleep(1000);
		
		WebElement addPatientBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/button[1]"));
		addPatientBtn.click();
		
		Thread.sleep(1000);
		
		WebElement fullname= driver.findElement(By.xpath("//input[@id='patientFullname']"));
		WebElement email= driver.findElement(By.xpath("//input[@id='patientEmail']"));
		WebElement password= driver.findElement(By.xpath("//input[@id='patientPassword']"));
		WebElement retype= driver.findElement(By.xpath("//input[@id='patientReTypePassword']"));
		WebElement id= driver.findElement(By.xpath("//input[@id='patientId']"));
		WebElement dob= driver.findElement(By.xpath("//input[@id='patientDOB']"));
		WebElement phone= driver.findElement(By.xpath("//input[@id='patientPhone']"));
		
		fullname.click();
		email.click();
		password.click();
		retype.click();
		id.click();
		dob.click();
		phone.click();
		id.click();
		
		Thread.sleep(1000);
		
		WebElement addPBtn= driver.findElement(By.xpath("//button[text()='Add Patient']"));
		addPBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		
		softassert.assertAll();
		
		alert.accept();
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=41,groups="admin")
	public void checkDeleteIconClickable() throws InterruptedException {
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement iconDelete= driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[3]/span[1]/i[2]"));
		iconDelete.click();
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(2000);
	}
	
	@Test(priority=42,groups="admin")
	public void checkDeleteContent() throws InterruptedException{
		
		String deleteHeader="Delete";
		String deleteText="Are you sure you want to delete?";
		String deleteBtnText="Delete";
		String cancelBtnText="Cancel";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		WebElement deleteIcon = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/button[3]"));
		
		deleteIcon.click();
		
		Thread.sleep(2000);
		
		WebElement dHeaderElement= driver.findElement(By.xpath("//h5[@id='staticBackdropLabel']"));
		WebElement dTextElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]"));
		WebElement dBtnElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/button[1]"));
		WebElement cBtnElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/button[2]"));
		
		softassert.assertEquals(dHeaderElement.getText(), deleteHeader);
		softassert.assertEquals(dTextElement.getText(), deleteText);
		softassert.assertEquals(dBtnElement.getText(), deleteBtnText);
		softassert.assertEquals(cBtnElement.getText(), cancelBtnText);
		
		softassert.assertAll();
		
		WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		cancelBtn.click();
		
		Thread.sleep(1000);
	}
	
	@Test(priority=43, groups="admin", dependsOnMethods="addPatientPositiveCase")
	public void checkDelete() throws InterruptedException{
		
		String deleteText="Are you sure you want to delete?";
		String alertText ="Patient has been deleted.";

		String dataToDelete = "nurhamizan@hotmail.com";
		
		Thread.sleep(1000);
		
		WebElement patientTabBtn= driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/admin-page[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]"));
		patientTabBtn.click();

		Thread.sleep(1000);
		
		int rowToFind=0;
		int columnToFind=0;
		
		//List<WebElement> tableColumn = driver.findElements(By.xpath("//thead/tr/th"));
		
		//unknown number of rows
		List<WebElement> tableRow = driver.findElements(By.xpath("//tbody/tr"));
		
		//finding data we need
		for(int i =1; i<=tableRow.size(); i++) {
			for(int j=1; j<=4;j++) {//known number of columns
				WebElement data=driver.findElement(By.xpath("//tbody/tr["+i+"]/td["+j+"]"));
				if(data.getText().equals(dataToDelete)) {
					rowToFind=i;
					columnToFind=j;
				}
			}
		}
		
		WebElement dToDeleteElement = driver.findElement(By.xpath("//tbody/tr["+rowToFind+"]/td["+columnToFind+"]"));
		
		softassert.assertEquals(dToDeleteElement.getText(), dataToDelete);
		
		//icon occurs at td[5] always
		WebElement deleteIcon = driver.findElement(By.xpath("//tbody/tr["+rowToFind+"]/td[5]/button[3]"));
		
		deleteIcon.click();
		
		Thread.sleep(2000);
		
		WebElement dTextElement= driver.findElement(By.xpath("/html[1]/body[1]/ngb-modal-window[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]"));

		softassert.assertEquals(dTextElement.getText(), deleteText);

		WebElement deleteBtn = driver.findElement(By.xpath("//button[contains(text(),'Delete')]"));
		deleteBtn.click();
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		
		softassert.assertEquals(alert.getText(), alertText);
		
		softassert.assertAll();
		
		alert.accept();
	}
	
	@AfterMethod
	public void afterEachTest() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	@AfterTest
	public void end() {
		driver.quit();
	}
}
