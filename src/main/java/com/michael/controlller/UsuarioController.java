package com.michael.controlller;

import com.michael.domain.Usuario;
import com.michael.service.UsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServicio;

    @GetMapping("/all")
    public List<Usuario> getAll() {
        return usuarioServicio.getAll();
    }

    @GetMapping("/all/{id_usuarios}")
    public ResponseEntity<?> findOne(@PathVariable("id_usuarios") int id) {
        return usuarioServicio.findOne(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("mensaje", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else {
            return usuarioServicio.save(usuario);
        }
    }

    @PutMapping("/actualizar/{id_usuarios}")
    public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable("id_usuarios") int id_usuario) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "Error en el campo '" + err.getField() + "'. Mensaje de error: " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("mensaje", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else {
            return usuarioServicio.update(id_usuario, usuario);
        }
    }

    @DeleteMapping("/eliminar/{id_usuarios}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id_usuarios") int id_usuario) {
        return usuarioServicio.Delete(id_usuario);
    }

}
