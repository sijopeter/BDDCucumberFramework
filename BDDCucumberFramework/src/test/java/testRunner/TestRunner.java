package testRunner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.cucumber.listener.Reporter;
import java.io.*;
import org.junit.AfterClass;
import rsweb.pageObjects.FileReaderManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/rsweb/features",
        glue="rsweb/stepDefenitions",
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        monochrome = true
)
public class TestRunner {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
    }

}
