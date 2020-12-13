package org.iit.healthcare.mmp.patientmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
	
	WebDriver driver;
	public Loginpage(	WebDriver driver)
	{
		this.driver = driver;
		
	}
	
	

	public void login(String uname,String pword)
	{
		//login
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		//type=submit and it is available in the html form
		driver.findElement(By.name("submit")).click();

	}
}
