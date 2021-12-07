package com.github.drbookings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstractByOriginFilter<T extends OriginProvider> implements ByOriginFilter {

    private final Collection<T> elements;

    public AbstractByOriginFilter(final Collection<? extends T> elements) {
	this.elements = new ArrayList<>(elements);

    }

    public AbstractByOriginFilter() {
	this.elements = new ArrayList<>();

    }

    public Collection<T> getAirbnbElements() {
	return elements.stream().filter(AIRBNB_FILTER).collect(Collectors.toList());
    }

    public Collection<T> getAllElements() {
	return getAllElements(false);
    }

    public Collection<T> getAllElements(final boolean cheat) {
	if (cheat)
	    return elements.stream().filter(CHEAT_FILTER_BOOKING).collect(Collectors.toList());
	return elements;
    }

    public Collection<T> getBookingElements() {
	return elements.stream().filter(BOOKING_FILTER).collect(Collectors.toList());
    }

    public Collection<T> getByOrigin(final BookingOrigin origin) {
	return elements.stream().filter(b -> origin.equals(b.getBookingOrigin())).collect(Collectors.toList());
    }

    public Collection<T> getByOrigin(final String originName) {
	return getByOriginName(originName);
    }

    public Collection<T> getByOriginName(final String name) {
	return elements.stream().filter(b -> name.equalsIgnoreCase(b.getBookingOrigin().getName()))
		.collect(Collectors.toList());
    }

    public Map<BookingOrigin, Collection<T>> getMap() {
	final Map<BookingOrigin, Collection<T>> result = new LinkedHashMap<>();
	for (final T be : elements) {
	    final Collection<T> value = result.computeIfAbsent(be.getBookingOrigin(), k -> new ArrayList<>());
	    value.add(be);
	}
	return result;
    }

    public Collection<T> getOtherElements() {
	return elements.stream().filter(OTHER_FILTER).collect(Collectors.toList());
    }

    public boolean isEmpty() {
	return elements.isEmpty();
    }

    public void add(final T bbb) {
	this.elements.add(bbb);

    }

    @Override
    public String toString() {
	final Map<BookingOrigin, Collection<T>> map = getMap();
	if (map.isEmpty())
	    return "BO: <empty>";
	final String s = "BO:" + map.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().size())
		.collect(Collectors.joining("\n"));
	return s;
    }

}
