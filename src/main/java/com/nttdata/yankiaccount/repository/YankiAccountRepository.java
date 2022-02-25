package com.nttdata.yankiaccount.repository;

import com.nttdata.yankiaccount.entity.YankiAccount;
import com.nttdata.yankiaccount.model.YankiPurse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface YankiAccountRepository extends ReactiveMongoRepository<YankiAccount, String> {

    Mono<YankiAccount> findByNumberCelphone(String numberCelphone);

    Mono<YankiPurse> findByNumberAccount(String numberAccount);
}
