package rolfor.model.container;


import rolfor.model.AbstractRepo;

import javax.ejb.Local;
import javax.ejb.Stateless;


@Local(ContainerRepo.class)
@Stateless(name = "ContainerRepoImpl")
public class ContainerRepoImpl extends AbstractRepo<Container, MutableContainer, ContainerImpl> implements ContainerRepo {
	@Override
	public Class<ContainerImpl> getEntityClass() {
		return ContainerImpl.class;
	}
	
	@Override
	public MutableContainer createEmpty() {
		return new ContainerImpl();
	}
	
	@Override
	protected void copy(Container from, MutableContainer to) {
		to.setParentId(from.getParentId());
		to.setTitle(from.getTitle());
		to.setAuthorId(from.getAuthorId());
		to.setImageName(from.getImageName());
		to.setDescription(from.getDescription());
	}
}

