/* (C) 2023 */
package io.github.springboottemplate.controllers;

import io.github.springboottemplate.entities.EchoHistory;
import io.github.springboottemplate.services.EchoService;
import java.time.Instant;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/echo")
public class EchoApi {

    @Autowired
    protected EchoService echoService;

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public Flux<EchoHistory> history() {
        return echoService.list();
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> hello() {
        return echoService
            .recordHistory(
                EchoHistory
                    .builder()
                    .id(UUID.randomUUID())
                    .message("Hello")
                    .timestamp(Instant.now())
                    .build()
            )
            .then(Mono.just("Hello"));
    }

    @GetMapping("echo/{str}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> echo(@PathVariable String str) {
        return echoService
            .recordHistory(
                EchoHistory
                    .builder()
                    .id(UUID.randomUUID())
                    .message("Echo: " + str)
                    .timestamp(Instant.now())
                    .build()
            )
            .then(Mono.just("Echo: " + str));
    }

    @GetMapping("echoquery")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> echoQuery(@RequestParam("name") String name) {
        return this.echoService.recordHistory(
                EchoHistory
                    .builder()
                    .id(UUID.randomUUID())
                    .message("Hello, " + name)
                    .timestamp(Instant.now())
                    .build()
            )
            .then(Mono.just("Hello, " + name));
    }
}
