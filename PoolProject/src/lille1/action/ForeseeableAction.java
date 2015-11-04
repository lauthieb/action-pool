package lille1.action;

public class ForeseeableAction extends Action {

	private final int baseTime;
	private int timeRemaining;

	public ForeseeableAction(int eBaseTime) {
		this.baseTime = eBaseTime;
		this.timeRemaining = eBaseTime;
	}

	@Override
	public boolean isReady() {
		return (this.baseTime == this.timeRemaining);
	}

	@Override
	public boolean isFinished() {
		return (this.timeRemaining == 0);
	}

	@Override
	public void reallyDoStep() {
		if(!(this.isFinished())) this.timeRemaining--; 
		return;
	}

	@Override
	public Action createAction() {
		return new ForeseeableAction(this.baseTime);
	}

}
