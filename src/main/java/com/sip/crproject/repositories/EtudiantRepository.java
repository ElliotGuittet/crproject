package com.sip.crproject.repositories;

import org.springframework.data.repository.CrudRepository;
import com.sip.crproject.entities.Etudiant;

public interface EtudiantRepository extends CrudRepository<Etudiant, Integer> {
	public Etudiant findByNom(String nom);
}
