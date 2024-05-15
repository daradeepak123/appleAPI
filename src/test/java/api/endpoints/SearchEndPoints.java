package api.endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;



public class SearchEndPoints {
	
	public static Response getSearchWithTerm(String name) {

		Response res = given()
				.queryParam("term",name)
				.when()
				.get(Routes.searchTerm);

		return res;


	}
	
	public static Response getSearchWithTermCountry(String name,String c) {

		Response res = given()
				.queryParam("term",name)
				.queryParam("country", c)
				.when()
				.get(Routes.searchTerm);

		return res;


	}
	
	//Serach record with limit count 
	public static Response getSearchWithTerm(String name,int limit) {

		Response res = given()
				.queryParam("term",name)
				.queryParam("limit",limit )
				.when()
				.get(Routes.searchTerm);

		return res;


	}
	
	//search name with music entity
	
	public static Response getSearchWithTermMusic(String name,String m) {

		Response res = given()
				.queryParam("term",name)
				.queryParam("music", m)
				.when()
				.get(Routes.searchTerm);

		return res;


	}
	
	
	//search name with name country software
	
	public static Response getSearchWithNameCountrySoftware(String name,String m,String software) {

		Response res = given()
				.queryParam("term",name)
				.queryParam("music", m)
				.queryParam("entity",software )
				.when()
				.get(Routes.searchTerm);

		return res;


	}
	public static Response getSearchWithNameMedia(String name,String media) {

		Response res = given()
				.queryParam("term",name)
				.queryParam("music", media)
				.when()
				.get(Routes.searchTerm);

		return res;


	}
	
	

}
