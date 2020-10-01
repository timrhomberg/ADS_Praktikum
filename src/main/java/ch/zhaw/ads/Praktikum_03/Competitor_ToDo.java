package ch.zhaw.ads.Praktikum_03;

import java.util.*;
import java.text.*;

public class Competitor_ToDo implements Comparable<Competitor_ToDo> {
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor_ToDo(int startNr, String name, int jg, String country, String time) {
        this.startNr = startNr;
        this.name = name;
        this.jg = jg;
        this.country = country;
        try {
            this.time = Competitor_ToDo.parseTime(time);
        } catch (ParseException e) {
            System.err.println("Error while parsing time!");
        }
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append(rank);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(Integer.toString(jg));
        sb.append(" ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }

    @Override
    public boolean equals(Object anObject) {
        if(this == anObject) {
            return true;
        }
        if(anObject instanceof Competitor_ToDo) {
            Competitor_ToDo anotherCompetitor = (Competitor_ToDo) anObject;
            return this.jg == anotherCompetitor.jg
                    && this.rank == anotherCompetitor.rank
                    && this.startNr == anotherCompetitor.startNr
                    && this.name.equals(anotherCompetitor.name)
                    && this.country.equals(anotherCompetitor.country)
                    && Double.compare(this.time, anotherCompetitor.time) == 0;
        }
        return false;
    }
    @Override
    public int compareTo(Competitor_ToDo o) {
        return Long.compare(this.time, o.time);
    }
}
