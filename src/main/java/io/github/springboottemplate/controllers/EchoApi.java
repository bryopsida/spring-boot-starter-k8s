/* (C) 2023 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EchoApi {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello!");
    }

    @GetMapping("echo/{str}")
    public Mono<String> echo(@PathVariable String str) {
        return Mono.just("Echo: " + str);
    }

    @GetMapping("echoquery")
    public Mono<String> echoQuery(@RequestParam("name") String name) {
        return Mono.just("Hello, " + name);
    }
}
