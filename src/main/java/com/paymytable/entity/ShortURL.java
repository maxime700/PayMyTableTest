package com.paymytable.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "SHORT_URL")
public class ShortURL {

    /**
     * The identifier of a @{@link ShortURL} and primary key
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * The URL value
     */
    private String url;

}
