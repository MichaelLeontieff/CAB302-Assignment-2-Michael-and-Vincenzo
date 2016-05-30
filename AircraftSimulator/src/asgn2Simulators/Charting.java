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

	private TimeSeriesCollection timeSeries;
	
	private TimeSeries econTotal;
	private TimeSeries busTotal; 
	private TimeSeries firstTotal;
	private TimeSeries premiumTotal;
	private TimeSeries totalTotal;
	private TimeSeries seatsAvailTotal;

	private Date timePoint;
	int time;
	
	private int economy;
	private int premium;
	private int business;
	private int first;
	private int total;
	private int empty;
	
	private int economyPrev;
	private int premiumPrev;
	private int businessPrev;
	private int firstPrev;
	private int totalPrev;
	private int emptyPrev;
	
	
	public Charting() {
		timeSeries = new TimeSeriesCollection(); 
		
		econTotal = new TimeSeries("Economy"); 
		busTotal = new TimeSeries("Business");
		firstTotal = new TimeSeries("First"); 
		premiumTotal = new TimeSeries("Premium"); 
		totalTotal = new TimeSeries("Total Bookings"); 
		seatsAvailTotal = new TimeSeries("Seats Available");
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
     
    public ChartPanel createComponent() {
    	return new ChartPanel(createChart(timeSeries));
    }
    
    public Date getTimePoint() {
    	return timePoint;
    }
    
    public void addToTimeSeries(Date timePoint) {
    	busTotal.add(new Day(timePoint),business);
		econTotal.add(new Day(timePoint),economy);
		firstTotal.add(new Day(timePoint),first);
		premiumTotal.add(new Day(timePoint),premium);
		totalTotal.add(new Day(timePoint),total);
		seatsAvailTotal.add(new Day(timePoint),empty);
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
    
    public void compileTimeSeries() {
    	timeSeries.addSeries(firstTotal);
		timeSeries.addSeries(premiumTotal);
		timeSeries.addSeries(econTotal);
		timeSeries.addSeries(busTotal);
		timeSeries.addSeries(totalTotal);
		timeSeries.addSeries(seatsAvailTotal);
    }

}
