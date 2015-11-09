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

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public int getBaseTime() {
		return baseTime;
	}

	
	
}
