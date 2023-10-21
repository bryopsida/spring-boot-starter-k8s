/* (C) 2023 */
package io.github.springboottemplate.services;

import io.github.springboottemplate.entities.EchoHistory;
import io.github.springboottemplate.repositories.EchoHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EchoService {

    @Autowired
    private EchoHistoryRepository repository;

    public Flux<EchoHistory> list() {
        return repository.findAll();
    }

    public Mono<EchoHistory> recordHistory(EchoHistory history) {
        return repository.save(history);
    }
}
