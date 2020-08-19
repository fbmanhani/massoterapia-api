package br.edu.ifsp.manhani.massoterapia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtAuthenticationDTO {

	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";

	public JwtAuthenticationDTO(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
