/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cosw.samples.controllers;

import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.samples.services.PatientServices;
import edu.eci.cosw.samples.services.PatientServicesImpl;
import edu.eci.cosw.samples.services.ServicesException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping("/pacientes")
public class PatientsController {
    
    
    @Autowired
    PatientServices services;
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Paciente> getPaciente(@PathVariable int id) {
        try {
            Paciente p=services.getPatient(id, "cc");
            if (p!=null){
                return ResponseEntity.ok().body(p);        
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } catch (ServicesException ex) {
            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);            
        }         
        
    }
    
    
    @RequestMapping(path = "/consultasPaciente",method = RequestMethod.GET)
    @ResponseBody
    public List<Paciente> getTopPacientes() {
        try {
            return services.topPatients(10);
        } catch (ServicesException ex) {
            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
}
