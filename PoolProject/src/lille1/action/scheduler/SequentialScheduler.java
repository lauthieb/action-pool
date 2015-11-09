package lille1.action.scheduler;

import lille1.action.exception.ActionFinishedException;

public class SequentialScheduler extends Scheduler {

	@Override
	public void reallyDoStep() throws ActionFinishedException {
		this.schedulerActions.get(this.currentAction).reallyDoStep();
		if(this.schedulerActions.get(this.currentAction).isFinished())
			if(this.currentAction < this.schedulerActions.size()-1) 
				this.currentAction++;
	}
}
