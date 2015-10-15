import static org.junit.Assert.*;
import lille1.pool.exception.ActionAlreadyFinishedException;

import org.junit.Test;

public class ActionTest {

	@Test
	public void foreseeableActionTest() {
		Action action = new ForeseeableAction(2);

		assertFalse(action.isFinished());
		assertFalse(action.isInProgress());
		assertTrue(action.isReady());

		try {
			action.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}

		assertFalse(action.isFinished());
		assertTrue(action.isInProgress());
		assertFalse(action.isReady());

		try {
			action.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}

		assertTrue(action.isFinished());
		assertFalse(action.isInProgress());
		assertFalse(action.isReady());

	}

	@Test
	public void actionSchedulerTest() {
		Action action = new ForeseeableAction(2);
		Action action2 = new ForeseeableAction(2);
		Action action3 = new Scheduler();

		Scheduler sched = (Scheduler) action3;

		sched.addAction(action);
		sched.addAction(action2);

		assertTrue(action.isReady());
		assertTrue(action2.isReady());
		assertTrue(sched.isReady());

		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}

		assertTrue(action.isInProgress());
		assertTrue(action2.isReady());
		assertTrue(action3.isInProgress());

		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e1) {
			e1.printStackTrace();
		}
		assertTrue(action.isFinished());
		assertTrue(action2.isReady());
		assertTrue(action3.isInProgress());

		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(action2.isInProgress());
		assertTrue(action3.isInProgress());

		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(action2.isFinished());
		assertTrue(action3.isFinished());

	}
}