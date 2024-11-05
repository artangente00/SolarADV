package com.PlayGroundAdv.Solar.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

@Slf4j
@Service
public class PasswordEncoderImpl implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return generateStrongPasswordHash(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return validatePassword(rawPassword.toString(), encodedPassword);
	}

	public String generateStrongPasswordHash(String password) {
		char[] chars = password.toCharArray();
		byte[] salt;

		try {
			salt = getSalt();
			PBEKeySpec spec = new PBEKeySpec(chars, salt, 2000, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return toHex(salt) + ":" + toHex(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error("Exception: " + e.getClass() + ", cause: " + e.getCause() + ", message:" + e.getMessage());

			return null;
		}
	}

	private byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	public boolean validatePassword(String originalPassword, String storedPassword) {
		String[] parts = storedPassword.split(":");
		byte[] salt = fromHex(parts[0]);
		byte[] hash = fromHex(parts[1]);

		try {
			PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, 2000, hash.length * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] testHash = skf.generateSecret(spec).getEncoded();

			int diff = hash.length ^ testHash.length;
			for (int i = 0; i < hash.length && i < testHash.length; i++) {
				diff |= hash[i] ^ testHash[i];
			}
			return diff == 0;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error("Exception: " + e.getClass() + ", cause: " + e.getCause() + ", message:" + e.getMessage());

			return false;
		}
	}

	private static byte[] fromHex(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
