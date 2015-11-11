package lille1.pool.resource;

/**
 * ResourcefulUser is the class to describe a resourceful user with one resource.
 * 
 * @author Amelie M., Laurent.T, Thibault.C, Quentin.G.
 *
 */
public class ResourcefulUser <R extends Resource>{
	protected R resource;
	
	/**
	 * To get the resource.
	 */
	public R getResource() {
		return resource;
	}
	
	/**
	 * To set a resource.
	 */
	public void setResource(R resource) {
		this.resource = resource;
	}
	
	/**
	 * To set the resource to null.
	 */
	public void resetResource() {
		this.resource = null;
	}
	
}
