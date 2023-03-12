import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class BaseTest {
    public static WebDriver driver = null;
    public static WebDriverWait wait;
    public static String url = null;
    public static Actions actions;
    String newPlayListName = "New Playlist";
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    @Parameters ({"BaseURL"})
    public static void openBrowser(String BaseURL) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications","--remote-allow-origins=*","--incognito","--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        url= BaseURL;
        driver.get(url);
    }

    public void logIn() {
        enterEmail("linulya1411@gmail.com");
        enterPassword("te$t$tudent");
        clickSubmit();
    }
    By enterEmailAddress = cssSelector(("[type = 'email']"));
    By passwordField = cssSelector(("[type='password']"));
    By submitButton = cssSelector(("[type='submit']"));
    By playList = By.xpath(("//*[@id=\"playlists\"]/ul/li[3]/a"));
    By xButton = cssSelector(("button[title='Delete this playlist']"));
    By notificationMessage = cssSelector(("div.success.show"));
    By editOption = cssSelector(("[data-testid='playlist-context-menu-edit-48111']"));
    By textField = By.xpath(("//*[@id=\"playlists\"]/ul/li[3]/a"));
    By newPlayList = By.xpath(("//a[text()='"+newPlayListName+"']"));

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(enterEmailAddress));
        emailElement.sendKeys("linulya1411@gmail.com");
    }
    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordElement.sendKeys("te$t$tudent");
    }

    public void clickSubmit() {
        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitElement.click();
    }
    public void clickPlayList() {
        WebElement clickOnPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(playList));
        actions.doubleClick(clickOnPlaylist).perform();
    }
//    public void chooseEdit() {
//        wait.until(ExpectedConditions.elementToBeClickable(editOption)).click();
//    }
    public void renamePlayListName() {
        WebElement textBox = driver.findElement(textField);
        textBox.sendKeys((Keys.chord(Keys.COMMAND,"a",Keys.SPACE)));
        textBox.sendKeys(newPlayListName);
        textBox.sendKeys(Keys.ENTER);
    }
    public boolean verifyNewPlayListName(){
        WebElement newPlaylistName = driver.findElement(newPlayList);
        return newPlaylistName.isDisplayed();
    }
    public void clickXPlaylist() {
        WebElement clickX = wait.until(ExpectedConditions.elementToBeClickable(xButton));
        clickX.click();
    }
    public void clickPlaylistToDelete() {
        WebElement deleteElement = wait.until(ExpectedConditions.elementToBeClickable(playList));
        deleteElement.click();
    }
    public boolean verifyNotification(){
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationMessage));
        return messageElement.isDisplayed();
    }
//    @DataProvider(name="loginData")
//    public static Object[][] getDataFromDataProviders(){
//        return new Object[][]{
//                {"linulya1411@gmail.com", "te$t$tudent"}
//        };
//    }

    @AfterMethod
    public  void closeBrowser(){
        driver.quit();
    }
}


