package modules;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import modules.Constants.orderPair;
import modules.Constants.productCategory;
import modules.Constants.queryType;

public class TestingSite {
	
	static ExecutorService ThreadPool = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) throws InterruptedException {
		CommerceSite site=CommerceSite.getSiteInstance();
		Constants constants=new Constants();
		Users newUsers[]=new Users[3];
		
		Thread t[] =new Thread[3];
		
		t[0]= new Thread(()->{
			newUsers[0]=site.addUser(13,"rishi","aamne saamne waali gali",98923783);
			System.out.println(newUsers[0].toString());
		});
	
		t[1] = new Thread(()->{
			newUsers[1]=site.addUser(15,"cuteness","aage aane waali gali se left",843763);
			System.out.println(newUsers[1].toString());
		});
		
		t[2]=new Thread(()->{
			newUsers[2]=site.addUser(33, "ShyanSharan", "MP ke saamne waali kothi", 774782);
			System.out.println(newUsers[2].toString());
		});
		

		for(int i=0;i<3;i++) {
			t[i].start();
		}
		
		
		for(int i=0;i<3;i++) {
			t[i].join();
		}
		
		
		
		
//		for(int i=0;i<3;i++) {
//			System.out.println(newUsers[i].toString());
//		}
//		
		
		Thread prodT[]=new Thread[6];
		prodT[0]=new Thread(()->{
			site.addProduct("SGB1", "Stained Glass Bird 1", "Stained glass bird - red colord bird", "Niche Krafts", 21, 2150, productCategory.DECOR);
			//System.out.println(site.getProduct("SGB1").toString());
			printProduct(site.getProduct("SGB1"));
			
			site.addProduct("SGB2", "Stained Glass Bird 2", "Stained glass bird - blue colord bird", "Grasshopper", 288, 2170, productCategory.DECOR);
			printProduct(site.getProduct("SGB2"));
		});
		
		prodT[1]=new Thread(()->{
			site.addProduct("SGB3", "Stained Glass Bird 1", "Stained glass bird - green colord bird", "Niche Krafts", 20, 7150, productCategory.DECOR);
			printProduct(site.getProduct("SGB3"));
			site.addProduct("SGB4", "Stained Glass Bird 2", "Stained glass bird - yellow colord bird", "Grasshopper", 72, 4150, productCategory.DECOR);
			printProduct(site.getProduct("SGB4"));
		});
		
		prodT[2]=new Thread(()->{
			site.addProduct("SGB5", "Stained Glass Bird 1", "Stained glass bird - pink colord bird", "Niche Krafts", 20, 750, productCategory.DECOR);
			printProduct(site.getProduct("SGB5"));
			site.addProduct("SGB6", "Stained Glass Bird 16", "Stained glass bird - brown colord bird", "Niche Krafts", 2, 9050, productCategory.DECOR);
			printProduct(site.getProduct("SGB6"));
		});
		
		prodT[5]=new Thread(()->{
			site.addProduct("WB1", "Water Bowl 1", "Kitchenware water bowl - glass", "Niche Krafts", 6, 250, productCategory.KITCHENWARE);
			System.out.println(site.getProduct("WB1").toString());
		});
		
		prodT[4]=new Thread(()->{
			if(site.addProduct("BOOK1", "Chetan Bhagat 1", "Chetan bhagat's first book", "CHetan BHagat", 1, 650, productCategory.BOOKS)==true)
				printProduct(site.getProduct("BOOK1"));
		});
		
		prodT[3]=new Thread(()->{
			if(site.addProduct("BOOK1", "Chetan Bhagat 1", "Chetan bhagat's first book", "CHetan BHagat", 1, 650, productCategory.BOOKS)==true)
				printProduct(site.getProduct("BOOK1"));
			site.addProduct("LIGHT1", "Bulb 1", "Philips Bulb", "Philips", 10, 50, productCategory.ELECTRONICS);
			printProduct(site.getProduct("LIGHT1"));
		});
		
		for(int i=0;i<6;i++) {
			prodT[i].start();
		}
		
		
		for(int i=0;i<6;i++) {
			prodT[i].join();
		}
//		Users user1=site.addUser(11,"rick","peeche waai gali 230",889900);
//		site.addProduct("SGB1", "Stained Glass Bird 1", "Stained glass bird - red colord bird", "Niche Krafts", 21, 2150, productCategory.DECOR);
//		site.addProduct("SGB2", "Stained Glass Bird 2", "Stained glass bird - blue colord bird", "Grasshopper", 288, 2170, productCategory.DECOR);
//		site.addProduct("SGB3", "Stained Glass Bird 1", "Stained glass bird - green colord bird", "Niche Krafts", 20, 7150, productCategory.DECOR);
//		site.addProduct("SGB4", "Stained Glass Bird 2", "Stained glass bird - yellow colord bird", "Grasshopper", 72, 4150, productCategory.DECOR);
//		site.addProduct("SGB5", "Stained Glass Bird 1", "Stained glass bird - pink colord bird", "Niche Krafts", 20, 750, productCategory.DECOR);
//		site.addProduct("SGB6", "Stained Glass Bird 16", "Stained glass bird - brown colord bird", "Niche Krafts", 2, 9050, productCategory.DECOR);
//		
//		site.addProduct("WB1", "Water Bowl 1", "Kitchenware water bowl - glass", "Niche Krafts", 6, 250, productCategory.KITCHENWARE);
//		site.addProduct("BOOK1", "Chetan Bhagat 1", "Chetan bhagat's first book", "CHetan BHagat", 1, 650, productCategory.BOOKS);
//		site.addProduct("LIGHT1", "Bulb 1", "Philips Bulb", "Philips", 10, 50, productCategory.ELECTRONICS);
//		
//		ArrayList<Product> choice1 = site.searchByCategory(productCategory.DECOR);
//		for(Product item:choice1) {
//			System.out.println(item.toString());
//		}
//		
//		
//		System.out.println("hellor there");
//		user1.searchItemsByCategory(productCategory.DECOR);
//		
//		user1.addToCart(constants.new orderPair("SGB4",12));
//		user1.addToCart(constants.new orderPair("SGB6",2));
//		user1.checkOut();
////		user1.addToCart(constants.new orderPair("SGB4",1));
//		
		Thread checkOutThreads[]=new Thread[3];
		
		checkOutThreads[0]= new Thread(()->{
			newUsers[0].searchItemsByCategory(productCategory.BOOKS);
			newUsers[0].addToCart(constants.new orderPair("BOOK1",1));
			newUsers[0].checkOut();
		});
		
		checkOutThreads[1]=new Thread(()->{
			newUsers[1].searchItemsByCategory(productCategory.DECOR);
			newUsers[1].addToCart(constants.new orderPair("SGB4",3));
			newUsers[1].addToCart(constants.new orderPair("SGB3",600));
			newUsers[1].addToCart(constants.new orderPair("LIGHT1",2));		
			newUsers[1].checkOut();
		});
		
		checkOutThreads[2]=new Thread(()->{
			newUsers[2].searchItemsByCategory(productCategory.DECOR);
			newUsers[2].addToCart(constants.new orderPair("WB1",34));
			newUsers[2].addToCart(constants.new orderPair("SGB1",2));
			newUsers[2].checkOut();
		});
		
		
		for(int i=0;i<3;i++) {
			checkOutThreads[i].start();
		}
		
		
		for(int i=0;i<3;i++) {
			checkOutThreads[i].join();
		}
		
	}
	
	static void printProduct(Product pr) {
		if(pr==null) {
			System.out.println("cannot print this");
		}
		else {
			System.out.println(pr.toString());
		}
	}

}
