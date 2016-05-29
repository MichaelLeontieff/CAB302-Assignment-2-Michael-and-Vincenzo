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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;


import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

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
public class GUISimulator extends JFrame implements Runnable, ActionListener {
	/*
	 * Constants that define window dimensions
	 */
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 650;
	

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
	private JButton btnShowGraphTwo;
	
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
	private TimeSeriesCollection timeSeries;
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
	
	/*
	 * Program entry point. Selects GUI or CLI from command line arg 
	 * options. 
	 * 
	 */
	
	private void createGUI() {
		// set general GUI parameters
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    tabbedPane = new JTabbedPane();
	    headingFont = new Font("Arial", Font.BOLD, 24);
		
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
		txtLoggingOutput.setEditable(false);
		
		scroll = new JScrollPane(txtLoggingOutput);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnlLoggingOutput.add(scroll);
		
		tabbedPane.addTab("Text Logging Output", pnlLoggingOutput);
	}
	
	private void layoutFreechartOnePanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlChartOne.setLayout(layout);
		
		tabbedPane.addTab("Chart One", pnlChartOne);
	}
	
	/**
     * Helper method to deliver the Chart - currently uses default colours and auto range 
     * @param dataset TimeSeriesCollection for plotting 
     * @returns chart to be added to panel 
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Bookings Chart", "Days", "Passengers", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return result;
    }
	
	/**
     * Private method creates the dataset. Lots of hack code in the 
     * middle, but you should use the labelled code below  
	 * @return collection of time series for the plot 
	 */
	private TimeSeriesCollection createTimeSeriesData(Simulator sim) {
		TimeSeriesCollection tsc = new TimeSeriesCollection(); 
		System.out.println(tsc);
		TimeSeries bookTotal = new TimeSeries("Total Bookings");
		TimeSeries econTotal = new TimeSeries("Economy"); 
		TimeSeries busTotal = new TimeSeries("Business");
		TimeSeries firstTotal = new TimeSeries("First"); 
		TimeSeries premiumTotal = new TimeSeries("Premium"); 
		
		//Base time, data set up - the calendar is needed for the time points
		Calendar cal = GregorianCalendar.getInstance();
		
		int economy = 0;
		int business = 0; 
		int first = 0;
		int premium = 0;
		
		//Hack loop to make it interesting. Grows for half of it, then declines
		for (int time = 0; time <= Constants.DURATION; time++) {
			//These lines are important 
			cal.set(2016,0,time,6,0);
	        Date timePoint = cal.getTime();
	        
	        
	        economy = sim.getTotalEconomy();
	        business = sim.getTotalBusiness();
	        premium = sim.getTotalPremium();
	        first = sim.getTotalFirst();
	        
	        //This is important - steal it shamelessly 
	        busTotal.add(new Day(timePoint),business);
			econTotal.add(new Day(timePoint),economy);
			firstTotal.add(new Day(timePoint),first);
			premiumTotal.add(new Day(timePoint),premium);
			bookTotal.add(new Day(timePoint),economy+business+first+premium);
			
		}
		
		//Collection
		tsc.addSeries(firstTotal);
		tsc.addSeries(premiumTotal);
		tsc.addSeries(econTotal);
		tsc.addSeries(busTotal);
		return tsc; 
	}
	
	private void layoutFreechartTwoPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlChartTwo.setLayout(layout);
		
		tabbedPane.addTab("Chart Two", pnlChartTwo);
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
	    btnRunSimulation.setPreferredSize(new Dimension(200, 40));
	    btnRunSimulation.addActionListener(this);
	    
	    btnShowGraphTwo = new JButton("Show Graph Two");
	    btnShowGraphTwo.setPreferredSize(new Dimension(200, 40));
	    btnShowGraphTwo.addActionListener(this);    
	    
	    btnShowGraphTwo.setEnabled(false);
	    
	    addToPanel(pnlExecution, lblExecution, lblHeadingConstraints, 0, 0, 1, 5); 
	    addToPanel(pnlExecution, btnRunSimulation, lblHeadingConstraints, 0, 5, 1, 5); 
	    addToPanel(pnlExecution, btnShowGraphTwo, lblHeadingConstraints, 0, 10, 1, 5); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource(); 	      
			if (src== btnRunSimulation) {
				System.out.println("reach here");
				try {
					createSimulation();
					runSimulation();
				} catch (AircraftException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SimulationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PassengerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
	
	/*
	 * create simulator object with parameters
	 */
	private void createSimulation() throws SimulationException {
		// grab parameters for simulation, input will be sanitized 
		// by the getters
		int seed = getSeed();
		double dailyMean = getDailyMean();
		int queueSize = getDailyQueueSize();
		int cancellation = getCancellation();
		
		double firstProb = getFirstProb();
		double businessProb = getBusinessProb();
		double premiumProb = getPremiumProb();
		double economyProb = getEconomyProb();
		
		sim = new Simulator(seed,queueSize,dailyMean,Constants.DEFAULT_DAILY_BOOKING_SD,firstProb,businessProb,
				  premiumProb,economyProb,Constants.DEFAULT_CANCELLATION_PROB);	
		
		
	}
	/*
	 * Method that runs the simulation and calls output methods
	 */
	private void runSimulation() throws AircraftException, SimulationException, PassengerException {
		sim.createSchedule();
		initialEntry(sim);
		
		// freechart stuff
		timeSeries = new TimeSeriesCollection(); 
		TimeSeries econTotal = new TimeSeries("Economy"); 
		TimeSeries busTotal = new TimeSeries("Business");
		TimeSeries firstTotal = new TimeSeries("First"); 
		TimeSeries premiumTotal = new TimeSeries("Premium"); 
		TimeSeries totalTotal = new TimeSeries("Total Bookings"); 
		TimeSeries seatsAvailTotal = new TimeSeries("Seats Available"); 
		
		//Base time, data set up - the calendar is needed for the time points
		Calendar cal = GregorianCalendar.getInstance();
		
		int prevEcon = 0;
		int economy = 0;
		int prevFirst = 0;
		int first = 0;
		int prevBusiness = 0;
		int business = 0; 
		int prevPremium = 0;
		int premium = 0;
		int total = 0;
		int seatsAvailable = 0;
		
		// main simulation loop
		for (int time=0; time<=Constants.DURATION; time++) {
			// freechart
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
				logFlightEntries(time, sim); // this
			} else {
				this.sim.processQueue(time);
			}
			
	        economy = sim.getTotalEconomy() - prevEcon;
	        business = sim.getTotalBusiness() - prevBusiness;
	        first = sim.getTotalFirst() - prevBusiness;
	        premium = sim.getTotalPremium() - prevPremium;
	        
	        prevEcon = economy;
	        prevBusiness = business;
	        prevFirst = first;
	        prevPremium = premium;
	        
	        total = economy + business + first + premium;
	        seatsAvailable = sim.getTotalEmpty();

			busTotal.add(new Day(timePoint),business);
			econTotal.add(new Day(timePoint),economy);
			firstTotal.add(new Day(timePoint),first);
			premiumTotal.add(new Day(timePoint),premium);
			totalTotal.add(new Day(timePoint),total);
			seatsAvailTotal.add(new Day(timePoint),seatsAvailable);
			
		}
		
		//Collection
		timeSeries.addSeries(firstTotal);
		timeSeries.addSeries(premiumTotal);
		timeSeries.addSeries(econTotal);
		timeSeries.addSeries(busTotal);
		timeSeries.addSeries(totalTotal);
		timeSeries.addSeries(seatsAvailTotal);
				
		pnlChartOne.add(new ChartPanel(createChart(timeSeries)));
				
		this.sim.finaliseQueuedAndCancelledPassengers(Constants.DURATION); 
		//this.log.logQREntries(Constants.DURATION, sim);
		finalise(this.sim); // this
		
		
	
	}
	
	
	/*
	 * Private method that outputs the initial entry
	 */
	private void initialEntry(Simulator sim) throws SimulationException {
		txtLoggingOutput.append("Start of Simulation\n");
		txtLoggingOutput.append(sim.toString() + "\n");
		String capacities = sim.getFlights(Constants.FIRST_FLIGHT).initialState();
		txtLoggingOutput.append(capacities);
	}
	
	/*
	 * Private method that outputs the flight entries
	 */
	private void logFlightEntries(int time, Simulator sim) throws SimulationException {
		Flights flights = sim.getFlights(time); 
		txtLoggingOutput.append(flights.getStatus(time) + "\n");
	}
	
	/*
	 * Private method that outputs the finalise string
	 */
	private void finalise(Simulator sim) {
		txtLoggingOutput.append("End of Simulation\n");
		txtLoggingOutput.append(sim.finalState());
	}
	
	/*
	 * Numerous getters which fetch the content of the text fields and return it 
	 * if it's acceptable for the simulation, if not then an error is raised.
	 * 
	 */
	private int getSeed() {
		return checkInteger(txtRNGInput.getText(), Constants.DEFAULT_SEED);
	}
	
	private Double getDailyMean() {
		return checkDouble(txtDailyMean.getText(), Constants.DEFAULT_DAILY_BOOKING_MEAN);
	}
	
	private int getDailyQueueSize() {
		return checkInteger(txtQueueSize.getText(), Constants.DEFAULT_MAX_QUEUE_SIZE);
	}
	
	private int getCancellation() {
		return checkInteger(txtCancellation.getText(), Constants.CANCELLATION_PERIOD);
	}
	
	private Double getFirstProb() {
		return checkDouble(txtFirst.getText(), Constants.DEFAULT_FIRST_PROB);
	}
	
	private Double getBusinessProb() {
		return checkDouble(txtFirst.getText(), Constants.DEFAULT_BUSINESS_PROB);
	}
	
	private Double getPremiumProb() {
		return checkDouble(txtFirst.getText(), Constants.DEFAULT_PREMIUM_PROB);
	}
	
	private Double getEconomyProb() {
		return checkDouble(txtFirst.getText(), Constants.DEFAULT_ECONOMY_PROB);
	}
	
	/*
	 * Helper method that parses the string to double and returns the constant if 
	 * parse fails
	 */
	private Double checkDouble(String input, Double constant) {
		double value;
		
		try {
		  value = Double.parseDouble(input);
		} catch(NumberFormatException e) {
		 value = constant;
		}
		return value;
	}
	
	/*
	 * Helper method that parses the string to int and returns the constant if 
	 * parse fails
	 */
	private Integer checkInteger(String input, int constant) {
		int value;
		
		try {
		  value = Integer.parseInt(input);
		} catch(NumberFormatException e) {
		 value = constant;
		}
		
		return value;
	}

}
