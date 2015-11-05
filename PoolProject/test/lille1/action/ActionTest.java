package lille1.action;
import static org.junit.Assert.*;

import org.junit.Test;

import lille1.action.exception.ActionFinishedException;
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
			} catch (ActionFinishedException e) {
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
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(scheduler.isFinished());
		assertTrue(action1.isFinished());
	}
	
	public abstract Action createAction();
	
	@Test(expected = ActionFinishedException.class, timeout = 2000)
	public void doStepWhileFinishedThrowsException() throws ActionFinishedException {
		Action action = createAction();
		while (!action.isFinished()) {
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