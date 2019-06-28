package rolfor.model.container;


import rolfor.model.AbstractRepo;


public class ContainerRepo extends AbstractRepo<Container, ContainerImpl> {
	@Override
	public Class<ContainerImpl> getEntityClass() {
		return ContainerImpl.class;
	}
	
	@Override
	protected ContainerImpl copy(Container from, ContainerImpl to) {
		to.setParentId(from.getParentId());
		to.setTitle(from.getTitle());
		to.setAuthorId(from.getAuthorId());
		to.setImageName(from.getImageName());
		to.setDescription(from.getDescription());
		return to;
	}
}

