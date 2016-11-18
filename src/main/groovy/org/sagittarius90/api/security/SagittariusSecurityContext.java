package org.sagittarius90.api.security;

import java.security.Principal;

public class SagittariusSecurityContext implements javax.ws.rs.core.SecurityContext {
	
	String authenticationScheme;
	private Principal sagittariusPrincipal;
	
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
		return getSagittariusPrincipal();
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

	public Principal getSagittariusPrincipal() {
		return sagittariusPrincipal;
	}

	public void setSagittariusPrincipal(SagittariusPrincipal sagittariusPrincipal) {
		this.sagittariusPrincipal = sagittariusPrincipal;
	}
	
	public SagittariusPrincipal getSagittariusPrincipalInstance() {
		if (this.sagittariusPrincipal == null) {
			this.sagittariusPrincipal = new SagittariusPrincipal();
		}
		
		return (SagittariusPrincipal) this.sagittariusPrincipal;
	}

}
