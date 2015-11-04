package lille1.action.resource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lille1.action.exception.ActionFinishedException;
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
	
	@Test
	public void testTakeResourceAction() {
		assertTrue(this.tra.isReady());
		assertNull(this.user.getResource());
		assertEquals(1, this.tra.getResourcePool().getResources().size());
		assertEquals(0, this.tra.getResourcePool().getUsedResources().size());
		
		try {
			this.tra.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		
		assertFalse(this.tra.isReady());
		assertNotNull(this.user.getResource());
		assertEquals(0, this.tra.getResourcePool().getResources().size());
		assertEquals(1, this.tra.getResourcePool().getUsedResources().size());
	}
	
	@Test
	public void testFreeResourceAction() {
		assertEquals(1, this.tra.getResourcePool().getResources().size());
		assertEquals(0, this.tra.getResourcePool().getUsedResources().size());
		
		try {
			this.tra.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		
		assertTrue(this.fra.isReady());
		assertNotNull(this.user.getResource());
		
		try {
			this.fra.doStep();
		} catch (ActionFinishedException e) {
			e.printStackTrace();
		}
		
		assertNull(this.user.getResource());
		assertEquals(1, this.tra.getResourcePool().getResources().size());
		assertEquals(0, this.tra.getResourcePool().getUsedResources().size());
	}
	
}
