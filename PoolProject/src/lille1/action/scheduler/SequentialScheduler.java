package lille1.action.scheduler;

import lille1.action.exception.ActionFinishedException;

/**
 * SequentialSchedulder is a scheduler which ends the started action before going to the next one.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public class SequentialScheduler extends Scheduler {

	/**
	 * Execute the last action which has been executed if it is not finished.
	 * If it is finished, we execute the next one.
	 */
	@Override
	public void reallyDoStep() throws ActionFinishedException {
		this.schedulerActions.get(this.currentAction).reallyDoStep();
		if(this.schedulerActions.get(this.currentAction).isFinished()) {
			if(this.currentAction < this.schedulerActions.size()-1)  {
				this.currentAction++;
			}
		}
	}
}
