package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		String jsonFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
				File.separator + "java" + File.separator + "rahulshettyacademy" + File.separator + "data" +
				File.separator + "PurchaseOrder.json"; 
				//C:\Users\priyamps\Downloads\Eclipse\Eclipse-Workspace\Selenium Udemy Rahul Shetty\SeleniumFrameworkDesign Part 4(Parameterization into Test)\src\test\java\rahulshettyacademy\data\PurchaseOrder.json
		//Read json to string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		
		//String to hashMap(Jackson Bind)
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
}
