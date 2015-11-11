package lille1.action.scheduler;
import java.util.ArrayList;

import lille1.action.Action;
import lille1.action.exception.ActionFinishedException;
/**
 * A scheduler gets a set of actions. We can deal with it to make progress these actions.
 * @author thibault
 *
 */
public abstract class Scheduler extends Action {

	protected ArrayList<Action> schedulerActions = new ArrayList<Action>();
	protected int currentAction = 0;

	/**
	 * A scheduler is ready if he gets at least one action, and if the first one is ready
	 * @return true if ready, false if not ready
	 */
	@Override
	public boolean isReady() {
		return ((this.schedulerActions.size() > 0) && (this.schedulerActions.get(0).isReady()));
	}

	/**
	 * A scheduler is finished if all his actions are finished
	 * @return true if finished, false if not finished
	 */
	@Override
	public boolean isFinished() {
		for (Action a : this.schedulerActions)
			if ((!a.isFinished()))
				return false;
		return true;
	}

	@Override
	public abstract void reallyDoStep() throws ActionFinishedException;

	/**
	 * Add an action to the set of actions of the scheduler
	 * @param eaction the action to add
	 */
	public void addAction(Action eaction) {
		this.schedulerActions.add(eaction);
	}

}
