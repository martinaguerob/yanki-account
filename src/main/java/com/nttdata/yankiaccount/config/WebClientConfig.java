package com.nttdata.yankiaccount.config;

import com.nttdata.yankiaccount.model.BankAccount;
import com.nttdata.yankiaccount.model.MovementBankAccount;
import com.nttdata.yankiaccount.model.YankiPurse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientConfig {

    private final WebClient.Builder webClientBuilder = WebClient.builder();

    public Mono<YankiPurse> deleteYankiPurseByid(@PathVariable String id){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/purse/delete/"+id)
                .retrieve()
                .bodyToMono(YankiPurse.class);
    }

    public Mono<YankiPurse> getYankiPurseByNumberAccount(@PathVariable String numberAccount){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/purse/number/"+numberAccount)
                .retrieve()
                .bodyToMono(YankiPurse.class);
    }

    public Mono<BankAccount> getAccountByNumberAccount(@PathVariable String numberAccount){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/accounts/bank-account/number/"+numberAccount)
                .retrieve()
                .bodyToMono(BankAccount.class);
    }

    public Mono<BankAccount> updateBankAccount(BankAccount bankAccount, String id){
        System.out.println("Se llegó a updateBankAccount");
        return webClientBuilder.build()
                .put()
                .uri("http://localhost:8080/accounts/bank-account/update/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bankAccount))
                .retrieve()
                .bodyToMono(BankAccount.class);
    }

    public Mono<MovementBankAccount> saveMovementBankAccount(MovementBankAccount movementBankAccount){
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/movements/bank-account")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(movementBankAccount))
                .retrieve()
                .bodyToMono(MovementBankAccount.class);
    }
}
