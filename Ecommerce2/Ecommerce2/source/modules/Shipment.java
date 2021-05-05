package modules;

import java.util.ArrayList;

public class Shipment {
	public String shipmentId;
	public Orders order;
	public ArrayList<String> position;
	public String paymentId;
	
	public Shipment(Integer userId, Orders order,String paymentId) {
		super();
		this.shipmentId = String.valueOf(userId)+paymentId;
		this.order = order;
		//this.position = position;
		this.paymentId=paymentId;
	}
	public String getShipmentId() {
		return shipmentId;
	}
	
	public Orders getOrder() {
		return order;
	}
	public ArrayList<String> getPosition() {
		return position;
	}
	
	public void updatePosition(String str) {
		position.add(str);
	}
	public String getPaymentId() {
		return paymentId;
	}
	
	
}
