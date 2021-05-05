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
		for(Map.Entry<String,Orders> item : orders.entrySet()) {
			
			String payId=payGateway.pay(payMethod, item.getValue().getProduct().getPrice(), item.getValue().getProduct().getProductId());
			
			item.getValue().setStatus(orderStatus.PROCESSING);
			
			Shipment temp=new Shipment(this.cartId,item.getValue(),payId);
			temp.updatePosition("seller's Address");
			res.put(temp.getShipmentId(), temp);	
		}
		return res;
	} 
}
