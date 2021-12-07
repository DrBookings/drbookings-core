package com.github.drbookings;

import java.time.LocalDate;

public interface EntityProvider {

    CleaningBeanSer2 getCleaningForRoomAndDate(String roomName, LocalDate date);

    BookingEntryPair2 getBookingForRoomAndDate(String roomName, LocalDate date);

}
