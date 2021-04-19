package com.home.accounting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subcategory")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.REMOVE)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.REMOVE)
    private List<Income> incomes;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.REMOVE)
    private List<RegularExpense> regularExpenses;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.REMOVE)
    private List<RegularIncome> regularIncomes;
}
