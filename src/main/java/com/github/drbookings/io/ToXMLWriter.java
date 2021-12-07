package com.github.drbookings.io;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.github.drbookings.ser.DataStoreCoreSer2;
import com.github.drbookings.ser.MarshallListener;

public class ToXMLWriter {

    public void write(final DataStoreCoreSer2 data, final Path outPath) throws IOException {
	try {
	    final JAXBContext jc = JAXBContext.newInstance(DataStoreCoreSer2.class);
	    final Marshaller jaxbMarshaller = jc.createMarshaller();
	    jaxbMarshaller.setListener(new MarshallListener());
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.marshal(data, outPath.toFile());
	} catch (final JAXBException e) {
	    throw new IOException(e);
	}
    }
}
