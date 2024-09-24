package com.example.BackendPersona.InterfaceService;

import com.example.BackendPersona.Entity.Persona;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IServicePersona {
    List<Persona> listarPersonas();
    Optional<Persona> obtenerPorId(Long id);
    ResponseEntity<String> nuevaPersona(Persona persona);
    ResponseEntity<String> actualizarPersona(Long id, Persona persona);
    boolean actualizarParcialmente(Long id, Map<String,String> data);
    ResponseEntity<String> obtenerEncabezados(Long id);
    ResponseEntity<String> eliminarPersonaPorId(Long id);
}
