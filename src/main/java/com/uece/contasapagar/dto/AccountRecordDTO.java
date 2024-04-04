package com.uece.contasapagar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountRecordDTO (@NotBlank String name, @NotNull BigDecimal value,
                                @NotBlank LocalDate dueDate, @NotNull double interestRate, @NotNull boolean paid) {

}