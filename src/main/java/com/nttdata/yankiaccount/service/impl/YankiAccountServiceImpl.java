package com.nttdata.yankiaccount.service.impl;

import com.nttdata.yankiaccount.config.WebClientConfig;
import com.nttdata.yankiaccount.entity.YankiAccount;
import com.nttdata.yankiaccount.model.YankiPurse;
import com.nttdata.yankiaccount.repository.YankiAccountRepository;
import com.nttdata.yankiaccount.service.YankiAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Random;

@Service
public class YankiAccountServiceImpl implements YankiAccountService {

    @Autowired
    YankiAccountRepository yankiAccountRepository;
    private final WebClientConfig webClientConfig = new WebClientConfig();

    @Override
    public Flux<YankiAccount> findAll() {
        return yankiAccountRepository.findAll();
    }

    @Override
    public Mono<YankiAccount> save(YankiAccount entity) {
        this.saveYankiPurse(entity.getNumberAccount()).subscribe();
        return yankiAccountRepository.save(entity);
    }

    @Override
    public Mono<YankiAccount> update(YankiAccount entity, String id) {

        return yankiAccountRepository.findById(id)
                .flatMap(origin -> {
                    origin.setEmail(entity.getEmail()==null ? origin.getEmail() : entity.getEmail());
                    origin.setNumberCelphone(entity.getNumberCelphone()==null ? origin.getNumberCelphone() : entity.getNumberCelphone());
                    origin.setImei(entity.getImei()==null ? origin.getImei() : entity.getImei());
                    origin.setTypeAccount(entity.getTypeAccount()==null ? origin.getTypeAccount() : entity.getTypeAccount());
                    origin.setNumberAccount(entity.getNumberAccount()==null ? origin.getNumberAccount() : entity.getNumberAccount());
                    return yankiAccountRepository.save(origin);
                }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
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
    public Mono<YankiAccount> saveYankiAccount(YankiAccount entity) {
        entity.setStatus(true);
        entity.setCreatedAt(new Date());
        entity.setUpdateAt(new Date());

        //Tipo y número de cuenta
        int rand = this.rand();
        String nAcount = String.valueOf(rand);
        entity.setTypeAccount("yanki purse");
        entity.setNumberAccount(nAcount);
        entity.setNumberAccountPurse(nAcount);

        return yankiAccountRepository.findByNumberCelphone(entity.getNumberCelphone())
                .switchIfEmpty(this.save(entity));
    }

    @Override
    public Mono<YankiPurse> saveYankiPurse(String numberAccount) {
        YankiPurse yankiPurse = new YankiPurse();
        yankiPurse.setNumberAccount(numberAccount);
        yankiPurse.setBalance(0.00);
        yankiPurse.setCreatedAt(new Date());
        yankiPurse.setStatus(true);
        return webClientConfig.saveYankiPurse(yankiPurse);
    }

    @Override
    public Mono<YankiAccount> addBankAccount(String numberCelphone, YankiAccount entity) {
        Mono<YankiAccount> yankiAccount = yankiAccountRepository.findByNumberCelphone(numberCelphone);
        return yankiAccount
                .flatMap(y -> {
                    //Actualizar datos de la cuenta
                    y.setTypeAccount("cuenta bancaria");
                    y.setNumberAccount(entity.getNumberAccount());
                    y.setUpdateAt(new Date());

                    //Actualizar monedero
                    Mono<YankiPurse>yankiPurse = webClientConfig.getYankiPurseByNumberAccount(y.getNumberAccountPurse());
                    yankiPurse
                            .flatMap(yp -> {
                                yp.setStatus(false);
                                return webClientConfig.deleteYankiPurseByid(yp.getId());
                            });

                    return yankiAccountRepository.save(y);
                });
    }

    @Override
    public Mono<YankiAccount> findByNumberCelphone(String numberCelphone) {
        return yankiAccountRepository.findByNumberCelphone(numberCelphone);
    }

    public int rand(){
        //int start = 100;
        //int end = 2147483647;
        int random = new Random().nextInt(2147483647);
        //int result = start+(random*(end-start));
        return random;
    }
}