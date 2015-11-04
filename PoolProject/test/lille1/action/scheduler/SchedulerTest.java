package lille1.action.scheduler;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lille1.action.*;
import lille1.action.exception.ActionFinishedException;


public abstract class SchedulerTest extends ActionTest {
	
	protected Action action1;
	protected Action action2;
	protected SequentialScheduler sequentialScheduler;
	protected FairScheduler fairScheduler;
	
	@Before
	public void init() {
		action1 = new ForeseeableAction(2);
		action2 = new ForeseeableAction(3);
		sequentialScheduler = new SequentialScheduler();
		fairScheduler = new FairScheduler();
	}
	
	@Test
	public void fairSchedulerInSequentialScheduler() {
		fairScheduler.addAction(action1);
		
		sequentialScheduler.addAction(fairScheduler);
		assertTrue(sequentialScheduler.isReady());
		assertTrue(fairScheduler.isReady());
		
		try {
			sequentialScheduler.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(sequentialScheduler.isInProgress());
		assertTrue(fairScheduler.isInProgress());
		
		try {
			sequentialScheduler.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		assertTrue(sequentialScheduler.isFinished());
		assertTrue(fairScheduler.isFinished());
	}
	
	public abstract void onlyOneValidStateAtEachMomentForAction();

}
