package com.github.drbookings;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;

public interface PaymentsProviderBean extends IDed {

    StringProperty getExpectedExpression();

    void setExpectedExpression(String expression);

    List<Payment> getPayments();

    ListProperty<Payment> paymentsProperty();

}
