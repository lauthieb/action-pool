package lille1.action;

/**
 * ForeseeableAction is the class to describe a foreseeable action with a baseTime and a timeRemaining.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public class ForeseeableAction extends Action {

	private final int baseTime;
	private int timeRemaining;

	/**
	 * Constructor of a ForeseeableAction
	 * @param baseTime
	 */
	public ForeseeableAction(int baseTime) {
		this.baseTime = baseTime;
		this.timeRemaining = baseTime;
	}

	/**
	 * To know if the action is ready.
	 * @return true if the action is ready and false if the action is not ready.
	 */
	@Override
	public boolean isReady() {
		return (this.baseTime == this.timeRemaining);
	}

	/**
	 * To know if the action is finished.
	 * @return true if the action is finished and false if the action is not finished.
	 */
	@Override
	public boolean isFinished() {
		return (this.timeRemaining == 0);
	}

	/**
	 * To really do a step for this action.
	 * 
	 */
	@Override
	public void reallyDoStep() {
		if(!(this.isFinished())) this.timeRemaining--; 
		return;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public int getBaseTime() {
		return baseTime;
	}

	
	
}
