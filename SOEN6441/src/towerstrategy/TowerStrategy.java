package towerstrategy;

public class TowerStrategy {
	private Strategy strategy;
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
		
	}
	public int getStrategy(){
		return strategy.generateStrategy();
	}

}
