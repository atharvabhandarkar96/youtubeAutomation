package demo;

//import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;


public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

        @Test (enabled = true)
        public void testCase01() throws InterruptedException
        {
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://www.youtube.com/");
                Thread.sleep(2000);
                Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
                WebElement about=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@id='guide-links-primary']//a[text()='About']")));
                about.click(); //clicked on about button
                Assert.assertTrue(driver.getCurrentUrl().contains("about"));
                WebElement message=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//p[@class='lb-font-display-3 lb-font-color-text-primary'])[1]")));
                WebElement aboutText=driver.findElement(By.xpath(".//h1"));
                System.out.println(aboutText.getText());
                System.out.println(message.getText()); 
                //printing out the message on about page
        }


        @Test (enabled = true)
        public void testCase02() throws InterruptedException
        {
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://www.youtube.com/");
                Thread.sleep(2000);
                Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
                WebElement films=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//yt-formatted-string[text()='Films']")));
                films.click(); //clicked on Films button
                Thread.sleep(3000);
               //List<WebElement> categories = driver.findElements(By.xpath(".//span[@id='title']"));
              // Wrappers.select_categories(categories, "Top Selling");

              //WebElement topselling=driver.findElement(By.xpath("(.//span[text()='Top selling'])[1]"));
             // Wrappers.click(topselling);

             WebElement next=driver.findElement(By.xpath("(.//div[@id='right-arrow']//ytd-button-renderer)[1]"));
             
             while(next.isDisplayed()==true)
             {
                Wrappers.click(next);    

             }
//Linked the generes of all movies belonging to Top Selling and stored it in List
             List<WebElement> list1_genere=driver.findElements(By.xpath("//span[text()='Top selling']/ancestor::ytd-shelf-renderer//ytd-grid-movie-renderer//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer']"));
       //Linking is : Found Top selling title and
       //Moved to ancestor or parent element of top selling : ytd-shelf-renderer
       //from this parent element we came down till we found //ytd-grid-movie-renderer//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer']
             int n=list1_genere.size();
        List<WebElement> list_certificate=driver.findElements(By.xpath("//span[text()='Top selling']/ancestor::ytd-shelf-renderer//ytd-grid-movie-renderer//div[@class='badge  badge-style-type-simple style-scope ytd-badge-supported-renderer style-scope ytd-badge-supported-renderer']"));

        //Parsed through the list of generes till last element -n-1th element  : and checked if there is something present
        System.out.println(list1_genere.get(n-1).getText());
        System.out.println(list_certificate.get(n-1).getText());

        SoftAssert sa=new SoftAssert();
        sa.assertEquals(list1_genere.get(n-1).getText(),"Animation • 2024");
        sa.assertEquals(list_certificate.get(n-1).getText(),"U");

        sa.assertAll();

        }

        @Test (enabled = true)
        public void testCase03() throws InterruptedException
        {
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://www.youtube.com/");
                Thread.sleep(2000);
                Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
                WebElement music=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//yt-formatted-string[text()='Music'])[1]")));
               Wrappers.click(music);
                Thread.sleep(2000);
  
             //   ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100);");
                //Thread.sleep(2000);
            
              WebElement nextbutton=driver.findElement(By.xpath("(.//div[@id='right-arrow']//ytd-button-renderer)[1]")); 
              Wrappers.click_next(nextbutton);      

              List<WebElement> numberOfSongs=driver.findElements(By.xpath(".//span[text()='This year in music']/ancestor::ytd-shelf-renderer//div[@class='badge-shape-wiz__text']"));
              List<WebElement> nameOfSongs=driver.findElements(By.xpath(".//span[text()='This year in music']/ancestor::ytd-shelf-renderer//span[@class='yt-core-attributed-string yt-core-attributed-string--white-space-pre-wrap']"));
              
              int tot=numberOfSongs.size();

            SoftAssert sa=new SoftAssert();
            String songs=numberOfSongs.get(tot-1).getText().substring(0,3).trim();
            int n_songs=Integer.parseInt(songs);
            
            System.out.println("Name of the playlist: "+nameOfSongs.get(tot-1).getText());
            System.out.println("Total Songs:    "+n_songs);

            sa.assertTrue(n_songs<50 || n_songs==50 , "Songs are less than or equal to 50");
              sa.assertAll();
       
       
 
                       
        }


        @Test (enabled = true)
        public void testCase04() throws InterruptedException
        {

                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://www.youtube.com/");
                Thread.sleep(2000);
                Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
                WebElement news=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//yt-formatted-string[text()='News'])[1]")));
               Wrappers.click(news);
                Thread.sleep(2000);
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
                Thread.sleep(4000);
                List<WebElement> newsListTitle=driver.findElements(By.xpath(".//span[text()='Latest news posts']/ancestor::ytd-rich-section-renderer//ytd-rich-item-renderer//a/span[@class='style-scope ytd-post-renderer']"));
                List<WebElement> newsContent=driver.findElements(By.xpath(".//span[text()='Latest news posts']/ancestor::ytd-rich-section-renderer//ytd-rich-item-renderer//yt-formatted-string[@id='home-content-text']//span[2]"));
              List<WebElement> likes=driver.findElements(By.xpath(".//span[text()='Latest news posts']/ancestor::ytd-rich-section-renderer//span[@id='vote-count-left']"));
                
              int n=newsContent.size();
                System.out.println("Number is:   "+ n);
               
                Wrappers.news(newsListTitle,newsContent,3);
                Wrappers.likeSum(likes,3);

               


        }


        @Test (enabled = false)
        public void testCase05() throws InterruptedException
        {
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://www.youtube.com/");
                Thread.sleep(2000);
                Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
                WebElement searchBox=driver.findElement(By.xpath("//div[@class='YtSearchboxComponentInputBox YtSearchboxComponentInputBoxDark']"));
                searchBox.click();
                searchBox.sendKeys(null);
        }
        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}