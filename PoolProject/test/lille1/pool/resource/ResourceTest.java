package lille1.pool.resource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResourceTest {
	
	protected Basket basket;
	protected Cubicle cubicle;
	protected ResourcefulUser<Resource> user;
	
	@Before
	public void init() {
		this.basket = new Basket();
		this.cubicle = new Cubicle();
		this.user = new ResourcefulUser<Resource>();
	}
	
	@Test
	public void testResources() {
		assertEquals("Basket", basket.description());
		assertEquals("Cubicle", cubicle.description());
		user.setResource(basket);
		assertEquals(basket, user.getResource());
		user.resetResource();
		assertNull(user.getResource());
	}
}
