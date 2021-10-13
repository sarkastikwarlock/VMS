package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignIn {
	
	public SignIn() {}
	
	public void signInUser(WebDriver driver, String id, String password) throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='inputNric']")).sendKeys(id);
		driver.findElement(By.xpath("//input[@name='inputPassword']")).sendKeys(password);
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@name='signInBtn']")).click();
	}
	
}
