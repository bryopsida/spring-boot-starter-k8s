/* (C) 2023 */
package io.github.springboottemplate.services;

import io.github.springboottemplate.entities.EchoHistory;
import io.github.springboottemplate.repositories.EchoHistoryRepository;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EchoService {

    @Autowired
    private EchoHistoryRepository repository;

    public Flux<EchoHistory> list() {
        return repository.findAll();
    }
}
