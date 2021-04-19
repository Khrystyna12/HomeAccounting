package com.home.accounting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Currency;

@Entity
@Table(name = "RegularIncome")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RegularIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "day", nullable = false)
    private int day;

    @Column(name = "amount", nullable = false)
    private Currency amount;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
}
