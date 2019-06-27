package rolfor.model.container;


import rolfor.model.AbstractRepo;


public class ContainerRepo extends AbstractRepo<ContainerImpl> {
	@Override
	public Class<ContainerImpl> getEntityClass() {
		return ContainerImpl.class;
	}
	
	@Override
	protected ContainerImpl copy(ContainerImpl from, ContainerImpl dest) {
		dest.setParentId(from.getParentId());
		dest.setTitle(from.getTitle());
		dest.setAuthorId(from.getAuthorId());
		dest.setImageName(from.getImageName());
		dest.setDescription(from.getDescription());
		return dest;
	}
}

