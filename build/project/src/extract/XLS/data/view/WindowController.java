package extract.XLS.data.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import extract.XLS.data.model.LanguageRange;
import extract.XLS.data.model.XLSFileTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class WindowController {
	@FXML private TextField fileName;
	@FXML private TextField stickerNumber;
	
	@FXML private CheckBox PLChk;	
	@FXML private ChoiceBox<String> columnPL;
	@FXML private ChoiceBox<String> rowStartPL;
	@FXML private ChoiceBox<String> rowEndPL;	
	@FXML private TextArea descriptionPL;	
	@FXML private Button copyPreferencesPLBtn;
	
	@FXML private CheckBox LVChk;	
	@FXML private ChoiceBox<String> columnLV;
	@FXML private ChoiceBox<String> rowStartLV;
	@FXML private ChoiceBox<String> rowEndLV;	
	@FXML private TextArea descriptionLV;	
	@FXML private Button copyPreferencesLVBtn;
	
	@FXML private CheckBox LTChk;	
	@FXML private ChoiceBox<String> columnLT;
	@FXML private ChoiceBox<String> rowStartLT;
	@FXML private ChoiceBox<String> rowEndLT;	
	@FXML private TextArea descriptionLT;	
	
	@FXML private CheckBox EEChk;	
	@FXML private ChoiceBox<String> columnEE;
	@FXML private ChoiceBox<String> rowStartEE;
	@FXML private ChoiceBox<String> rowEndEE;	
	@FXML private TextArea descriptionEE;	
	
	@FXML private CheckBox RUChk;	
	@FXML private ChoiceBox<String> columnRU;
	@FXML private ChoiceBox<String> rowStartRU;
	@FXML private ChoiceBox<String> rowEndRU;	
	@FXML private TextArea descriptionRU;	

	
	@FXML private Button saveDataBtn;
	@FXML private Button loadDataBtn;

	@FXML private Button deleteLineBreaksBtn;
	@FXML private Button deleteDoubleSpacesBtn;
	
	@FXML private Tooltip pokazCalaSciezke;
	
	@FXML private Stage stage;
	
	private String path;
	private List<DataSet> listDataSet;
	ObservableList<String> rows;
	
	@FXML private void initialize(){
		path = "";		
		
		ObservableList<String> columns = 
			    FXCollections.observableArrayList(
			        "A",
			        "B",
			        "C",
			        "D",
			        "E",
			        "F",
			        "G",
			        "H",
			        "I",
			        "J",
			        "K",
			        "L",
			        "M",
			        "N",
			        "O"
			    );
		
		rows = FXCollections.observableArrayList(
			        "1", "2", "3", "4", "5", "6", "7", "8", "9",
			        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
			        "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			        "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
			        "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
			        "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
			        "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
			        "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
			        "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
			        "90", "91", "92", "93", "94", "95", "96", "97", "98", "99"
			    );			

		listDataSet = new ArrayList<DataSet>();
		listDataSet.add(new DataSet("PL", PLChk, columnPL, rowStartPL, rowEndPL, descriptionPL, columns, rows, rows));
		listDataSet.add(new DataSet("LV", LVChk, columnLV, rowStartLV, rowEndLV, descriptionLV, columns, rows, rows));
		listDataSet.add(new DataSet("LT", LTChk, columnLT, rowStartLT, rowEndLT, descriptionLT, columns, rows, rows));
		listDataSet.add(new DataSet("EE", EEChk, columnEE, rowStartEE, rowEndEE, descriptionEE, columns, rows, rows));
		listDataSet.add(new DataSet("RU", RUChk, columnRU, rowStartRU, rowEndRU, descriptionRU, columns, rows, rows));
		
		// disable all fields
		saveDataBtn.setDisable(true);
		loadDataBtn.setDisable(true);
		deleteLineBreaksBtn.setDisable(true);
		deleteDoubleSpacesBtn.setDisable(true);
		copyPreferencesPLBtn.setDisable(true);
		copyPreferencesLVBtn.setDisable(true);
		
		for (DataSet dataSet : listDataSet) {
			dataSet.setDisableCheckBox(true);
			dataSet.setColumnNumberChoiceBox(true);
			dataSet.setRowStartNumberChoiceBox(true);
			dataSet.setRowEndNumberChoiceBox(true);
			dataSet.setDisableTextFieldDescription(true);
		}
	}
	
	@FXML private void setFile() throws Exception{
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System
				.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("XLS Files", "*.xls", "*.XLS"));
		
		final File file = fileChooser.showOpenDialog(stage);
		
		if (file != null)
		try{
			path = file.getAbsolutePath();
			enableCheckBoxes();
			enableButtons();
			
			pokazCalaSciezke.setText(path);
	
			fileName.setText(path);
			
			stickerNumber.setText(lookForStrickerNumber(file.getName()));
			
			List<LanguageRange> languagesList = new ArrayList<LanguageRange>();
			//languagesList.add(lookForLanguage(path, "PL"));
			languagesList.add(lookForLanguage(path, new String[] {"PL"}));
			languagesList.add(lookForLanguage(path, new String[] {"LV"}));
			languagesList.add(lookForLanguage(path, new String[] {"LT"}));
			languagesList.add(lookForLanguage(path, new String[] {"EE", "EST"}));
			languagesList.add(lookForLanguage(path, new String[] {"RU"}));
			
			listDataSet.stream().forEach(x ->{
				languagesList.stream().forEach(y -> {
					if (x.getLanguageName().equals(y.getLanguage())) {						
						x.selectColumn(y.getColumn());
						x.selectRowEnd(y.getRowEnd());
						x.selectRowStart(y.getRowStart()+1);						
					}
				});
			});

		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	private String lookForStrickerNumber(String name) {
		String txtToFind = "9000";
		
		int index = name.indexOf(txtToFind);
		System.out.println("Index: " + index);
		
		if (index > -1) return name.substring(index, index+10);
		
		return "0000000000";
	}
	
	
	private LanguageRange lookForLanguage(String fileName, String[] language) throws Exception{
		
		for (int rowStart = 0; rowStart <= 1; rowStart++) {
			for (int column = 0; column <= 10; column++) {
				String zawartosc = XLSFileTools.readCell(fileName, 0, rowStart, column);		
				System.out.println("Zawartość wiersza " + rowStart + " , kolumny " + column + ": " + zawartosc);
				
				for (String lang : language) {
					if (zawartosc.equals(lang)) {
						int rowEnd = rowStart;
						while ((zawartosc != null) && (!zawartosc.equals(""))) {
							rowEnd = rowEnd + 1;
							zawartosc = XLSFileTools.readCell(fileName, 0, rowEnd, column);
							
							System.out.println("zawartosc: " + zawartosc);
						}
						return new LanguageRange(language[0], rowStart, rowEnd-1, column);
					}
				}				
			}
		}
		
		return new LanguageRange();
	}
	
	private LanguageRange lookForLanguage(String fileName, String language) throws Exception{
				
		for (int rowStart = 0; rowStart <= 1; rowStart++) {
			for (int column = 0; column <= 10; column++) {
				String zawartosc = XLSFileTools.readCell(fileName, 0, rowStart, column);		
				System.out.println("Zawartość wiersza " + rowStart + " , kolumny " + column + ": " + zawartosc);
				
				if (zawartosc.equals(language)) {
					int rowEnd = rowStart;
					while ((zawartosc != null) && (!zawartosc.equals(""))) {
						rowEnd = rowEnd + 1;
						zawartosc = XLSFileTools.readCell(fileName, 0, rowEnd, column);
						
						System.out.println("zawartosc: " + zawartosc);
					}
					return new LanguageRange(language, rowStart, rowEnd-1, column);
				}
			}
		}
		
		return new LanguageRange();
	}
	
	private LanguageRange lookForLanguage(String fileName, String language1, String language2) throws Exception{
		String language;
		for (int rowStart = 0; rowStart <= 1; rowStart++) {
			for (int column = 0; column <= 10; column++) {
				String zawartosc = XLSFileTools.readCell(fileName, 0, rowStart, column);		
				System.out.println("Zawartość wiersza " + rowStart + " , kolumny " + column + ": " + zawartosc);
				
				if ((zawartosc.equals(language1)) || (zawartosc.equals(language2))) {
					language = "EE";
					int rowEnd = rowStart;
					while ((zawartosc != null) && (!zawartosc.equals(""))) {
						rowEnd+=1;
						//rowEnd = rowEnd + 1;
						zawartosc = XLSFileTools.readCell(fileName, 0, rowEnd, column);
						
						System.out.println("zawartosc: " + zawartosc);
				}

				return new LanguageRange(language, rowStart, rowEnd -1, column);
			}
			}
		}
		
		return new LanguageRange();
	}
	
	@FXML private void loadData() throws Exception{		
			listDataSet.stream().forEach(x -> {
				x.setColumnNumber();
				x.setRowStartNumber();
				x.setRowEndNumber();
				String text = "";
				try {
					text = XLSFileTools.readCells(path, x);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// usunięcie tabulatorów
				text = text.replaceAll("\\t", " ");
				x.writeToTextFieldDescription(text);
			});	
			
		if (!listDataSet.isEmpty())
		for (int i = 0; i < listDataSet.size(); i++) 
			System.out.println(listDataSet.get(i).readTextFieldDescription());		
	}
	
	@FXML private void writeData() throws Exception{		
		ArrayList<String> daneProduktu = new ArrayList<String>();
		
		daneProduktu.add(stickerNumber.getText());
		
		listDataSet.stream().forEach(x -> daneProduktu.add(x.readTextFieldDescription()));
		
		String text = arrayToString(daneProduktu);

		System.out.println("path:" + path);
		System.out.println("Dane:" + text);
		
		int length = path.length();
		
		String nameWithPath = path.substring(0, length-3) + "txt";
		
		XLSFileTools.saveFile(nameWithPath, text);
	}
	
	// usunięcie ENTER-ów
	@FXML private void deleteLineBreaks() {
		
		listDataSet.stream().forEach(x ->{
			String text = x.readTextFieldDescription();
			text = text.replaceAll("\\r\\n|\\r|\\n", " ");
			x.writeToTextFieldDescription(text);
		});		
	}
	
	// usunięcie podwójnych spacji
	@FXML private void deleteDoubleSpaces() {
		
		listDataSet.stream().forEach(x -> {
			String text = x.readTextFieldDescription();
			while (text.indexOf("  ") != -1)
				text = text.replace("  ", " ");
			x.writeToTextFieldDescription(text);
		});
	}
	
	@FXML private void copyPreferencesFromPL() {
		DataSet PLData = listDataSet.get(0);
		
		int selectedColumn = PLData.getSelectedColumn();
		int selectedRowStart = PLData.getSelectedRowStart();
		int selectedRowEnd = PLData.getSelectedRowEnd();
		
		int columnToSelect = selectedColumn;
		for (int i = 1; i < listDataSet.size(); i++) {
			DataSet x = listDataSet.get(i);
			
			System.out.println("columnToSelect: " + columnToSelect);
			System.out.println("x.getSizeColumnNumberChoiceBox(): " + x.getSizeColumnNumberChoiceBox());

			
			if ((columnToSelect + 1) < x.getSizeColumnNumberChoiceBox()) columnToSelect += 1;
			System.out.println("columnToSelect: " + columnToSelect);
			x.selectColumn(columnToSelect);
			x.selectRowStart(selectedRowStart);
			x.selectRowEnd(selectedRowEnd);
		}
	}
	
	@FXML private void copyPreferencesFromLV() {
		DataSet PLData = listDataSet.get(1);
		
		int selectedColumn = PLData.getSelectedColumn();
		int selectedRowStart = PLData.getSelectedRowStart();
		int selectedRowEnd = PLData.getSelectedRowEnd();
		
		int columnToSelect = selectedColumn;
		for (int i = 2; i < listDataSet.size(); i++) {
			DataSet x = listDataSet.get(i);
			
			System.out.println("columnToSelect: " + columnToSelect);
			System.out.println("x.getSizeColumnNumberChoiceBox(): " + x.getSizeColumnNumberChoiceBox());

			
			if ((columnToSelect + 1) < x.getSizeColumnNumberChoiceBox()) columnToSelect += 1;
			System.out.println("columnToSelect: " + columnToSelect);
			x.selectColumn(columnToSelect);
			x.selectRowStart(selectedRowStart);
			x.selectRowEnd(selectedRowEnd);
		}
	}
		
	public static String arrayToString(ArrayList<String> data) {
		StringBuilder outputText = new StringBuilder();
		for (int i = 0; i < data.size(); i++){					
			outputText.append(data.get(i)).append("\t");
		}
		
		return outputText.substring(0, outputText.length() - 1).toString(); // usunięcie ostatniego tabulatora
	}
	
	
	
	@FXML private void enableDisableTextFields(){		
		
		for (DataSet dataSet : listDataSet) {
			if (dataSet.isSelectedCheckBox()) {
				dataSet.setColumnNumberChoiceBox(false);
				dataSet.setRowStartNumberChoiceBox(false);
				dataSet.setRowEndNumberChoiceBox(false);
				dataSet.setDisableTextFieldDescription(false);
				if (dataSet.getLanguageName().equals("PL")) copyPreferencesPLBtn.setDisable(false);
				if (dataSet.getLanguageName().equals("LV")) copyPreferencesLVBtn.setDisable(false);
			}
			else {
				dataSet.setColumnNumberChoiceBox(true);
				dataSet.setRowStartNumberChoiceBox(true);
				dataSet.setRowEndNumberChoiceBox(true);
				dataSet.setDisableTextFieldDescription(true);
				dataSet.writeToTextFieldDescription("");
				if (dataSet.getLanguageName().equals("PL")) copyPreferencesPLBtn.setDisable(true);
				if (dataSet.getLanguageName().equals("LV")) copyPreferencesLVBtn.setDisable(true);
			}
		}
	}
	
/*	@FXML private void setEndRowsRange() {
		for (DataSet dataSet : listDataSet) {
			//dataSet.setEndRowNumberRange();
		}
	}*/
	
	private void enableCheckBoxes() {
		for (DataSet dataSet : listDataSet)  
			dataSet.setDisableCheckBox(false);
	}
	
	private void enableButtons(){
		saveDataBtn.setDisable(false);
		loadDataBtn.setDisable(false);
		deleteLineBreaksBtn.setDisable(false);
		deleteDoubleSpacesBtn.setDisable(false);
	}
}
