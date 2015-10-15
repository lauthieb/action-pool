import lille1.pool.exception.ActionAlreadyFinishedException;

public abstract class Action {

	public abstract boolean isReady();

	public abstract boolean isFinished();

	public abstract void reallyDoStep();

	public boolean isInProgress() {
		return (!isReady()) && (!isFinished());
	}

	public void doStep() throws ActionAlreadyFinishedException {
		if (isFinished())
			throw new ActionAlreadyFinishedException();
		else
			reallyDoStep();
	}
}

