package org.sagittarius90.api.filters.security;

import org.sagittarius90.model.UserModel;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {
	
	String authenticationScheme;
	private java.security.Principal principal;
	
	public class Principal implements java.security.Principal {
		UserModel user;

		public Principal() {
		}

		public Principal(UserModel user) {
			this.user = user;
		}

		@Override
		public String getName() {
			return user.getUsername();
		}

		public UserModel getUser() {
			return user;
		}

		public void setUser(UserModel user) {
			this.user = user;
		}
	}

	public SecurityContext(UserModel user) {
		getPrincipalInstance().setUser(user);
	}

	@Override
	public java.security.Principal getUserPrincipal() {
		return getPrincipal();
	}

	@Override
	public boolean isUserInRole(String role) {
		return true;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return authenticationScheme;
	}
	
	public void setAuthenticationScheme(String scheme) {
		authenticationScheme = scheme;
	}

	public java.security.Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	
	public Principal getPrincipalInstance() {
		if (this.principal == null) {
			this.principal = new Principal();
		}
		
		return (Principal) this.principal;
	}

}
