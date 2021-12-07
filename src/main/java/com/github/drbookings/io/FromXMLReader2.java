package com.github.drbookings.io;

import java.io.File;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.drbookings.ser.DataStoreCoreSer;
import com.github.drbookings.ser.DataStoreCoreSer2;
import com.github.drbookings.ser.MarshallListener;

public class FromXMLReader2 {

    public static final int DEFAULT_FILE_VERSION = 1;

    private static final Logger logger = LoggerFactory.getLogger(FromXMLReader2.class);
    private Unmarshaller.Listener listener;

    private final int fileVersion;

    public FromXMLReader2(final int fileVersion) {
	super();
	this.fileVersion = fileVersion;
    }

    public Unmarshaller.Listener getListener() {

	return listener;
    }

    public Object readFromFile(final Path file) throws JAXBException {

	return readFromFile(file.toFile());
    }

    public Object readFromFile(final File file) throws JAXBException {

	final Class<?> clazz = getVersionClass();
	final JAXBContext jc = JAXBContext.newInstance(clazz);
	final Unmarshaller jaxbMarshaller = jc.createUnmarshaller();
	if (listener != null) {
	    jaxbMarshaller.setListener(listener);
	}
	final Object ds = jaxbMarshaller.unmarshal(file);
	// if (logger.isDebugEnabled()) {
	// logger.debug("Loaded data from " + file);
	// }
	return ds;
    }

    private Class<?> getVersionClass() {
	switch (fileVersion) {
	case 1:
	    return DataStoreCoreSer.class;
	case 2:
	    return DataStoreCoreSer2.class;
	default:
	    throw new IllegalArgumentException("Unknown file version " + fileVersion);
	}
    }

    public FromXMLReader2 setListener(final Unmarshaller.Listener listener) {

	this.listener = listener;
	return this;
    }

    public void writeToFile(final DataStoreCoreSer ds, final File file) throws Exception {
	final Class<?> clazz = getVersionClass();
	final JAXBContext jc = JAXBContext.newInstance(clazz);
	final Marshaller jaxbMarshaller = jc.createMarshaller();
	jaxbMarshaller.setListener(new MarshallListener());
	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	jaxbMarshaller.marshal(ds, file);
	if (logger.isInfoEnabled()) {
	    logger.info("Wrote to " + file);
	}
    }
}
