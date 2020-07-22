package parkingLotDesign;

import parkingLotDesign.Constants.ParkingSpotType;

public class Customer implements Account{
	private Address address;
	private ParkingSpotType parkingSpotType;
	private String vehicleId;
	private Long ticketId;
	private PaymentMethod paymentMethod;

	@Override
	public void setVehicleId(String vehicleId) {
		this.vehicleId=vehicleId;
	}

	@Override
	public void setAddress(String addressString) {
		Address adr=new Address(addressString);
		this.address=adr;
	}

	@Override
	public String getVehicleId() {
		return this.vehicleId;
	}

	@Override
	public ParkingSpotType getParkingSpotType() {
		return parkingSpotType;
	}
	
	@Override
	public void setParkingSpotType(ParkingSpotType parkingSpotType) {
		this.parkingSpotType = parkingSpotType;
	}

	@Override
	public void setTicketId(Long ticketId) {
		this.ticketId=ticketId;
		
	}

	@Override
	public Long getTicketId() {
		return this.ticketId;
	}
	
	@Override
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
	
	
}

