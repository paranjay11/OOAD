package modules;

import modules.Constants.paymentMethod;

public class PaymentGateway {
	public String paymentId;
	public Integer price;
	public paymentMethod payMethod;
	public PaymentGateway() {
		super();
//		this.paymentId = String.valueOf(userId);
	}
	
	public String pay(paymentMethod payMethod,Integer price,String productId) {
		this.paymentId=String.valueOf(payMethod)+productId+String.valueOf(price);
		return this.paymentId;
	}

	
}
