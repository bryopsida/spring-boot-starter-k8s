/* (C) 2023 */
package io.github.springboottemplate.controllers;

/* (C) 2023 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/echo")
public class EchoApi {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> hello() {
        return Mono.just("Hello!");
    }

    @GetMapping("echo/{str}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> echo(@PathVariable String str) {
        return Mono.just("Echo: " + str);
    }

    @GetMapping("echoquery")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> echoQuery(@RequestParam("name") String name) {
        return Mono.just("Hello, " + name);
    }
}
