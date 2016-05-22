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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {
	/*
	 * Constants that define window dimensions
	 */
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	/*
	 * JFrame Panel Declarations which will house elements of GUI
	 */
	private JPanel pnlSimulation;
	private JPanel pnlFareClasses;
	private JPanel pnlExecution;
	
	/*
	 * Panel Elements for Simulation Panel
	 */
	private JLabel lblSimulation;
	private JLabel lblRNGSeed;
	private JLabel lblDailyMean;
	private JLabel lblQueueSize;
	private JLabel lblCancellation;
	
	private JTextField txtRNGInput;
	
	
	/*
	 * Panel Elements for Simulation Panel
	 */
	private JLabel lblFareClasses;
	
	/*
	 * Panel Elements for Simulation Panel
	 */
	private JLabel lblExecution;
	
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
		createGUI();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new GUISimulator("GUI Simulator"));

	}
	
	private void createGUI() {
		// set general GUI parameters
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new GridBagLayout());
		
		
		// instantiate panels
		pnlSimulation = createPanel(Color.BLUE);
		pnlFareClasses = createPanel(Color.GREEN);
		pnlExecution = createPanel(Color.RED);
		
		// instantiate elements
		layoutSimulationPanel();
		layoutFareClassesPanel();
		layoutExecutionPanel();
		

		
		// add panels
		this.getContentPane().add(pnlSimulation, gridBagConstraints(0, 0, 2, 1, 50));
		this.getContentPane().add(pnlFareClasses, gridBagConstraints(3, 0, 2, 1, 50));
		this.getContentPane().add(pnlExecution, gridBagConstraints(6, 0, 2, 1, 50));
		
		
		repaint();
		setVisible(true);
	}
	
	private GridBagConstraints gridBagConstraints(int x, int y, int w, int h, int paddingx) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		constraints.ipadx = paddingx;
		return constraints;
	}
	
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	   }
	
	private void layoutSimulationPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlSimulation.setLayout(layout);
		
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    // Title Label
	    lblSimulation = new JLabel("Simulation");
	   
	    // Sub Headings
	    lblRNGSeed = new JLabel("RNG Seed");
	    lblDailyMean = new JLabel("Daily Mean");
	    lblQueueSize = new JLabel("Queue Size");
	    lblCancellation = new JLabel("Cancellation");
	    
	    // widgets
	    txtRNGInput = new JTextField();
	    
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    constraints.gridwidth = 2;
	    
	    addToPanel(pnlSimulation, lblSimulation, constraints, 0, 0, 2, 1);
	   
	    addToPanel(pnlSimulation, lblRNGSeed, constraints, 0, 1, 2, 1);
	    addToPanel(pnlSimulation, lblDailyMean, constraints, 0, 2, 2, 1);
	    addToPanel(pnlSimulation, lblQueueSize, constraints, 0, 3, 2, 1);
	    addToPanel(pnlSimulation, lblCancellation, constraints, 0, 4, 2, 1);
	    
	    
	    //addToPanel(pnlSimulation, txtRNGInput, constraints, 1, 2, 10, 1);
	}
	
	private void layoutFareClassesPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlFareClasses.setLayout(layout);
		
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    // title label
	    lblFareClasses = new JLabel("Fare Classes");
	    
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    constraints.gridwidth = 2;
	    
	    addToPanel(pnlFareClasses, lblFareClasses, constraints, 2, 2, 1, 5);   
	}
	
	private void layoutExecutionPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlExecution.setLayout(layout);
		
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    lblExecution = new JLabel("Execution");
	    
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    constraints.gridwidth = 2;
	    
	    addToPanel(pnlExecution, lblExecution, constraints, 3, 2, 1, 5);   
	}

}
