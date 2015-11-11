package lille1.action;

import lille1.action.exception.ActionFinishedException;

/**
 * Action is the abstract class to describe an Action.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public abstract class Action {
	
	/**
	 * Defined on each subclass to know if the action is ready.
	 * @return true if the action is ready and false if the action is not ready.
	 */
	public abstract boolean isReady();
	
	/**
	 * To know if the action is in progress.
	 * @return true if the action is in progress and false if the action is not in progress.
	 */
	public boolean isInProgress() {
		return (!isReady()) && (!isFinished());
	}

	/**
	 * Defined on each subclass to know if the action is ready.
	 * @return true if the action is finished and false if the action is not finished.
	 */
	public abstract boolean isFinished();

	/**
	 * Defined on each subclass to apply the behavior of a doStep for this subclass.
	 * 
	 */
	public abstract void reallyDoStep() throws ActionFinishedException;

	/**
	 * Called when an action is doing a step.
	 * This function uses reallyDoStep() defined in subclass to use the design pattern "Template method".
	 * @throws ActionFinishedException
	 */
	public void doStep() throws ActionFinishedException {
		if (isFinished()) {
			throw new ActionFinishedException();
		}
		else {
			reallyDoStep();
		}
	}
}

