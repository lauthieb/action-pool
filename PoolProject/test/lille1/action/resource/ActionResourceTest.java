package lille1.action.resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import lille1.pool.BasketPool;
import lille1.pool.resource.Basket;
import lille1.pool.resource.ResourcefulUser;

public class ActionResourceTest {
	protected FreeResourceAction<Basket> fra;
	protected TakeResourceAction<Basket> tra;
	protected BasketPool bp;
	protected ResourcefulUser<Basket> user;
	
	@Before
	public void init() {
		this.bp = new BasketPool(1);
		this.user = new ResourcefulUser<Basket>();
		this.fra = new FreeResourceAction<Basket>(bp, user);
		this.tra = new TakeResourceAction<Basket>(bp, user);
	}
	
	@Test
	public void testInit() {
		assertFalse(this.fra.isFinished());
		assertTrue(this.fra.isReady());
		assertFalse(this.tra.isFinished());
		assertTrue(this.tra.isReady());
		assertNotNull(this.fra.getResourcePool());
		assertNotNull(this.tra.getResourcePool());
	}
}
