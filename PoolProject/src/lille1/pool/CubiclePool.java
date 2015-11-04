package lille1.pool;

import lille1.pool.resource.Cubicle;

public class CubiclePool extends ResourcePool<Cubicle> {

	public CubiclePool(int nbResources) {
		super(nbResources);
	}

	@Override
	protected Cubicle create() {
		return new Cubicle();
	}
	
}
