package parkingLotDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parkingLotDesign.Constants.ParkingSpotType;
import parkingLotDesign.Constants.ParkingStatus;

public class ParkingFloor{
	private HashMap<ParkingSpotType,HashMap<Long,Cell>> matrix;
	private int[] capacityOfMatrix;
	private int[] occupancyOfMatrix;
	private int[] freeSpace;
	private Display display;


	public ParkingFloor(){
		this.matrix=new HashMap<ParkingSpotType,HashMap<Long,Cell>>();
		for(ParkingSpotType spotType:ParkingSpotType.values()) {
			matrix.put(spotType, new HashMap<Long,Cell>());
		}
		this.capacityOfMatrix=new int[ParkingSpotType.values().length];
		this.occupancyOfMatrix=new int[ParkingSpotType.values().length];
		this.freeSpace=new int[ParkingSpotType.values().length];
		this.display=new Display();
	}

	public void setCapacityOfMatrix(int[] capacityOfMatrix){
//		this.capacityOfMatrix=capacityOfMatrix;
		this.capacityOfMatrix=capacityOfMatrix.clone();
		this.freeSpace=capacityOfMatrix.clone();
	}

	public void addParkingSpot(ParkingSpotType parkingSpotType,Cell cell){
		matrix.get(parkingSpotType).put(cell.getId(),cell);
	}

	public void removeParkingSpot(ParkingSpotType parkingSpotType,Cell cell){
		matrix.get(parkingSpotType).remove(cell.getId());
	}

	public Cell getCell(ParkingSpotType parkingSpotType){
		Cell ans=null;
		HashMap<Long,Cell> temp=matrix.get(parkingSpotType);
		for(Map.Entry mapElement : temp.entrySet()){
			ParkingStatus check=(ParkingStatus)((Cell) mapElement.getValue()).getParkingStatus();
			if(check==ParkingStatus.FREE){
				ans = (Cell) mapElement.getValue();
				break;
			}
		}
		
		return ans;
	}

	public void updateMatrix(Cell cell ,ParkingStatus parkingStatus){
		ParkingSpotType parkingSpotType=cell.getParkingSpotType();
		cell.setParkingStatus(parkingStatus);
		
		if(cell!=null) {
			
			if(parkingStatus==ParkingStatus.FREE) {
				occupancyOfMatrix[parkingSpotType.ordinal()]--;
//				System.out.println("customer just checked-out....occupancy= " + occupancyOfMatrix[parkingSpotType.ordinal()]);
			}
			else if(parkingStatus==ParkingStatus.OCCUPIED) {
				occupancyOfMatrix[parkingSpotType.ordinal()]++;
//				System.out.println("Customer has been issued a ticket......occupancy= " + occupancyOfMatrix[parkingSpotType.ordinal()]);
			}
			
			freeSpace[parkingSpotType.ordinal()] = capacityOfMatrix[parkingSpotType.ordinal()] - occupancyOfMatrix[parkingSpotType.ordinal()];
//			System.out.println("freeSpot : "+freeSpace[parkingSpotType.ordinal()]);
			display.update(freeSpace);
		}
		
	}

	public boolean isParkingSpotAvailable(ParkingSpotType parkingSpotType){
		return freeSpace[parkingSpotType.ordinal()]>0?true:false;
	}

	public Display getDisplay() {
		return display;
	}
	

}

