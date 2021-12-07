package com.github.drbookings.io;

import java.nio.file.Path;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.BookingFactory;
import com.github.drbookings.Booking;
import com.github.drbookings.CleaningBeanSer2;
import com.github.drbookings.CleaningBeanSer2Factory;
import com.github.drbookings.RoomFactory;
import com.github.drbookings.ser.BookingBeanSer;
import com.github.drbookings.ser.BookingBeanSer2;
import com.github.drbookings.ser.CleaningBeanSer;
import com.github.drbookings.ser.ExpenseSer;

public abstract class AbstractReadFileTask2 implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AbstractReadFileTask2.class);

    private final Path file;

    private final BookingFactory bookingFactory;

    public AbstractReadFileTask2(final Path file) {
	this.file = file;
	bookingFactory = new BookingFactory(RoomFactory.getInstance());

    }

    @Override
    public void run() {
	if (logger.isDebugEnabled()) {
	    logger.debug("Reading '" + file + "'");
	}
	try {
	    new FromXMLReader2(getFileVersion()).setListener(new Unmarshaller.Listener() {
		@Override
		public void afterUnmarshal(final Object target, final Object parent) {

		    super.afterUnmarshal(target, parent);
		    if (target instanceof BookingBeanSer) {
			readBooking((BookingBeanSer) target);
		    } else if (target instanceof BookingBeanSer2) {
			readBooking((BookingBeanSer2) target);
		    } else if (target instanceof CleaningBeanSer2) {
			readCleaning((CleaningBeanSer2) target);
		    } else if (target instanceof CleaningBeanSer) {
			readCleaning(new CleaningBeanSer2Factory().build((CleaningBeanSer) target));
		    } else if (target instanceof ExpenseSer) {
			readExpense((ExpenseSer) target);
		    } else {
			// if (logger.isWarnEnabled()) {
			// logger.warn("Unsupported type " + target);
			// }
		    }
		}
	    }).readFromFile(file);
	} catch (final JAXBException e) {
	    if (logger.isErrorEnabled()) {
		logger.error(e.getLocalizedMessage(), e);
	    }
	}
    }

    protected abstract int getFileVersion();

    protected void readCleaning(final CleaningBeanSer2 c) {
	handleNewCleaning(c);
    }

    protected abstract void handleNewCleaning(CleaningBeanSer2 c);

    protected abstract void handleNewExpense(ExpenseSer c);

    protected void readBooking(final BookingBeanSer newBooking) {
	final Booking bbb = bookingFactory.build(newBooking);
	handleNewBooking(bbb);
    }

    protected void readBooking(final BookingBeanSer2 newBooking) {
	final Booking bbb = bookingFactory.build(newBooking);
	handleNewBooking(bbb);
    }

    protected void readExpense(final ExpenseSer target) {
	handleNewExpense(target);
    }

    protected abstract void handleNewBooking(Booking bbb);
}
