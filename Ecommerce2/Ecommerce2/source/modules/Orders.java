package modules;

import modules.Constants.orderStatus;

public class Orders {
	

	public String orderId;
	public Product product;
	public Integer quantity;
	public Boolean isPaymentDone;
	public orderStatus status;
	
	public Orders(Product product, Integer quantity, Boolean isPaymentDone,Integer userId) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.isPaymentDone = isPaymentDone;
		this.status=orderStatus.DEFAULT;
		this.orderId=String.valueOf(userId)+product.getProductId();
		
	}

	public String getOrderId() {
		return orderId;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsPaymentDone() {
		return isPaymentDone;
	}

	public void setIsPaymentDone(Boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	public orderStatus getStatus() {
		return status;
	}

	public void setStatus(orderStatus status) {
		this.status = status;
	}
	
	

}
