package lille1.action.resource;

import lille1.action.Action;
import lille1.pool.ResourcePool;
import lille1.pool.resource.Resource;
import lille1.pool.resource.ResourcefulUser;

public class FreeResourceAction<T extends Resource> extends Action {
	
	protected ResourcePool<T> resourcePool;
	protected ResourcefulUser<T> resourcefulUser;
	protected boolean ready;
	protected boolean finished;

	public FreeResourceAction(ResourcePool<T> resourcePool, ResourcefulUser<T> resourcefulUser) {
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
			this.resourcePool.freeResource(this.resourcefulUser.getResource());
			this.resourcefulUser.resetResource();
			this.ready = true;
			System.out.println("success");
		} catch(IllegalArgumentException e){
			System.out.println("failed");
		}
	}

	public ResourcePool<T> getResourcePool() {
		return resourcePool;
	}

}
