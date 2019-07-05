package rolfor.rest.authentication;


import rolfor.ejb.authentication.AuthenticationBean;
import rolfor.model.token.Token;
import rolfor.model.user.User;

import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/authenticate")
public class AuthEndpoint {
	@Inject
	private UserInfo           userInfo;
	@Inject
	private AuthenticationBean authenticationBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticate(@QueryParam("login") String login,
	                             @QueryParam("password") String password,
	                             @QueryParam("remembered") Boolean remembered) {
		try {
			User  user  = authenticationBean.authenticate(login, password);
			Token token = authenticationBean.createToken(user, remembered);
			return Response.ok(token.getTokenString()).build();
		} catch (AuthenticationException e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	@Secured
	@GET
	@Path("/logout")
	public Response logout() {
		authenticationBean.logout(userInfo.getToken());
		return Response.ok().build();
	}
}
