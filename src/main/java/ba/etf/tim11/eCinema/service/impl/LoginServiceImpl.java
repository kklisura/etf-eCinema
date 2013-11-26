package ba.etf.tim11.eCinema.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import ba.etf.tim11.eCinema.models.User;
import ba.etf.tim11.eCinema.service.LoginService;


public class LoginServiceImpl implements LoginService 
{
	private static LoginServiceImpl instance = null;
	
	
	private LoginServiceImpl()
	{
		
	}
	
	public static LoginService getInstance() 
	{
		if (instance == null) {
			instance = new LoginServiceImpl();
		}
		return instance;
	}
	
	
	@Override
	public boolean validateSession(String username, String key) 
	{
		// TODO(kklisura): Need to implement this.
		return false;
	}

	// http://stackoverflow.com/questions/4895523/java-string-to-sha1
	@Override
	public String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}

	@Override
	public User getCurrentUser() 
	{
		// TODO(kklisura): Return logged in user.
		return null;
	}

}
