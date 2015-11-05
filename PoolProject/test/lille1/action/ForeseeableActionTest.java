package lille1.action;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lille1.action.exception.ActionFinishedException;


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
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}

		assertFalse(action.isFinished());
		assertTrue(action.isInProgress());
		assertFalse(action.isReady());

		try {
			action.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}

		assertTrue(action.isFinished());
		assertFalse(action.isInProgress());
		assertFalse(action.isReady());
	}

	@Override
	public Action createAction() {
		return new ForeseeableAction(2);
	}

}
