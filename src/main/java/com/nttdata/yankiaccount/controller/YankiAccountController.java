package com.nttdata.yankiaccount.controller;

import com.nttdata.yankiaccount.entity.YankiAccount;
import com.nttdata.yankiaccount.service.YankiAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/yanki-account")
public class YankiAccountController {

    @Autowired
    YankiAccountService yankiAccountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<YankiAccount> getYankiAccounts(){
        System.out.println("Listar cuentas de Yanki");
        return yankiAccountService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<YankiAccount> saveYankiAccount(@RequestBody YankiAccount yankiAccount){
        System.out.println("Guardar cuenta en Yanki");
        return yankiAccountService.save(yankiAccount);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<YankiAccount> saveYankiAccount(@RequestBody YankiAccount yankiAccount, @PathVariable String id){
        System.out.println("Actualizar cuenta en Yanki");
        return yankiAccountService.update(yankiAccount, id);
    }

    @GetMapping("/number/{numberCelphone}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<YankiAccount> getYankiAccountByNumberCelphone(@PathVariable String numberCelphone){
        return yankiAccountService.findByNumberCelphone(numberCelphone);
    }
}
