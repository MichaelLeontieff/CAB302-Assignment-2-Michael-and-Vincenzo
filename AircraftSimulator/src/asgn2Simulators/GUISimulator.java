/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {
	/*
	 * Constants that define window dimensions
	 */
	public static final int WINDOW_WIDTH = 300;
	public static final int WINDOW_HEIGHT = 200;
	
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		createGUI();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	}

}
