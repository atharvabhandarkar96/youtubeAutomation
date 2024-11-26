package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void select_categories(List<WebElement> cat,String category) throws InterruptedException
     {
        for(WebElement e:cat)
        {
            if(e.getText().contains(category))
            {
                e.click();
                Thread.sleep(2000);
            }
        }
     }

     public static void click(WebElement element)throws InterruptedException
     {
        element.click();
        Thread.sleep(2000);
     }

     public static void click_next(WebElement element) throws InterruptedException
     {
        while(element.isDisplayed()==true)
        {
           element.click();  
           Thread.sleep(2000); 

        }
     }

     public static void news(List<WebElement> e1,List<WebElement> e2,int n)
     {
        for(int i=0;i<=n-1;i++)
                {
                        System.out.println("News Title is: "+e1.get(i).getText());
                        System.out.println("News Description is: "+e2.get(i).getText());

                }
     }

     public static void likeSum(List<WebElement> e,int n)
     {
        int sum=0;
        for(int i=0;i<n;i++) //to print the sum of likes on 1st 3 news
        {
            if(e.get(i).getText().isEmpty()==true)
            {
                sum=sum+0;
            } 
            else
            {  
            int l=Integer.parseInt(e.get(i).getText().trim());
            sum=sum+l;
            }
                
        }

        System.out.println("Total Likes for 1st 3 news: "+sum);

     }
}
