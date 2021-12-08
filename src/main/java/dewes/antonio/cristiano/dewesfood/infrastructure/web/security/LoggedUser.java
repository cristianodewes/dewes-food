package dewes.antonio.cristiano.dewesfood.infrastructure.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dewes.antonio.cristiano.dewesfood.domain.cliente.Cliente;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.Restaurante;
import dewes.antonio.cristiano.dewesfood.domain.usuario.Usuario;
import lombok.Getter;

@SuppressWarnings("serial")
public class LoggedUser implements UserDetails {

	@Getter
	private Usuario usuario;
	@Getter
	private Role role;
	private Collection<? extends GrantedAuthority> roles;

	public LoggedUser(Usuario usuario) {
		this.usuario = usuario;

		Role role;

		if (usuario instanceof Cliente) {
			
			role = Role.CLIENTE;
			
		} else if (usuario instanceof Restaurante) {
			
			role = Role.RESTAURANTE;
			
		} else {
			
			throw new IllegalStateException();
			
		}

		this.role = role;
		this.roles = List.of(new SimpleGrantedAuthority("ROLE_" + role));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
