package com.dogiant.cms.ticket.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoderMD5 implements PasswordEncoder {

	public String encode(String password) {

		return DigestUtils.md5Hex(password);

	}

}