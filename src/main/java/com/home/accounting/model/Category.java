package com.home.accounting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "ofExpense", nullable = false)
    private boolean ofExpense;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_entity_id")
    private User user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Subcategory> subcategories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Income> incomes;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<RegularExpense> regularExpenses;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<RegularIncome> regularIncomes;
}
