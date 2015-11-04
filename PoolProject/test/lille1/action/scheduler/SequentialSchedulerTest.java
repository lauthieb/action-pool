package lille1.action.scheduler;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import lille1.action.*;
import lille1.action.exception.ActionFinishedException;


public class SequentialSchedulerTest extends SchedulerTest {
	
	@Test
	public void onlyOneValidStateAtEachMomentForAction() {
		SequentialScheduler sched = new SequentialScheduler();
		sched.addAction(new ForeseeableAction(2));
		onlyOneValidStateAtEachMoment(sched);
	}
	
	@Test
	public void actionSequentialSchedulerTest() {
		Action action = new ForeseeableAction(2);
		Action action2 = new ForeseeableAction(2);
		Action action3 = new SequentialScheduler();

		SequentialScheduler sched = (SequentialScheduler) action3;

		sched.addAction(action);
		sched.addAction(action2);

		assertTrue(action.isReady());
		assertTrue(action2.isReady());
		assertTrue(sched.isReady());

		try {
			action3.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}

		assertTrue(action.isInProgress());
		assertTrue(action2.isReady());
		assertTrue(action3.isInProgress());

		try {
			action3.doStep();
		} catch (ActionFinishedException e1) {
			e1.printStackTrace();
		}
		assertTrue(action.isFinished());
		assertTrue(action2.isReady());
		assertTrue(action3.isInProgress());

		try {
			action3.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(action2.isInProgress());
		assertTrue(action3.isInProgress());

		try {
			action3.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(action2.isFinished());
		assertTrue(action3.isFinished());
	}
	
	@Test
	public void SequentialSchedulerWithSequentialSchedulerTest() {
		Action action = new ForeseeableAction(2);
		SequentialScheduler sequentialScheduler = new SequentialScheduler();
		SequentialScheduler subSequentialScheduler = new SequentialScheduler();
		
		subSequentialScheduler.addAction(action);
		sequentialScheduler.addAction(subSequentialScheduler);
		
		assertTrue(action.isReady());
		assertTrue(subSequentialScheduler.isReady());
		assertTrue(sequentialScheduler.isReady());
		
		try {
			sequentialScheduler.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		
		assertTrue(action.isInProgress());
		assertTrue(subSequentialScheduler.isInProgress());
		assertTrue(sequentialScheduler.isInProgress());
		
		try {
			sequentialScheduler.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		
		assertTrue(action.isFinished());
		assertTrue(subSequentialScheduler.isFinished());
		assertTrue(sequentialScheduler.isFinished());
	}
}
