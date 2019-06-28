package rolfor.model;


public class AbstractEntity implements Entity {
	protected Integer id;
	
	@Override
	public Integer getId() {
		return id;
	}
}
