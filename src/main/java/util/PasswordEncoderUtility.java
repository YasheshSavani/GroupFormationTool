package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtility {

	public static void main(String[] args) {
		String encodedPassword = "$2a$10$NQJKFGeMsB7x1wsL3v6TVu4e3a3S0wI9iepIKtTprFknU2QP244Va";
		String formpswrd = "admin19";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		System.out.println(passwordEncoder.matches(formpswrd, encodedPassword));
	}
	
}
