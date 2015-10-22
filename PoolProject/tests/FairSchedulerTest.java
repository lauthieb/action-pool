import static org.junit.Assert.assertTrue;
import lille1.pool.exception.ActionAlreadyFinishedException;

import org.junit.Test;


public class FairSchedulerTest extends SchedulerTest {
	
	@Test
	public void onlyOneValidStateAtEachMomentForAction() {
		FairScheduler sched = new FairScheduler();
		sched.addAction(new ForeseeableAction(2));
		onlyOneValidStateAtEachMoment(sched);
	}
	
	@Test
	public void actionFairSchedulerTest() {
		Action action = new ForeseeableAction(2);
		Action action2 = new ForeseeableAction(3);
		Action action3 = new FairScheduler();

		FairScheduler sched = (FairScheduler) action3;

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
		assertTrue(sched.isInProgress());
		
		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}
		
		assertTrue(action.isInProgress());
		assertTrue(action2.isInProgress());
		
		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}
		
		assertTrue(action.isFinished());
		assertTrue(action2.isInProgress());
		
		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}
		
		assertTrue(action.isFinished());
		assertTrue(action2.isInProgress());
		
		try {
			action3.doStep();
		} catch (ActionAlreadyFinishedException e) {
			e.printStackTrace();
		}
		
		assertTrue(action.isFinished());
		assertTrue(action2.isFinished());
		assertTrue(sched.isFinished());
	}

}
