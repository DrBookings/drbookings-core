package com.github.drbookings;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

public interface Payment {

    MonetaryAmount getAmount();

    LocalDate getDate();

}