package com.home.accounting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name = "Account")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "amount", nullable = false)
    private Currency amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_entity_id")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Income> incomes;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<RegularExpense> regularExpenses;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<RegularIncome> regularIncomes;
}
