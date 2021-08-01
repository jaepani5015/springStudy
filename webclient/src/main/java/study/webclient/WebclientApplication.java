package study.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebclientApplication {

    @Autowired
    private WebClient webClient;

    public static void main(String[] args) {
        SpringApplication.run(WebclientApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
//            Mono<GetData[]> result = getAllData();
//            System.out.println("result = " + result);
//
//            GetData getData = Arrays.stream(result.block()).findFirst().get();
//            System.out.println("getData = " + getData);
//
//            Stream<String> stringStream = Arrays.stream(result.block()).map(GetData::getName);
//            System.out.println("stringStream = " + stringStream.findFirst());

            Mono<ResponseEntity<GetData>> data = getData("1");
            HttpStatus statusCode = data.block().getStatusCode();
            GetData body = data.block().getBody();
            System.out.println("statusCode = " + statusCode);
            System.out.println("body = " + body);
        };
    }

    public Mono<GetData[]> getAllData() {
        return webClient.mutate()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build()
                .get()
                .uri("/comments")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GetData[].class);
    }

    public Mono<ResponseEntity<GetData>> getData(String id) {
        return webClient.mutate()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build()
                .get()
                .uri("/comments/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(GetData.class);
    }

}
