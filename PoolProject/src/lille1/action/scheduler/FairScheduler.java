package lille1.action.scheduler;

import lille1.action.exception.ActionFinishedException;

/**
 * FairScheduler is a scheduler which execute actions in parallel.
 * It executes an action and goes the next one which is not finished.  
 * @author thibault
 *
 */
public class FairScheduler extends Scheduler {

	/**
	 * Execute the action which follows the last action executed.
	 * If the action we want to execute is already finished, we execute the first next one which is not finished. 
	 */
	@Override
	public void reallyDoStep() throws ActionFinishedException {
		if(this.currentAction >= schedulerActions.size()) {
			this.currentAction = 0;
		}
		
		if(!this.schedulerActions.get(this.currentAction).isFinished()) {
			this.schedulerActions.get(this.currentAction).reallyDoStep();
			this.currentAction++;
		} else {
			this.currentAction++;
			this.reallyDoStep();
		}
	}

}
