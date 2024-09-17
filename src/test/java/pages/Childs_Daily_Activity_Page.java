package pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Childs_Daily_Activity_Page {
	WebDriver driver;
	@FindBy(xpath = "//p[text()='Reports']")
	WebElement Reportsfeature;
	@FindBy(xpath = "//tbody/tr[9]/td[2]/a")
	WebElement ViewReportsfeature;
	@FindBy(id = "childId")
	WebElement childdrdp;
	@FindBy(id = "classId")
	WebElement classdrdp;
	@FindBy(id = "date-of-birth")
	WebElement DOB;
	@FindBy(css = "button[type='submit']")
	WebElement submitbtn;
	@FindBy(id = "comments")
	WebElement comments;
	@FindBy(xpath = "//p[text()=' Certificate Of Achievement ']")
	WebElement successReport;
	@FindBy(xpath = "//body/div/div/div/child::a[3]")
	WebElement closebtn;
	@FindBy(xpath = "//h6[text()='No Activity of Child Found for selected Date!']")
	WebElement worning;
	public Childs_Daily_Activity_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void viewbtn() {
		ViewReportsfeature.click();
	}
	
	public void triestoadd(String string, String string2, String string3)  {
		classdrdp.sendKeys(string);
		DOB.sendKeys(string3);
//		Thread.sleep(2000);
//		submitbtn.click();
//		String str=driver.switchTo().alert().getText();
//		{
//			System.out.println(str);	
//		}
//		driver.switchTo().alert().accept();
		Select se=new Select(childdrdp);
		se.selectByVisibleText(string2);
//		childdrdp.sendKeys(string2);
//		comments.sendKeys(string4);
		submitbtn.click();
	}
	public void submit() {
		submitbtn.click();
	}
	public void parentchildwindow() {

		Set<String> handle=driver.getWindowHandles();
		Iterator<String> it=handle.iterator();
		String pwid=it.next();
		String cwid=it.next();
		for(String i:handle) {
			if(!pwid.equals(i)) {
				driver.switchTo().window(i);
			}
		}
	}

	public void pagevalidation(String string) {

		try {
			if(successReport.isDisplayed()) {
				System.out.println(string);
				closebtn.click();
				if(classdrdp.isDisplayed()) {
					System.out.println("User is on Child Immunizations Page");
				}
			}
		} catch (Exception e) {
			if(worning.isDisplayed()) {
				System.out.println(string);
			}	


		}
	}
}
