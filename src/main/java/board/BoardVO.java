package board;

public class BoardVO {
	private int bid;
	private String mid;
	private String msg;
	
	private int favcnt;
	private int rcnt;
	private int cnt; // 메인 페이지에 보여줄 글의 개수 [JAVA] ★★★
	private int bcnt;// 보드 태이블의 글 전체개수 
	private int mb; 	//내글보기에서 글을 저장 할 때 사용
	private int my; 	//내글보기에서 글을 저장 할 때 사용
	
	
	
	public int getMy() {
		return my;
	}
	public void setMy(int my) {
		this.my = my;
	}
	public int getMb() {
		return mb;
	}
	public void setMb(int mb) {
		this.mb = mb;
	}
	public int getBcnt() {
		return bcnt;
	}
	public void setBcnt(int bcnt) {
		this.bcnt = bcnt;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getFavcnt() {
		return favcnt;
	}
	public void setFavcnt(int favcnt) {
		this.favcnt = favcnt;
	}
	public int getRcnt() {
		return rcnt;
	}
	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "BoardVO [bid=" + bid + ", mid=" + mid + ", msg=" + msg + ", favcnt=" + favcnt + ", rcnt=" + rcnt
				+ ", cnt=" + cnt + "]";
	}
}
