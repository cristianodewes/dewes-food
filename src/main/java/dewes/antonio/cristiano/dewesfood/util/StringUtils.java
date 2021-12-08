package dewes.antonio.cristiano.dewesfood.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtils {

	public static boolean isEmpty(String str) {
		
		return (str==null) ? true : str.trim().length()==0;
	}
	
	public static String encrypt(String rawString) {
		if(isEmpty(rawString)) { 
			return null;
		}
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(rawString);
	}
}
