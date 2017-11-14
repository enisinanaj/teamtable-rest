package org.sagittarius90.api.security;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {
	
	String authenticationScheme;
	private java.security.Principal principal;
	
	public class Principal implements java.security.Principal {
		private String username;
		private String password;
		private String token;

		public Principal() {
		}

		public Principal(String username, String password) {
			this.username = username;
			this.password = password;
		}

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

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}

	public SecurityContext(String token) {
		getPrincipalInstance().setToken(token);
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
