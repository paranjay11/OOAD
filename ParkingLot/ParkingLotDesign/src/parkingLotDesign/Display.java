package parkingLotDesign;

import java.util.ArrayList;

import parkingLotDesign.Constants.ParkingSpotType;

public class Display implements Cloneable{
	int[] displaySpotType;
	
	public Display() {
		this.displaySpotType=new int[ParkingSpotType.values().length];
	}

	public void update(int[] freeSpace) {
		this.displaySpotType=freeSpace.clone();
		this.runDisplay();
	}

	private void runDisplay() {
		for(ParkingSpotType spotType:ParkingSpotType.values()) {
			System.out.println("Empty space available in "+spotType+" "+this.displaySpotType[spotType.ordinal()]);
		}
		
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}