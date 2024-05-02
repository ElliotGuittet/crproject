package com.sip.crproject;

import static org.assertj.core.api.Assertions.assertThat; 

import java.util.List; 
 
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation; 
import org.junit.jupiter.api.Order; 
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.TestMethodOrder; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase; 
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace; 
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest; 
import org.springframework.test.annotation.Rollback;

import com.sip.crproject.entities.Etudiant;
import com.sip.crproject.repositories.EtudiantRepository;

@DataJpaTest 
@AutoConfigureTestDatabase(replace = Replace.NONE) 
@TestMethodOrder(OrderAnnotation.class)
public class TestDemoDataController {
  
    @Autowired 
    private EtudiantRepository repo; 
      
    @Test 
    @Rollback(false) 
    @Order(1) 
    public void testCreateEtudiant() { 
        Etudiant savedEtudiant = repo.save(new Etudiant("Guittet", 23)); 
          
        assertThat(savedEtudiant.getId()).isGreaterThan(0); 
    } 
     
    @Test 
    @Order(2) 
    public void testFindEtudiantByName() { 
        Etudiant etudiant = repo.findByNom("Guittet");
        
        assertThat(etudiant.getNom()).isEqualTo("Guittet"); 
    } 
     
    @Test 
    @Order(3) 
    public void testListProducts() { 
        List<Etudiant> etudiants = (List<Etudiant>) repo.findAll(); 
        
        assertThat(etudiants).size().isGreaterThan(0); 
    } 

    @Test 
    @Rollback(true) 
    @Order(4) 
    public void testUpdateEtudiant() { 
        Etudiant etudiant = repo.findByNom("Guittet"); 
        etudiant.setAge(22);         
        repo.save(etudiant); 
        
        Etudiant updatedEtudiant = repo.findByNom("Guittet");  
        
        assertThat(updatedEtudiant.getAge()).isEqualTo(22); 
    } 
     
    @Test 
    @Rollback(false) 
    @Order(5) 
    public void testDeleteEtudiant() { 
        Etudiant etudiant = repo.findByNom("Guittet"); 
        repo.deleteById(etudiant.getId()); 
        
        Etudiant deletedEtudiant = repo.findByNom("Guittet"); 
        
        assertThat(deletedEtudiant).isNull();        
    } 
} 