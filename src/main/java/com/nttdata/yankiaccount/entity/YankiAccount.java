package com.nttdata.yankiaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@Document(collection = "yanki-account")
public class YankiAccount {

    @Id
    private String id;
    private String typeDoc;//dni, ce
    private String numberDoc;
    private String numberCelphone;
    private String imei;
    private String email;
    private String typeAccount; //yanki purse, cuenta bancaria
    private String numberAccountPurse; //Guardará la cuenta de yankiPurse
    private String numberAccount; //Sea de banco o yankiPurse
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updateAt;
    private Boolean status;
}
