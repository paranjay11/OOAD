package parkingLotDesign;

import parkingLotDesign.Constants.ParkingSpotType;

public interface Account {
	void setVehicleId(String vehicleId);
	void setAddress(String address);
	String getVehicleId();
	void setParkingSpotType(ParkingSpotType handicapped);
	ParkingSpotType getParkingSpotType();
	Long getTicketId();
	void setTicketId(Long ticketId);
	void setPaymentMethod(PaymentMethod paymentMethod);
	PaymentMethod getPaymentMethod();
}
