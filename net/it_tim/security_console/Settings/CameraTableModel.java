package net.it_tim.security_console.Settings;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import net.it_tim.security_console.Camera;
import net.it_tim.security_console.HibernateUtil;

import org.hibernate.Session;

public class CameraTableModel implements TableModel {
    
	private ArrayList<Camera> results;             // The ResultSet to interpret
    int numcols;
	private Class[] columnTypes = new Class[] {
			Long.class, String.class, String.class
	};
	private String colNames[] = { "Id", "Description", "URL" };
    
    CameraTableModel(ArrayList<Camera> results) throws SQLException {
    	this.results = results;                 // Save the results
    	numcols = 3;    // How many columns?
    }
    
    public void close() {
    	results.clear();
    }
    
    public void add(String description, String URL) {
    	Camera e = new Camera();
    	e.setDescription(description);
    	e.setCameraURL(URL);
    	System.out.println(e.toString());
    	results.add(e);
    }
    
    public void save() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        for (Camera p : results) {
        	session.saveOrUpdate(p);
        }
        session.getTransaction().commit();
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
		return colNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return results.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;
		switch (columnIndex) {
		case 0:
			result = results.get(rowIndex).getId();
			break;
		case 1:
			result = results.get(rowIndex).getDescription().toString();
			break;
		case 2:
			result = results.get(rowIndex).getCameraURL().toString();
			break;
		}
		return result;
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
