import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleTest {

    @BeforeTest
    public void setUp() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.reportsFolder = "target/surefire-reports";
        Configuration.downloadsFolder = "target/downloads";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("90.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("enableLog", false);
        Configuration.browserCapabilities = capabilities;


    }

    @Test
    void canGetClipboardContent() {
        open("https://github.com/selenide/selenide");
        $(".file-navigation get-repo details.details-overlay").click();
        $(".octicon-clippy").click();
        Assert.assertEquals("https://github.com/selenide/selenide.git", $(".input-group > input").getValue());
    }

}
