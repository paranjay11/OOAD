package modules;

public class Constants {
	enum productCategory{
		KITCHENWARE,
		DECOR,
		FURNITURE,
		TOILETRIES,
		ELECTRONICS,
		BOOKS, MISC
	}
	
	enum queryType{
		CATEGORY,
		NAME
	}
	
	enum paymentMethod{
		CREDITCARD,
		BANKTRANSFER
	}
	
	enum userType{
		CUSTOMER,
		SELLER
	}
	
	enum orderStatus{
		SHIPPED,
		PROCESSING,
		RECEIVED,
		CANCELLED, 
		DEFAULT
	}
	
	public class orderPair{
		private String productId;
		private Integer quantity;
		
		
		public orderPair(String productId, Integer quantity) {
			super();
			this.productId = productId;
			this.quantity = quantity;
		}

		public String getProductId() {
			return productId;
		}
		
		public Integer getQuantity() {
			return quantity;
		}
	}
}
