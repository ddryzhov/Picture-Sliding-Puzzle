package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@EntityScan("sk.tuke.gamestudio.entity")
public class GameStudioServer {
    public static void main(String[] args) {
        SpringApplication.run(GameStudioServer.class, args);
    }

    @Bean
    public ScoreInterface scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public CommInterface commInterface(){
        return new CommServiceJPA();
    }

    @Bean
    public RatingInterface ratingInterface(){
        return new RatingServiceJPA();
    }
}