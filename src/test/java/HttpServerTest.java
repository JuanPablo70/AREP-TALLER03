import edu.eci.arep.app.HttpServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HttpServerTest {

    @Test
    public void shouldSearchMovie() {
        try {
            String movieJson = HttpServer.searchMovie("300");
            String movie = "{\"Title\":\"300\",\"Year\":\"2006\",\"Rated\":\"R\",\"Released\":\"09 Mar 2007\",\"Runtime\":\"117 min\",\"Genre\":\"Action, Drama\",\"Director\":\"Zack Snyder\",\"Writer\":\"Zack Snyder, Kurt Johnstad, Michael B. Gordon\",\"Actors\":\"Gerard Butler, Lena Headey, David Wenham\",\"Plot\":\"King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C.\",\"Language\":\"English\",\"Country\":\"United States, Canada, Bulgaria\",\"Awards\":\"19 wins & 57 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMjc4OTc0ODgwNV5BMl5BanBnXkFtZTcwNjM1ODE0MQ@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.6/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"61%\"},{\"Source\":\"Metacritic\",\"Value\":\"52/100\"}],\"Metascore\":\"52\",\"imdbRating\":\"7.6\",\"imdbVotes\":\"826,361\",\"imdbID\":\"tt0416449\",\"Type\":\"movie\",\"DVD\":\"31 Jul 2007\",\"BoxOffice\":\"$210,629,101\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
            Assert.assertEquals(movie, movieJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldFindMovieInCache() {
        try {
            String movieJson = HttpServer.searchMovie("300");
            Assert.assertEquals(HttpServer.getCache().get("300"), movieJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
