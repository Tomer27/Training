import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Cello {

    public static ChromeDriver driver;
    public static WebElement SearchResultText;
    public static WebElement SearchResultPrice;
    public static WebElement searchField;
    public static WebElement searchButton;
    public static String driverLocalPath = "C:/Users/Tomer/Desktop/Selenium/chromedriver.exe"; // Enter your local chromedriver path
    public static String ProductName = "עגבניה";
    public static String URL = "https://www.shufersal.co.il/online/";
    public static String TextInSearchResult = null;
    public static String TextInResultScreen = null;
    public static String[] PriceInSearchResult = null;
    public static String PriceInResultScreen = null;

    @BeforeTest
    public static void BeforeTest() throws Exception {

        System.setProperty("web-driver.chrome.driver", driverLocalPath);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(URL);
        driver.manage().window().maximize();
        System.out.println("Opening Chrome on " + URL);
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        Thread.sleep(2000);

    }

    @Test
    public static void SearchWithAutoComplete() throws Exception {

        searchField = driver.findElement(By.id("js-site-search-input"));
        searchButton = driver.findElement(By.xpath("//*[@id=\"formSearchSubmit\"]/fieldset/div/div/button"));

        searchField.sendKeys(ProductName);
        Thread.sleep(1500);

        SearchResultText = driver.findElement(By.className("tt-highlight"));
        TextInSearchResult = SearchResultText.getText();
        SearchResultPrice = driver.findElement(By.className("pricePerUnit"));
        PriceInSearchResult = SearchResultPrice.getText().split(" ");

        System.out.println("\n============================================================");
        System.out.println("Step 1: Check that the result text contains the product name");
        System.out.println("============================================================\n");

        Assert.assertTrue(TextInSearchResult.contains(ProductName), "Result doesn't contain the product name - " + ProductName);
        System.out.println("Result contains the product name - " + ProductName);
        Thread.sleep(500);

        SearchResultText.click();
        Thread.sleep(2000);

        TextInResultScreen = driver.findElement(By.id("modalTitle")).getText();
        PriceInResultScreen = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div[1]/div/div/section[1]/div[2]/div[1]/div[2]/div[1]/div/span[1]")).getAttribute("content");

        System.out.println("\n============================================================");
        System.out.println("Step 2: Check that the result text in the result screen is equal to the product name");
        System.out.println("============================================================\n");

        Assert.assertTrue(TextInSearchResult.equals(ProductName), "Text in the result screen isn't equal to the product name - " + ProductName);
        System.out.println("Text in the result screen is equal to the product name - " + ProductName);

        System.out.println("\n============================================================");
        System.out.println("Step 3: Check that the result prices are equal between the result screen and when searching");
        System.out.println("============================================================\n");

        Assert.assertTrue(PriceInResultScreen.equals(PriceInSearchResult[0]), "The prices shown when searching and in the result screen aren't equal");
        System.out.println("The prices shown when searching and in the result screen are equal");

    }

    @AfterTest
    public static void AfterTest() throws Exception {

        Thread.sleep(2000);
        driver.quit();

    }

}
