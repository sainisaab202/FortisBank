package bus;

import java.io.Serializable;

public class InternalNumberInit implements Serializable{
	
	private static final long serialVersionUID = 6146989302580778351L;
	private int accNum;
	private int custNum;
	private int trnNum;
	
	public InternalNumberInit() {
		this.accNum = 1111;
		this.trnNum = 2222;
		this.custNum = 3333;
	}

	public InternalNumberInit(int accNum, int custNum, int trnNum) {
		this.accNum = accNum;
		this.custNum = custNum;
		this.trnNum = trnNum;
	}
	
	public void setInternalNumberGenerator() {
		InternalNumberGenerators ing = new InternalNumberGenerators(this.accNum, this.trnNum, this.custNum);
	}
	
	
}
