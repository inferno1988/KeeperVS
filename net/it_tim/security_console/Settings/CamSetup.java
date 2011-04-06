package net.it_tim.security_console.Settings;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.it_tim.security_console.Camera;
import net.it_tim.security_console.HibernateUtil;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;

public class CamSetup extends JFrame {
	private static final long serialVersionUID = 1872239971833519211L;
	private JPanel contentPane;
	private JTable table;
    private CameraTableModel camTable = null;
	/**
	 * Create the frame.
	 */
	public CamSetup() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(0, 0, 590, 410);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 590, 32);
		panel.add(toolBar);
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				camTable.add("test", "testURL");
				table.removeAll();
				table.setModel(camTable);
				table.updateUI();
			}
		});
		btnAdd.setIcon(new ImageIcon(CamSetup.class.getResource("/net/it_tim/security_console/Signage/Add_Square.png")));
		toolBar.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(camTable);
			}
		});
		btnDelete.setIcon(new ImageIcon(CamSetup.class.getResource("/net/it_tim/security_console/Signage/Remove_Square.png")));
		toolBar.add(btnDelete);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(CamSetup.class.getResource("/net/it_tim/security_console/Signage/Shortkey.png")));
		toolBar.add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				camTable.save();
			}
		});
		btnSave.setIcon(new ImageIcon(CamSetup.class.getResource("/net/it_tim/security_console/Signage/Done_Square.png")));
		toolBar.add(btnSave);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		btnClose.setIcon(new ImageIcon(CamSetup.class.getResource("/net/it_tim/security_console/Signage/Close_Square.png")));
		toolBar.add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 35, 590, 366);
		panel.add(scrollPane);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Camera> results = session.createCriteria(Camera.class)
        .setMaxResults(50)
        .addOrder(Order.asc("id"))
        .list();
        session.getTransaction().commit();
		try {
			camTable = new CameraTableModel((ArrayList<Camera>) results);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table = new JTable();
		table.setModel(camTable);
		table.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setViewportView(table);
	}
	
	public void close() {
		this.dispose();
	}
}
