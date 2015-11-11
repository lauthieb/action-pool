package lille1.action.resource;

import lille1.action.Action;
import lille1.pool.ResourcePool;
import lille1.pool.resource.Resource;
import lille1.pool.resource.ResourcefulUser;

/**
 * ActionFinishedException is the class to describe an action to free a resource.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public class FreeResourceAction<T extends Resource> extends Action {
	
	protected ResourcePool<T> resourcePool;
	protected ResourcefulUser<T> resourcefulUser;
	protected boolean ready;
	protected boolean finished;

	/**
	 * Constructor of a FreeResourceAction
	 * @param resourcePool
	 * @param resourcefulUser
	 */
	public FreeResourceAction(ResourcePool<T> resourcePool, ResourcefulUser<T> resourcefulUser) {
		this.resourcePool = resourcePool;
		this.resourcefulUser = resourcefulUser;
		this.ready = true;
		this.finished = false;
	}

	/**
	 * To know if the action is ready.
	 * @return true if the action is ready and false if the action is not ready.
	 */
	@Override
	public boolean isReady() {
		return this.ready;
	}

	/**
	 * To know if the action is finished.
	 * @return true if the action is finished and false if the action is not finished.
	 */
	@Override
	public boolean isFinished() {
		return this.finished;
	}

	/**
	 * To really do a step for this action.
	 * 
	 */
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
