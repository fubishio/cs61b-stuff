package ngordnet;


import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;
import com.xeiam.xchart.StyleManager.ChartTheme;
import com.xeiam.xchart.ChartBuilder;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;

/*Most of this was taken from the method signatures document
 * and the reference code provided but I modified some conditions
 * http://joshh.ug/plotter.html*/

/*I had to add return conditions to the graphing methods I used
 * in the UI because that is how I handled insufficient information
 * exceptions in the program since it would be very redundant to 
 * check the data first in the UI if the data can be directly checked
 * in the making of the graph*/

public class Plotter {
    /** Creates a plot of the TimeSeries TS. Labels the graph with the
      * given TITLE, XLABEL, YLABEL, and LEGEND. */
    public static void plotTS(TimeSeries ts, String title, 
                              String xlabel, String ylabel, String legend) {
        Collection years = ts.years();
        Collection counts = ts.data();

        Chart chart = QuickChart.getChart(title, xlabel, ylabel, legend, years, counts);
     
        new SwingWrapper(chart).displayChart();        
    }

    /** Creates a plot of the absolute word counts for WORD from STARTYEAR
      * to ENDYEAR, using NGM as a data source. */
    public static void plotCountHistory(NGramMap ngm, String word, 
                                      int startYear, int endYear) {
        TimeSeries countHistory = ngm.countHistory(word, startYear, endYear);
        plotTS(countHistory, "Popularity", "year", "count", word);
    }

    /** Creates a plot of the normalized weight counts for WORD from STARTYEAR
      * to ENDYEAR, using NGM as a data source. */
    public static void plotWeightHistory(NGramMap ngm, String word, 
                                       int startYear, int endYear) {
        TimeSeries weightHistory = ngm.weightHistory(word, startYear, endYear);
        System.out.println(weightHistory.data());
        System.out.println(weightHistory.years());
        plotTS(weightHistory, "Popularity", "year", "weight", word);
    }
    
    /** Creates a plot of the processed history from STARTYEAR to ENDYEAR, using
      * NGM as a data source, and the YRP as a yearly record processor. */
    public static void plotProcessedHistory(NGramMap ngm, int startYear, int endYear,
                                            YearlyRecordProcessor yrp) {
        TimeSeries wordWeights = ngm.processedHistory(startYear, endYear, yrp);
        plotTS(wordWeights, "Word Length", "year", "avg. length", "word length");
    }

    /** Creates a plot of the total normalized count of every word that is a hyponym
      * of CATEGORYLABEL from STARTYEAR to ENDYEAR using NGM and WN as data sources. */
    public static void plotCategoryWeights(NGramMap ngm, WordNet wn, String categoryLabel,
                                            int startYear, int endYear) {
        Set words = wn.hyponyms(categoryLabel);        
        TimeSeries summedWeightHistory = ngm.summedWeightHistory(words, startYear, endYear);
        plotTS(summedWeightHistory, "Popularity", "year", "weight", categoryLabel);
    }

    /** Creates overlaid category weight plots for each category label in CATEGORYLABELS
      * from STARTYEAR to ENDYEAR using NGM and WN as data sources. */
    public static void plotCategoryWeights(NGramMap ngm, WordNet wn, String[] categoryLabels,
                                            int startYear, int endYear) {

        Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle("years").yAxisTitle("data").build();

        for (String categoryLabel : categoryLabels) {
            Set words = wn.hyponyms(categoryLabel);        
            TimeSeries bundle = ngm.summedWeightHistory(words, startYear, endYear);
            chart.addSeries(categoryLabel, bundle.years(), bundle.data());
        }
        new SwingWrapper(chart).displayChart();
    }    

    /** Makes a plot showing overlaid individual normalized count for every word in WORDS
      * from STARTYEAR to ENDYEAR using NGM as a data source. */
    public static void plotAllWords(NGramMap ngm, String[] words, int startYear, int endYear) {
        Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle("years").yAxisTitle("data").build();

        for (String word : words) {
            TimeSeries bundle = ngm.weightHistory(word, startYear, endYear);
            if (bundle.data().isEmpty() || bundle.years().isEmpty()) {
            	System.out.println("Insufficient information on at least one of the words in a given year");
            	return;
    		}
            chart.addSeries(word, bundle.years(), bundle.data());
        }
        new SwingWrapper(chart).displayChart();
    }

    /** Returns the numbers from max to 1, inclusive in decreasing order. */
    private static Collection downRange(int max) {
        ArrayList ranks = new ArrayList();
        for (int i = max; i >= 1; i -= 1) {
            ranks.add(i);
        }
        return ranks;
    }

    /** Plots the normalized count of every word against the rank of every word on a
      * log-log plot. Uses data from YEAR, using NGM as a data source. */
    public static void plotZipfsLaw(NGramMap ngm, int year) {
        YearlyRecord yr = ngm.getRecord(year);
        Collection counts = yr.counts();
        Collection ranks = downRange(counts.size()); 
        if (counts.isEmpty() || ranks.isEmpty()) {
        	System.out.println("Not enough information in that year");
        	return;
        }
        Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle("rank").yAxisTitle("count").build();
        chart.getStyleManager().setYAxisLogarithmic(true);
        chart.getStyleManager().setXAxisLogarithmic(true);
        
        chart.addSeries("zipf", ranks, counts);
        new SwingWrapper(chart).displayChart();
    }

}