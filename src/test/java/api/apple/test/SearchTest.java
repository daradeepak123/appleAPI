package api.apple.test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apple.generic.Dd.Limit;

import api.endpoints.Routes;
import api.endpoints.SearchEndPoints;
import api.payload.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;



public class SearchTest {
	
	 @DataProvider(name = "excelDataProvider")
	    public static Object[][] excelDataProvider() {
	        String filePath = "./dataApple.xlsx";
	        String sheetName = "Sheet1";
	        int columnIndex = 0; // Change this to the index of the column you want to use

	        // Read Excel column data
	        String[] columnData = ExcelReader.readExcelColumn(filePath, sheetName, columnIndex);

	        // Create data array for TestNG Data Provider
	        Object[][] testData = new Object[columnData.length][1];
	        for (int i = 0; i < columnData.length; i++) {
	            testData[i][0] = columnData[i];
	        }

	        return testData;
	    }
	 @DataProvider(name = "readInvalidtermData")
	 public static Object[][] readInvalidtermData() {
	        String filePath = "./dataApple.xlsx";
	        String sheetName = "Sheet1";
	        int columnIndex = 1; // Change this to the index of the column you want to use

	        // Read Excel column data
	        String[] columnData = ExcelReader.readExcelColumn(filePath, sheetName, columnIndex);

	        // Create data array for TestNG Data Provider
	        Object[][] testData = new Object[columnData.length][1];
	        for (int i = 0; i < columnData.length; i++) {
	            testData[i][0] = columnData[i];
	        }

	        return testData;
	    }
	
	 
	 
	 
	 @DataProvider(name = "entityMusic")
	 public static Object[][] entityMusic() {
	        String filePath = "./dataApple.xlsx";
	        String sheetName = "Sheet1";
	        int columnIndex = 2; //, For music Change this to the index of the column you want to use

	        // Read Excel column data
	        String[] columnData = ExcelReader.readExcelColumn(filePath, sheetName, columnIndex);

	        // Create data array for TestNG Data Provider
	        Object[][] testData = new Object[columnData.length][1];
	        for (int i = 0; i < columnData.length; i++) {
	            testData[i][0] = columnData[i];
	        }

	        return testData;
	    }
	@Test(dataProvider = "excelDataProvider")
	public void testWithTerm(String data)
	{   
		  
		 Response res= SearchEndPoints.getSearchWithTerm(data);
	       res.then().statusCode(200);		
	}
	
	@Test(dataProvider = "excelDataProvider")
	public void testWithTermAndCountry(String data)
	{
		 Response res= SearchEndPoints. getSearchWithTermCountry(data,"ca");
	       res.then().log().body();
	      
	}
	@Test(dataProvider="readInvalidtermData")
	public void testTermWithInvlidRec(String data)
	{
		 Response res= SearchEndPoints.getSearchWithTerm(data);
	       res.then().statusCode(200);	
	       res.then()
           .assertThat()
           .statusCode(200) //  status code is 200
           .body("resultCount", equalTo(0))
           .body("results", empty());
	}
	
	@Test
	public void testTermWithLimitCount()
	{
		Response res= SearchEndPoints.getSearchWithTerm("jhon",Limit.COUNT);
		res.then().log().body();
		 res.then()
         .assertThat()
         .statusCode(200) //  status code is 200
         .body("resultCount", equalTo(Limit.COUNT));  //limit the count with 1
		
		
	}
	
	@Test(dataProvider = "entityMusic")
	public void testWithTermMusic(String music)
	{
		 Response res= SearchEndPoints.getSearchWithTermMusic("jack",music);
	       res.then().statusCode(200);
	      
	}
	
	//read entity software 
	 @DataProvider(name = "enititySoftware")
	 public static Object[][] readEntitySoftware() {
	        String filePath = "./dataApple.xlsx";
	        String sheetName = "Sheet1";
	        int columnIndex = 3; // read software Change this to the index of the column you want to use

	        // Read Excel column data
	        String[] columnData = ExcelReader.readExcelColumn(filePath, sheetName, columnIndex);

	        // Create data array for TestNG Data Provider
	        Object[][] testData = new Object[columnData.length][1];
	        for (int i = 0; i < columnData.length; i++) {
	            testData[i][0] = columnData[i];
	        }

	        return testData;
	    }
	
	 
	 //search a single name with country and diffent softwares 
	@Test(dataProvider="enititySoftware")
	public void searchRecWithNameCountryEntity(String software)
	{
		 Response res= SearchEndPoints.getSearchWithNameCountrySoftware("jack","us",software);
	       res.then().statusCode(200);
	       res.then().contentType("text/javascript; charset=utf-8");
	}
	

	
	//data Provider for media 
	 @DataProvider(name = "mediaDataProvider")
	    public static Object[][] mediaDataProvider() {
	        return new Object[][] {
	            { "movie" },
	            { "podcast" },
	            { "musicVideo" },
	            { "audiobook" },
	            { "shortFilm" },
	            { "tvShow" },
	            { "software" },
	            { "ebook" },
	            { "all" }
	        };
	    }
	@Test(dataProvider="mediaDataProvider")
	public void searchNameWithMedia(String med)
	{
		Response res=SearchEndPoints.getSearchWithNameMedia("jhon", med);
		res.then().statusCode(200);
		 res.then().body("results.wrapperType", everyItem(equalTo("track")));
		
	}
	
	
	
	//directly using given when then 
	//verify that limit is only 200 when we give above value than 200
	@Test
	public void serachNameWithAboveLimit()
	{
		Response res=RestAssured.given()
				.queryParam("term","jhon")
				.queryParam("limit","500")
				.when().get(Routes.searchTerm);
		
	    res.then().statusCode(200);
	    res.then().body("resultCount", equalTo(200));
	    
	}
	
	
	
	

}
