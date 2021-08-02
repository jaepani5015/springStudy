package study.webclient;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
@Slf4j
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

//            Mono<ResponseEntity<GetData>> data = getData("1");
//            HttpStatus statusCode = data.block().getStatusCode();
//            GetData body = data.block().getBody();
//            System.out.println("statusCode = " + statusCode);
//            System.out.println("body = " + body);

//            Mono<ResponseEntity<GetData>> getData = getData_baseUrl("1");
//            GetData body = getData.block().getBody();
//            System.out.println("body = " + body.toString());

//            Mono<String> body = httpGet();
//
//            body.subscribe(s -> log.info("subscript 1 :: {}", s.length()));
//            body.subscribe(s -> log.info("subscript 2 :: {}", s.length()));

//            Mono<String> stringMono = httpGet().cache();
//
//            stringMono.subscribe(s -> System.out.println("s = " + s));
//            stringMono.subscribe(s -> System.out.println("s = " + s));
//            stringMono.subscribe(s -> System.out.println("s = " + s));

//            Flux<Integer> flux = Flux.range(1,5).log().cache();

//            Flux<Integer> flux = Flux.range(1,5).log().cache();
//            flux.subscribe(integer -> System.out.println("integer = " + integer));
//            flux.subscribe(integer -> System.out.println("integer = " + integer));

            Mono<GetData> getDataMono = webClientExchange("aaaaa").cache().log();
            getDataMono.subscribe(getData -> System.out.println("getData = " + getData));

        };
    }

    public Mono<GetData> webClientExchange(String id) {
        return webClient.get()
                .uri("/comments/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(GetData.class);
                    } else return Mono.error((Supplier<? extends Throwable>) response.createException());
                });
    }

    public Mono<String> httpGet() {
        return WebClient
                .create()
                .get()
                .uri("http://naver.com")
                .retrieve()
                .bodyToMono(String.class)
                .log();
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

    public Mono<ResponseEntity<GetData>> getData_mutate(String id) {
        return webClient.mutate()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build()
                .get()
                .uri("/comments/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(GetData.class);
    }

    public Mono<ResponseEntity<GetData>> getData_baseUrl(String id) {
        return webClient.get()
                .uri("/comments/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(GetData.class);
    }

}
