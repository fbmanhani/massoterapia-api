package br.edu.ifsp.manhani.massoterapia.model.firebase;

import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseDocumentId;
import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseDocumentPath;

import br.edu.ifsp.manhani.massoterapia.model.Posicao;
import lombok.Data;

@Data
@FirebaseDocumentPath("/fila")
public class Fila {

    @FirebaseDocumentId
    private Long id;

    private Posicao posicao;

}
