package parkingLotDesign;

import java.util.ArrayList;
import java.util.HashMap;

import parkingLotDesign.Constants.ParkingSpotType;
import parkingLotDesign.Constants.ParkingStatus;
import parkingLotDesign.Constants.PaymentStatus;

public class ParkingLot{
	static private ParkingLot Instance;
	static private ArrayList<ParkingFloor> floors;
	private static HashMap<Long,ParkingTicket> customers;
//	private static ArrayList<Display> floorsDisplay;

	private ParkingLot(){
		floors=new ArrayList<ParkingFloor>();
		customers=new HashMap<Long,ParkingTicket>();
//		floorsDisplay=new ArrayList<Display>();
	}
	
	public static ParkingLot getParkingLotInstance() {
		if(Instance==null) {
			Instance=new ParkingLot();
		}
		return Instance;
	}

	public void addFloor(ParkingFloor parkingFloor){
		floors.add(parkingFloor);
//		floorsDisplay.add(parkingFloor.getDisplay());
	}

	public void removeFloor(ParkingFloor parkingFloor){
		floors.remove(parkingFloor);
	}

	public static boolean isAvailable(ParkingSpotType parkingSpotType){
		for(int i=0;i<floors.size();i++){
			ParkingFloor temp=floors.get(i);
			if(temp.isParkingSpotAvailable(parkingSpotType)) {
				return true;
			}
		}
		return false;
	}

	
	public static Long issueParkingTicket(Account account,Long initTime) {
		Cell cell=null;
		Long ticketId=null;
		ParkingTicket ticket;
		ParkingSpotType parkingSpotType=account.getParkingSpotType();
		int i;
		for(i=0;i<floors.size();i++){
			ParkingFloor temp=floors.get(i);
			if(temp.isParkingSpotAvailable(parkingSpotType)) {
				cell=temp.getCell(parkingSpotType);
				break;
			}
		}
		if(cell!=null) {
			System.out.println("Updating the matrix......");
			((ParkingFloor)floors.get(i)).updateMatrix(cell, ParkingStatus.OCCUPIED);
			ticket=new ParkingTicket(ParkingStatus.OCCUPIED,account.getVehicleId(),cell,initTime,i);
			ticketId=ticket.getParkingTicketId();
			customers.put(ticketId,ticket);
		}
		
		return ticketId;
	}
	
	
	public static boolean CheckOut(Long ticketId, PaymentMethod paymentMethod) {
		System.out.println("Checking out initiated...." );
		ParkingTicket ticket=customers.get(ticketId);
		System.out.println("tickeId = "+ ticketId);
		Cell cell=ticket.getCell();
		boolean ticketStatus=false;
		if(paymentMethod.pay()) {
			System.out.println("Payment received.....");
			((ParkingFloor)floors.get(ticket.getParkingFloor())).updateMatrix(cell, ParkingStatus.FREE);
			ticket.setPaymentStatus(PaymentStatus.FREE);
			ticketStatus = true;
			customers.remove(ticketId);
		}
		
		return ticketStatus;
	}

}