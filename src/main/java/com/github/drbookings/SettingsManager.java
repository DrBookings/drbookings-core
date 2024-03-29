/*
 * DrBookings
 * Copyright (C) 2016 - 2018 Alexander Kerner
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package com.github.drbookings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import net.sf.kerner.utils.objects.Objects;

public class SettingsManager {

	private static class InstanceHolder {

		private static final SettingsManager instance = new SettingsManager();
	}

	private static final String cleaningFeesKey = "cleaningFeeKey";
	private static final String cleaningPlanLookBehindKey = "cleaningPlanLookBehindKey";
	private static final String completePaymentKey = "completePaymentKey";
	public static final float DEFAULT_CLEANING_FEES = 0;
	public static final float DEFAULT_CLEANING_COSTS = DEFAULT_CLEANING_FEES;
	public static final int DEFAULT_CLEANINGPLAN_LOOKBEHIND_DAYS = 7;
	public static final boolean DEFAULT_COMPLETE_PAYMENT = false;
	public static final float DEFAULT_EARNINGS_PAYOUT_PERCENT = 1;
	public static final String DEFAULT_FILE_NAME = "booking-data.xml";
	public static final short DEFAULT_FIX_COSTS_PAYMENT_DAY = 1;
	// public static final boolean DEFAULT_STATISTICS_HIDE_CLEANING = false;
	public static final int DEFAULT_NUMBER_OF_ROOMS = 2;
	public static final String DEFAULT_ROOM_NAME_PREFIX = "F";
	public static final float DEFAULT_SERVICE_FEES = 0;
	public static final float DEFAULT_SERVICE_FEES_PERCENT = 0;
	public static final int DEFAULT_UPCOMING_LOOK_AHEAD_DAYS = 3;
	private static final String fileKey = "data";
	private static final Logger logger = LoggerFactory.getLogger(SettingsManager.class);
	public static final int MAX_NUMBER_OF_ROOMS = 10;
	private static final String roomNameMapKey = "roomNameMapKey";
	private static final String roomNamePrefixKey = "roomPrefixKey";
	private static final String upcomingLookAheadKey = "upcomingLookAheadKey";
	public static final String CONFIG_FILE_PATH = "drbookings.properties";

	public static SettingsManager getInstance() {

		return InstanceHolder.instance;
	}

	static Properties readProperties() {

		final Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(System.getProperty("user.home") + File.separator + CONFIG_FILE_PATH);
			prop.load(input);
			if(logger.isInfoEnabled()) {
				logger.info("Read settings from " + System.getProperty("user.home") + File.separator + CONFIG_FILE_PATH);
			}
			return prop;
		} catch(final Exception ex) {
			if(logger.isDebugEnabled()) {
				logger.debug("Failed to read properties file " + ex.toString());
			}
		} finally {
			IOUtils.closeQuietly(input);
		}
		return null;
	}

	private final FloatProperty additionalCosts = new SimpleFloatProperty();
	private final FloatProperty cleaningCosts = new SimpleFloatProperty(DEFAULT_CLEANING_COSTS);
	private final FloatProperty cleaningFees = new SimpleFloatProperty(DEFAULT_CLEANING_FEES);
	private final BooleanProperty completePayment = new SimpleBooleanProperty(DEFAULT_COMPLETE_PAYMENT);
	private final FloatProperty earningsPayoutPercent = new SimpleFloatProperty(DEFAULT_EARNINGS_PAYOUT_PERCENT);
	private final IntegerProperty fixCostsPaymentDay = new SimpleIntegerProperty(DEFAULT_FIX_COSTS_PAYMENT_DAY);
	// private final BooleanProperty hideCleaningStatistics = new
	// SimpleBooleanProperty(DEFAULT_STATISTICS_HIDE_CLEANING);
	private final IntegerProperty numberOfRooms = new SimpleIntegerProperty(DEFAULT_NUMBER_OF_ROOMS);
	private final Preferences prefs = Preferences.userNodeForPackage(getClass());
	private final FloatProperty referenceColdRentLongTerm = new SimpleFloatProperty();
	private final FloatProperty serviceFees = new SimpleFloatProperty(DEFAULT_SERVICE_FEES);
	private final FloatProperty serviceFeesPercent = new SimpleFloatProperty(DEFAULT_SERVICE_FEES_PERCENT);
	private final BooleanProperty showNetEarnings = new SimpleBooleanProperty();
	private final FloatProperty workHoursPerMonth = new SimpleFloatProperty();
	public static final String SHOW_NET_EARNINGS_KEY = "show-net-earnings";

	private SettingsManager() {

		completePayment.set(prefs.getBoolean(completePaymentKey, false));
		completePayment.addListener((ChangeListener<Boolean>)(observable, oldValue, newValue) -> prefs.putBoolean(completePaymentKey, newValue));
		cleaningFeesProperty().set(prefs.getFloat(cleaningFeesKey, DEFAULT_CLEANING_FEES));
		cleaningFeesProperty().addListener((ChangeListener<Number>)(observable, oldValue, newValue) -> {
			prefs.putFloat(cleaningFeesKey, newValue.floatValue());
		});
	}

	public final FloatProperty additionalCostsProperty() {

		return additionalCosts;
	}

	public final FloatProperty cleaningCostsProperty() {

		return cleaningCosts;
	}

	public FloatProperty cleaningFeesProperty() {

		return cleaningFees;
	}

	public BooleanProperty completePaymentProperty() {

		return completePayment;
	}

	public final FloatProperty earningsPayoutPercentProperty() {

		return earningsPayoutPercent;
	}

	public final IntegerProperty fixCostsPaymentDayProperty() {

		return fixCostsPaymentDay;
	}

	/**
	 * Returns the fix-costs per month per room
	 *
	 * @return fix-costs per month per room
	 *
	 * @see DrBookingsApplication#ADDITIONAL_COSTS_KEY
	 */
	public final float getAdditionalCosts() {

		return additionalCostsProperty().get();
	}

	public final float getCleaningCosts() {

		return cleaningCostsProperty().get();
	}

	public float getCleaningFees() {

		return cleaningFeesProperty().get();
	}

	public int getCleaningPlanLookBehind() {

		return prefs.getInt(SettingsManager.cleaningPlanLookBehindKey, DEFAULT_CLEANINGPLAN_LOOKBEHIND_DAYS);
	}

	public File getDataFile() {

		final String fileString = prefs.get(fileKey, System.getProperty("user.home") + File.separator + DEFAULT_FILE_NAME);
		return new File(fileString);
	}

	public final float getEarningsPayoutPercent() {

		return earningsPayoutPercentProperty().get();
	}

	public final int getFixCostsPaymentDay() {

		return fixCostsPaymentDayProperty().get();
	}

	public final int getNumberOfRooms() {

		return numberOfRoomsProperty().get();
	}

	public final float getReferenceColdRentLongTerm() {

		return referenceColdRentLongTermProperty().get();
	}

	public Map<String, String> getRoomNameMappings() throws ClassNotFoundException, IOException {

		final byte[] bytes = prefs.getByteArray(roomNameMapKey, null);
		if(bytes == null)
			return new LinkedHashMap<>();
		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>)Objects.fromBytes(bytes);
		return map;
	}

	/**
	 * Returns a room-name-prefix. This should be changeable and independent from
	 * the Model. Do not use in Model.
	 *
	 * @return the room-name-prefix
	 */
	public String getRoomNamePrefix() {

		return prefs.get(roomNamePrefixKey, DEFAULT_ROOM_NAME_PREFIX);
	}

	public final float getServiceFees() {

		return serviceFeesProperty().get();
	}

	public final float getServiceFeesPercent() {

		return serviceFeesPercentProperty().get();
	}

	public int getUpcomingLookAhead() {

		return prefs.getInt(SettingsManager.upcomingLookAheadKey, DEFAULT_UPCOMING_LOOK_AHEAD_DAYS);
	}

	public final float getWorkHoursPerMonth() {

		return workHoursPerMonthProperty().get();
	}

	public boolean isCompletePayment() {

		return completePaymentProperty().get();
	}

	public final boolean isShowNetEarnings() {

		return showNetEarningsProperty().get();
	}

	public final IntegerProperty numberOfRoomsProperty() {

		return numberOfRooms;
	}

	public void putRoomNameMapping(final Map.Entry<String, String> pair) throws ClassNotFoundException, IOException {

		final Map<String, String> map = getRoomNameMappings();
		map.put(pair.getKey(), pair.getValue());
		setRoomNameMapping(map);
	}

	public void putRoomNameMapping(final String vendorName, final String ourName) throws ClassNotFoundException, IOException {

		putRoomNameMapping(new ImmutablePair<>(vendorName, ourName));
	}

	public final FloatProperty referenceColdRentLongTermProperty() {

		return referenceColdRentLongTerm;
	}

	protected void saveAllToFile() {

		final Properties prop = readProperties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(System.getProperty("user.home") + File.separator + CONFIG_FILE_PATH);
			prop.setProperty(SHOW_NET_EARNINGS_KEY, Boolean.toString(isShowNetEarnings()));
			prop.store(output, "Dr.Bookings Preferences");
			if(logger.isInfoEnabled()) {
				logger.info("Properties file updated");
			}
		} catch(final IOException io) {
			if(logger.isErrorEnabled()) {
				logger.error("Failed to write properties file " + io.toString());
			}
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	public void saveToFile() {

		saveAllToFile();
	}

	public final FloatProperty serviceFeesPercentProperty() {

		return serviceFeesPercent;
	}

	public final FloatProperty serviceFeesProperty() {

		return serviceFees;
	}

	public final void setAdditionalCosts(final float additionalCosts) {

		additionalCostsProperty().set(additionalCosts);
	}

	public final void setCleaningCosts(final float cleaningCosts) {

		cleaningCostsProperty().set(cleaningCosts);
	}

	public void setCleaningFees(final float fees) {

		cleaningFeesProperty().set(fees);
	}

	public void setCleaningPlanLookBehind(final int value) {

		prefs.putInt(cleaningPlanLookBehindKey, value);
	}

	public void setCompletePayment(final boolean completePayment) {

		completePaymentProperty().set(completePayment);
	}

	public void setDataFile(final File file) {

		prefs.put(fileKey, file.getAbsolutePath());
	}

	public final void setEarningsPayoutPercent(final float earningsPayoutPercent) {

		earningsPayoutPercentProperty().set(earningsPayoutPercent);
	}

	public final void setFixCostsPaymentDay(final int fixCostsPaymentDay) {

		fixCostsPaymentDayProperty().set(fixCostsPaymentDay);
	}

	public final void setNumberOfRooms(int numberOfRooms) {

		if(numberOfRooms < DEFAULT_NUMBER_OF_ROOMS) {
			numberOfRooms = DEFAULT_NUMBER_OF_ROOMS;
		} else if(numberOfRooms > MAX_NUMBER_OF_ROOMS) {
			numberOfRooms = MAX_NUMBER_OF_ROOMS;
		}
		numberOfRoomsProperty().set(numberOfRooms);
	}

	public final void setReferenceColdRentLongTerm(final float referenceColdRentLongTerm) {

		referenceColdRentLongTermProperty().set(referenceColdRentLongTerm);
	}

	public void setRoomNameMapping(final Map<String, String> map) throws IOException {

		prefs.putByteArray(roomNameMapKey, Objects.toBytes(map));
	}

	public void setRoomNamePrefix(final String prefix) {

		prefs.put(roomNamePrefixKey, prefix);
	}

	public final void setServiceFees(final float serviceFees) {

		serviceFeesProperty().set(serviceFees);
	}

	public final void setServiceFeesPercent(final float serviceFeesPercent) {

		serviceFeesPercentProperty().set(serviceFeesPercent);
	}

	public final void setShowNetEarnings(final boolean showNetEarnings) {

		showNetEarningsProperty().set(showNetEarnings);
	}

	public void setUpcomingLookAhead(final int value) {

		prefs.putInt(upcomingLookAheadKey, value);
	}

	public final void setWorkHoursPerMonth(final float workHoursPerMonth) {

		workHoursPerMonthProperty().set(workHoursPerMonth);
	}

	public final BooleanProperty showNetEarningsProperty() {

		return showNetEarnings;
	}

	public final FloatProperty workHoursPerMonthProperty() {

		return workHoursPerMonth;
	}
	// public final BooleanProperty hideCleaningStatisticsProperty() {
	// return this.hideCleaningStatistics;
	// }
	// public final boolean isHideCleaningStatistics() {
	// return this.hideCleaningStatisticsProperty().get();
	// }
	// public final void setHideCleaningStatistics(final boolean
	// hideCleaningStatistics) {
	// this.hideCleaningStatisticsProperty().set(hideCleaningStatistics);
	// }
}
