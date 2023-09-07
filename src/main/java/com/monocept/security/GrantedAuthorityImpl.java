package com.monocept.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	private String authority;

	public GrantedAuthorityImpl(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public GrantedAuthorityImpl() {
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "GrantedAuthorityImpl [authority=" + authority + "]";
	}

}
