import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import lille1.pool.exception.ActionAlreadyFinishedException;

import org.junit.Test;


public class ForeseeableActionTest extends ActionTest {
	
	@Test
	public void onlyOneValidStateAtEachMomentForAction() {
		onlyOneValidStateAtEachMoment(new ForeseeableAction(10));
	}
	
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

}
