package utfpr.ppgca.AcademicReport.entities;

public class ArticleByItemType {
	private String itemType;
	private int amount;
	private double percent;
	
	public ArticleByItemType() {
	}

	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	
}
