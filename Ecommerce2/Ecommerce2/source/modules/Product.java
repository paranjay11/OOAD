package modules;

import java.util.ArrayList;

import modules.Constants.productCategory;

public class Product {
	
	

	public String productId;
	public String name;
	public String desc;
	public productCategory category;
	public String seller;
	public Integer quantity;
	public Integer price;
	public ArrayList<Reviews> reviews;
	
	public Product(String prodId,String name, String desc, String seller, Integer quantity,Integer price,productCategory category) {
		super();
		this.productId=prodId;
		this.name = name;
		this.desc = desc;
		this.seller = seller;
		this.quantity = quantity;
		this.price=price;
		this.category=category;
	}
	

	public productCategory getCategory() {
		return category;
	}


	public String getProductId() {
		return productId;
	}

	public Integer getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getSeller() {
		return seller;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ArrayList<Reviews> getReviews() {
		return reviews;
	}

	public void addReviews(Reviews review) {
		this.reviews.add(review);
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", desc=" + desc + ", category=" + category
				+ ", seller=" + seller + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
