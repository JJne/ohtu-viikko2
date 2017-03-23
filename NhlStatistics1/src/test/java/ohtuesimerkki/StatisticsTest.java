/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jannejau
 */
public class StatisticsTest {
    
     Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    
    public StatisticsTest() {
    }

    @Before
    public void setUp() {
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSearchFindsPlayerWhenExists() {
        Player player = stats.search("Semenko");
        assertEquals("Semenko", player.getName());
    }
    
    @Test
    public void testSearchDoesNotFindPlayerWhenDoesNotExist() {
        Player player = stats.search("Marko");
        assertEquals(null, player);
    }


    @Test
    public void testTeam() {
        List<Player> playersOfTeam = stats.team("EDM");
        assertEquals(3, playersOfTeam.size());
    }
    
    @Test
    public void testTeamWhenTeamDoesNotExist() {
        List<Player> playersOfTeam = stats.team("XXX");
        assertEquals(0, playersOfTeam.size());
    }
  
    @Test
    public void testTopScorers() {
        List<Player> topScorers = stats.topScorers(2);
        String[] trueTopScorers = {"Gretzky", "Lemieux", "Yzerman"};
                
        for (int i=0; i < topScorers.size(); i += 1) {
            assertEquals(trueTopScorers[i], topScorers.get(i).getName());
        }        
    }
    
}
