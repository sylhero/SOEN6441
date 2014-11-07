package towerstrategy;

public class StrongestStrategy implements Strategy{
	private static final int STRONGEST = 1;

	@Override
	public int generateStrategy() {
		
		return STRONGEST;
	}
	

}
