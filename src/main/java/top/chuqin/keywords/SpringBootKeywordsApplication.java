package top.chuqin.keywords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"top.chuqin.keywords.repository"})
public class SpringBootKeywordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKeywordsApplication.class, args);
    }
}
