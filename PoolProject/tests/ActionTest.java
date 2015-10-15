import static org.junit.Assert.*;
import lille1.pool.exception.ActionAlreadyFinishedException;

import org.junit.Test;


public class ActionTest {
	private Scheduler createScheduler(int timeToEnd) {
		return new Scheduler(timeToEnd);
	}

	@Test
	public void foreseeableAction() {
		Action action = createScheduler(2);
		// 2 steps remaining
		assertTrue(action.isReady());
		assertFalse(action.isInProgress());
		assertFalse(action.isFinished());
		try {
			action.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 1 step remaining
		assertFalse(action.isReady());
		assertTrue(action.isInProgress());
		assertFalse(action.isFinished());
		try {
			action.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 0 step remaining
		assertFalse(action.isReady());
		assertFalse(action.isInProgress());
		assertTrue(action.isFinished());
	}

	@Test
	public void scheduler() {
		Scheduler Scheduler1 = createScheduler(2);
		Scheduler Scheduler2 = createScheduler(1);
		Scheduler scheduler = createScheduler(0);
		scheduler.addAction(Scheduler1);
		scheduler.addAction(Scheduler2);
		assertTrue(Scheduler1.isReady());
		assertTrue(Scheduler2.isReady());
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(Scheduler1.isInProgress());
		assertTrue(Scheduler2.isReady());
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(Scheduler1.isFinished());
		assertTrue(Scheduler2.isReady());
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(Scheduler1.isFinished());
		assertTrue(Scheduler2.isFinished());
	}

	@Test
	public void schedulerWithScheduler() {
		Scheduler Scheduler1 = createScheduler(2);
		Scheduler subScheduler = createScheduler(0);
		Scheduler scheduler = createScheduler(0);
		subScheduler.addAction(Scheduler1);
		scheduler.addAction(subScheduler);
		assertTrue(Scheduler1.isReady());
		assertTrue(subScheduler.isReady());
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(Scheduler1.isInProgress());
		assertTrue(subScheduler.isInProgress());
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(Scheduler1.isFinished());
		assertTrue(subScheduler.isFinished());
	}

	@Test
	public void onlyOneValidStateAtEachMomentForForeseebleAction() {
		onlyOneValidStateAtEachMoment(createScheduler(10));
	}

	@Test
	public void onlyOneValidStateAtEachMomentForScheduler() {
		Scheduler scheduler = createScheduler(0);
		scheduler.addAction(createScheduler(1));
		onlyOneValidStateAtEachMoment(scheduler);
	}

	protected void onlyOneValidStateAtEachMoment(Action action) {
		assertTrue(action.isReady());
		assertFalse(action.isInProgress());
		assertFalse(action.isFinished());
		while (!action.isFinished()) {
			try {
				action.doStep();
			} catch (ActionAlreadyFinishedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertFalse(action.isReady());
			// isFinished xor isInProgress
			assertTrue(action.isFinished() == !action.isInProgress());
		}
		assertFalse(action.isReady());
		assertFalse(action.isInProgress());
		assertTrue(action.isFinished());
	}
}