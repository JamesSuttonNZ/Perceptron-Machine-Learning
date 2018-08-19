package part3;

public class Instance {

	private String category;
	private boolean[][] image;
	private String pCategory;
	
	public Instance(String category, boolean[][] image){
		this.category = category;
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean[][] getImage() {
		return image;
	}

	public void setImage(boolean[][] image) {
		this.image = image;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	
}
