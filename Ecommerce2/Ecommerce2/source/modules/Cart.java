package modules;

import java.util.HashMap;
import java.util.Map;

import modules.Constants.orderPair;
import modules.Constants.orderStatus;
import modules.Constants.paymentMethod;

public class Cart {
	public Integer cartId;
	public HashMap<String,Orders> orders;
	public PaymentGateway payGateway;
	
	public Cart(Integer userId) {
		super();
		this.cartId = userId;
		this.orders = new HashMap<String,Orders>();
		this.payGateway=new PaymentGateway();
	}
	
	void addOrders(Product product ,Integer quant) {
		Orders temp=new Orders(product,quant,false,this.cartId);
		orders.put(temp.getOrderId(),temp);
	}

	public HashMap<String,Shipment> checkOut(paymentMethod payMethod){
		HashMap<String,Shipment> res=new HashMap<String,Shipment>();
		int sumVal=0;
		for(Map.Entry<String,Orders> item : orders.entrySet()) {
			
			String payId=payGateway.pay(payMethod, item.getValue().getProduct().getPrice(), item.getValue().getProduct().getProductId());
			System.out.println("Payment is being processed for : "+ item.getValue().getProduct().getName()+ " with orderID : "+ item.getValue().getOrderId());
			sumVal+=item.getValue().getProduct().getPrice()*item.getValue().getProduct().getQuantity();
			
			item.getValue().setStatus(orderStatus.PROCESSING);
			System.out.println("Your order is getting ready for being shipped.......");

			Shipment temp=new Shipment(this.cartId,item.getValue(),payId);
			temp.updatePosition("seller's Address");
			res.put(temp.getShipmentId(), temp);	
		}
		
		System.out.println("The Total value of the products : "+sumVal+" for user : "+cartId);
		return res;
	} 
}
