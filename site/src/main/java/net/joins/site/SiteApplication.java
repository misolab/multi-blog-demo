package net.joins.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "net.joins")
public class SiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }

    // @Autowired
    // PostRepository repository;

    // @Bean
    // public CommandLineRunner insertDB() {
    //     return args -> {
    //         List<Post> list = IntStream.range(1, 50)
    //                             .mapToObj(i -> Post.builder()
    //                                             .link("post.html")
    //                                             .title("제목 - " + i)
    //                                             .subtitle("subtitle - " + i)
    //                                             .writter("misolab")
    //                                             .content("Wellcome!!<img class=\"img-fluid\" src=\"images/post-bg.jpg\">")
    //                                             .bgImage("images/contact-bg.jpg")                        
    //                                             .build())
    //                             .collect(Collectors.toList());
    //         repository.saveAll(list);
    //     };
    // }
}
