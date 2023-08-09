package board.model.vo;

public class WriterVo {
//	VARCHAR2(10 BYTE)	ID	No		1	아이디
//	VARCHAR2(50 BYTE)	WRITER	No		2	작성자명,UNIQUE
//	VARCHAR2(20 BYTE)	PASSWORD	No		3	비밀번호
//	VARCHAR2(250 BYTE)	EMAIL_ADDRESS	No		4	이메일주소,UNIQUE
	
	private String id;           // 아이디
	private String writer;       // 작성자명, UNIQUE
	private String password;     // 비밀번호
	private String emailAddress; // 이메일주소, UNIQUE	
	
	
	public WriterVo(String id, String writer, String password, String emailAddress) {
		super();
		this.id = id;
		this.writer = writer;
		this.password = password;
		this.emailAddress = emailAddress;
	}


	@Override
	public String toString() {
		return "WriterVo [id=" + id + ", writer=" + writer + ", password=" + password + ", emailAddress=" + emailAddress
				+ "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
