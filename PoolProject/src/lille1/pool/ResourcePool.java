package lille1.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import lille1.pool.resource.Resource;

public abstract class ResourcePool <T extends Resource>{
	
	protected int nbResources;
	protected List<T> resources;
	protected List<T> usedResources = new ArrayList<T>();
	
	public ResourcePool(int nbResources) {
		this.nbResources = nbResources;
		this.resources = new ArrayList<T>();
		for (int i = 0; i < nbResources; i++) {
			this.resources.add(create());
		}
	}
	
	protected abstract T create();
	
	public T provideResource() {
		if (!resources.isEmpty()){
			T tmp = resources.get(0);
			usedResources.add(tmp);
			resources.remove(0);
			return tmp;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public void freeResource(T resource) {
		if (usedResources.contains(resource)){
			resources.add(resource);
			usedResources.remove(resource);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}

