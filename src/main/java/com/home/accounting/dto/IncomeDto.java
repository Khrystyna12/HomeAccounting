package com.home.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDto {
    private long id;

    @NotBlank(message = "The 'date' cannot be empty")
    private String date;

    @Pattern(regexp = "[\\p{Digit}]+\\.[\\p{Digit}]{1,2}", message = "Must be numeric in format #.##")
    @NotBlank(message = "The 'amount' cannot be empty")
    private String amount;

    @NotNull
    private String comment;

    @NotNull
    private String account;

    @NotNull
    private String category;
}
