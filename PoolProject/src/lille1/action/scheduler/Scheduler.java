package lille1.action.scheduler;
import java.util.ArrayList;

import lille1.action.Action;
import lille1.action.exception.ActionFinishedException;

public abstract class Scheduler extends Action {

	protected ArrayList<Action> schedulerActions = new ArrayList<Action>();
	protected int currentAction = 0;

	@Override
	public boolean isReady() {
		return ((this.schedulerActions.size() > 0) && (this.schedulerActions.get(0).isReady()));
	}

	@Override
	public boolean isFinished() {
		for (Action a : this.schedulerActions)
			if ((!a.isFinished()))
				return false;
		return true;
	}

	@Override
	public abstract void reallyDoStep() throws ActionFinishedException;

	public void addAction(Action eaction) {
		this.schedulerActions.add(eaction);
	}

}
