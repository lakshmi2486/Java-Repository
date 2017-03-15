package runner;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = { "src/test/java/features/SyntelComposeWebMail.feature" }, format = {
		"json:target/cucumber.json", "html:target/site/cucumber-html" }, tags = "", strict = true, glue = "pages")
public class TestRunner {

	@Test(description = "Example of using TestNGCucumberRunner to invoke Cucumber")
	public void runCukes() {
		new TestNGCucumberRunner(getClass()).runCukes();
	}
}

// package runner;
//
// import cucumber.api.CucumberOptions;
// import cucumber.api.testng.TestNGCucumberRunner;
// import cucumber.api.testng.CucumberFeatureWrapper;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.DataProvider;
// import org.testng.annotations.Test;
//
// @CucumberOptions(features =
// "src/test/java/features/SyntelComposeWebMail.feature", plugin =
// "json:target/cucumber.json")
// public class TestRunner {
// private TestNGCucumberRunner testNGCucumberRunner;
//
// @BeforeClass(alwaysRun = true)
// public void setUpClass() throws Exception {
// testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
// }
//
// @Test(description = "Runs Cucumber Feature", dataProvider = "features")
// public void feature(CucumberFeatureWrapper cucumberFeature) {
// testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
// }
//
// @DataProvider
// public Object[][] features() {
// return testNGCucumberRunner.provideFeatures();
// }
//
// @AfterClass(alwaysRun = true)
// public void tearDownClass() throws Exception {
// testNGCucumberRunner.finish();
// }
// }