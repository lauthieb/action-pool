package lille1.pool;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lille1.action.exception.ActionFinishedException;

public class SwimmerTest {
	
	protected Swimmer swimmer;
	protected BasketPool baskets;
	protected CubiclePool cubicles;
	
	
	@Before 
	public void init() {
		baskets = new BasketPool(6);
		cubicles = new CubiclePool(3);
		swimmer = new Swimmer("Laulau", baskets, cubicles, 2,2,3);
	}

	@Test
	public void testIsReady() throws ActionFinishedException {
		assertTrue(swimmer.isReady());
		swimmer.doStep();
		assertFalse(swimmer.isReady());
	}
	
	@Test
	public void testGetName() {
		assertEquals(swimmer.getName(),"Laulau");
	}
	
	@Test
	public void testGetBasketPool() {
		assertEquals(swimmer.getBasketPool(),baskets);
	}
	
	@Test
	public void testGetCubiclePool() {
		assertEquals(swimmer.getCubiclePool(),cubicles);
	}
	
	@Test
	public void testReallyDoStep() throws ActionFinishedException {
		assertNull(swimmer.getBasketRfU().getResource());
		assertNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNotNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNotNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNotNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNotNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNotNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNotNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNotNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNotNull(swimmer.getBasketRfU().getResource());
		assertNull(swimmer.getCubicleRfU().getResource());
		swimmer.doStep();
		assertNull(swimmer.getBasketRfU().getResource());
		assertNull(swimmer.getCubicleRfU().getResource());
		assertTrue(swimmer.isFinished());
	}

}
