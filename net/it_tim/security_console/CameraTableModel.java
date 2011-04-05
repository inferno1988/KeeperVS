package net.it_tim.security_console;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CameraTableModel implements TableModel {
    
	ResultSet results;             // The ResultSet to interpret
    ResultSetMetaData metadata;    // Additional information about the results
    int numcols, numrows;          // How many rows and columns in the table
    
	Class[] columnTypes = new Class[] {
			Long.class, String.class, String.class
	};
    
    CameraTableModel(ResultSet results) throws SQLException {
    	this.results = results;                 // Save the results
    	metadata = results.getMetaData();       // Get metadata on them
    	numcols = metadata.getColumnCount();    // How many columns?
    	results.last();                         // Move to last row
    	numrows = results.getRow();             // How many rows?
    }
    
    public void close() {
    	try { results.getStatement().close(); }
    	catch(SQLException e) {};
    }
    
    protected void finalize() { close(); }
    
	@Override
	public void addTableModelListener(TableModelListener l) {
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return numcols;
	}

	@Override
	public String getColumnName(int columnIndex) {
		try {
		    return metadata.getColumnLabel(columnIndex+1);
		} catch (SQLException e) { return e.toString(); }
	}

	@Override
	public int getRowCount() {
		return numrows;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
		    results.absolute(rowIndex+1);                // Go to the specified row
		    Object o = results.getObject(columnIndex+1); // Get value of the column
		    if (o == null) return null;       
		    else return o.toString();               // Convert it to a string
		} catch (SQLException e) { return e.toString(); }
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}
}
