package board.model.vo;

public class boardVo {
//	number	IDX	No	"SEMI"."IDX_SEQUENCE"."NEXTVAL"
//	VARCHAR2(300 BYTE)	SUBJECT	No	
//	VARCHAR2(4000 BYTE)	CONTENT	No	
//	TIMESTAMP(6)	WDATE	No	"SYSTIMESTAMP	"
//	VARCHAR2(10 BYTE)	ID	No	
	private String idx;
    private String subject;
    private String content;
    private String wdate;
    private String id;
    private String writer; //테이블에는 존재하지 않는 컬럼이나 join이 사용되는 함수에서 쓰기위해 추가함.
    
    
	public boardVo() {
		super();
	}

	public boardVo(String idx, String subject, String content, String wdate, String id, String writer) {
		super();
		this.idx = idx;
		this.subject = subject;
		this.content = content;
		this.wdate = wdate;
		this.id = id;
		this.writer = writer;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public boardVo(String idx, String subject, String content, String wdate, String id) {
		super();
		this.idx = idx;
		this.subject = subject;
		this.content = content;
		this.wdate = wdate;
		this.id = id;
	}

	@Override
	public String toString() {
		return "boardVo [idx=" + idx + ", subject=" + subject + ", content=" + content + ", wdate=" + wdate
				+ ", id=" + id + "]";
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
    
}
