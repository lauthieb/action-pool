package lille1.action;
import static org.junit.Assert.*;

import org.junit.Test;

import lille1.action.exception.ActionFinishedException;

public abstract class Action {

	public abstract boolean isReady();

	public abstract boolean isFinished();

	public abstract void reallyDoStep();
	
	public abstract Action createAction();

	public boolean isInProgress() {
		return (!isReady()) && (!isFinished());
	}

	public void doStep() throws ActionFinishedException {
		if (isFinished())
			throw new ActionFinishedException();
		else
			reallyDoStep();
	}
	
	@Test(expected = ActionFinishedException.class, timeout = 2000)
	public void doStepWhileFinishedThrowsException() throws ActionFinishedException {
		Action action = createAction();
		while(!action.isFinished()) {
			try {
				action.doStep();
			} catch (ActionFinishedException e) {
				fail("action was not supposed to be finished, we just checked");
			}
		}
		
		assertTrue(action.isFinished());
		action.doStep();
	}
}

