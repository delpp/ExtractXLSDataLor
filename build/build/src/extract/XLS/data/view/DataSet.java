package extract.XLS.data.view;

import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

@SuppressWarnings("restriction")
public class DataSet {
	private String language;
	private CheckBox checkBox;
	private ChoiceBox<String> columnNumberChoiceBox;
	private ChoiceBox<String> rowStartNumberChoiceBox;
	private ChoiceBox<String> rowEndNumberChoiceBox;
	private TextArea textFieldDescription;
	
	private int columnNumber;
	private int rowStartNumber;
	private int rowEndNumber;

	
	public DataSet() {
		
	}
	
	public DataSet(String language, 
			CheckBox checkBox, 
			ChoiceBox<String> columnNumberChoiceBox, 
			ChoiceBox<String> rowStartNumberChoiceBox, 
			ChoiceBox<String> rowEndNumberChoiceBox, 
			TextArea textFieldDescription,
			ObservableList<String> column,
			ObservableList<String> startRow,
			ObservableList<String> endRow) {
		
		this.language = language;
		this.checkBox = checkBox;
		this.columnNumberChoiceBox = columnNumberChoiceBox;
		this.rowStartNumberChoiceBox = rowStartNumberChoiceBox;
		this.rowEndNumberChoiceBox = rowEndNumberChoiceBox;
		this.textFieldDescription = textFieldDescription;

		columnNumberChoiceBox.getItems().addAll(column);		
		columnNumberChoiceBox.getSelectionModel().select(0);
		
		rowStartNumberChoiceBox.getItems().addAll(startRow);		
		rowStartNumberChoiceBox.getSelectionModel().select(0);	
		
		rowEndNumberChoiceBox.getItems().addAll(endRow);		
		rowEndNumberChoiceBox.getSelectionModel().select(0);
	}
	
	
	public boolean isDisableCheckBox() {
		return checkBox.isDisable();

	}
	public void setDisableCheckBox(boolean disable) {
		checkBox.setDisable(disable);
	}
	public boolean isSelectedCheckBox() {
		return checkBox.isSelected();
	}
	public void selectCheckBox() {
		checkBox.setSelected(true);
	}
	
	
	
	public boolean isDisableTextFieldDescription() {
		return textFieldDescription.isDisable();
	}
	public void setDisableTextFieldDescription(boolean disable) {
		textFieldDescription.setDisable(disable);
	}
	public void writeToTextFieldDescription(String description){
		textFieldDescription.setText(description);
	}
	public String readTextFieldDescription() {
		return textFieldDescription.getText();
	}

	
	public boolean isDisableColumnNumberChoiceBox() {
		return columnNumberChoiceBox.isDisable();
	}
	public void setColumnNumberChoiceBox(boolean disable) {
		columnNumberChoiceBox.setDisable(disable);
	}
	public int getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber() {
		columnNumber = columnNumberChoiceBox.getSelectionModel().getSelectedIndex();
	}
	public void selectColumn(int numberToSelect) {
		columnNumberChoiceBox.getSelectionModel().select(numberToSelect);
	}
	public int getSelectedColumn() {		
		return columnNumberChoiceBox.getSelectionModel().getSelectedIndex();
	}
	public int getSizeColumnNumberChoiceBox() {
		return columnNumberChoiceBox.getItems().size();
	}
	
	
	public boolean isDisablerowStartNumberChoiceBox() {
		return rowStartNumberChoiceBox.isDisable();
	}
	public void setRowStartNumberChoiceBox(boolean disable) {
		rowStartNumberChoiceBox.setDisable(disable);
	}
	public int getRowStartNumber() {
		return rowStartNumber;
	}	
	public void setRowStartNumber() {
		//rowStartNumber = rowStartNumberChoiceBox.getSelectionModel().getSelectedIndex();
		rowStartNumber = Integer.valueOf(rowStartNumberChoiceBox.getSelectionModel().getSelectedItem());
	}
	public void selectRowStart(int numberToSelect) {
		rowStartNumberChoiceBox.getSelectionModel().select(numberToSelect);		
	}
	public int getSelectedRowStart() {	
		return rowStartNumberChoiceBox.getSelectionModel().getSelectedIndex();
	}

	public boolean isDisablerowEndNumberChoiceBox() {
		return rowEndNumberChoiceBox.isDisable();
	}
	public void setRowEndNumberChoiceBox(boolean disable) {
		rowEndNumberChoiceBox.setDisable(disable);
	}
	public int getRowEndNumber() {
		return rowEndNumber;
	}	
	public void setRowEndNumber() {
		rowEndNumber = Integer.valueOf(rowEndNumberChoiceBox.getSelectionModel().getSelectedItem());
		//System.out.println("Wiersz końcowy: " + rowEndNumber);
	}
	public void selectRowEnd(int numberToSelect) {
		rowEndNumberChoiceBox.getSelectionModel().select(numberToSelect);
	}
	public int getSelectedRowEnd() {		
		return rowEndNumberChoiceBox.getSelectionModel().getSelectedIndex();
	}
	
/*	public void setEndRowNumberRange() {
		ObservableList<String> rows = FXCollections.observableArrayList();

		//for (int i = rowStartNumberChoiceBox.getSelectionModel().getSelectedIndex(); i < rowStartNumberChoiceBox.getItems().size(); i++) {
		//	rows.add(rowStartNumberChoiceBox.getItems().get(i));
		//}
		
		//System.out.println("kontent: " + rowStartNumberChoiceBox.getItems());
		
		rows = rowStartNumberChoiceBox.getItems();
		
		System.out.println("Language: " + language + ", kontent: " + rows);
				
		rows.remove(rowStartNumberChoiceBox.getSelectionModel().getSelectedIndex(), rowStartNumberChoiceBox.getItems().size());
		
		String select = rowEndNumberChoiceBox.getSelectionModel().getSelectedItem();
		
		
		
		rowEndNumberChoiceBox.getItems().clear();
		rowEndNumberChoiceBox.getItems().addAll(rows);
		
		
		rowEndNumberChoiceBox.getSelectionModel().select(select);
		if (rowEndNumberChoiceBox.getSelectionModel().getSelectedIndex() < 0)
			rowEndNumberChoiceBox.getSelectionModel().select(0);
		
		System.out.println("zmieniono zakres");
	}*/
	

	public String getLanguageName() {
		return language;
	}
	
	
	
	
}
