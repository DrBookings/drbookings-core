package com.github.drbookings;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.drbookings.io.AbstractReadFileTask2;

public abstract class SynchronousReadFileTask extends AbstractReadFileTask2 {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SynchronousReadFileTask.class);

    public SynchronousReadFileTask(final Path file) {
	super(file);
    }

    @Override
    public void run() {
	super.run();
    }

}
