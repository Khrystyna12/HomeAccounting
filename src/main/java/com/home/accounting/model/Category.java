package com.home.accounting.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    @NonNull
    private String title;

    @Column(name = "ofExpense", nullable = false)
    @NonNull
    private boolean ofExpense;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_entity_id")
    @NonNull
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
