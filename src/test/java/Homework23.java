import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework23 extends BaseTest{

    @Test//(dataProvider = "loginData")
    public void renamePlaylist(){
        LoginPage logIntoProfile = new LoginPage(driver);
        HomePage newPlayListName = new HomePage(driver);


        logIntoProfile.enterEmail("linulya1411@gmail.com");
        logIntoProfile.enterPassword("te$t$tudent");
        logIntoProfile.clickSubmit();
        newPlayListName.clickPlayList();
        newPlayListName.chooseEdit();
        newPlayListName.renamePlayListName("New Playlist");

        Assert.assertTrue(HomePage.verifyNotification());
    }
}