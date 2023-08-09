package board.model.vo;

public class CommentVo {
//	NUMBER	CIDX	No	"SEMI"."CIDX_SEQUENCE"."NEXTVAL"	1	번호
//	NUMBER	IDX	No		2	게시글번호
//	VARCHAR2(4000 BYTE)	CONTENT	No		3	내용
//	TIMESTAMP(6)	WDATE	No	"SYSTIMESTAMP	"	4	작성일
//	NUMBER	depth	No	"1	"	5	답글의깊이
//	NUMBER	STEP	No	"1	"	6	원본글기준 답글순서
//	VARCHAR2(10 BYTE)	ID	No		7	아이디,UNIQUE
    private int cidx;      // 번호 (CIDX_SEQUENCE.NEXTVAL)
    private int ccidx;
    public int getCcidx() {
		return ccidx;
	}

	public CommentVo() {
		super();
	}

	public void setCcidx(int ccidx) {
		this.ccidx = ccidx;
	}
	private int idx;       // 게시글번호
    private String content; // 내용
    private String wdate; // 작성일
    private int depth;      // 답글의 깊이 (1)
    private int step;       // 원본글 기준 답글 순서 (1)
    private String id;      // 아이디, UNIQUE
    private String writer;
    
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public CommentVo(int cidx, int idx, String content, String wdate, int depth, int step, String id) {
		super();
		this.cidx = cidx;
		this.idx = idx;
		this.content = content;
		this.wdate = wdate;
		this.depth = depth;
		this.step = step;
		this.id = id;
	}

	@Override
	public String toString() {
		return "CommentVo [cidx=" + cidx + ", idx=" + idx + ", content=" + content + ", wdate=" + wdate + ", depth="
				+ depth + ", step=" + step + ", id=" + id + "]";
	}
	
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public int getdepth() {
		return depth;
	}
	public void setdepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
    
}
