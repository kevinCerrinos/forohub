package com.kev.forohub.domain.curso;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public DatosRespuestaCurso registrarCurso(Curso curso){
        cursoRepository.save(curso);
        return new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }
}
