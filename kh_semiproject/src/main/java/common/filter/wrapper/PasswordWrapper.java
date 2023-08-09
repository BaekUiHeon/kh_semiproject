package common.filter.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordWrapper extends HttpServletRequestWrapper{

	public PasswordWrapper(HttpServletRequest request) { //기본생성자없음.
		super(request);
	}
	@Override
	public String getParameter(String name) {
		if(name!=null && name.equals("pwd"))
			name=getSha512(super.getParameter(name));
		else
			name=super.getParameter(name);
		System.out.println("암호화된 크기"+name.length()+","+name);
		return name;
	}
	
	private String getSha512(String pwd) {
		String result=null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] pwdBytes = pwd.getBytes(Charset.forName("UTF-8"));
			md.update(pwdBytes);
			result=Base64.getEncoder().encodeToString(pwdBytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
