package com.github.drbookings3;

public class Named {

    private final String name;

    public Named(final String name) {
	super();
	this.name = name;
    }

    public String getName() {
	return name;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Named))
	    return false;
	final Named other = (Named) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = (prime * result) + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public String toString() {
	return "Named [name=" + name + "]";
    }

}
