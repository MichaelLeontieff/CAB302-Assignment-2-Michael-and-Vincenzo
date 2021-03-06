/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 * This class comprises the main methods and functionality for the JFreeChart Charting 
 * in use by the GUI Simulator Class
 * @author michaelleontieff
 * @version 1.0
 */
public class ChartPanel {
	
	private static final int NUM_SEATS_A380 = 484;
	private static final int NUM_SEATS_B747 = 353;
	private static final int NUM_A380 = 2;
	
	private static final int BOOKINGS_CHART = 1;
	private static final int QUEUE_REFUSE_CHART = 2;

	private TimeSeriesCollection timeSeriesBookings;
	private TimeSeriesCollection timeSeriesQueuedRefused;
	
	private TimeSeries econTotal;
	private TimeSeries busTotal; 
	private TimeSeries firstTotal;
	private TimeSeries premiumTotal;
	private TimeSeries totalTotal;
	private TimeSeries seatsAvailTotal;
	
	private TimeSeries refusedTotal;
	private TimeSeries queuedTotal;

	private Date timePoint;
	int time;
	
	private int economy;
	private int premium;
	private int business;
	private int first;
	private int total;
	private int empty;
	
	private int refused;
	private int queued;
	
	private int economyPrev;
	private int premiumPrev;
	private int businessPrev;
	private int firstPrev;
	private int totalPrev;
	private int emptyPrev;
	
	/**
	 * Constructor to instantiate obejcts required for chart creation
	 * @param <code>chartType</code> defines the type of chart to construct
	 */
	public ChartPanel(int chartType) {
		if (chartType == BOOKINGS_CHART) {
			timeSeriesBookings = new TimeSeriesCollection(); 	
			econTotal = new TimeSeries("Economy"); 
			busTotal = new TimeSeries("Business");
			firstTotal = new TimeSeries("First"); 
			premiumTotal = new TimeSeries("Premium"); 
			totalTotal = new TimeSeries("Total Bookings"); 
			seatsAvailTotal = new TimeSeries("Seats Available");
			
		} else if (chartType == QUEUE_REFUSE_CHART){
			timeSeriesQueuedRefused = new TimeSeriesCollection(); 
			refusedTotal = new TimeSeries("Refused");
			queuedTotal = new TimeSeries("Queued");
		}
		
	}
	
	/**
     * Helper method to deliver the Chart for Bookings- currently uses default colours and auto range 
     * @param dataset <code>TimeSeriesCollection</code> for plotting 
     * @returns <code>JFreeChart</code> to be added to panel 
     */
     private JFreeChart createChartBookings(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Bookings Chart (Daily)", "Days", "Passengers", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return result;
    }
    
 	/**
      * Helper method to deliver the Chart for Queue Refused Passengers - currently uses default colours and auto range 
      * @param dataset <code>TimeSeriesCollection</code> for plotting 
      * @returns <code>JFreechart</code> to be added to panel 
      */
     private JFreeChart createChartQueuedRefused(final XYDataset dataset) {
         final JFreeChart result = ChartFactory.createTimeSeriesChart(
             "Queued and Refused Chart (Cumulative)", "Days", "Passengers", dataset, true, true, false);
         final XYPlot plot = result.getXYPlot();
         ValueAxis domain = plot.getDomainAxis();
         domain.setAutoRange(true);
         ValueAxis range = plot.getRangeAxis();
         range.setAutoRange(true);
         return result;
    }
     
    /**
     * Create Bookings Chart 
     * @return <code>ChartPanel</code> for adding into tabbed pane
     */
    public org.jfree.chart.ChartPanel createComponentBookings() {
    	return new org.jfree.chart.ChartPanel(createChartBookings(timeSeriesBookings));
    }
    
    /**
     * Create Queued Refused Chart 
     * @return <code>ChartPanel</code> for adding into tabbed pane
     */
    public org.jfree.chart.ChartPanel createComponentQueuedRefused() {
    	return new org.jfree.chart.ChartPanel(createChartQueuedRefused(timeSeriesQueuedRefused));
    }
    
