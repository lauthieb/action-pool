package lille1.pool;

import lille1.action.Action;
import lille1.action.ForeseeableAction;
import lille1.action.exception.ActionFinishedException;
import lille1.action.resource.FreeResourceAction;
import lille1.action.resource.TakeResourceAction;
import lille1.pool.resource.Basket;
import lille1.pool.resource.Cubicle;
import lille1.pool.resource.ResourcefulUser;


public class Swimmer extends Action {
	protected String name;
	protected BasketPool basketPool;
	protected CubiclePool cubiclePool;
	
	protected ResourcefulUser<Basket> basketRfU;
	protected ResourcefulUser<Cubicle> cubicleRfU;
	
	protected ForeseeableAction undressing;
	protected ForeseeableAction swimming;
	protected ForeseeableAction dressing;
	
	protected TakeResourceAction<Basket> basketTRA;
	protected FreeResourceAction<Basket> basketFRA;
	protected TakeResourceAction<Cubicle> cubicleTRA;
	protected FreeResourceAction<Cubicle> cubicleFRA;
	
	protected boolean ready;
	
	public Swimmer(String name, BasketPool basketPool, CubiclePool cubiclePool, int undressingTime, int swimmingTime, int dressingTime){
		this.name = name;
		this.basketPool = basketPool;
		this.cubiclePool = cubiclePool;
		
		this.basketRfU = new ResourcefulUser<Basket>();
		this.cubicleRfU = new ResourcefulUser<Cubicle>();
		
		this.undressing = new ForeseeableAction(undressingTime);
		this.swimming = new ForeseeableAction(swimmingTime);
		this.dressing = new ForeseeableAction(dressingTime);
		
		this.basketTRA = new TakeResourceAction<Basket>(basketPool, basketRfU);
		this.basketFRA = new FreeResourceAction<Basket>(basketPool, basketRfU);
		this.cubicleTRA = new TakeResourceAction<Cubicle>(cubiclePool, cubicleRfU);
		this.cubicleFRA = new FreeResourceAction<Cubicle>(cubiclePool, cubicleRfU);
		
		this.ready = true;
	}

	@Override
	public boolean isReady() {
		return this.ready;
	}

	@Override
	public boolean isFinished() {
		return this.undressing.isFinished() && this.swimming.isFinished() && this.dressing.isFinished() 
				&& this.basketRfU.getResource() == null && this.cubicleRfU.getResource() == null;
	}

	@Override
	public void reallyDoStep() throws ActionFinishedException {
		this.ready = false;
		System.out.println(getName()+"'s turn");
		if (basketRfU.getResource() == null){
			System.out.print(getName()+" trying to take resource from pool basket... ");
			basketTRA.doStep();
		}
		else if (!undressing.isFinished()){
			if (cubicleRfU.getResource() == null){
				System.out.print(getName()+" trying to take resource from pool cubicle... ");
				cubicleTRA.doStep();			
			}
			else {
				undressing.doStep();
				System.out.println("undressing "+(undressing.getBaseTime()-undressing.getTimeRemaining())+"/"+undressing.getBaseTime());
			}
		}
		else if (cubicleRfU.getResource() != null && !swimming.isFinished()){
			System.out.println(getName()+" freeing resource pool cubicle");
			cubicleFRA.doStep();		
		}
		else if (!swimming.isFinished()){
			swimming.doStep();
			System.out.println("swimming "+(swimming.getBaseTime()-swimming.getTimeRemaining())+"/"+swimming.getBaseTime());			
		}
		else if (cubicleRfU.getResource() == null && !dressing.isFinished()){
			System.out.print(getName()+" trying to take resource from pool cubicle... ");
			cubicleTRA.doStep();
		}
		else if (!dressing.isFinished()){
			dressing.doStep();
			System.out.println("dressing "+(dressing.getBaseTime()-dressing.getTimeRemaining())+"/"+dressing.getBaseTime());			
		}
		else if (cubicleRfU.getResource() != null && dressing.isFinished()){
			System.out.println(getName()+" freeing resource pool cubicle");
			cubicleFRA.doStep();		
		}
		else{
			System.out.println(getName()+" freeing resource pool basket");
			basketFRA.doStep();					
		}
	}
	
	public String getName(){
		return name;
	}
	
	public BasketPool getBasketPool(){
		return basketPool;
	}
	
	public CubiclePool getCubiclePool(){
		return cubiclePool;
	}

	public ResourcefulUser<Basket> getBasketRfU() {
		return basketRfU;
	}

	public ResourcefulUser<Cubicle> getCubicleRfU() {
		return cubicleRfU;
	}
	
	
}
