package rolfor.rest;


import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;


public class PaginationParams {
	@Min(value = 1, message = "pageNumber start at 1")
	@QueryParam("pageNumber")
	@DefaultValue("1")
	private Integer pageNumber;
	
	@Min(value = 1, message = "pageSize start at 1")
	@QueryParam("pageSize")
	@DefaultValue("10")
	private Integer pageSize;
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
}
