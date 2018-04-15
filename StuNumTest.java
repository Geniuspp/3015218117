//Homework LiShichao 3015218117
package StuNumTest;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SNTest {
    SNTest test;
    WebDriver dri;
    public String webTest(String user,WebDriver dri){
	//cin username
	WebElement username = dri.findElement(By.xpath("//*[@id=\"username\"]"));
	username.clear();
	username.sendKeys(user);
	//cin password
	String passWd=user.substring(4);
	WebElement password = dri.findElement(By.xpath("//*[@id=\"password\"]"));
	password.clear();
	password.sendKeys(passWd);

	//submit
	dri.findElement(By.xpath("//*[@id=\"submitButton\"]")).click();
	//get url
	WebElement path = dri.findElement(By.xpath("/html/body/div[1]/div[2]/a/p")); 
//return
	return path.getText();
    }

    @Before
    public void setUp() throws Exception {
	System.setProperty("webdri.chrome.dri", "webdri\\chromedri.exe");
	dri = new ChromeDriver();
	dri.get("https://psych.liebes.top/st");
	test=new TestTest();
    }

    @Test
    public void test() throws IOException{
	Excel_reader data= new Excel_reader(); 
	ArrayList<ArrayList<String>> arr=data.xlsx_reader("input.xlsx",0,1);  
	for(int i=0;i<arr.size();i++){
	    ArrayList<String> row=arr.get(i);
	    String expected=row.get(1);
	    
	    
	    if(row.get(1).equals(""))
	      //no url
		continue;    
	    else {
		String result=" ";
		try {
		    result = test.webTest(row.get(0), dri);
		} catch (Exception e) {
     		e.printStackTrace();
		}
		if(expected.charAt(expected.length()-1)=='/') {
		    expected=expected.substring(0, expected.length()-1);
		}
		if(result.charAt(result.length()-1)=='/') {
		    result=result.substring(0, result.length()-1);
		}
		assertEquals(expected,result);
		dri.navigate().back();
	    }
	}
	dri.close();
	System.out.println("AC");
    }
}
