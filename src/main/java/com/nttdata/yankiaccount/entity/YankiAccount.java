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
    private String typeDoc;
    private String numberDoc;
    private String numberCelphone;
    private String imei;
    private String email;
    private String typeAccount;
    private String numberAccount;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private Boolean status;
}
