package pages;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Activity_Planner_page_report {
	WebDriver driver;
	@FindBy(xpath = "//p[text()='Reports']")
	WebElement Reportsfeature;
	@FindBy(xpath = "//tbody/tr[1]/td[2]/a")
	WebElement ViewReportsfeature;
	@FindBy(id = "classId")
	WebElement classdrdp;
	@FindBy(id = "stdate")
	WebElement startdate;
	@FindBy(id = "endate")
	WebElement enddate;
	@FindBy(css = "button[type='submit']")
	WebElement submitbtn;
	@FindBy(xpath = "//p[text()=' Certificate Of Achievement ']")
	WebElement successReport;
	@FindBy(xpath = "//div[contains(text(),'No Activities Found for selected Timeline!')]")
	WebElement existing;


	public Activity_Planner_page_report(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void Reportsfeature() {
		Reportsfeature.click();
	}

	public void viewreport() {
		ViewReportsfeature.click();
	}
	public void triestoadd(String string, String string2, String string3, String string4) {
		classdrdp.sendKeys(string);
		startdate.sendKeys(string4);
		enddate.sendKeys(string4);
		submitbtn.click();
		String str=driver.switchTo().alert().getText();
		{
			System.out.println(str);	
		}
		driver.switchTo().alert().accept();
		startdate.clear();
		enddate.clear();
		startdate.sendKeys(string3);
		enddate.sendKeys(string2);
		submitbtn.click();
		String str1=driver.switchTo().alert().getText();
		{
			System.out.println(str1);	
		}
		driver.switchTo().alert().accept();
		startdate.clear();
		enddate.clear();
		startdate.sendKeys(string2);
		enddate.sendKeys(string3);
		submitbtn.click();
		
	}
	
	public void parentchildwindow() {
		
		String pwid=driver.getWindowHandle();
		Set<String> handle=driver.getWindowHandles();
		Iterator<String> it=handle.iterator();
		String pwid1=it.next();
		String cwid=it.next();
		for(String i:handle) {
			if(!pwid1.equals(i)) {
				driver.switchTo().window(i);
			}
		}


	}

public void alerts(String string) {
	try {
		if(successReport.isDisplayed()) {
			System.out.println(string);
		}
	} catch (Exception e) {
		if(existing.isDisplayed()) {
			System.out.println(string);
		}
	}
	
}








}
