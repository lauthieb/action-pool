package lille1.action;

import lille1.action.exception.ActionFinishedException;

public abstract class Action {

	public abstract boolean isReady();

	public abstract boolean isFinished();

	public abstract void reallyDoStep() throws ActionFinishedException;

	public boolean isInProgress() {
		return (!isReady()) && (!isFinished());
	}

	public void doStep() throws ActionFinishedException {
		if (isFinished())
			throw new ActionFinishedException();
		else
			reallyDoStep();
	}
}

