package extract.XLS.data.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import extract.XLS.data.view.DataSet;


public class XLSFileTools {

	public static String readCells(
			String imputFileName, 
			DataSet dataSet) throws Exception{
		
		System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
		String text = "";
		
			if (dataSet.isSelectedCheckBox()) {
				int column = dataSet.getColumnNumber();
				
				try {
					FileInputStream file = new FileInputStream(imputFileName);
					Workbook workbook;
					
					Cell cell;
		
					workbook = new HSSFWorkbook(file);
					Sheet sheet = workbook.getSheetAt(0);
						
					ArrayList<String> dataFromOneColumn = new ArrayList<String>();
					
					for (int i = dataSet.getRowStartNumber()-1; i <= dataSet.getRowEndNumber()-1; i++)	{		
						System.out.println("kolumna: " + column + ", wiersz: " + i);
						cell = sheet.getRow(i).getCell(column);
						
						//System.out.println("Typ: " + cell.getCellType());
						
						//System.out.println("Czy komórka jest częścią grupy: " + cell.isPartOfArrayFormulaGroup());
	
						
						if ((cell != null) && (cell.getCellType() == 1)) {
							dataFromOneColumn.add(formatStringXLS(workbook, cell));
							dataFromOneColumn.add("\n");
						}
						else dataFromOneColumn.add("");
					}	
					
					// polączenie zawartości wszystkich komórek
					//String text = dataFromOneColumn.stream().map(e -> e.toString()).reduce("", String::concat);
					
					StringBuilder textBuilder = dataFromOneColumn.stream().map(e -> e.toString()).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
					
					text = textBuilder.toString();					
					
					workbook.close();
					file.close();
					
				}
				catch (IOException exIO){
					exIO.printStackTrace();
				}	
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
	return text;
}
	
	public static String readCell(
			String imputFileName, 
			int sheetNumber,
			int rowNumber,
			int columnNumber) throws Exception{
		
		System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
		
		String data = "";
		
		try {
			FileInputStream file = new FileInputStream(imputFileName);
			Workbook workbook;
			
			Cell cell;

			workbook = new HSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(sheetNumber);
										
			Row row = sheet.getRow(rowNumber);
							
			try {
				cell = row.getCell(columnNumber);	
				
				CellStyle cellStyle = cell.getCellStyle();

				data = formatStringXLS(workbook, cell, cellStyle);
				}
				catch (NullPointerException npex) {
					data = "";
				}
			
			workbook.close();
			file.close();
			
		}
		catch (IOException ex){
			ex.printStackTrace();
		}		
	return data;
}
	
	public static String formatStringXLS(Workbook workbook, Cell cell, CellStyle cellStyle){
		String text = "";
		
		if (cell.getCellType() == 0) { // typ: NUMERIC	
			cell.setCellType(1);
		}
			
		if (cell.getCellType() == 1) {	// typ: STRING			
			text = cell.getStringCellValue();

		}
		
		if (cell.getCellType() == 2) { //typ: FORMULA
			DataFormatter objDefaultFormat = new DataFormatter(true);
			//FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
			
			if (cellStyle != null) {
				  // save the format of the cell for later use.
				  //int styleIndex = Integer.parseInt(cellStyle);
				  //XSSFCellStyle style = cellStyle.getStyleAt(styleIndex);
				  
				  int formatIndex = cellStyle.getDataFormat();
				  
				  String formatString = cellStyle.getDataFormatString();
				  
				 /*System.out.println("cellStyle.getDataFormat(): " + formatIndex);
				 System.out.println("cellStyle.getDataFormatString(): " + formatString);
				 System.out.println("");*/
				 				  
				  if (formatString == null) {
				    // formatString could not be found, so it must be a builtin format.
				    formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
				  }
				  
				  try {
					// format the floating-point value
				  text = objDefaultFormat.formatRawCellContents(
					  cell.getNumericCellValue(), formatIndex, formatString);
				  }
				  catch (IllegalStateException ex) {
					  text = "";
				  }

				}
				
			else {
				FormulaEvaluator objFormulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
				objFormulaEvaluator.evaluateFormulaCell(cell);
				
				text = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
				
				text = objDefaultFormat.formatCellValue(cell);
			}

		}
		
		return text;
	}
	
	
	public static String formatStringXLS(Workbook workbook, Cell cell){
		StringBuilder outString = new StringBuilder();	
		String text = cell.getStringCellValue().toString();
		HSSFRichTextString rts = (HSSFRichTextString) cell.getRichStringCellValue();
		HSSFFont font;
		
		HSSFCellStyle style = (HSSFCellStyle) cell.getCellStyle();
		font = style.getFont(workbook);
		
		//System.out.println("Czy font pogrubiony getBoldweight: " + font.getBoldweight());
    
		boolean bold = false;
		boolean underline = false;
		boolean italic = false;		
		boolean global = false;
		
		int previousBold = 0;
		byte previousUnderline = 0;
		int previousCharSet = 0;
		//boolean globalUnderline = false;
		
		List<String> list = new ArrayList<String>(); // lista z włączonymi nastawami (bold - b, underline - u, italic - i)
		
		if (font.getBoldweight() == 700) {
			outString.append("<b>");
			bold = true;
			global = true;
	    	list.add("b");
		}
		
		if (font.getUnderline() == 1) {
			outString.append("<u>");
			underline = true;
			global = true;
	    	list.add("u");
		}
		
		System.out.println("Czy font pogrubiony dla całej komórki getBoldweight: " + font.getBoldweight());
		System.out.println("Czy font podkreślony dla całej komórki getUnderline: " + font.getUnderline());
		System.out.println("Czy font pochylony dla całej komórki getItalic: " + font.getItalic());
		System.out.println("CharSet: " + font.getCharSet());
		
		int size;
       		
			for (int i = 0; i < cell.getStringCellValue().length(); i++) {		    
			    font = (HSSFFont) workbook.getFontAt(rts.getFontAtIndex(i));
			    System.out.print("Analizuję znak: " + text.charAt(i) + " ");
			    //if (font.getCharSet() < 238){ // jeśli jest różne od 238 - bierze formatowanie z każdego znaku
			    System.out.print("Bold - getBoldweight " + font.getBoldweight());
			    System.out.print(", getCharSet " + font.getCharSet());
			    System.out.print(", Underline - getUnderline " + font.getUnderline());
			    System.out.println(", Italic - getItalic " + font.getItalic());
			    
			    
			    if (i == 0) previousCharSet = font.getCharSet();
			    if ((global) && (i>0)){
			    	if (previousBold != font.getBoldweight()) global = false;
			    	if (previousUnderline != font.getUnderline()) global = false;
			    	if (previousCharSet != font.getCharSet()) global = false;
			    }
			    
				    if ((bold == false) && (font.getBoldweight() == 700)) {
				    	if (global) {
				    		global = false;
				    	}
				    	outString.append("<b>");
				    	bold = true;
				    	list.add("b");

				    }
				    if ((bold) && (font.getBoldweight() == 400) && (!global))
				    	bold = false;
				    
				    if ((underline == false) && (font.getUnderline() == 1)) {
				    	if (global) {
				    		global = false;
				    	}
				    	outString.append("<u>");
				    	underline = true;
				    	list.add("u");
				    }
				    if ((underline) && (font.getUnderline() == 0) && (!global)) 
				    	underline = false;				    
		
				    if ((italic == false) && (font.getItalic() == true)) {
				    	outString.append("<i>");
				    	italic = true;
				    	list.add("i");
				    }
				    if ((italic) && (font.getItalic() == false)) 
				    	italic = false;
				    
				    // wyłączenie nastaw, jeśli była zmiana fontu
				    size = list.size();
				    for (int j = size - 1; j >= 0; j--){
						String znak = (String) list.get(j);
						if ((znak.equals("b")) && (!bold)) {
							//if (!generalCellBold) {
								list.remove("b");
								outString.append("</b>");
							//}
						}
						if ((znak.equals("u")) && (!underline)) {
							list.remove("u");
							outString.append("</u>");
						}
						if ((znak.equals("i")) && (!italic)) {
							list.remove("i");
							outString.append("</i>");
						}						
					}
				    previousBold = font.getBoldweight();
				    previousUnderline = font.getUnderline();
				    
			    outString.append(text.charAt(i)); 
			}	
						
		size = list.size();
		for (int i = size - 1; i >= 0; i--){
			String znak = (String) list.get(i);
			if (znak.equals("b")) outString.append("</b>");
			if (znak.equals("u")) outString.append("</u>");
			if (znak.equals("i")) outString.append("</i>");
		}
		
		// dodanie spacji na końcu tekstu
		if (outString.charAt(outString.length()-1) != ' ') outString.append(" ");
		
		return outString.toString();
	}
	
	
	public static void saveFile(String fileName, String text){
		System.out.println(fileName);
		try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {
		    out.print(text);
		}
		catch (IOException ex){
			ex.printStackTrace();
		};
	}

	
	/*
	 * public static void saveFile(String outputFileName, String outputText){
	 * writeTextToFile(outputFileName, outputText); }
	 */

}
