package br.edu.ifsp.manhani.massoterapia.repository;

import org.springframework.stereotype.Repository;

import com.github.alperkurtul.firebaserealtimedatabase.annotation.FirebaseRealtimeDbRepoServiceImpl;

import br.edu.ifsp.manhani.massoterapia.model.firebase.Fila;

@Repository
public class FilaRepository extends FirebaseRealtimeDbRepoServiceImpl<Fila, String> {

}
