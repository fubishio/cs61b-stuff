package ngordnet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class TimeSeries<T extends java.lang.Number> extends TreeMap<Integer, T> {
    
    public TimeSeries() {
    }
    
    public TimeSeries(TimeSeries<T> ts) {
        for (Integer x : ts.keySet()) {
            this.put(x, ts.get(x));
        }
    }
    
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
        for (Integer x : ts.keySet()) {
            if (x >= startYear && x <= endYear) {
                this.put(x, ts.get(x));
            }
        }
    }
    
    public java.util.Collection<java.lang.Number> data() {
        Collection<Number> datalist = new ArrayList<Number>();
        for (T x : this.values()) {
            datalist.add(x);
        }
        return datalist;
    }
    
    public TimeSeries<java.lang.Double> dividedBy(TimeSeries<? extends java.lang.Number> ts) 
        throws IllegalArgumentException {
        TimeSeries<Double> tsQuotient = new TimeSeries<Double>();
        
        for (Integer x : this.keySet()) {
            if (ts.containsKey(x)) {
                tsQuotient.put(x, (this.get(x).doubleValue() / ts.get(x).doubleValue()));
            } else {
                tsQuotient.put(x, this.get(x).doubleValue());
            }
        }
        for (Integer y : ts.keySet()) {
            if (this.containsKey(y)) {
                tsQuotient.put(y, this.get(y).doubleValue() / ts.get(y).doubleValue());
            } else {
                throw new IllegalArgumentException();
                /*tsQuotient.put(y, ts.get(y).doubleValue());*/
            }
        }
        return tsQuotient;
    }
    
    public TimeSeries<java.lang.Double> plus(TimeSeries<? extends java.lang.Number> ts) {
        TimeSeries<Double> tsSum = new TimeSeries<Double>();
        
        for (Integer x : this.keySet()) {
            if (ts.containsKey(x)) {
                tsSum.put(x, (this.get(x).doubleValue() + ts.get(x).doubleValue()));
            } else {
                tsSum.put(x, this.get(x).doubleValue());
            }
        }
        for (Integer y : ts.keySet()) {
            if (this.containsKey(y)) {
                tsSum.put(y, this.get(y).doubleValue() + ts.get(y).doubleValue());
            } else {
                tsSum.put(y, ts.get(y).doubleValue());
            }
        }
        return tsSum;
    }
    
    public java.util.Collection<java.lang.Number> years() {
        Collection<Number> yearlist = new ArrayList<>();
        for (Integer x : this.keySet()) {
            yearlist.add(x);
        }
        return yearlist;
        
    }
    
}
