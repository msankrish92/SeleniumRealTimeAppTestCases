package supportingClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

public class StudyAbroadHelper {

	public static Map<String, String> filter(List<WebElement> collegeNameList, List<WebElement> feesList) {
		Map<String, String> enggWithFeesList = new HashMap<>();
		for (int i = 0; i < collegeNameList.size(); i++) {
			if (collegeNameList.get(i).getText().contains("Engineering")) {
				enggWithFeesList.put(collegeNameList.get(i).getText(), feesList.get(i).getText());
			}
		}
		return enggWithFeesList;
	}
	
}
