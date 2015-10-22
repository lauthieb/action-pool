
public class SequentialScheduler extends Scheduler {

	@Override
	public void reallyDoStep() {
		this.schedulerActions.get(this.currentAction).reallyDoStep();
		if(this.schedulerActions.get(this.currentAction).isFinished())
			if(this.currentAction < this.schedulerActions.size()-1) 
				this.currentAction++;
	}
}
