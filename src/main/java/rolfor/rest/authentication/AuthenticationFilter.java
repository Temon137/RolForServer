package rolfor.rest.authentication;


import rolfor.ejb.authentication.AuthenticationBean;
import rolfor.model.token.Token;
import rolfor.model.user.User;
import rolfor.model.user.UserRepo;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Map;


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	@Inject
	private UserInfoInitiator  userInfoInitiator;
	@Inject
	private AuthenticationBean authenticationBean;
	@Inject
	private UserRepo           userRepo;
	
	private static final String AUTHENTICATION_SCHEME = "Bearer";
	
	@Override
	public void filter(ContainerRequestContext requestContext) {
		try {
			String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			validateAuthHeader(authHeader);
			
			String tokenString = authHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
			Token  token       = authenticationBean.getTokenByString(tokenString);
			
			authenticationBean.refreshToken(token);
			
			User currentUser = userRepo.find(token.getUserId());
			userInfoInitiator.init(currentUser, token);
		} catch (AuthenticationException e) {
			abortWithUnauthorized(requestContext, e.getMessage());
		}
	}
	
	private void validateAuthHeader(String authHeader) throws AuthenticationException {
		if (authHeader == null || !authHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ")) {
			throw new AuthenticationException("Неизвестный формат заголовка аутентификации.");
		}
	}
	
	private void abortWithUnauthorized(ContainerRequestContext requestContext, String message) {
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
		                                 .type(MediaType.APPLICATION_JSON_TYPE)
		                                 .entity(Map.of("error", message))
		                                 .build());
	}
}
