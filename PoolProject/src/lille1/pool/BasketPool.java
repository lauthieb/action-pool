package lille1.pool;

import lille1.pool.ResourcePool;
import lille1.pool.resource.Basket;

public class BasketPool extends ResourcePool<Basket>{

	public BasketPool(int nbResources) {
		super(nbResources);
	}

	@Override
	protected Basket create() {
		return new Basket();
	}

}
