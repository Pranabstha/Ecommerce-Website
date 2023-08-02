package model;

//creating the product model with necessary properties and methods
public class Products {
	//properties for products
	private int productID;
	private String productName;
	private String productCategory;
	private float productUnitPrice;
	private String productImageURL;
	
	//getters and setters for products
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public float getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(float productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public String getProductImageURL() {
		return productImageURL;
	}
	public void setProductImageURL(String productImageURL) {
		this.productImageURL = productImageURL;
	}
	
	
}
