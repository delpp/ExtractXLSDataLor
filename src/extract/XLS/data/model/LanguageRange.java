package extract.XLS.data.model;

public class LanguageRange {
	String language;
	public int rowStart;
	public int rowEnd;
	public int column;
	
	public LanguageRange() {
		language = "";
		rowStart = -1;
		rowEnd = 1;
		column = -1;
	}
	
	public LanguageRange(String language, int rowStart, int rowEnd, int column) {
		this.language = language;
		this.rowStart = rowStart;
		this.rowEnd = rowEnd;
		this.column = column;
	}
	
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	
	public int getRowStart() {
		return rowStart;
	}
	
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLanguage() {
		return language;
	}

	@Override
	public String toString() {
		return "LanguageRange [language=" + language + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd + ", column="
				+ column + "]";
	}
}
