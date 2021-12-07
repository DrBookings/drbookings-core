package com.github.drbookings.persistence;

import com.github.drbookings.CleaningBeanSer2;
import com.github.drbookings.EntityProvider;

public interface PersistenceService extends EntityProvider {

    CleaningBeanSer2 addCleaning(CleaningBeanSer2 data);

}
