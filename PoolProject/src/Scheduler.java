import java.util.ArrayList;

public class Scheduler extends Action {

	private ArrayList<Action> schedulerActions = new ArrayList<Action>();
	private int currentAction = 0;

	@Override
	public boolean isReady() {
		return ((this.schedulerActions.size() > 0)&&(this.schedulerActions.get(0).isReady()));
	}

	@Override
	public boolean isFinished() {
		for (Action a : this.schedulerActions)
			if ((!a.isFinished()))
				return false;
		return true;
	}

	@Override
	public void reallyDoStep() {
		this.schedulerActions.get(this.currentAction).reallyDoStep();
		if(this.schedulerActions.get(this.currentAction).isFinished())
			if(this.currentAction < this.schedulerActions.size()) 
				this.currentAction++;

	}

	public void addAction(Action eaction) {
		this.schedulerActions.add(eaction);
	}

}
