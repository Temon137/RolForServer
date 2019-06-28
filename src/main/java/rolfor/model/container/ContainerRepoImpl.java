package rolfor.model.container;


import rolfor.model.AbstractRepo;


public class ContainerRepoImpl extends AbstractRepo<Container, ContainerImpl> implements ContainerRepo {
	@Override
	public Class<ContainerImpl> getEntityClass() {
		return ContainerImpl.class;
	}
	
	@Override
	protected ContainerImpl copy(Container from, ContainerImpl to) {
		to.setId(from.getId());
		to.setParentId(from.getParentId());
		to.setTitle(from.getTitle());
		to.setAuthorId(from.getAuthorId());
		to.setImageName(from.getImageName());
		to.setDescription(from.getDescription());
		return to;
	}
}

