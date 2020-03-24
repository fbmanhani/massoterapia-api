package br.edu.ifsp.manhani.massoterapia.model;

import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseDocumentId;
import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseDocumentPath;

import lombok.Data;

@Data
@FirebaseDocumentPath("/fila")
public class Fila {

    @FirebaseDocumentId
    private Long id;

    private Posicao posicao;

}
