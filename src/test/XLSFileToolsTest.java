package test;

import org.junit.Test;

import extract.XLS.data.view.DataSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class XLSFileToolsTest {

	@Test
	public void readCellsTest() {
		String value = "valueTest";
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
