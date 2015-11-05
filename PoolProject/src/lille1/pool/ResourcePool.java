package lille1.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import lille1.pool.resource.Resource;

public abstract class ResourcePool <T extends Resource>{
	
	protected int nbResources;
	protected List<T> availableResources;
	protected List<T> usedResources = new ArrayList<T>();
	
	public ResourcePool(int nbResources) {
		this.nbResources = nbResources;
		this.availableResources = new ArrayList<T>();
		for (int i = 0; i < nbResources; i++) {
			this.availableResources.add(create());
		}
	}
	
	protected abstract T create();
	
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

