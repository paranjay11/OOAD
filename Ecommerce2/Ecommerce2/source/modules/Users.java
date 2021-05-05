package modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modules.Constants.orderPair;
import modules.Constants.orderStatus;
import modules.Constants.paymentMethod;
import modules.Constants.productCategory;
import modules.Constants.queryType;

public class Users {
	public Integer userId;
	public String name;
	public String address;
	public Integer number;
	public HashMap<String,Orders> orders;
	public Cart cart;
	public HashMap<String,Shipment> shipments;
	public CommerceSite site;
	public HashMap<String,Product> items;
	public paymentMethod payMethod;
	
	public Users(Integer userId,String name, String address, Integer number) {
		super();
		this.userId=userId;
		this.name = name;
		this.address = address;
		this.number = number;
		this.orders=new HashMap<String,Orders>();
		this.cart=new Cart(userId);
		this.shipments=new HashMap<String,Shipment>();
		this.site=CommerceSite.getSiteInstance();
		this.items=new HashMap<String,Product>();
	}
	
	public void searchItemsByCategory(productCategory val) {
		ArrayList<Product> temp=new ArrayList<Product>();
		temp=site.searchByCategory(val);
		for(Product k:temp) {
			items.put(k.getProductId(), k);
			System.out.println(k.getProductId()+ " >>> "+k.getName()+" , "+ k.getSeller()+" , priced at : "+k.getPrice());
		}		
	}
	
	
	
	public void searchItemsByName(String val) {
		ArrayList<Product> temp=new ArrayList<Product>();
		temp=site.searchByName(val);
		
		for(Product k:temp) {
			items.put(k.getProductId(), k);
			System.out.println(k.getProductId()+ " >>> "+k.getName()+" , "+ k.getSeller()+" , priced at : "+k.getPrice());
		}		
	}
	
	public void addToCart(orderPair product) {
			Product prod=site.getProduct(product.getProductId(),product.getQuantity());
			if(prod!=null)	{
				cart.addOrders(prod,product.getQuantity());
				System.out.println("Product has been added to the cart"+ prod.getProductId());
			}
			else {
				System.out.println("Product could not be added to the cart ");
			}
	}
	
	public void cancelOrder(String shipmentId) {
		if(shipments.containsKey(shipmentId)==true) {
			if(shipments.get(shipmentId).getOrder().getStatus()==orderStatus.PROCESSING) {
				shipments.get(shipmentId).getOrder().setStatus(orderStatus.CANCELLED);
				System.out.println("The order "+ shipments.get(shipmentId).getOrder().getOrderId()+" is Cancelled");
			}
			else {
				System.out.println("The order is shipped already");
			}
		}
	}
	
	public void checkOut() {
		shipments=cart.checkOut(this.payMethod);
		site.processShipment(shipments);
		System.out.println("Thank you for shopping with us");
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public paymentMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(paymentMethod payMethod) {
		this.payMethod = payMethod;
	}
	
	
}
