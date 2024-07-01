package com.Spring.EMailSender.Utility;

public class EmailUtility {
	
	public static String getMessage(String name, String host, String token) {
		
			return "Hi "+ name+", \n\n your new account has been created. Please verify your account by clicking the following link \n\n"
					+getVerificationLink(token, host) +"\n\n the support team";
			
	}

	public static String getVerificationLink(String token, String host) {
		
		return host+"/email?token="+token;
	}
	
}
