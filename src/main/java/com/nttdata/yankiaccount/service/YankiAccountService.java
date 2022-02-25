package com.nttdata.yankiaccount.service;

import com.nttdata.yankiaccount.entity.YankiAccount;
import reactor.core.publisher.Mono;

public interface YankiAccountService extends CrudService<YankiAccount, String>{

    Mono<YankiAccount> findByNumberCelphone(String numberCelphone);
}
