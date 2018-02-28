package edu.eci.cosw.samples;


import edu.eci.cosw.example.persistence.PatientsRepository;
import edu.eci.cosw.jpa.sample.model.Consulta;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import edu.eci.cosw.samples.services.PatientServices;
import edu.eci.cosw.samples.services.ServicesException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataRestApiApplication.class)
@WebAppConfiguration

@ActiveProfiles("test")
public class SpringDataRestApiApplicationTests {

        
@Autowired
    private PatientsRepository pr;

    @Autowired
    private PatientServices ps;

    @Test
	public void deberiaExistirUnPacienteEnBD(){

       PacienteId pId = new PacienteId(2101751, "cc");
        Paciente paciente = new Paciente(pId, "Alejandro Villarraga", new Date());
        pr.save(paciente);
        
        try {
            Paciente pacienteConsulta = ps.getPatient(2101751, "cc");
            Assert.assertEquals(pacienteConsulta.getId().getId()+pacienteConsulta.getId().getTipoId(),paciente.getId().getId()+paciente.getId().getTipoId());
        } catch (ServicesException ex) {
            Logger.getLogger(SpringDataRestApiApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("Error");
        }
    }

    @Test
    public void noDeberiaExistirPaciente(){

        pr.deleteAll();
        PacienteId pid=new PacienteId(2101751,"cc");
        Paciente paciente = new Paciente(pid, "Alejandro Villarraga", new Date(1990,01,01));
        Paciente pacientePrueba=pr.findOne(pid);
        Assert.assertNull("No existe",pacientePrueba);
    }

    @Test
    public void noExistePacientesConNConsultas() throws ServicesException {

        pr.deleteAll();
        PacienteId pid=new PacienteId(2101751,"cc");
        Paciente paciente = new Paciente(pid, "Alejandro Villarraga", new Date(2000,11,22));
        paciente.getConsultas().add(new Consulta(new Date(2012,5,21), "Gripa"));

        pr.save(paciente);

        List<Paciente> pacientes = ps.topPatients(2);

        Assert.assertEquals("Lista vacia",pacientes.size(),0);

    }

    @Test
    public void existePacientesConNConsultas() throws ServicesException {

        pr.deleteAll();
        PacienteId pid=new PacienteId(2101751,"cc");
        Paciente paciente = new Paciente(pid, "Alejandro Villarraga", new Date(1990,01,01));

        PacienteId pid2=new PacienteId(235689,"cc");
        Paciente paciente2 = new Paciente(pid2, "Andres Rojas", new Date(2010,12,12));

        PacienteId pid3=new PacienteId(748532,"cc");
        Paciente paciente3 = new Paciente(pid3, "Andrea Romero", new Date(2001,11,11));

        pr.save(paciente);

        paciente2.getConsultas().add(new Consulta(new Date(2017,9,15), "Apendicitis"));
        pr.save(paciente2);

        paciente3.getConsultas().add(new Consulta(new Date(2017,9,16), "Fractura"));
        paciente3.getConsultas().add(new Consulta(new Date(2017,9,17), "Migraña"));
        pr.save(paciente3);

        List<Paciente> pacientes = ps.topPatients(1);

        Assert.assertEquals("Lista",pacientes.size(),2);
    }

}
