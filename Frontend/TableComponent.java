package Frontend;

public class TableComponent {

	private int rows = 0;
	private int columns;
	private Object[] columnHeaders;

	public int getRows() {
		return this.rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Object[] getColumnHeaders() {
		return this.columnHeaders;
	}

	public void setColumnHeaders(Object[] columnHeaders) {
		this.columnHeaders = columnHeaders;
	}

	/**
	 * 
	 * @param rowObject
	 */
	public void addRow(Object[] rowObject) {
		// TODO - implement TableComponent.addRow
		throw new UnsupportedOperationException();
	}

}