package lille1.action.resource;

import java.util.NoSuchElementException;

import lille1.action.Action;
import lille1.pool.resource.Resource;
import lille1.pool.ResourcePool;
import lille1.pool.resource.ResourcefulUser;

public class TakeResourceAction<T extends Resource> extends Action {
	
	protected ResourcePool<T> resourcePool;
	protected ResourcefulUser<T> resourcefulUser;
	protected boolean ready;
	protected boolean finished;

	public TakeResourceAction(ResourcePool<T> resourcePool, ResourcefulUser<T> resourcefulUser) {
		this.resourcePool = resourcePool;
		this.resourcefulUser = resourcefulUser;
		this.ready = true;
		this.finished = false;
	}

	@Override
	public boolean isReady() {
		return this.ready;
	}

	@Override
	public boolean isFinished() {
		return this.finished;
	}

	@Override
	public void reallyDoStep() {
		try {
			T res = this.resourcePool.provideResource();
			this.resourcefulUser.setResource(res);
			this.ready = false;
			System.out.println("success");
		}
		catch(NoSuchElementException e){
			System.out.println("failed");
			e.printStackTrace();
		}
	}

	@Override
	public Action createAction() {
		return new TakeResourceAction<T>(this.resourcePool, this.resourcefulUser);
	}
	
	public ResourcePool<T> getResourcePool(){
		return resourcePool;
	}

}
