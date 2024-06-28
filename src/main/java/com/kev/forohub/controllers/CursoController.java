package com.kev.forohub.controllers;

import com.kev.forohub.domain.curso.Curso;
import com.kev.forohub.domain.curso.CursoService;
import com.kev.forohub.domain.curso.DatosRegistroCurso;
import com.kev.forohub.domain.curso.DatosRespuestaCurso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder){
        var curso = cursoService.registrarCurso(new Curso(datos));
        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.id()).toUri();
        return ResponseEntity.created(url).body(curso);
    }

    @GetMapping
    public List<Curso> listar(){
        return cursoService.listar();
    }
}
