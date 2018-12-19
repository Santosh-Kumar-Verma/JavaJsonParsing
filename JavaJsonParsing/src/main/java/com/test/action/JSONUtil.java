package com.test.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.test.dto.Employee;

public class JSONUtil {

	public static void convertJavaObjectToJSONByJackson(String filePaht,List<Employee> empList) throws JsonGenerationException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder sb = new StringBuilder();
        for (Employee employee : empList) {
        	String jsonInString = mapper.writeValueAsString(employee);
        	sb.append(jsonInString);
            System.out.println(jsonInString);
		}
        mapper.writeValue(new File(filePaht), sb.toString());
	}
	public static void convertJsonObjectToJavaObjectByJackson(String filePaht,List<Employee> empList) throws JsonGenerationException, JsonMappingException, IOException {
		
			ObjectMapper mapper = new ObjectMapper();
	        System.out.println("---------------------Object Serialization---------------");
        	String jsonInString = mapper.writeValueAsString(empList.get(0));
            System.out.println(jsonInString);
		
            //JSON from String to Object
            System.out.println("---------------------Object DeSerialization---------------");
            Employee employee = mapper.readValue(jsonInString, Employee.class);
            System.out.println(employee);
	}
	public static void convertJavaObjectToJSONByGoogleJson(String filePaht,List<Employee> empList) throws JsonIOException, IOException {
		
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
        for (Employee employee : empList) {
        	String jsonInString = gson.toJson(employee);
        	sb.append(jsonInString);
            System.out.println(jsonInString);
		}
        gson.toJson(sb.toString(), new FileWriter(filePaht));
	}
	public static void convertJSONObjectToJavaByGoogleJson(String filePaht,List<Employee> empList) throws JsonIOException, IOException {
		
		Gson gson = new Gson();
        for (Employee employee : empList) {
        	 System.out.println("---------------------Object Serialization---------------");
        	String jsonInString = gson.toJson(employee);
        	System.out.println(jsonInString);
            System.out.println("---------------------Object DeSerialization---------------");
            Employee emplyee = gson.fromJson(jsonInString, Employee.class);
            System.out.println(emplyee);
		}
	}
	@SuppressWarnings("unchecked")
	public static void writeJSONToFileByJSONSimple(String filePaht) throws JsonGenerationException, JsonMappingException, IOException {
		
		JSONObject obj = new JSONObject();
        obj.put("name", "Satnosh");
        obj.put("age", new Integer(10));

        JSONArray list = new JSONArray();
        list.add("msg1");
        list.add("msg2");
        list.add("msg3");

        obj.put("messages", list);

        try (FileWriter file = new FileWriter(filePaht)) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);
	}
	public static void readJSONToFileByJSONSimple(String filePath) {
		 JSONParser parser = new JSONParser();
	        try {

	            Object obj = parser.parse(new FileReader(filePath));

	            JSONObject jsonObject = (JSONObject) obj;
	            System.out.println(jsonObject);

	            String name = (String) jsonObject.get("name");
	            System.out.println(name);

	            long age = (Long) jsonObject.get("age");
	            System.out.println(age);

	            // loop array
	            JSONArray msg = (JSONArray) jsonObject.get("messages");
	            Iterator<String> iterator = msg.iterator();
	            while (iterator.hasNext()) {
	                System.out.println(iterator.next());
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	    }
}
