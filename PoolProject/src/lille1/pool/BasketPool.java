package lille1.pool;

import lille1.pool.ResourcePool;
import lille1.pool.resource.Basket;

/**
 * BasketPool is the class to describe a resource pool of baskets with available and used resources.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public class BasketPool extends ResourcePool<Basket>{

	/**
	 * Constructor of a BasketPool
	 * @param nbResources
	 */
	public BasketPool(int nbResources) {
		super(nbResources);
	}

	/**
	 * Called when the user wants a new Basket.
	 */
	@Override
	protected Basket create() {
		return new Basket();
	}

}
