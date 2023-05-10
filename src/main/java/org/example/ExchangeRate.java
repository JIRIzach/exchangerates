package org.example;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity //entity - java class mapped to a db table
@Table(name="exchange_rates")
public class ExchangeRate {
//update mysql.User set Password=PASSWORD("root123") where User="root";
    // Fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="shortName")
    private String shortName;

    @Column(name="validFrom")
    private String validFrom;

    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;

    @Column(name="move")
    private double move;

    @Column(name="amount")
    private int amount;

    @Column(name="valBuy")
    private double valBuy;

    @Column(name="valSell")
    private double valSell;

    @Column(name="valMid")
    private double valMid;

    @Column(name="currBuy")
    private double currBuy;

    @Column(name="currSell")
    private double currSell;

    @Column(name="currMid")
    private double currMid;

    @Column(name="version")
    private int version;

    @Column(name="cnbMid")
    private double cnbMid;

    @Column(name="ecbMid")
    private double ecbMid;

}

