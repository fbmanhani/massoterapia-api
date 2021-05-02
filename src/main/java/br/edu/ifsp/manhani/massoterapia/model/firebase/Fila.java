package br.edu.ifsp.manhani.massoterapia.model.firebase;

import java.util.List;

import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseDocumentId;
import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseDocumentPath;
import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseUserAuthKey;

import br.edu.ifsp.manhani.massoterapia.dto.FilaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FirebaseDocumentPath("/filas")
public class Fila {

	@FirebaseUserAuthKey
	private String authKey;

	@FirebaseDocumentId
	private String id;

	List<FilaDTO> posicoes;

}
