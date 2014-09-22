package currency;
//factory class for currency
public class CurrencyFactory {
	public CurrencyFactory(){
		
		
	}
	public Currency getCurrency(String currencyType){
		Currency result = null;
		if(currencyType == null){
			result = null;
		}else{
			if(currencyType.equalsIgnoreCase("coin")){
				result =  new Coin();
			}
		}
		return result;
		
	}

}
