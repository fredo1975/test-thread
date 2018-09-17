package fr.bluechipit.thread.lock;

public class StockKey {

	
	public StockKey(String isin, String ccy, String bookRef) {
		super();
		this.isin = isin;
		this.ccy = ccy;
		this.bookRef = bookRef;
	}
	private String isin;
	private String ccy;
	private String bookRef;
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getBookRef() {
		return bookRef;
	}
	public void setBookRef(String bookRef) {
		this.bookRef = bookRef;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookRef == null) ? 0 : bookRef.hashCode());
		result = prime * result + ((ccy == null) ? 0 : ccy.hashCode());
		result = prime * result + ((isin == null) ? 0 : isin.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockKey other = (StockKey) obj;
		if (bookRef == null) {
			if (other.bookRef != null)
				return false;
		} else if (!bookRef.equals(other.bookRef))
			return false;
		if (ccy == null) {
			if (other.ccy != null)
				return false;
		} else if (!ccy.equals(other.ccy))
			return false;
		if (isin == null) {
			if (other.isin != null)
				return false;
		} else if (!isin.equals(other.isin))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StockKey [isin=" + isin + ", ccy=" + ccy + ", bookRef=" + bookRef + "]";
	}
	
}
