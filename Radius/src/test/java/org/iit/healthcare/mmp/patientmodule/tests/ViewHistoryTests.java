package org.iit.healthcare.mmp.patientmodule.tests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.healthcare.TestBase.TestBaseClass;
import org.iit.healthcare.mmp.patientmodule.pages.HomePage;
import org.iit.healthcare.mmp.patientmodule.pages.Loginpage;
import org.iit.healthcare.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.healthcare.mmp.patientmodule.pages.ViewHistoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
 
import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewHistoryTests extends TestBaseClass

{
WebDriver driver;

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
public void login(String uname,String pword)
{
	//login
	driver.findElement(By.id("username")).sendKeys(uname);
	driver.findElement(By.id("password")).sendKeys(pword);
	//type=submit and it is available in the html form
	driver.findElement(By.name("submit")).click();

}
public void navigationMenuItem(String tabName)
{
	//Navigation to the module
	driver.findElement(By.xpath("//span[contains(text(), '"+tabName+"')]")).click();

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
	sPageHMap.put("DateOfApp",driver.findElement(By.xpath("//div[@class='panel panel-info']//h3[@class='panel-title']")).getText().trim());


	String timeWE = driver.findElement(By.xpath("//a[contains(text(),'Time')]")).getText();
	String timeWEArr[] = timeWE.split(":");
	sPageHMap.put("Time",timeWEArr[1].trim());

	String symWE= driver.findElement(By.xpath("//a[contains(text(),'Symptoms')]")).getText();
	String symWEArr[] = symWE.split(":");
	sPageHMap.put("Appointment",  symWEArr[1].trim());

	String doctorNameWE= driver.findElement(By.xpath("//a[contains(text(),'Provider')]")).getText();
	String doctorNameWEArr[] =doctorNameWE.split(":");
	sPageHMap.put("Doctor",doctorNameWEArr[1].trim() );
	return sPageHMap;
	
}


@Test
public void ViewHistoryTest() throws Exception


	{
	instantiateDriver();
	launchApplication("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
	login("ria1","Ria12345");
	   driver.findElement(By.xpath("//a[@href='profile.php']")).click();
         driver.findElement(By.xpath("//button[contains(text(),'View History')]")).click();
         driver.findElement(By.xpath("//input[@value='Past Appointments']")).click();
     	System.out.println("Past Transaction viewed");
     	

		HashMap<String,String> patientinfoHMap=validatepatientinformation();
		System.out.println("1st HashMap read");
		navigationMenuItem("Schedule Appointment");
	 
		HashMap<String,String> sPageHMap=validateScheduleAppinformation();
		System.out.println("2nd HashMap read");

		Assert.assertEquals(patientinfoHMap,sPageHMap);


driver.quit();

	}
 
	
	
}



