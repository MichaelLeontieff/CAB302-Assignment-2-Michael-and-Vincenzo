package asgn2Simulators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class Charting {

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
	
	private int queuedPrev;
	private int refusedPrev;
	
	private static final int BOOKINGS_CHART = 1;
	private static final int QUEUE_REFUSE_CHART = 2;
	
	
	public Charting(int chartType) {
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
     * Helper method to deliver the Chart - currently uses default colours and auto range 
     * @param dataset TimeSeriesCollection for plotting 
     * @returns chart to be added to panel 
     */
     private JFreeChart createChartBookings(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Bookings Chart", "Days", "Passengers", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return result;
    }
    
     private JFreeChart createChartQueuedRefused(final XYDataset dataset) {
         final JFreeChart result = ChartFactory.createTimeSeriesChart(
             "Queued and Refused Chart", "Days", "Passengers", dataset, true, true, false);
         final XYPlot plot = result.getXYPlot();
         ValueAxis domain = plot.getDomainAxis();
         domain.setAutoRange(true);
         ValueAxis range = plot.getRangeAxis();
         range.setAutoRange(true);
         return result;
     }
     
    public ChartPanel createComponentBookings() {
    	return new ChartPanel(createChartBookings(timeSeriesBookings));
    }
    
    public ChartPanel createComponentQueuedRefused() {
    	return new ChartPanel(createChartQueuedRefused(timeSeriesQueuedRefused));
    }
    
    public Date getTimePoint() {
    	return timePoint;
    }
    
    public void addToTimeSeriesBookings(Date timePoint) {
    	busTotal.add(new Day(timePoint),business);
		econTotal.add(new Day(timePoint),economy);
		firstTotal.add(new Day(timePoint),first);
		premiumTotal.add(new Day(timePoint),premium);
		totalTotal.add(new Day(timePoint),total);
		seatsAvailTotal.add(new Day(timePoint),empty);
    }
    
    public void addToTimeSeriesQueuedRefused(Date timePoint) {
    	refusedTotal.add(new Day(timePoint),refused);
		queuedTotal.add(new Day(timePoint),queued);
    }
    
    public void setEconomy(int economy) {
    	economyPrev += this.economy;
    	this.economy = economy - economyPrev;
    }
    
    public void setPremium(int premium) {
    	premiumPrev += this.premium;
    	this.premium = premium - premiumPrev;
    }
    
    public void setBusiness(int business) {
    	businessPrev += this.business;
    	this.business = business - businessPrev;
    }
    
    public void setFirst(int first) {
    	firstPrev += this.first;
    	this.first = first - firstPrev;
    }
    
    public void setTotal(int total) {
    	totalPrev += this.total;
    	this.total = total - totalPrev;
    }
    
    public void setEmpty(int empty) {
    	emptyPrev += this.empty;
    	this.empty = empty - emptyPrev;
    }
    
    public void setRefused(int refused) {
    	refusedPrev += this.refused;
    	this.refused = refused - refusedPrev;
    }
    
    public void setQueued(int queued) {
    	this.queued = queued;
    }
    
    public void compileTimeSeriesBookings() {
    	timeSeriesBookings.addSeries(firstTotal);
    	timeSeriesBookings.addSeries(premiumTotal);
    	timeSeriesBookings.addSeries(econTotal);
    	timeSeriesBookings.addSeries(busTotal);
    	timeSeriesBookings.addSeries(totalTotal);
    	timeSeriesBookings.addSeries(seatsAvailTotal);
    }
    
    public void compileTimeSeriesQueuedRefused() {
    	timeSeriesQueuedRefused.addSeries(refusedTotal);
    	timeSeriesQueuedRefused.addSeries(queuedTotal);
    }

}
