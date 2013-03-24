/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Statistics;
import ohtuesimerkki.Reader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lvapaaka
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public StatisticsTest() {
        this.stats = new Statistics(readerStub);
    }

    @Test
    public void objectIsNotNull() {
        assertNotNull(stats);
    }

    @Test
    public void searchFindsTheRightPlayer() {
        assertEquals(stats.search("Semenko").toString(), new Player("Semenko", "EDM", 4, 12).toString());
    }

    @Test
    public void searchReturnsNullIfPLayerNotFound() {
        assertNull(stats.search("nlkjsdfnkjsdfjnk"));
    }

    @Test
    public void teamReturnsTheRightAmountOfPLayers() {
        assertEquals(stats.team("EDM").size(), 3);
    }

    @Test
    public void teamReturnsAnEmptyListForInvalidTeam() {
        assertTrue(stats.team("TPR").isEmpty());
    }

    @Test
    public void teamFindsTheRightFirstPlayerOfATeam() {
        assertEquals(stats.team("DET").get(0).toString(), new Player("Yzerman", "DET", 42, 56).toString());
    }

    @Test
    public void topScorerFindstheBestPlayer() {
        assertEquals(stats.topScorers(1).get(0).toString(), new Player("Gretzky", "EDM", 35, 89).toString());
    }

    @Test
    public void topScorersReturnsAnEmptyListWithZero() {
        assertTrue(stats.topScorers(-5).isEmpty());
    }
}
