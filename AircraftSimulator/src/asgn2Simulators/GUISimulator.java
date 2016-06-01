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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @author Michael Leontieff
 * @version 1.1
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable, ActionListener {
	
	/*
	 * Constants that define window dimensions
	 */
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 650;
	
	/*
	 * Constants to be parsed via constructor to ChartPanel class
	 */
	private static final int BOOKINGS_CHART = 1;
	private static final int QUEUE_REFUSE_CHART = 2;
	
	/*
	 * Misc Constants
	 */
	private static final int NUM_ARGS = 9;
	private static final int FONT_SIZE = 24;
	
	private static final int TXT_LOGGING_OUTPUT_TAB = 0;
	
	private static final int RNG_INPUT_ARG = 0;
	private static final int DAILY_MEAN_ARG = 2;
	private static final int QUEUE_SIZE_ARG = 1;
	private static final int FIRST_PROB_INPUT_ARG = 4;
	private static final int BUSINESS_PROB_INPUT_ARG = 5;
	private static final int PREMIUM_PROB_INPUT_ARG = 6;
	private static final int ECONOMY_PROB_INPUT_ARG = 7;
	private static final int CANCELLATION_INPUT_ARG = 8;
	

	/* tabbed pane
	 * 
	 */
	private JTabbedPane tabbedPane;
	
	/*
	 * Heading Fonts
	 */
	private Font headingFont;
	
	/*
	 * JFrame Panel Declarations which will house elements of GUI
	 */
	private JPanel pnlSimulation;
	private JPanel pnlFareClasses;
	private JPanel pnlExecution;
	private JPanel pnlLoggingOutput;
	private JPanel pnlUserInput;
	private JPanel pnlChartOne;
	private JPanel pnlChartTwo;
	
	/*
	 * Elements for logging
	 */
	private JTextArea txtLoggingOutput;
	private JScrollPane scroll;
	
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
	 * Border definition for panels
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
	private JButton btnShowBookingGraph;
	private JButton btnShowQueueRefusedGraph;
	
	/*
	 * Constraints that govern positioning of element types
	 */
	private GridBagConstraints lblHeadingConstraints;
	private GridBagConstraints lblSubHeadingConstraints;
	private GridBagConstraints txtConstraints;
	
	/*
	 * Simulator and time series Object
	 */
	private Simulator sim;
	private String[] arguments;
	
	/*
	 * Validation tracker
	 */
	private boolean isValidInput = true;
	
	/**
	 * @param arg0
	 * @param args String array parsed from Simulation Runner that contains simulation arguments
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0, String[] args) throws HeadlessException {
		super(arg0);
		arguments = args;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI();
	}


	/**
	 * Called from Run() - Initialises panels and their elements through private method calls
	 * options. 
	 * 
	 */	
	private void createGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// set general GUI parameters
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    tabbedPane = new JTabbedPane();
	    headingFont = new Font("Arial", Font.BOLD, FONT_SIZE);
		
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
		
		pnlChartOne = createPanel(Color.WHITE);
		pnlChartTwo = createPanel(Color.WHITE);
		
		pnlUserInput = createPanel(Color.RED);
		pnlUserInput.setOpaque(false);
		
		// instantiate elements
		layoutLoggingPanel();
		userInputFieldsPanel();
		layoutFreechartOnePanel();
		layoutFreechartTwoPanel();
		
		// add panels
		this.getContentPane().add(tabbedPane,BorderLayout.NORTH);
		this.getContentPane().add(pnlUserInput, BorderLayout.SOUTH);
		
	    // disable chart tabs
	    tabbedPane.setEnabledAt(BOOKINGS_CHART, false);
	    tabbedPane.setEnabledAt(QUEUE_REFUSE_CHART, false);
		
	    // populate text fields with arguments if applicable
	    if (arguments.length == NUM_ARGS) {
	    	populateFieldsStringArguments(arguments);
	    } else {
	    	populateFieldsConstants();
	    }
	    
		repaint();
		setVisible(true);
	}
	
	/**
	 * Private helper method to construct user input fields panel (which is comprised of three other panels)
	 * and place them within a grid bag layout
	 */
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
	
	/**
	 * Private helper method to return constraints object based on given parameter fields
	 * @param x grid x
	 * @param y grid y
	 * @param w grid width
	 * @param h grid height
	 * @param paddingx padding x axis
	 * @return GridBagConstraints obejct comprised of parameters
	 */
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
	
	/**
	 * Private Method to set the constraints of the top label headings
	 */
	private void setConstraintsLblHeading() {
	    lblHeadingConstraints.anchor = GridBagConstraints.CENTER;
	    lblHeadingConstraints.weightx = 100;
	    lblHeadingConstraints.weighty = 100;
	    lblHeadingConstraints.gridwidth = 2;
	}
	
	/**
	 * Private Method to set the constraints of the sub headings
	 */
	private void setConstraintsLblSubHeading() {
	    lblSubHeadingConstraints.anchor = GridBagConstraints.WEST;
	    lblSubHeadingConstraints.weightx = 100;
	    lblSubHeadingConstraints.weighty = 100;
	    lblSubHeadingConstraints.gridwidth = 2;
	    lblSubHeadingConstraints.insets = new Insets(0, 10, 0, 10);
	}
	
	/**
	 * Private Method to set the constraints of the text fields
	 */
	private void setConstraintsTxtField() {
	    txtConstraints.anchor = GridBagConstraints.EAST;
	    txtConstraints.weightx = 100;
	    txtConstraints.weighty = 100;
	    txtConstraints.gridwidth = 2;
	}
	
	/**
	 * Private helper to create panel with parameter colour
	 * @param c colour of background
	 * @return JPanel with defined colour
	 */
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	/**
	 * Private helper method to add parameter Component to parameter JPanel with parameter constraints
	 * @param jp JPanel
	 * @param c Component to be added
	 * @param constraints Constraints that apply to Component
	 * @param x grid x
	 * @param y grid y
	 * @param w grid width
	 * @param h grid height
	 */
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	   }
	
	/**
	 * Private helper method which constructs the logging panel using a gridbag layout
	 * and handles the instantiation of the contained elements
	 */
	private void layoutLoggingPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlLoggingOutput.setLayout(layout);
		
		txtLoggingOutput = new JTextArea(25, 60);
		txtLoggingOutput.setEditable(false);
		
		scroll = new JScrollPane(txtLoggingOutput);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnlLoggingOutput.add(scroll);
		
		tabbedPane.addTab("Text Logging Output", pnlLoggingOutput);
	}
	
	/**
	 * Private helper method to handle panel creation for the Bookings chart
	 */
	private void layoutFreechartOnePanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlChartOne.setLayout(layout);
		
		tabbedPane.addTab("Chart One", pnlChartOne);
	}
	
	/**
	 * Private helper method to handle panel creation for the Queued/Refused chart
	 */
	private void layoutFreechartTwoPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlChartTwo.setLayout(layout);
		
		tabbedPane.addTab("Chart Two", pnlChartTwo);
	}
	
	/**
	 * Private helper method to handle the creation of panels and elements for the simulation panel
	 */
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
	    txtRNGInput = new JTextField("", 6);
	    txtDailyMean = new JTextField("", 6);
	    txtQueueSize = new JTextField("", 6);
	    txtCancellation = new JTextField("", 6);
	    
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
	
	/**
	 * Private helper method to handle the creation of panels and elements for the Fare Classes panel
	 */
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
	    txtFirst = new JTextField("", 6);
	    txtBusiness = new JTextField("", 6);
	    txtPremium = new JTextField("", 6);
	    txtEconomy = new JTextField("", 6);
	    
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
	
	/**
	 * Private helper method to handle the creation of panels and elements for the Execution panel
	 */
	private void layoutExecutionPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlExecution.setLayout(layout);
	    
	    lblExecution = new JLabel("Execution");
	    lblExecution.setFont(headingFont);
	    
	    btnRunSimulation = new JButton("Run Simulation");
	    btnRunSimulation.setPreferredSize(new Dimension(250, 35));
	    btnRunSimulation.addActionListener(this);
	    
	    btnShowBookingGraph = new JButton("Show/Update Bookings Graph");
	    btnShowBookingGraph.setPreferredSize(new Dimension(250, 35));
	    btnShowBookingGraph.addActionListener(this);    
	    
	    btnShowQueueRefusedGraph = new JButton("Show/Update Queue-Refused Graph");
	    btnShowQueueRefusedGraph.setPreferredSize(new Dimension(250, 35));
	    btnShowQueueRefusedGraph.addActionListener(this);    
	    
	    btnShowBookingGraph.setEnabled(false);
	    btnShowQueueRefusedGraph.setEnabled(false);
	    
	    addToPanel(pnlExecution, lblExecution, lblHeadingConstraints, 0, 0, 1, 5); 
	    addToPanel(pnlExecution, btnRunSimulation, lblHeadingConstraints, 0, 5, 1, 5); 
	    addToPanel(pnlExecution, btnShowBookingGraph, lblHeadingConstraints, 0, 10, 1, 5);
	    addToPanel(pnlExecution, btnShowQueueRefusedGraph, lblHeadingConstraints, 0, 15, 1, 5);
	}

	/**
	 * Overridden class from ActionListener to handle events
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource(); 	      
			if (src == btnRunSimulation) {
				tabbedPane.setSelectedIndex(TXT_LOGGING_OUTPUT_TAB);
				try {			
					createSimulation();
				} catch (AircraftException e1) {
					e1.printStackTrace();
				} catch (SimulationException e1) {
					e1.printStackTrace();
				} catch (PassengerException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			// enable graph buttons
			btnShowBookingGraph.setEnabled(true);
			btnShowQueueRefusedGraph.setEnabled(true);
			
			if (src == btnShowBookingGraph) {
				tabbedPane.setSelectedIndex(BOOKINGS_CHART);
			}	
			if (src == btnShowQueueRefusedGraph) {
				tabbedPane.setSelectedIndex(QUEUE_REFUSE_CHART);
			}		
	}
	
	/**
	 * Private method to populate fields with constants
	 */
	private void populateFieldsConstants() {
		txtRNGInput.setText(Integer.toString(Constants.DEFAULT_SEED));
		txtDailyMean.setText(Double.toString(Constants.DEFAULT_DAILY_BOOKING_MEAN));
		txtQueueSize.setText(Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE));
		txtCancellation.setText(Double.toString(Constants.DEFAULT_CANCELLATION_PROB));
		
		txtFirst.setText(Double.toString(Constants.DEFAULT_FIRST_PROB));
		txtBusiness.setText(Double.toString(Constants.DEFAULT_BUSINESS_PROB));
		txtPremium.setText(Double.toString(Constants.DEFAULT_PREMIUM_PROB));
		txtEconomy.setText(Double.toString(Constants.DEFAULT_ECONOMY_PROB));
	}
	
	/**
	 * @param String array containing arguments
	 * Private method to populate fields with string arguments
	 */
	private void populateFieldsStringArguments(String[] args) {
		// String arguments length validated before reaching here
		txtRNGInput.setText(args[RNG_INPUT_ARG]);
		txtDailyMean.setText(args[DAILY_MEAN_ARG]);
		txtQueueSize.setText(args[QUEUE_SIZE_ARG]);
		txtCancellation.setText(args[CANCELLATION_INPUT_ARG]);
		
		txtFirst.setText(args[FIRST_PROB_INPUT_ARG]);
		txtBusiness.setText(args[BUSINESS_PROB_INPUT_ARG]);
		txtPremium.setText(args[PREMIUM_PROB_INPUT_ARG]);
		txtEconomy.setText(args[ECONOMY_PROB_INPUT_ARG]);
	}
	
	/**
	 * Private method to create simulator object with parameters
	 * @throws SimulationException
	 * @throws Aircraft Exception
	 * @throws Passenger Exception
	 * @throws IOException
	 */
	private void createSimulation() throws SimulationException, AircraftException, PassengerException, IOException {
		// re-disable graph buttons
		btnShowBookingGraph.setEnabled(false);
		btnShowQueueRefusedGraph.setEnabled(false);
		
		// reset tracker
		isValidInput = true;
		
		// grab parameters for simulation, input will be sanitized 
		int seed = getSeed();
		double dailyMean = getDailyMean();
		int queueSize = getDailyQueueSize();
		double cancellation = getCancellation();
		
		double firstProb = getFirstProb();
		double businessProb = getBusinessProb();
		double premiumProb = getPremiumProb();
		double economyProb = getEconomyProb();
		
		checkSumProbabilities(firstProb, businessProb, premiumProb, economyProb);
		
		checkProbabilityInput(cancellation);
		
		if (isValidInput) {
			this.sim = new Simulator(seed,queueSize,dailyMean,dailyMean * 0.33,firstProb,businessProb,
					  premiumProb,economyProb,cancellation);			
			runSimulation();
		} else {
			txtLoggingOutput.append("Please fix errors before running simulation again!\n");
		}	
	}

	/**
	 * Private method that runs the simulation and calls output methods
	 * @throws SimulationException
	 * @throws Aircraft Exception
	 * @throws Passenger Exception
	 * @throws IOException
	 */
	private void runSimulation() throws AircraftException, SimulationException, PassengerException, IOException {
		txtLoggingOutput.setText("");
		sim.createSchedule();
		initialEntry(sim);
		
		// logging object
		Log log = new Log();
		
		asgn2Simulators.ChartPanel bookingsChart = new asgn2Simulators.ChartPanel(BOOKINGS_CHART);
		asgn2Simulators.ChartPanel queuedRefusedChart = new asgn2Simulators.ChartPanel(QUEUE_REFUSE_CHART);
		
		Calendar cal = GregorianCalendar.getInstance();

		// main simulation loop
		for (int time = 0; time <= Constants.DURATION; time++) {
			
			cal.set(2016,0,time,0,0);
	        Date timePoint = cal.getTime();
	        
			this.sim.resetStatus(time); 
			this.sim.rebookCancelledPassengers(time); 
			this.sim.generateAndHandleBookings(time);
			this.sim.processNewCancellations(time);
			if (time >= Constants.FIRST_FLIGHT) {
				this.sim.processUpgrades(time);
				this.sim.processQueue(time);
				this.sim.flyPassengers(time);
				this.sim.updateTotalCounts(time); 
				log.logFlightEntries(time, sim);
				
				// set data for bookings chart 
				bookingsChart.setEconomy(sim.getTotalEconomy());
				bookingsChart.setPremium(sim.getTotalPremium());
				bookingsChart.setFirst(sim.getTotalFirst());
				bookingsChart.setBusiness(sim.getTotalBusiness());
				bookingsChart.setTotal(getTotalBookings(sim));
				bookingsChart.setEmpty();
				
				// set data for queue refused chart
				queuedRefusedChart.setRefused(sim.numRefused());
				queuedRefusedChart.setQueued(sim.numInQueue());
				
				bookingsChart.addToTimeSeriesBookings(timePoint);
				queuedRefusedChart.addToTimeSeriesQueuedRefused(timePoint);
			} else {
				this.sim.processQueue(time);
			}
			//Log progress 
			log.logQREntries(time, sim);
			log.logEntry(time,this.sim);
			// text area output
			logEntry(time, this.sim);
		}
		
		bookingsChart.compileTimeSeriesBookings();
		queuedRefusedChart.compileTimeSeriesQueuedRefused();
		
		pnlChartOne.removeAll();
		pnlChartTwo.removeAll();
		
		pnlChartOne.add(bookingsChart.createComponentBookings());
		pnlChartTwo.add(queuedRefusedChart.createComponentQueuedRefused());
				
		log.logQREntries(Constants.DURATION, sim);
		log.finalise(this.sim);
		this.sim.finaliseQueuedAndCancelledPassengers(Constants.DURATION); 
		finalise(this.sim);
	}
	
	/**
	 * Private method to fetch the number of total bookings
	 * @param Simulator object
	 */
	private int getTotalBookings(Simulator sim) {
		return sim.getTotalBusiness() + sim.getTotalEconomy() 
		+ sim.getTotalFirst() + sim.getTotalPremium();
	}
	
	/**
	 * Private method that outputs the summary of the simulator for that day
	 * @param time Current time
	 * @param sim Simulator Object
	 * @throws IOException
	 * @throws Simulation Exception
	 */
	public void logEntry(int time,Simulator sim) throws IOException, SimulationException {
		boolean flying = (time >= Constants.FIRST_FLIGHT);
		txtLoggingOutput.append(sim.getSummary(time, flying));
	}
	
	/**
	 * Private method that outputs the initial entry
	 * @param sim Simulator Object
	 * @throws Simulation Exception
	 */
	private void initialEntry(Simulator sim) throws SimulationException {
		txtLoggingOutput.append( getCurrentTime() + ": Start of Simulation\n");
		txtLoggingOutput.append(sim.toString() + "\n");
		String capacities = sim.getFlights(Constants.FIRST_FLIGHT).initialState();
		txtLoggingOutput.append(capacities);
	}
	
	
	/**
	 * Private method that outputs the finalise string
	 * @param sim Simulator Object
	 */
	private void finalise(Simulator sim) {
		txtLoggingOutput.append("\n" + getCurrentTime() + ": End of Simulation\n");
		txtLoggingOutput.append(sim.finalState());
	}
	
	/**
	 * Private helper method to return the current time for use in logging
	 * @return
	 */
	private String getCurrentTime() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Numerous getters which fetch the content of the text fields and return it 
	 * if it's acceptable for the simulation, if not then an error is raised.
	 * 
	 */
	private int getSeed() {
		return checkInteger(txtRNGInput.getText(), "Seed");
	}
	
	private Double getDailyMean() {
		return checkDouble(txtDailyMean.getText(), "Daily Mean");
	}
	
	private int getDailyQueueSize() {
		return checkInteger(txtQueueSize.getText(), "Daily Queue Size");
	}
	
	private Double getCancellation() {
		return checkDouble(txtCancellation.getText(), "Cancellation Probability");
	}
	
	private Double getFirstProb() {
		return checkDouble(txtFirst.getText(), "First Class Probability");
	}
	
	private Double getBusinessProb() {
		return checkDouble(txtBusiness.getText(), "Business Class Probability");
	}
	
	private Double getPremiumProb() {
		return checkDouble(txtPremium.getText(), "Premium Class Probability");
	}
	
	private Double getEconomyProb() {
		return checkDouble(txtEconomy.getText(), "Economy Class Probability");
	}
	
	/**
	 * Private helper method to check the sum of the probabilities
	 * @param first probablity of first class
	 * @param business probablity of business class
	 * @param premium probablity of premium class
	 * @param economy probablity of economy class
	 */
	private void checkSumProbabilities(Double first, Double business, Double premium, Double economy) {
		if (first + business + premium + economy > 1) {
			isValidInput = false;
			txtLoggingOutput.append("Sum of Class Probabilities must be equal to or less than one!\n");
		}
	}
	
	/**
	 * private helper method to test if a probabaility is greater than one
	 * @param value given probability to check
	 */
	private void checkProbabilityInput(Double value) {
		if (value > 1) {
			isValidInput = false;
			txtLoggingOutput.append("Cancellation Probability must be equal to or less than one!\n");
		}
	}
	
	/**
	 * Helper method that parses the string to double and returns it or null if
	 * parse fails
	 * @param name String input that represent name
	 * @param input input string sourced from text fields
	 */
	private Double checkDouble(String input, String name) {
		double value = 0;
		
		try {
		  value = Double.parseDouble(input);
		} catch(NumberFormatException e) {
			txtLoggingOutput.append(name + " Could not be passed to Double\n");
			isValidInput = false;
		}
		if (value < 0) {
			txtLoggingOutput.append(name + " is less than zero\n");
		}
		return value;
	}
	
	/**
	 * Helper method that parses the string to double and returns it or null if
	 * parse fails
	 * @param name String input that represent name
	 * @param input input string sourced from text fields
	 */
	private Integer checkInteger(String input, String name) {
		int value = 0;
		
		try {
		  value = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			txtLoggingOutput.append(name + " Could not be passed to Integer\n");
			isValidInput = false;
		}
		if (value < 0) {
			txtLoggingOutput.append(name + " is less than zero\n");
			isValidInput = false;
		}
		return value;
	}

}
