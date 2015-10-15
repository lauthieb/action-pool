import java.util.ArrayList;

public class Scheduler extends Action {
	
	private ArrayList<Action> schedulerActions = new ArrayList<Action>();

	public Scheduler(int timeToEnd) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reallyDoStep() {
		// TODO Auto-generated method stub

	}

	public void addAction(Action eaction) {
		this.schedulerActions.add(eaction);
	}

}
