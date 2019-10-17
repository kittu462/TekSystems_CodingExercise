package test.java.sampleproject.com;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import main.java.sampleproject.com.GetCapitalCity;

public class GetCapitalCityTest {

	//This test is to verify capital city based on country name
	@Test
	public void testWithName() throws ParseException {
		
		String capitalCity = GetCapitalCity.getCapitalCity("United States of America");

		Assert.assertEquals("Washington, D.C.", capitalCity);
	}
	//This test is to verify capital city based on alpha-2 code
	@Test
	public void testWithAlpha2Code() throws ParseException {
		
		String capitalCity = GetCapitalCity.getCapitalCity("US");

		Assert.assertEquals("Washington, D.C.", capitalCity);
	}
	//This test is to verify capital city based on alpha-3 code
	@Test
	public void testWithAlpha3Code() throws ParseException {
		
		String capitalCity = GetCapitalCity.getCapitalCity("USA");

		Assert.assertEquals("Washington, D.C.", capitalCity);
	}
	//This is negative test 
	@Test
	public void testNegative() throws ParseException {
		
		String capitalCity = GetCapitalCity.getCapitalCity("bjhgfdxgj");

		Assert.assertEquals("Sorry: Country name or code does not exist", capitalCity);
	}

}
