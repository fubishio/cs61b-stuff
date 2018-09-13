package ngordnet;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class NGramMap {
    List<Ngram> nGramList = new ArrayList<Ngram>();
    TreeMap<Integer, Long> yearCounts = new TreeMap<Integer, Long>();
    public NGramMap(String wordsFilename, String countsFilename) {
        String tempLine;
        String tempWord;
        int tempYear;
        int temptCount;
        int tempdistinctCount;
        Ngram tempNgram;
        String yearCountsTempLine;
        int yearCountsyear;
        Long yearCountscounts;
        
        In in = new In(new File(wordsFilename));
        while (in.hasNextLine()) {
            tempLine = in.readLine();
            String[] sortedString = tempLine.split("\t");
            tempWord = sortedString[0];
            tempYear = Integer.parseInt(sortedString[1]);
            temptCount = Integer.parseInt(sortedString[2]);
            tempdistinctCount = Integer.parseInt(sortedString[3]);
            tempNgram = new Ngram(tempWord, tempYear, temptCount, tempdistinctCount);
            nGramList.add(tempNgram);
        }
        in.close();
        
        In in1 = new In(new File(countsFilename));
        while (in1.hasNextLine()) {
            yearCountsTempLine = in1.readLine();
            String[] sortedLine = yearCountsTempLine.split(",");
            yearCountsyear = Integer.parseInt(sortedLine[0]);
            yearCountscounts = Long.parseLong(sortedLine[1]);
            yearCounts.put(yearCountsyear, yearCountscounts);
        }
        in1.close();
        /*String tempLine;
        Scanner lineSorter;
        String tempWord;
        int tempYear;
        int temptCount;
        int tempdistinctCount;
        Ngram tempNgram;
        
        String yearCountsTempLine;
        Scanner yearCountsLineSorter;
        int yearCountsyear;
        Long yearCountscounts;
        
        Scanner scanner = new Scanner(new File(wordsFilename));
        while (scanner.hasNextLine()) {
            tempLine = scanner.nextLine();
            lineSorter = new Scanner(tempLine);
            lineSorter.useDelimiter("    ");
            tempWord = lineSorter.next();
            tempYear = Integer.parseInt(lineSorter.next());
            temptCount = Integer.parseInt(lineSorter.next());
            tempdistinctCount = Integer.parseInt(lineSorter.next());
            tempNgram = new Ngram(tempWord, tempYear, temptCount, tempdistinctCount);
            nGramList.add(tempNgram);
        }
        scanner.close();
        
        Scanner scanner1 = new Scanner(new File(countsFilename));
        while (scanner1.hasNextLine()) {
            yearCountsTempLine = scanner1.nextLine();
            yearCountsLineSorter = new Scanner(yearCountsTempLine);
            yearCountsLineSorter.useDelimiter(",");
            yearCountsyear = Integer.parseInt(yearCountsLineSorter.next());
            yearCountscounts = Long.parseLong(yearCountsLineSorter.next());
            yearCounts.put(yearCountsyear, yearCountscounts);
        }
        scanner1.close();*/
    }
    
    public int countInYear(String word, int year) {
        for (Ngram x : nGramList) {
            if (x.getWord().equals(word) && x.getYear() == year) {
                return x.getTotalCount();
            }
        }
        return 0;
    }

    public YearlyRecord getRecord(int year) {
        YearlyRecord retyRecord = new YearlyRecord();
        for (Ngram x : nGramList) {
            if (x.getYear() == year) {
                retyRecord.put(x.getWord(), x.getTotalCount());
            }
        }
        return retyRecord;
    }

    public TimeSeries<Long> totalCountHistory() {
        TimeSeries<Long> retTS = new TimeSeries<Long>();
        for (Integer x : yearCounts.keySet()) {
            retTS.put(x, yearCounts.get(x));
        }
        return retTS;
    }
    
    /*Must Test*/
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear) {
        TimeSeries<Integer> retTS = new TimeSeries<Integer>();
        for (Ngram x : nGramList) {
            if (x.getWord().equals(word) && x.getYear() <= endYear && x.getYear() >= startYear) {
                retTS.put(x.getYear(), x.getTotalCount());
            }
        }
        return retTS;
    }

    public TimeSeries<Integer> countHistory(String word) {
        TimeSeries<Integer> retTS = new TimeSeries<Integer>();
        for (Ngram x : nGramList) {
            if (x.getWord().equals(word)) {
                retTS.put(x.getYear(), x.getTotalCount());
            }
        }
        return retTS;
    }

    /*Must Test*/
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear) {
        TimeSeries<Double> retTS = new TimeSeries<Double>();
        TimeSeries<Integer> x = countHistory(word);
        TimeSeries<Long> y = totalCountHistory();
        for (Integer a: x.keySet()) {
            if (a >= startYear && a <= endYear) {
                retTS.put(a, (x.get(a) / (double) y.get(a)));
            }
        }
        return retTS;
    }

    public TimeSeries<Double> weightHistory(String word) {
        TimeSeries<Double> retTS = new TimeSeries<Double>();
        TimeSeries<Integer> x = countHistory(word);
        TimeSeries<Long> y = totalCountHistory();
        for (Integer a: x.keySet()) {
            retTS.put(a, (x.get(a) / (double) y.get(a)));
        }
        return retTS;
    }
    
    /*Must Test*/
    public TimeSeries<Double> summedWeightHistory(Collection<String> words, 
            int startYear, int endYear) {
        TimeSeries<Double> retTS = new TimeSeries<Double>();
        TimeSeries<Integer> summed = new TimeSeries<Integer>();
        int c = 0;
        for (String a : words) {
            if (c == 0) {
                for (Integer g : countHistory(a).keySet()) {
                    if (g >= startYear && g <= endYear) {
                        summed.put(g, countHistory(a).get(g));
                    }
                }
            } else {
                for (Integer h : countHistory(a).keySet()) {
                    if (summed.keySet().contains(h)) {
                        if (h >= startYear && h <= endYear) {
                            summed.put(h, ((int) countHistory(a).get(h) + summed.get(h)));
                        }
                    } else {
                        if (h >= startYear && h <= endYear) {
                            summed.put(h, countHistory(a).get(h));
                        }
                    }
                    
                }
            }
            
            c = c + 1;
        }
        TimeSeries<Long> y = new TimeSeries<Long>();
        for (Integer x : yearCounts.keySet()) {
            if (x >= startYear && x <= endYear) {
                y.put(x, yearCounts.get(x));
            }
        }
        
        for (Integer a: summed.keySet()) {
            retTS.put(a, (summed.get(a) / (double) y.get(a)));
        }
        return retTS;
    }

    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> retTS = new TimeSeries<Double>();
        TimeSeries<Integer> summed = new TimeSeries<Integer>();
        int c = 0;
        for (String a : words) {
            if (c == 0) {
                for (Integer g : countHistory(a).keySet()) {
                    summed.put(g, countHistory(a).get(g));
                }
            } else {
                for (Integer h : countHistory(a).keySet()) {
                    if (summed.keySet().contains(h)) {
                        summed.put(h, ((int) countHistory(a).get(h) + summed.get(h)));
                    } else {
                        summed.put(h, countHistory(a).get(h));
                    }
                    
                }
            }
            
            c = c + 1;
        }
        TimeSeries<Long> y = totalCountHistory();
        for (Integer a: summed.keySet()) {
            retTS.put(a, (summed.get(a) / (double) y.get(a)));
        }
        return retTS;
    }

    /** Provides processed history of all words between STARTYEAR 
     * and ENDYEAR as processed by YRP. */
    public TimeSeries<Double> processedHistory(int startYear, int endYear, 
        YearlyRecordProcessor yrp) {
        TimeSeries<Double> a = new TimeSeries<Double>();
        for (Integer y : yearCounts.keySet()) {
            if (y >= startYear && y <= endYear) {
                YearlyRecord z = new YearlyRecord();
                for (Ngram x : nGramList) {
                    if (x.getYear() == y) {
                        z.put(x.getWord(), x.getTotalCount());
                    }
                }
                a.put(y, yrp.process(z));
            }
        }
        return a;
    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> a = new TimeSeries<Double>();
        
        for (Integer y : yearCounts.keySet()) {
            YearlyRecord z = new YearlyRecord();
            for (Ngram x : nGramList) {
                if (x.getYear() == y) {
                    z.put(x.getWord(), x.getTotalCount());
                }
            }
            a.put(y, yrp.process(z));
        }
        return a;
    }
    
}
