import org.junit.Before;
import org.junit.Test;


public abstract class SchedulerTest extends ActionTest {
	
	protected SequentialScheduler sequentialScheduler;
	protected FairScheduler fairScheduler;
	
	@Before
	public void init() {
		Action action1 = new ForeseeableAction(2);
		Action action2 = new ForeseeableAction(3);
	}
	
	@Test
	public void schedulersHaveSameBehaviors() {
		
	}
	
	public abstract void onlyOneValidStateAtEachMomentForAction();

}
