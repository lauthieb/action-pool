package lille1.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import lille1.pool.resource.Resource;

/**
 * ResourcePool is the abstract class to describe a resource pool with available and used resources.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public abstract class ResourcePool <T extends Resource>{
	
	protected int nbResources;
	protected List<T> availableResources;
	protected List<T> usedResources = new ArrayList<T>();
	
	/**
	 * Constructor of a ResourcePool
	 * @param nbResources
	 */
	public ResourcePool(int nbResources) {
		this.nbResources = nbResources;
		this.availableResources = new ArrayList<T>();
		for (int i = 0; i < nbResources; i++) {
			this.availableResources.add(create());
		}
	}
	
	/**
	 * Defined on each subclass to create the resource.
	 * @return
	 */
	protected abstract T create();
	
	/**
	 * Called when the user want to provide a resource.
	 * @return T the resource
	 */
	public T provideResource() {
		if (!availableResources.isEmpty()){
			T tmp = availableResources.get(0);
			usedResources.add(tmp);
			availableResources.remove(0);
			return tmp;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Called when the user want to free a resource.
	 * @return T the resource
	 */
	public void freeResource(T resource) {
		if (usedResources.contains(resource)){
			availableResources.add(resource);
			usedResources.remove(resource);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public List<T> getResources() {
		return availableResources;
	}

	public void setResources(List<T> resources) {
		this.availableResources = resources;
	}

	public List<T> getUsedResources() {
		return usedResources;
	}

	public void setUsedResources(List<T> usedResources) {
		this.usedResources = usedResources;
	}

}

