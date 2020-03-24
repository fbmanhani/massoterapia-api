package br.edu.ifsp.manhani.massoterapia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe para envelopamento de respostas de uma API.
 * 
 * @author felipe.manhani
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private Object body;

}
