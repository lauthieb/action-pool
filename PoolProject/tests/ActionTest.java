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
	
	@Test
	public void schedulerWithSchedulerTest() {
		Action action = new ForeseeableAction(2);
		Scheduler scheduler = new Scheduler();
		Scheduler subScheduler = new Scheduler();
		
		subScheduler.addAction(action);
		scheduler.addAction(subScheduler);
		
		assertTrue(action.isReady());
		assertTrue(subScheduler.isReady());
		assertTrue(scheduler.isReady());
		
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(action.isInProgress());
		assertTrue(subScheduler.isInProgress());
		assertTrue(scheduler.isInProgress());
		
		try {
			scheduler.doStep();
		} catch (ActionAlreadyFinishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(action.isFinished());
		assertTrue(subScheduler.isFinished());
		assertTrue(scheduler.isFinished());
		
		
	}
	
	@Test
	public void onlyOneValidStateAtEachMomentForForeseeableAction() {
		onlyOneValidStateAtEachMoment(new ForeseeableAction(10));
	}

	
	@Test
	public void onlyOneValidStateAtEachMomentForScheduler() {
		Scheduler sched = new Scheduler();
		sched.addAction(new ForeseeableAction(2));
		onlyOneValidStateAtEachMoment(sched);
	}
	
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
}