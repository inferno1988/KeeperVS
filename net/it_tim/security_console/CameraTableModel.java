package net.it_tim.security_console;

import javax.swing.table.DefaultTableModel;

public class CameraTableModel extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7922498518861662387L;
	
	static Object rowData[][] = { 
		{new Long(21L), "sfgsd", "sfsadfasd"}, 
		{new Long(21L), "sfgsd", "sfsadfasd"},
		};
	static String[] cols =  { "Id", "Description", "URL" };
	
	Class[] columnTypes = new Class[] { Long.class, String.class, String.class };
	
	public CameraTableModel() {
		super(rowData, cols);
	}

	public Class getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return cols.length;
	}

	@Override
	public int getRowCount() {
		return rowData.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return null;
	}
}
