package parkingLotDesign;

public class Cash implements PaymentMethod {

	@Override
	public boolean pay() {
		return true;
	}

}