    public Date getTimePoint() {
    	return timePoint;
    }
    
    /**
     * Method to add the time series to the Bookings Collection
     * @param <code>timePoint</code> current timepoint in simulation
     */
    public void addToTimeSeriesBookings(Date timePoint) {
    	busTotal.add(new Day(timePoint),business);
		econTotal.add(new Day(timePoint),economy);
		firstTotal.add(new Day(timePoint),first);
		premiumTotal.add(new Day(timePoint),premium);
		totalTotal.add(new Day(timePoint),total);
		seatsAvailTotal.add(new Day(timePoint),empty);
    }
    
    /**
     * Method to add the time series to the Queued Refused Collection
     * @param <code>timePoint</code> current timepoint in simulation
     */
    public void addToTimeSeriesQueuedRefused(Date timePoint) {
    	refusedTotal.add(new Day(timePoint),refused);
		queuedTotal.add(new Day(timePoint),queued);
    }
    
    /**
     * Method to calculate and set the economy value through calculating the daily bookings from a cumulative input
     * @param <code>economyTotal</code> number of bookings
     */
    public void setEconomy(int economy) {
    	economyPrev += this.economy;
    	this.economy = economy - economyPrev;
    }
    
    /**
     * Method to calculate and set the premium value through calculating the daily bookings from a cumulative input
     * @param <code>premiumtotal</code> number of bookings
     */
    public void setPremium(int premium) {
    	premiumPrev += this.premium;
    	this.premium = premium - premiumPrev;
    }
    
    /**
     * Method to calculate and set the business value through calculating the daily bookings from a cumulative input
     * @param <code>businessTotal</code> number of bookings
     */
    public void setBusiness(int business) {
    	businessPrev += this.business;
    	this.business = business - businessPrev;
    }
    
    /**
     * Method to calculate and set the first value through calculating the daily bookings from a cumulative input
     * @param <code>firstTotal</code> number of bookings
     */
    public void setFirst(int first) {
    	firstPrev += this.first;
    	this.first = first - firstPrev;
    }
    
    /**
     * Method to calculate and set the total value through calculating the daily bookings from a cumulative input
     * @param <code>total</code> number of bookings
     */
    public void setTotal(int total) {
    	totalPrev += this.total;
    	this.total = total - totalPrev;
    }
    
    /**
     * Method to calculate and set the empty value through calculating the daily bookings from a cumulative input
     * @param <code>empty</code> total number of bookings
     */
    public void setEmpty(int empty) {
    	emptyPrev += this.empty;
    	this.empty = empty - emptyPrev;
    }
    
    /**
     * Method to calculate and set the refused value through calculating the daily bookings from a cumulative input
     * @param <code>refused</code> total number of bookings
     */
    public void setRefused(int refused) {
    	this.refused = refused;
    }
    
    /**
     * Method to set the Queued value through parameter input
     * @param <code>queued</code> current number of queued passengers
     */
    public void setQueued(int queued) {
    	this.queued = queued;
    }
    
    public void setEmpty() {
    	this.empty = ((NUM_SEATS_A380 * NUM_A380) + NUM_SEATS_B747) - first - business - premium - economy;
    }
    
    /**
     * Method to compile the time series for the bookings chart into a single collection
     */
    public void compileTimeSeriesBookings() {
    	timeSeriesBookings.addSeries(firstTotal);
    	timeSeriesBookings.addSeries(premiumTotal);
    	timeSeriesBookings.addSeries(econTotal);
    	timeSeriesBookings.addSeries(busTotal);
    	timeSeriesBookings.addSeries(totalTotal);
    	timeSeriesBookings.addSeries(seatsAvailTotal);
    }
    
    /**
     * Method to compile the time series for the queued refused chart into a single collection
     */
    public void compileTimeSeriesQueuedRefused() {
    	timeSeriesQueuedRefused.addSeries(refusedTotal);
    	timeSeriesQueuedRefused.addSeries(queuedTotal);
    }

}
