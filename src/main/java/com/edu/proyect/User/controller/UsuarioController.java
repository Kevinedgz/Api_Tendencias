package com.edu.proyect.User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.edu.proyect.User.model.Usuario;
import com.edu.proyect.User.service.UsuarioService;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService userS;

    @GetMapping("/usuariolistar")
    public ResponseEntity<List<Usuario>> getAllFacture(){       
			return ResponseEntity.status(HttpStatus.OK).body(userS.findByAll());
		
    }

    @GetMapping("/usuariobuscarById/{id}")
    public ResponseEntity<Usuario> facturaById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userS.findById(id));
    }

    @PostMapping("/usuariosave")
    public ResponseEntity<Usuario> saveFacture(@RequestBody Usuario user){
        return new ResponseEntity<>(userS.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/usuariodelete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletefactura(@PathVariable("id") Integer id){
    	userS.delete(id);
    }

    @PutMapping("/usuarioupdate/{id}")
    public ResponseEntity<Usuario> updateFactura(@RequestBody Usuario user, @PathVariable("id") Integer id){
        Usuario userD = userS.findById(id);
        if(userD == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            try {
            	userD.setNombre(user.getNombre());
            	userD.setEmail(user.getEmail());
            	userD.setEstado(user.getEstado());
            	userD.setClave(user.getClave());
                return ResponseEntity.ok(userS.save(userD));
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
