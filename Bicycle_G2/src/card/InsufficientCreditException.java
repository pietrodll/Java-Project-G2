package card;

public class InsufficientCreditException extends Exception {

	private static final long serialVersionUID = 393917302384664045L;
	private int userCredit;
	private int creditToUse;
	
	/**
	 * @param userCredit
	 * @param creditToUse
	 */
	public InsufficientCreditException(int userCredit, int creditToUse) {
		this.userCredit = userCredit;
		this.creditToUse = creditToUse;
	}

	/**
	 * @return the userCredit
	 */
	public int getUserCredit() {
		return userCredit;
	}

	/**
	 * @return the creditToUse
	 */
	public int getCreditToUse() {
		return creditToUse;
	}

	/**
	 * Returns a message to explain the {@code Exception}.
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Not enough credit ! Tried to use " + this.creditToUse + " but only " + this.userCredit;
	}
	
	

}
