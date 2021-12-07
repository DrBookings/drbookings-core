package com.github.drbookings;

import javafx.collections.ListChangeListener;

public interface UIBaseData<T>  {
    
    public void addListener(ListChangeListener<? super T> listener);

    public void removeListener(ListChangeListener<? super T> listener);

}
