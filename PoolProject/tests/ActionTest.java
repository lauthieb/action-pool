import static org.junit.Assert.*;
import lille1.pool.exception.ActionAlreadyFinishedException;

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
	
	public abstract void onlyOneValidStateAtEachMomentForAction();
}