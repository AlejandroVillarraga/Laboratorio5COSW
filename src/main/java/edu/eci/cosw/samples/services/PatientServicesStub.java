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
import edu.eci.cosw.jpa.sample.model.PacienteId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
public class PatientServicesStub implements PatientServices{

    @Override
    public Paciente getPatient(int id, String tipoid) {
        return new Paciente(new PacienteId(1, "cc"),"Juan Perez",new Date());
    }

    @Override
    public List<Paciente> topPatients(int n) {
        List<Paciente> lp=new LinkedList<>();
        lp.add(new Paciente(new PacienteId(1, "cc"),"Juan Perez",new Date()));
        return lp;
    }
    
}
