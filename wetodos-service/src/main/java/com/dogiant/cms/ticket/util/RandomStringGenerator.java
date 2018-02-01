package com.dogiant.cms.ticket.util;

public interface RandomStringGenerator {

	/**
	 * @return the minimum length as an int guaranteed by this generator.
	 */
	int getMinLength();

	/**
	 * @return the maximum length as an int guaranteed by this generator.
	 */
	int getMaxLength();

	/**
	 * @return the new random string
	 */
	String getNewString();

	byte[] getNewStringAsBytes();
}
