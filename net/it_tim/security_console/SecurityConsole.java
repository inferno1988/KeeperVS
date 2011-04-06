package net.it_tim.security_console;

import java.awt.EventQueue;
import javax.swing.JFrame;

import net.it_tim.security_console.Settings.CamSetup;
import net.it_tim.security_console.VideoExeptions.CantPlayException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SecurityConsole {
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecurityConsole window = new SecurityConsole();
					window.frame.setVisible(true);
					try {
						container.playAll();
					} catch (CantPlayException ex) {
						System.out.println(ex.getMessage());
					}
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SecurityConsole() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		container.createCanvas("rtsp://root:root0881@192.168.52.200/axis-media/media.amp", "Boguna 5a");
		container.createCanvas("rtsp://root:root0881@192.168.52.201/axis-media/media.amp", "Boguna 5a");
		container.createCanvas("rtsp://root:root0881@192.168.52.203/axis-media/media.amp", "Boguna 5a");
		frame = new JFrame("Відео спостереження");
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setSize(1347, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(100, 100));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(220, 220));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 204, 153));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		scrollPane.setColumnHeaderView(panel_2);
		
		JLabel lblKeeper = new JLabel("Keeper");
		panel_2.add(lblKeeper);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		scrollPane.setViewportView(panel_3);
		
		panel_3.add(container.getCanvas(0));
		panel_3.add(container.getCanvas(1));
		panel_3.add(container.getCanvas(2));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1335, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);


		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);
		
		JMenuItem mntmCamerasSetup = new JMenuItem("Cameras Setup");
		mntmCamerasSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CamSetup camWnd = new CamSetup();
				camWnd.setVisible(true);
			}
		});
		mnSystem.add(mntmCamerasSetup);

	}
	
	private static VideoContainer container = new VideoContainer();
}
