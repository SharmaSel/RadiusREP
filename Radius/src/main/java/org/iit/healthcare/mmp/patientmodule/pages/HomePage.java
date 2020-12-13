package org.iit.healthcare.mmp.patientmodule.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	
	public void navigationMenuItem(String tabName)
	{
		//Navigation to the module
		driver.findElement(By.xpath("//span[contains(text(), '"+tabName+"')]")).click();

	}
	public HashMap<String, String> validateApptDetailsinHomePage()
	{

		//Validation -2 Appointment details are displayed in the home page 
		//table[@class='table']//tr[1]/td[1] - dateofAppointment
		//table[@class='table']//tr[1]/td[2]-  time
		//table[@class='table']//tr[1]/td[3] - sym
		//table[@class='table']//tr[1]/td[4] - doctorName
		HashMap<String,String> homePageDetailsHMap = new HashMap<String,String>();
		homePageDetailsHMap.put("dateofAppointment",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[1]")).getText());
		homePageDetailsHMap.put("time",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[2]")).getText());
		homePageDetailsHMap.put("sym",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[3]")).getText());
		homePageDetailsHMap.put("doctorName",driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[4]")).getText());
		return homePageDetailsHMap;
	}
	public boolean validatePatientPortalMessage()
	{
		//Validation-1
		WebDriverWait wait = new WebDriverWait(driver,30);
		Boolean result = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h3[@class='panel-title']"), "Patient Portal"));
		return result;
	}

	


	
}
