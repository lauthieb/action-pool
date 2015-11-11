package lille1.pool;

import lille1.pool.resource.Cubicle;

/**
 * CubiclePool is the class to describe a resource pool of cubicles with available and used resources.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public class CubiclePool extends ResourcePool<Cubicle> {

	/**
	 * Constructor of a CubiclePool
	 * @param nbResources
	 */
	public CubiclePool(int nbResources) {
		super(nbResources);
	}

	/**
	 * Called when the user wants a new Basket.
	 */
	@Override
	protected Cubicle create() {
		return new Cubicle();
	}
	
}
