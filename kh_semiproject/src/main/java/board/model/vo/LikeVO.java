package board.model.vo;

public class LikeVO {
//	IDX	NUMBER	No		1	번호
//	ID	VARCHAR2(10 BYTE)	No		2	아이디,UNIQUE
	private int idx; // 번호
	private String id; // 아이디, UNIQUE
	
	public LikeVO() {
		super();
	}
	public LikeVO(int idx, String id) {
		super();
		this.idx = idx;
		this.id = id;
	}
	@Override
	public String toString() {
		return "LikeVO [idx=" + idx + ", id=" + id + "]";
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
