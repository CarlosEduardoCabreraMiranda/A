package com.example.BackendPersona.Controller;

import com.example.BackendPersona.Entity.Persona;
import com.example.BackendPersona.InterfaceService.IServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/persona")
public class PersonaController {
    @Autowired
    IServicePersona personaIService;

    @GetMapping("/all")
    public List<Persona> listaPersonas() {
        return personaIService.listarPersonas();
    }

    @GetMapping("/{id}")
    public Optional<Persona> obtenerPorId(@PathVariable Long id) {
        return personaIService.obtenerPorId(id);
    }

    @PostMapping("/save")
    public ResponseEntity<String> guardarPersona(@RequestBody Persona jsonPersona) {
        return personaIService.nuevaPersona(jsonPersona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPersona(@PathVariable Long id, @RequestBody Persona jsonPersona) {
        return personaIService.actualizarPersona(id, jsonPersona);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> actualizarParcialmentePersona(@PathVariable Long id, @RequestBody Map<String,String> data) {
         return personaIService.actualizarParcialmente(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long id) {
        return personaIService.eliminarPersonaPorId(id);
    }

    // Obtener solo los encabezados de una persona (HEAD)
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<String> obtenerEncabezados(@PathVariable Long id) {
        return personaIService.obtenerEncabezados(id);
    }
}
