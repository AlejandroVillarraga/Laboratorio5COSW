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
package edu.eci.cosw.samples.services;

import edu.eci.cosw.jpa.sample.model.Paciente;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */

@Service
public interface PatientServices {
    
    /**
     * Obj: consultar un paciente dado su ID y tipo ID
     * @param id
     * @param tipoid
     * @return la instancia del paciente
     * @throws ServicesException si hay un error interno o si 
     * no existe un paciente con dicho identificador.
     */
    public Paciente getPatient(int id, String tipoid) throws ServicesException;
    
    /**
     * Obj: retorna los pacientes que tengan más registradas más de N consultas.
     * @param n - el valor N a ser usado como parámetro en la consulta
     * @return el listao de pacientes que tengan más registradas más de N consultas.
     * @throws ServicesException si se presenta un error interno en la consulta.
     */
    public List<Paciente> topPatients(int n) throws ServicesException;
    
}
