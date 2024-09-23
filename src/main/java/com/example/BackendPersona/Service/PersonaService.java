package com.example.BackendPersona.Service;

import com.example.BackendPersona.Entity.Persona;
import com.example.BackendPersona.InterfaceService.IServicePersona;
import com.example.BackendPersona.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonaService implements IServicePersona {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> obtenerPorId(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> nuevaPersona(Persona persona) {
        try {
            Persona nuevaPersona = Persona.builder()
                    .nombre(persona.getNombre())
                    .apellido(persona.getApellido())
                    .edad(persona.getEdad())
                    .build();
            personaRepository.save(nuevaPersona);
            return ResponseEntity.ok("Recurso guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salio mal en esta acción");
        }
    }

    @Override
    public ResponseEntity<String> actualizarPersona(Long id, Persona persona) {
        Optional<Persona> personaBuscada = personaRepository.findById(id);
        if (personaBuscada.isPresent()) {
            Persona personaActuaizada = Persona.builder()
                    .id(id)
                    .nombre(persona.getNombre())
                    .apellido(persona.getApellido())
                    .edad(persona.getEdad())
                    .build();
            personaRepository.save(personaActuaizada);
            return ResponseEntity.ok("Recurso actualizado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El recurso solicitado con el ID " + id + " no fue encontrado");
    }

    @Override
    public ResponseEntity<String> actualizarParcialmente(Long id, Map<String,String> json) {
        Optional<Persona> personaBuscada = personaRepository.findById(id);
        if (personaBuscada.isPresent()){
            if (json.containsKey("id")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No es posible actualizar el campo 'id'");
            }
            if (json.containsKey("nombre")) {
                personaBuscada.get().setNombre(json.get("nombre"));
            }
            if (json.containsKey("apellido")) {
                personaBuscada.get().setApellido(json.get("apellido"));
            }
            if (json.containsKey("edad")) {
                try {
                    personaBuscada.get().setEdad(Integer.parseInt(json.get("edad")));
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato inválido para 'edad'");
                }
            }
            personaRepository.save(personaBuscada.get());
            return ResponseEntity.ok("Recurso actualizado parcialmente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El recurso solicitado con el ID " + id + " no fue encontrado");
    }

    @Override
    public ResponseEntity<String> obtenerEncabezados(Long id) {
        Optional<Persona> personaExistente = personaRepository.findById(id);
        if (personaExistente.isPresent()) {
            // Devolvemos solo los encabezados (por ejemplo, la fecha de última modificación)
            HttpHeaders headers = new HttpHeaders();
            return ResponseEntity.ok()
                    .headers(headers)
                    .build();  // No devolvemos cuerpo
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El recurso con el ID " + id + " no fue encontrado");
    }

    @Override
    public ResponseEntity<String> eliminarPersonaPorId(Long id) {
        Optional<Persona> personaBuscada = personaRepository.findById(id);
        if (personaBuscada.isPresent()) {
            personaRepository.deleteById(id);
            return ResponseEntity.ok("Recurso eliminado correctamente de la base de datos");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El recurso solicitado con el ID " + id + " no fue encontrado");
    }
}
