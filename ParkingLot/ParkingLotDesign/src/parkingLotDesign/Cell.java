package parkingLotDesign;

import parkingLotDesign.Constants.ParkingSpotType;
import parkingLotDesign.Constants.ParkingStatus;

public class Cell{
	private Long id;
	// private String vehicleId;
	private ParkingStatus parkingStatus;
	private ParkingSpotType parkingSpotType;

	private static Long count=0L;
	public Cell(){
		this.id=++count;
	}

	// public String getVehicleId(){
	// 	return this.vehicleId;
	// }

	public Long getId(){
		return id;
	}
	public ParkingSpotType getParkingSpotType(){
		return parkingSpotType;
	}

	public ParkingStatus getParkingStatus(){
		return parkingStatus;
	}

	public void setParkingStatus(ParkingStatus parkingStatus){
		this.parkingStatus=parkingStatus;
	}

	public void setParkingSpotType(ParkingSpotType parkingSpotType){
		this.parkingSpotType=parkingSpotType;
	}

	// public void setVehicleId(String id){
	// 	this.vehicleId;
	// }
}
