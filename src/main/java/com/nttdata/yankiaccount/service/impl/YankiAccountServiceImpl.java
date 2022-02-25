package com.nttdata.yankiaccount.service.impl;

import com.nttdata.yankiaccount.entity.YankiAccount;
import com.nttdata.yankiaccount.repository.YankiAccountRepository;
import com.nttdata.yankiaccount.service.YankiAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class YankiAccountServiceImpl implements YankiAccountService {

    @Autowired
    YankiAccountRepository yankiAccountRepository;

    @Override
    public Flux<YankiAccount> findAll() {
        return yankiAccountRepository.findAll();
    }

    @Override
    public Mono<YankiAccount> save(YankiAccount entity) {
        return yankiAccountRepository.findByNumberCelphone(entity.getNumberCelphone())
                .switchIfEmpty(yankiAccountRepository.save(entity));
    }

    @Override
    public Mono<YankiAccount> update(YankiAccount entity, String id) {

        return yankiAccountRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)))
                .flatMap(origin ->
                {
                    origin.setEmail(entity.getEmail()==null ? origin.getEmail() : entity.getEmail());
                    origin.setNumberCelphone(entity.getNumberCelphone()==null ? origin.getNumberCelphone() : entity.getNumberCelphone());
                    origin.setImei(entity.getImei()==null ? origin.getImei() : entity.getImei());
                    origin.setTypeAccount(entity.getTypeAccount()==null ? origin.getTypeAccount() : entity.getTypeAccount());
                    origin.setNumberAccount(entity.getNumberAccount()==null ? origin.getNumberAccount() : entity.getNumberAccount());
                    return yankiAccountRepository.save(origin);
                });
    }

    @Override
    public Mono<YankiAccount> deleteById(String id) {
        return yankiAccountRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)))
                .flatMap(t -> {
                    t.setStatus(false);
                    return yankiAccountRepository.save(t);
                });
    }

    @Override
    public Mono<YankiAccount> findByNumberCelphone(String numberCelphone) {
        return yankiAccountRepository.findByNumberCelphone(numberCelphone);
    }
}
