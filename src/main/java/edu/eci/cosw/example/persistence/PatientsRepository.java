/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.example.persistence;

import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author davillarraga
 */
@Service
public interface PatientsRepository extends JpaRepository<Paciente, PacienteId> {

    @Query("select u from Paciente u where u.consultas.size>=?1")
    List<Paciente> topPatients(int n);
    
    //@Query("select u from PACIENTES where u.Size()>=n")
    //List<Paciente> patientsWithMinimumNConsultations(int n);


}
