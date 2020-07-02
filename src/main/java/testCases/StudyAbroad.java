package testCases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import supportingClass.StudyAbroadHelper;

public class StudyAbroad {

	public static RemoteWebDriver driver;

	public static void browserSetUp(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64 bit.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
	}

	public static void main(String[] args) throws InterruptedException {
//		1) Go to https://studyabroad.shiksha.com/

		browserSetUp("chrome");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://studyabroad.shiksha.com/");

//		2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges

		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("(//label[contains(text(),\"Colleges\")])[2]")).perform();
		Thread.sleep(5000);
		driver.findElementByXPath("//a[text()=\"MS in Computer Science &Engg\"]").click();

//		3) Click Change course / country select box, choose course as BE/Btech and Choose specialization as Computer Science & Engineering

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("categoryPagePushDownBannerFrame")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Stage_Rectangle2")));
		driver.switchTo().defaultContent();
		driver.findElementByXPath("//a[text()=' Change course / country ']").click();
		Select desiredCourse = new Select(driver.findElementByName("desiredCourse"));
		desiredCourse.selectByValue("1510");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("subCatSelect")));
		Select subCat = new Select(driver.findElementByName("subCatSelect"));
		subCat.selectByValue("277");

//		4) Select Study destination as USA, UK, Canada and click Update

		Thread.sleep(5000);
		driver.findElementByClassName("select-overlap").click();
		WebElement flag = driver.findElementByXPath("//input[@id='0-flag']/..");
		flag.click();
		driver.findElementByXPath("//input[@id='1-flag']/..").click();
		driver.findElementByXPath("//input[@id='5-flag']/..").click();
		driver.findElementByXPath("//a[text()='OK']").click();
		driver.findElementByXPath("//div[@id='courseCountryLayer_error']/following::input").click();

//		5) In Filters Select IELTS and score as 7.5 & Below in Exam Accepted
		for (int i = 0; i < 3; i++) {
			try {
				driver.findElementByXPath("//p[text()='IELTS']//preceding-sibling::span").click();
				break;
			} catch (Exception e) {

			}
		}
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//div[contains(@class,\"filter-dropdown\")])[2]/select")));
		Select score = new Select(driver.findElementByXPath("(//div[contains(@class,\"filter-dropdown\")])[2]/select"));
		score.selectByVisibleText("6.5 & below");

//		6) Total Fees as Max 20L

		wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.stalenessOf(driver.findElementByXPath("//p[text()='Max 20 Lakhs']//preceding-sibling::span"))));
//		wait.until(ExpectedConditions
//				.invisibilityOfElementLocated(By.xpath("//p[text()='Max 20 Lakhs']//preceding-sibling::span")));
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//p[text()='Max 20 Lakhs']//preceding-sibling::span")));
		driver.findElementByXPath("//p[text()='Max 20 Lakhs']//preceding-sibling::span").click();

//		7) Capture the college Names and fees only if it is Engineering  course 
		Thread.sleep(5000);
		List<WebElement> courseNameList = driver
				.findElementsByXPath("//div[contains(@class,\"course-touple\")]//a[@class='tuple-sub-title']");

		List<WebElement> collegeNameList = driver.findElementsByXPath("//div[@class='tuple-title']/p[2]/a");

		List<WebElement> feesList = driver
				.findElementsByXPath("//strong[text()=' 1st Year Total Fees']/following-sibling::p");

//		BiFunction<List<WebElement>, List<WebElement>, Map<String, String>> fun = ((coll, fees) -> {
//
//			return StudyAbroadHelper.filter(coll, fees);
//		});

//		System.out.println(fun.apply(collegeNameList, feesList));

		Map<String, String> collegeListWithFees = new HashMap<>();
		Map<String, String> courseWithFees = new HashMap<>();
		for (int i = 0; i < courseNameList.size(); i++) {
			if (courseNameList.get(i).getText().contains("Engineering")) {
				collegeListWithFees.put(collegeNameList.get(i).getText(), feesList.get(i).getText());
				courseWithFees.put(courseNameList.get(i).getText(), feesList.get(i).getText());
			}
		}
		System.out.println(collegeListWithFees);

//		for (int i = 0; i < collegeNameList.size(); i++) {
//			collegeListWithFees.put(collegeNameList.get(i).getText(), feesList.get(i).getText());
//		}
//
//		Map<String, String> output = collegeListWithFees.entrySet().stream()
//				.filter(map -> map.getKey().contains("Engineering"))
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//		System.out.println(output);

//		8) Take 20 colleges by Click Next button and go to next page.
		for (WebElement a : collegeNameList) {
			System.out.println(a.getText());
		}
		driver.findElementByXPath("//a[text()='Next']").click();

//		9) Search the college name in the search box based on low fees
		Map<String, Double> output2 = new LinkedHashMap<>();
		for (Map.Entry<String, String> a : collegeListWithFees.entrySet()) {
			String replaceAll = a.getValue().replaceAll("[^0-9.]", "");
			double fee = Double.parseDouble(replaceAll);
			output2.put(a.getValue(), fee);
		}

		List<Entry<String, Double>> entries = new ArrayList<>(output2.entrySet());
		Collections.sort(entries, new Comparator<Entry<String, Double>>() {

			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				// TODO Auto-generated method stub
				return o1.getValue().compareTo(o2.getValue());
			}
		});
//		System.out.println(temp);
		String lowestFee = entries.get(0).getKey();
		System.out.println(lowestFee);
		String collegeName = new String();
		for (Map.Entry<String, String> a : collegeListWithFees.entrySet()) {
			if (a.getValue().equals(lowestFee)) {
				collegeName = a.getKey();
			}
		}
		Thread.sleep(5000);

		driver.findElementById("seachTextBox").click();
		driver.findElementById("mainSearchBox").sendKeys(collegeName);
		driver.findElementById("searchGoButton").click();

//		10) Match the IELTS score, course Title and country from the University Page
		driver.findElementByXPath("//label[text()='Courses']").click();
		String coureseName = new String();
		for(Map.Entry<String, String> a : courseWithFees.entrySet()) {
			if(a.getValue().equals(lowestFee)) {
				coureseName=a.getKey();
			}
		}
		
		String Itil = driver.findElementByXPath("//a[text()='"+coureseName+"']/following-sibling::div/div/div[3]/p").getText();
		System.out.println(Itil);
		
//	
	}
}
