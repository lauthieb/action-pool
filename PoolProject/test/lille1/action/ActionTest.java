package lille1.action;
import static org.junit.Assert.*;

import org.junit.Test;

import lille1.action.exception.ActionAlreadyFinishedException;
import lille1.action.scheduler.Scheduler;
import lille1.action.scheduler.SequentialScheduler;

public abstract class ActionTest {
	
	protected void onlyOneValidStateAtEachMoment(Action eAction) {
		assertTrue(eAction.isReady());
		assertFalse(eAction.isInProgress());
		assertFalse(eAction.isFinished());
		
		while(!eAction.isFinished()) {
			try {
				eAction.doStep();
			} catch (ActionAlreadyFinishedException e) {
				e.printStackTrace();
			}
			assertFalse(eAction.isReady());
			assertTrue(eAction.isFinished() == !eAction.isInProgress());
		}
		assertFalse(eAction.isReady());
		assertFalse(eAction.isInProgress());
		assertTrue(eAction.isFinished());
	}
	
	protected Scheduler createScheduler(Action action) {
		Scheduler scheduler = new SequentialScheduler();
		scheduler.addAction(action);
		return scheduler;
	}
	
	public abstract void onlyOneValidStateAtEachMomentForAction();
	
	@Test
	public void withOneStepAction() {
		OneStepAction action1 = new OneStepAction();
		Scheduler scheduler = createScheduler(action1);
		assertFalse(scheduler.isFinished());
		assertFalse(action1.isFinished());
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(scheduler.isFinished());
		assertTrue(action1.isFinished());
	}
}