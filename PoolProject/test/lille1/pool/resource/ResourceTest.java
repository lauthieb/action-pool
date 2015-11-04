package lille1.pool.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceTest {
	
	@Test
	public void basketTest() {
		Basket basket = new Basket();
		assertEquals("Basket", basket.description());
	}
	
	@Test
	public void cubicleTest() {
		Cubicle cubicle = new Cubicle();
		assertEquals("Cubicle", cubicle.description());
	}
}
