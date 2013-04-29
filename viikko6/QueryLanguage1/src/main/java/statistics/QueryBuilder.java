/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

/**
 *
 * @author lassi
 */
class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {
        this.matcher = new Matcher();
    }

    public QueryBuilder(Matcher matcher) {
        this.matcher = matcher;
    }

    public QueryBuilder playsIn(String team) {
        return new QueryBuilder(new PlaysIn(matcher, team));
    }

    public QueryBuilder hasAtLeast(int number, String things) {
        return new QueryBuilder(new HasAtLeast(matcher, number, things));
    }

    public QueryBuilder hasFewerThan(int number, String things) {
        return new QueryBuilder(new HasFewerThan(matcher, number, things));
    }

    Matcher build() {
        return matcher;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        return new QueryBuilder(new Or(m1, m2));
    }
}
