package org.sagittarius90.api.security;

import java.security.Principal;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {
	
	String authenticationScheme;
	private Principal principal;
	
	public class SagittariusPrincipal implements Principal {
		private String username;
		private String password;
		
		@Override
		public String getName() {
			return username;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	@Override
	public Principal getUserPrincipal() {
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

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(SagittariusPrincipal principal) {
		this.principal = principal;
	}
	
	public SagittariusPrincipal getSagittariusPrincipalInstance() {
		if (this.principal == null) {
			this.principal = new SagittariusPrincipal();
		}
		
		return (SagittariusPrincipal) this.principal;
	}

}
