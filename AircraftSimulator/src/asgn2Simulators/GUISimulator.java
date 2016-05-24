/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
	 * Heaading Fonts
	 */
	private Font headingFont = new Font("Arial", Font.BOLD, 24);
	
	/*
	 * JFrame Panel Declarations which will house elements of GUI
	 */
	private JPanel pnlSimulation;
	private JPanel pnlFareClasses;
	private JPanel pnlExecution;
	private JPanel pnlLoggingOutput;
	private JPanel pnlUserInput;
	
	private JPanel pnlSpacer;
	/*
	 * Elements for logging
	 */
	private JTextArea txtLoggingOutput;
	
	/*
	 * Panel Elements for Simulation Panel
	 */
	private JLabel lblSimulation;
	
	private JLabel lblRNGSeed;
	private JLabel lblDailyMean;
	private JLabel lblQueueSize;
	private JLabel lblCancellation;
	
	private JTextField txtRNGInput;
	private JTextField txtDailyMean;
	private JTextField txtQueueSize;
	private JTextField txtCancellation;
	
	/*
	 * Border definintion for panels
	 */
	private Border borderLowered;
	
	/*
	 * Panel Elements for FareClasses Panel
	 */
	private JLabel lblFareClasses;
	
	private JLabel lblFirst;
	private JLabel lblBusiness;
	private JLabel lblPremium;
	private JLabel lblEconomy;
	
	private JTextField txtFirst;
	private JTextField txtBusiness;
	private JTextField txtPremium;
	private JTextField txtEconomy;
	
	/*
	 * Panel Elements for Execution Panel
	 */
	private JLabel lblExecution;
	
	private JButton btnRunSimulation;
	private JButton btnShowGraphTwo;
	
	/*
	 * Constraints that govern positioning of element types
	 */
	private GridBagConstraints lblHeadingConstraints;
	private GridBagConstraints lblSubHeadingConstraints;
	private GridBagConstraints txtConstraints;
	
	
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
		setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
		
	    lblHeadingConstraints = new GridBagConstraints(); 
	    lblSubHeadingConstraints = new GridBagConstraints(); 
	    txtConstraints = new GridBagConstraints();
	    
	    // set constraints
	    setConstraintsLblHeading();
	    setConstraintsLblSubHeading();
	    setConstraintsTxtField();
	    
	    // instantiate panel characteristics 
	    borderLowered = BorderFactory.createLoweredBevelBorder();
		
		// instantiate panels
		pnlSimulation = createPanel(Color.LIGHT_GRAY);
		pnlSimulation.setBorder(borderLowered);
		
		pnlFareClasses = createPanel(Color.LIGHT_GRAY);
		pnlFareClasses.setBorder(borderLowered);
		
		pnlExecution = createPanel(Color.LIGHT_GRAY);
		pnlExecution.setBorder(borderLowered);
		
		pnlLoggingOutput = createPanel(Color.WHITE);
		pnlLoggingOutput.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		
		pnlUserInput = createPanel(Color.RED);
		pnlUserInput.setOpaque(false);
		
		// instantiate elements
		layoutLoggingPanel();
		userInputFieldsPanel();
		
		// add panels
		this.getContentPane().add(pnlLoggingOutput,BorderLayout.NORTH);
		this.getContentPane().add(pnlUserInput, BorderLayout.SOUTH);
		
		
		
		repaint();
		setVisible(true);
	}
	
	private void userInputFieldsPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlUserInput.setLayout(layout);
		
		layoutSimulationPanel();
		layoutFareClassesPanel();
		layoutExecutionPanel();
		
		pnlUserInput.add(pnlSimulation, gridBagConstraints(0, 1, 2, 1, 50));
		pnlUserInput.add(pnlFareClasses, gridBagConstraints(5, 1, 2, 1, 30));
		pnlUserInput.add(pnlExecution, gridBagConstraints(10, 1, 2, 1, 50));
		
	}
	
	private GridBagConstraints gridBagConstraints(int x, int y, int w, int h, int paddingx) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		constraints.ipadx = paddingx;
		constraints.ipady = 10;
		constraints.weightx = 0.1;
		constraints.weighty = 100;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.NORTH;
		return constraints;
	}
	
	private void setConstraintsLblHeading() {
	    lblHeadingConstraints.anchor = GridBagConstraints.CENTER;
	    lblHeadingConstraints.weightx = 100;
	    lblHeadingConstraints.weighty = 100;
	    lblHeadingConstraints.gridwidth = 2;
	}
	
	private void setConstraintsLblSubHeading() {
	    lblSubHeadingConstraints.anchor = GridBagConstraints.WEST;
	    lblSubHeadingConstraints.weightx = 100;
	    lblSubHeadingConstraints.weighty = 100;
	    lblSubHeadingConstraints.gridwidth = 2;
	    lblSubHeadingConstraints.insets = new Insets(0, 10, 0, 10);
	}
	
	private void setConstraintsTxtField() {
	    txtConstraints.anchor = GridBagConstraints.EAST;
	    txtConstraints.weightx = 100;
	    txtConstraints.weighty = 100;
	    txtConstraints.gridwidth = 2;
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
	
	private void layoutLoggingPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlLoggingOutput.setLayout(layout);
		
		txtLoggingOutput = new JTextArea(25, 60);
		
		pnlLoggingOutput.add(txtLoggingOutput);
	}
	
	private void layoutSimulationPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlSimulation.setLayout(layout);
	    
	    // Title Label
	    lblSimulation = new JLabel("Simulation");
	    lblSimulation.setFont(headingFont);
	   
	    // Sub Headings
	    lblRNGSeed = new JLabel("RNG Seed");
	    lblDailyMean = new JLabel("Daily Mean");
	    lblQueueSize = new JLabel("Queue Size");
	    lblCancellation = new JLabel("Cancellation");
	    
	    // text field widgets
	    txtRNGInput = new JTextField(Integer.toString(Constants.DEFAULT_SEED), 6);
	    txtDailyMean = new JTextField(Double.toString(Constants.DEFAULT_DAILY_BOOKING_MEAN), 6);
	    txtQueueSize = new JTextField(Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE), 6);
	    txtCancellation = new JTextField(Integer.toString(Constants.CANCELLATION_PERIOD), 6);
	    
	    // title
	    addToPanel(pnlSimulation, lblSimulation, lblHeadingConstraints, 0, 0, 4, 1);
	   
	    // sub headings
	    addToPanel(pnlSimulation, lblRNGSeed, lblSubHeadingConstraints, 0, 1, 2, 1);
	    addToPanel(pnlSimulation, lblDailyMean, lblSubHeadingConstraints, 0, 2, 2, 1);
	    addToPanel(pnlSimulation, lblQueueSize, lblSubHeadingConstraints, 0, 3, 2, 1);
	    addToPanel(pnlSimulation, lblCancellation, lblSubHeadingConstraints, 0, 4, 2, 1);
	    
	    // input fields  
	    addToPanel(pnlSimulation, txtRNGInput, txtConstraints, 0, 1, 1, 1);
	    addToPanel(pnlSimulation, txtDailyMean, txtConstraints, 0, 2, 1, 1);
	    addToPanel(pnlSimulation, txtQueueSize, txtConstraints, 0, 3, 1, 1);
	    addToPanel(pnlSimulation, txtCancellation, txtConstraints, 0, 4, 1, 1);
	}
	
	private void layoutFareClassesPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlFareClasses.setLayout(layout);
	    
	    // title label
	    lblFareClasses = new JLabel("Fare Classes");
	    lblFareClasses.setFont(headingFont);
	    
	    // Sub Headings
	    lblFirst = new JLabel("First");
	    lblBusiness = new JLabel("Business");
	    lblPremium = new JLabel("Premium");
	    lblEconomy = new JLabel("Economy");
	    
	    // text field widgets
	    txtFirst = new JTextField(Double.toString(Constants.DEFAULT_FIRST_PROB), 6);
	    txtBusiness = new JTextField(Double.toString(Constants.DEFAULT_BUSINESS_PROB), 6);
	    txtPremium = new JTextField(Double.toString(Constants.DEFAULT_PREMIUM_PROB), 6);
	    txtEconomy = new JTextField(Double.toString(Constants.DEFAULT_ECONOMY_PROB), 6);
	    
	    addToPanel(pnlFareClasses, lblFareClasses, lblHeadingConstraints, 0, 0, 4, 1); 
	    
	    // sub headings
	    addToPanel(pnlFareClasses, lblFirst, lblSubHeadingConstraints, 0, 1, 2, 1);
	    addToPanel(pnlFareClasses, lblBusiness, lblSubHeadingConstraints, 0, 2, 2, 1);
	    addToPanel(pnlFareClasses, lblPremium, lblSubHeadingConstraints, 0, 3, 2, 1);
	    addToPanel(pnlFareClasses, lblEconomy, lblSubHeadingConstraints, 0, 4, 2, 1);
	    
	    // input fields  
	    addToPanel(pnlFareClasses, txtFirst, txtConstraints, 0, 1, 1, 1);
	    addToPanel(pnlFareClasses, txtBusiness, txtConstraints, 0, 2, 1, 1);
	    addToPanel(pnlFareClasses, txtPremium, txtConstraints, 0, 3, 1, 1);
	    addToPanel(pnlFareClasses, txtEconomy, txtConstraints, 0, 4, 1, 1);
	    
	    
	}
	
	private void layoutExecutionPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlExecution.setLayout(layout);
	    
	    lblExecution = new JLabel("Execution");
	    lblExecution.setFont(headingFont);
	    
	    btnRunSimulation = new JButton("Run Simulation");
	    btnShowGraphTwo = new JButton("Show Graph Two");
	    btnRunSimulation.setPreferredSize(new Dimension(200, 40));
	    btnShowGraphTwo.setPreferredSize(new Dimension(200, 40));
	    
	    addToPanel(pnlExecution, lblExecution, lblHeadingConstraints, 0, 0, 1, 5); 
	    addToPanel(pnlExecution, btnRunSimulation, lblHeadingConstraints, 0, 5, 1, 5); 
	    addToPanel(pnlExecution, btnShowGraphTwo, lblHeadingConstraints, 0, 10, 1, 5); 
	}

}
