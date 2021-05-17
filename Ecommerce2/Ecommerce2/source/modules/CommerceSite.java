package modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import modules.Constants.productCategory;
//https://www.geeksforgeeks.org/reference-variable-in-java/
public class CommerceSite {
	
	public HashMap<productCategory,ArrayList<Product>> productsInCategory;
	public HashMap<String,ArrayList<Product>> productsByNames;
	public HashMap<Integer,Users> users;
	public HashMap<String,Product> prods;
	
	private static CommerceSite commerceSiteInstance=null;
	
	private CommerceSite() {
		this.productsByNames=new HashMap<String,ArrayList<Product>>();
		this.productsInCategory = new HashMap<productCategory,ArrayList<Product>>();
		this.prods=new HashMap<String,Product>();
		this.users=new HashMap<Integer,Users>();
	}
	
	// thread safe singleton pattern with double-check
	public static CommerceSite getSiteInstance() {
		if(commerceSiteInstance==null) {// for the first time it will be slow otherwise other threads
			                            // will jump over this condition and get the instance.
			synchronized(CommerceSite.class) {// synchronized over the class object
				if(commerceSiteInstance==null) {
					commerceSiteInstance=new CommerceSite();
				}
			}
		}
		return commerceSiteInstance;
	}

	public ArrayList<Product> searchByCategory(productCategory val) {
		// TODO Auto-generated method stub
		
		return productsInCategory.get(val);
	}

	public ArrayList<Product> searchByName(String val) {
		// TODO Auto-generated method stub
		return productsByNames.get(val);
	}
	
	public Users addUser(Integer userId,String name,String address,Integer number) {
//		users.put(user.getUserId(),user);
		Users user=new Users(userId,name,address,number);
		users.put(userId, user);
		return user;
	}
	
	public Product getProduct(String prodId,Integer quant) {
	
		Product k=prods.get(prodId);
		if(k.getQuantity()>=quant) {
			return k;
		}
		else {
			return null;
		}
	}
	
	public Product getProduct(String prodId) {
		
		return prods.get(prodId);
	}
	
	synchronized public boolean addProduct(String prodId,String name,String desc,String seller,Integer quantity,Integer price,productCategory category) {
		if(prods.containsKey(prodId)==true) {
			System.out.println("This product"+ prodId +" is already present in the site");
			return false;
		}
		else {
			Product prod=new Product(prodId,name,desc,seller,quantity,price,category);
			prods.put(prodId,prod);
			if(productsInCategory.containsKey(category)==true) {
				productsInCategory.get(category).add(prod);
			}
			else {
				ArrayList<Product> items = new ArrayList<Product>();
				items.add(prod);
				productsInCategory.put(category,items);
			}
			
			if(productsByNames.containsKey(name)==true) {
				productsByNames.get(name).add(prod);
			}
			else {
				ArrayList<Product> items = new ArrayList<Product>();
				items.add(prod);
				productsByNames.put(prodId,items);
			}
			return true;
			
		}
		
	}
	
	public boolean addToCartPossible() {
		return false;
	}
	
	
	synchronized public void processShipment(HashMap<String,Shipment> shipments) {
		for(Map.Entry<String, Shipment> k:shipments.entrySet()) {
			Orders ord = k.getValue().getOrder();
			Product prod = ord.getProduct();
			Integer quant = ord.getQuantity();
			String name = prod.getName();
			String prodId = prod.getProductId();
			
			for(Map.Entry<String, ArrayList<Product>> item : productsByNames.entrySet()) {
				for(Product p:item.getValue()) {
					if(p.getProductId()==prodId) {
						Integer origQuant = p.getQuantity();
						p.setQuantity(origQuant - quant);
					}
				}
			}
		}
	}

	
	
	
	
	

}
