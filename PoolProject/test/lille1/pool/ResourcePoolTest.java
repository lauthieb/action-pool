package lille1.pool;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import lille1.pool.resource.Basket;
import lille1.pool.resource.Cubicle;

public class ResourcePoolTest {
	protected ResourcePool<Basket> res1;
	protected ResourcePool<Cubicle> res2;
	
	@Before
	public void init(){
		res1 = new BasketPool(4);
		res2 = new CubiclePool(2);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void provideTest() {
		for(int i=0; i<3; i++) {
			res2.provideResource();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void freeTest() {
		Basket b1 = res1.provideResource();
		res1.freeResource(b1);
		Basket b2 = new Basket();
		res1.freeResource(b2);
	}
}
