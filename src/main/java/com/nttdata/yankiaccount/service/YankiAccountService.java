package com.nttdata.yankiaccount.service;

import com.nttdata.yankiaccount.entity.YankiAccount;
import com.nttdata.yankiaccount.model.YankiPurse;
import reactor.core.publisher.Mono;

public interface YankiAccountService extends CrudService<YankiAccount, String>{

    //Grabar Yanki Account
    Mono<YankiAccount> saveYankiAccount(YankiAccount entity);

    //Grabar Yanki Purse
    Mono<YankiPurse> saveYankiPurse(String numberAccount);

    //Agregar Cuenta Bancaria
    Mono<YankiAccount> addBankAccount(String numberCelphone, YankiAccount entity);

    //Buscar por número celular
    Mono<YankiAccount> findByNumberCelphone(String numberCelphone);
}
