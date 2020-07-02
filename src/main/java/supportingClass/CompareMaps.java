package supportingClass;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

public class CompareMaps {

	public static void differences(Map<String, String> first, Map<String, String> second) {

		Map<String, String> a1 = new LinkedHashMap<String, String>();

		for (Map.Entry<String, String> a : first.entrySet()) {
			String key = a.getKey();

			String value1 = a.getValue();

			String value2 = second.get(key);
//			if(value1.equals(value2)) {
//				
//			}else {
//				a1.put(value1, value2);
//			}

			String c = value1.equals(value2) ? "" : a1.put(value1, value2);

			System.out.println(c);
		}

		System.out.println(a1);

		MapDifference<String, String> difference = Maps.difference(first, second);
		System.out.println(difference);

	}

	public static void differences2(Map<String, String> first, Map<String, String> second) {
		Map<String, String> a1 = new LinkedHashMap<String, String>();
		Iterator<Map.Entry<String, String>> iter1 = first.entrySet().iterator();
		Iterator<Map.Entry<String, String>> iter2 = second.entrySet().iterator();
		while (iter1.hasNext()) {
			Entry<String, String> entry1 = iter1.next();
			Entry<String, String> entry2 = iter2.next();
			if(!entry1.getValue().equals(entry2.getValue())) {
				a1.put(entry1.getValue(), entry2.getValue());
			}
		}
		System.out.println(a1);
	}

	public static void main(String[] args) {
		Map<String, String> dioEngineKeyValue = new LinkedHashMap<String, String>();
		Map<String, String> activaEngineKeyValue = new LinkedHashMap<String, String>();

		dioEngineKeyValue.put("Type", "Fan Cooled, 4 Stroke, SI Engine");
		dioEngineKeyValue.put("Displacement", "109.51cc");
		dioEngineKeyValue.put("Max Net Power", "5.71 kW @ 8000 rpm");

		activaEngineKeyValue.put("Type", "Fan Cooled, 4 Stroke, SI Engine");
		activaEngineKeyValue.put("Displacement", "124 cc");
		activaEngineKeyValue.put("Max Net Power", "6.10 kW @ 6500 rpm");

//		System.out.println(dioEngineKeyValue.equals(activaEngineKeyValue));

		differences2(dioEngineKeyValue, activaEngineKeyValue);

//		String a = "Sanjay";
//		String b = "Sanjay";
//		
//		String c = a.equals("Sanjay")?"equal":"notEqual";
	}

}
