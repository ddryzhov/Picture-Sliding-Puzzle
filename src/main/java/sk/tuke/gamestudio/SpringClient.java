package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.PuzzleGame;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PuzzleGame puzzleGame) {
        return args -> {
            puzzleGame.play();
        };
    }

    @Bean
    public int myNumber() {
        return 3;
    }

    @Bean
    public ScoreInterface scoreInterface(){
        return new ScoreServiceRestClient();
        //return new ScoreServiceJPA();
    }

    @Bean
    public CommInterface commInterface(){
        //return new CommServiceJPA();
        return new CommentServiceRestClient();
    }

    @Bean
    public RatingInterface ratingInterface(){
        return new RatingServiceRestClient();
        //return new RatingServiceJPA();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}