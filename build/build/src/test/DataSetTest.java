package test;

import org.junit.Test;

import extract.XLS.data.view.DataSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

@SuppressWarnings("restriction")

public class DataSetTest {
	
	@Test
	public void constructorEmptyTest() {
		DataSet dataSet = new DataSet();
	}
	
	@Test
	public void constructorWithDataTest() {
		
		ObservableList<String> columns = 
	    FXCollections.observableArrayList(
	        "A",
	        "B");
		
		ObservableList<String> rows = FXCollections.observableArrayList(
		        "1", 
		        "2");
		
		CheckBox checkBox = new CheckBox("");
		
		DataSet dataSet = new DataSet(
				"languageSymbol", 
				checkBox, 
				new ChoiceBox<String>(), 
				new ChoiceBox<String>(), 
				new ChoiceBox<String>(), 
				new TextArea(), 
				columns, 
				rows, 
				rows);
				
	}
}
