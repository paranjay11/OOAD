package parkingLotDesign;

import parkingLotDesign.Constants.ParkingStatus;
import parkingLotDesign.Constants.PaymentStatus;

public class ParkingTicket{
	private static Long countTickets=0L;
	private Long id;
	private ParkingStatus parkingStatus;
	private String  vehicleId;// 
	private Cell cell;
	private Long initTime;
	private PaymentStatus paymentStatus;
	private int parkingFloor;


	public ParkingTicket(ParkingStatus parkingStatus , String vehicleId, Cell cell,Long initTime,int parkingFloor){
		this.id=++countTickets;
		this.parkingStatus=parkingStatus;
		this.vehicleId=vehicleId;
		this.cell=cell;
		this.initTime=initTime;
		this.parkingFloor=parkingFloor;
	}


	public String getVehicleId(){
		return vehicleId;
	}

	public Long getParkingTicketId(){
		return id;
	}

	public ParkingStatus getParkingStatus(){
		return parkingStatus;
	}

	public Cell getCell(){
		return cell;
	}

	public Long getParkingTime(){
		return initTime;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus){
		this.paymentStatus=paymentStatus;
	}


	public int getParkingFloor() {
		return parkingFloor;
	}


	public void setParkingFloor(int parkingFloor) {
		this.parkingFloor = parkingFloor;
	}

}