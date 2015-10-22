
public class FairScheduler extends Scheduler {

	@Override
	public void reallyDoStep() {
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
