package statistics;

import statistics.matcher.*;

public class Main {
    
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
        
        QueryBuilder query = new QueryBuilder();
        
        Matcher m = query.playsIn("NYR")
                .hasAtLeast(10, "goals")
                .hasFewerThan(25, "assists").build();
        
        Matcher m1 = query.playsIn("PHI")
                .hasAtLeast(10, "goals")
                .hasFewerThan(15, "assists").build();
        
        Matcher m2 = query.playsIn("EDM")
                .hasAtLeast(50, "points").build();
        
        Matcher m3 = query.oneOf(m1, m2).build();
        
        Matcher m4 = query.hasFewerThan(5, "goals")
                .hasFewerThan(5, "assists")
                .playsIn("PHI").build();
        
        
        
        System.out.println("Ainakin 10 maalia ja alle 25 syöttöä, joukkueessa NYR");
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        
        System.out.println("Ainakin 10 maalia ja alle q5 syöttöä, joukkueessa PHI");
        for (Player player : stats.matches(m1)) {
            System.out.println(player);
        }
        
        System.out.println("Ainakin 50 pistettä ja joukkueessa EDM");
        for (Player player : stats.matches(m2)) {
            System.out.println(player);
        }
        
        System.out.println("Jompikumpi edellisistä");
        for (Player player : stats.matches(m3)) {
            System.out.println(player);
        }
        
        System.out.println("Ei ole yli viittä maalia tai syöttöä joukkueessa PHI");
        for (Player player : stats.matches(m4)) {
            System.out.println(player);
        }

// VANHAN MALLIN KYSELYT

//        Matcher mJ = new PlaysIn("PHX");
//
//        Matcher m = new And(new HasAtLeast(10, "goals"),
//                new HasAtLeast(10, "assists"),
//                new PlaysIn("PHI"));
//
//        Matcher m2 = new Or(new HasAtLeast(30, "goals"),
//                new HasAtLeast(30, "assists"));
//
//        Matcher m3 = new Not(new HasAtLeast(5, "goals"),
//                new HasAtLeast(5, "assists"));
//
//        Matcher m4 = new And(m3, new HasAtLeast(1, "goals"),
//                new HasAtLeast(1, "assists"), mJ);
//
//        Matcher m5 = new And(new HasFewerThan(5, "goals"), mJ);
//        System.out.println("Ainakin 10 maalia ja syöttöä, joukkueessa PHI");
//        System.out.println("Joko 30 maalia tai syöttöä");
//        for (Player player : stats.matches(m2)) {
//            System.out.println(player);
//        }
//
//        System.out.println("Ei ole yli viittä maalia tai syöttöä");
//        for (Player player : stats.matches(m3)) {
//            System.out.println(player);
//        }
//
//        System.out.println("Ei ole yli viittä maalia tai syöttöä, muttei myöskään nollaa kumpaakaan, ja pelaa PHX");
//        for (Player player : stats.matches(m4)) {
//            System.out.println(player);
//        }
//
//        System.out.println("Alle 5 maalia PHX");
//        for (Player player : stats.matches(m5)) {
//            System.out.println(player);
//        }
    }
}
