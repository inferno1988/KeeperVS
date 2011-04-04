package net.it_tim.security_console;

import java.awt.EventQueue;
import javax.swing.JFrame;

import org.hibernate.classic.Session;
import java.util.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
					} catch (VideoExceptions.CantPlayException ex) {
						System.out.println(ex.getMessage());
					}
					
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			        session.beginTransaction();

			        Camera cam = new Camera();
			        cam.setDescription("Boguna 5a");
			        cam.setCameraURL("rtsp://root:root0881@192.168.52.200:554/axis-media/media.amp");
			        session.saveOrUpdate(cam);
			        session.getTransaction().commit();
			        
			        session = HibernateUtil.getSessionFactory().getCurrentSession();
			        session.beginTransaction();
			        List cameras = session.createCriteria(Camera.class)
			        .setMaxResults(50)
			        .list();
			        session.getTransaction().commit();

			        for (Object cams: cameras){
			        	System.out.println( ((Camera)cams).getCameraURL() );
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
		frame = new JFrame("Відео спостереження");
		frame.setSize(1280, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//container.createCanvas("rtsp://root:root0881@192.168.52.200:554/axis-media/media.amp", "Boguna 5a");
		//container.createCanvas("rtsp://root:root0881@192.168.52.201:554/axis-media/media.amp", "Boguna 5a");
		//container.createCanvas("rtsp://root:root0881@192.168.52.203:554/axis-media/media.amp", "Boguna 5a");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		GridBagConstraints gbc_canvas = new GridBagConstraints();
		gbc_canvas.insets = new Insets(0, 0, 1, 1);
		gbc_canvas.gridx = 1;
		gbc_canvas.gridy = 0;
		//frame.getContentPane().add(container.getCanvas(0), gbc_canvas);

		GridBagConstraints gbc_canvas_1 = new GridBagConstraints();
		gbc_canvas_1.insets = new Insets(0, 0, 1, 1);
		gbc_canvas_1.gridx = 2;
		gbc_canvas_1.gridy = 0;
		//frame.getContentPane().add(container.getCanvas(1), gbc_canvas_1);

		GridBagConstraints gbc_canvas_2 = new GridBagConstraints();
		gbc_canvas_2.insets = new Insets(0, 0, 1, 1);
		gbc_canvas_2.gridx = 1;
		gbc_canvas_2.gridy = 1;
		//frame.getContentPane().add(container.getCanvas(2), gbc_canvas_2);
	}
	
	private static VideoContainer container = new VideoContainer();
}
