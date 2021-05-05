package modules;

import java.util.ArrayList;

import modules.Constants.orderPair;
import modules.Constants.productCategory;
import modules.Constants.queryType;

public class TestingSite {
	public static void main(String[] args) {
		CommerceSite site=CommerceSite.getSiteInstance();
		Constants constants=new Constants();
		Users user1=site.addUser(11,"rick","peeche waai gali 230",889900);
		site.addProduct("SGB1", "Stained Glass Bird 1", "Stained glass bird - red colord bird", "Niche Krafts", 21, 2150, productCategory.DECOR);
		site.addProduct("SGB2", "Stained Glass Bird 2", "Stained glass bird - blue colord bird", "Grasshopper", 288, 2170, productCategory.DECOR);
		site.addProduct("SGB3", "Stained Glass Bird 1", "Stained glass bird - green colord bird", "Niche Krafts", 20, 7150, productCategory.DECOR);
		site.addProduct("SGB4", "Stained Glass Bird 2", "Stained glass bird - yellow colord bird", "Grasshopper", 72, 4150, productCategory.DECOR);
		site.addProduct("SGB5", "Stained Glass Bird 1", "Stained glass bird - pink colord bird", "Niche Krafts", 20, 750, productCategory.DECOR);
		site.addProduct("SGB6", "Stained Glass Bird 16", "Stained glass bird - brown colord bird", "Niche Krafts", 2, 9050, productCategory.DECOR);
		
		site.addProduct("WB1", "Water Bowl 1", "Kitchenware water bowl - glass", "Niche Krafts", 6, 250, productCategory.KITCHENWARE);
		site.addProduct("BOOK1", "Chetan Bhagat 1", "Chetan bhagat's first book", "CHetan BHagat", 1, 650, productCategory.BOOKS);
		site.addProduct("LIGHT1", "Bulb 1", "Philips Bulb", "Philips", 10, 50, productCategory.ELECTRONICS);
		
//		ArrayList<Product> choice1 = site.searchByCategory(productCategory.DECOR);
		
		
		System.out.println("hellor there");
		user1.searchItemsByCategory(productCategory.DECOR);
		
		user1.addToCart(constants.new orderPair("SGB4",12));
		user1.addToCart(constants.new orderPair("SGB6",2));
		
		user1.checkOut();
//		user1.addToCart(constants.new orderPair("SGB4",1));
		
		
	}

}
