package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import extract.XLS.data.model.LanguageRange;

public class LanguageRangeTests {
	private LanguageRange languageRange;

	@Test
	public void constructorEmptyTest() {
		languageRange = new LanguageRange();
	}

	@Test
	public void constructorWithDataTest() {
		languageRange = new LanguageRange("LanguageTest", 1, 1, 1);
	}
	
	@Test 
	public void setRowStartTest() { languageRange = new LanguageRange();
	  languageRange.setRowStart(3);
	  
	  int value = languageRange.getRowStart();
	  
	  assertEquals(3, value); 
	}
	 
	@Test
	public void getRowStartTest() {
		languageRange = new LanguageRange();
		languageRange.setRowStart(0);
		
		int value = languageRange.getRowStart();

		assertEquals(0, value);
	}
	
	@Test
	public void setRowEndTest() {
		languageRange = new LanguageRange();
		languageRange.setRowEnd(3);

		int value = languageRange.getRowEnd();

		assertEquals(3, value);
	}
	
	@Test
	public void getRowEndTest() {
		languageRange = new LanguageRange();
		languageRange.setRowEnd(0);
		
		int value = languageRange.getRowEnd();

		assertEquals(0, value);
	}

	@Test
	public void setColumnTest() {
		languageRange = new LanguageRange();
		languageRange.setColumn(3);

		int value = languageRange.getColumn();

		assertEquals(3, value);
	}
	
	@Test
	public void getColumnTest() {
		languageRange = new LanguageRange();
		languageRange.setColumn(0);
		
		int value = languageRange.getColumn();

		assertEquals(0, value);
	}
	
	@Test
	public void getLanguageTest() {
		languageRange = new LanguageRange();

		String value = languageRange.getLanguage();

		assertEquals("", value);
	}
	
	@Test
	public void setLanguageTest() {
		languageRange = new LanguageRange();		
		languageRange.setLanguage("testLanguage");

		String value = languageRange.getLanguage();

		assertEquals("testLanguage", value);
	}

	@Test
	public void toStringTest() {
		languageRange = new LanguageRange();
		languageRange.setRowStart(0);
		languageRange.setRowEnd(0);
		languageRange.setColumn(0);
		languageRange.setLanguage("testLanguage");

		String value = languageRange.toString();

		assertEquals("LanguageRange [language=testLanguage, rowStart=0, rowEnd=0, column=0]", value);
	}
}
