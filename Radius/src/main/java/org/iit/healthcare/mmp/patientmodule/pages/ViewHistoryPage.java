package org.iit.healthcare.mmp.patientmodule.pages;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.healthcare.TestBase.TestBaseClass;
import org.iit.healthcare.mmp.patientmodule.pages.HomePage;
import org.iit.healthcare.mmp.patientmodule.pages.Loginpage;
import org.iit.healthcare.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewHistoryPage {
	

WebDriver driver;



public ViewHistoryPage (WebDriver driver)
{ 
	this.driver = driver;
	
}
	
public void instantiateDriver()
{	
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

}
public void launchApplication(String url)
{
	
	driver.get(url);
}
public void login() throws Exception
		{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		driver.manage().window().maximize();
		}	

public void navigationMenuItem(String tabName)
{
	//Navigation to the module
	driver.findElement(By.xpath("//span[contains(text(), '"+tabName+"')]")).click();

}
        public void history() throws Exception
        {
	
        driver.findElement(By.xpath("//a[@href='profile.php']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'View History')]")).click();
        driver.findElement(By.xpath("//input[@value='Past Appointments']")).submit();
        	
    
}
        public HashMap<String, String> validatepatientinformation() 
        {
        	HashMap<String,String> patientinfoHMap = new HashMap<String,String>();
        	patientinfoHMap.put("DateOfApp", driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText());
        	patientinfoHMap.put("Time", driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText());

        	patientinfoHMap.put("Appointment", driver.findElement(By.xpath("//tbody/tr[1]/td[3]")).getText());

        	patientinfoHMap.put("Doctor", driver.findElement(By.xpath("//tbody/tr[1]/td[4]")).getText());
            return patientinfoHMap;
            
        }
        public HashMap<String,String> validateScheduleAppinformation()
        {
        	HashMap<String,String> sPageHMap = new HashMap<String,String>();
    		sPageHMap.put("dateofAppointment",driver.findElement(By.xpath("//div[@class='panel panel-info']//h3[@class='panel-title']")).getText().trim());


    		String timeWE = driver.findElement(By.xpath("//a[contains(text(),'Time')]")).getText();
    		String timeWEArr[] = timeWE.split(":");
    		sPageHMap.put("time",timeWEArr[1].trim());

    		String symWE= driver.findElement(By.xpath("//a[contains(text(),'Symptoms')]")).getText();
    		String symWEArr[] = symWE.split(":");
    		sPageHMap.put("sym",  symWEArr[1].trim());

    		String doctorNameWE= driver.findElement(By.xpath("//a[contains(text(),'Provider')]")).getText();
    		String doctorNameWEArr[] =doctorNameWE.split(":");
    		sPageHMap.put("doctorName",doctorNameWEArr[1].trim() );
    		return sPageHMap;
    		
        }

        
        
        }
	

