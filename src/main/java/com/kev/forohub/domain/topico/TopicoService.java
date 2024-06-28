package com.kev.forohub.domain.topico;

import com.kev.forohub.domain.curso.Curso;
import com.kev.forohub.domain.curso.CursoRepository;
import com.kev.forohub.helper.ResponseMessage;
import com.kev.forohub.helper.Type;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final TopicoRespository topicoRespository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRespository topicoRespository,CursoRepository cursoRepository){
        this.topicoRespository = topicoRespository;
        this.cursoRepository = cursoRepository;
    }

    public DatoRespuestaTopico registrarTopico(DatosRegistroTopico datos){

        if(datos.id_curso() == null){
            throw new ValidationException("El id de curso no debe ser nulo");
        }

        if (!cursoRepository.existsById(datos.id_curso())){
            throw new EntityNotFoundException("el id de referencia al curso no existe");
        }

        var curso = cursoRepository.getReferenceById(datos.id_curso());

        var topico = new Topico(datos,curso);

        topicoRespository.save(topico);

        return new DatoRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getStatus().toString(),
                topico.getFechaCreacion(),
                topico.getFechaActualizacion()
        );
    }

    public Page<DatosListadoTopico> listarTopicos(Pageable pageable) {
        return topicoRespository.findAll(pageable).map(DatosListadoTopico::new);
    }

    public DatoRespuestaTopico getTopico(Long id) {

        if(id == null){
            throw new ValidationException("El id del topico no debe ser nulo");
        }

        if (!topicoRespository.existsById(id)){
            throw new EntityNotFoundException("el id de referencia al topico no existe");
        }

        var topico = topicoRespository.getReferenceById(id);

        return new DatoRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getStatus().toString(),
                topico.getFechaCreacion(),
                topico.getFechaActualizacion()
        );
    }

    public DatosRespuestaTopicoUpdate updateTopico(Long idTopico, DatosUpdateTopico datosUpdateTopico) {
        if(idTopico == null){
            throw new ValidationException("El id del topico no debe ser nulo");
        }

        if (!topicoRespository.existsById(idTopico)){
            throw new EntityNotFoundException("el id de referencia al topico no existe");
        }

        var topico = topicoRespository.getReferenceById(idTopico);
        topico.updateDatos(datosUpdateTopico);

        return new DatosRespuestaTopicoUpdate(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getStatus().toString(),
                topico.getFechaActualizacion()
        );
    }

    public ResponseMessage deleteTopico(Long id) {
        if(id == null){
            throw new ValidationException("El id del topico no debe ser nulo");
        }

        if (!topicoRespository.existsById(id)){
            throw new EntityNotFoundException("el id de referencia al topico no existe");
        }
        var topico = topicoRespository.getReferenceById(id);
        topicoRespository.delete(topico);

        return new ResponseMessage(Type.SUCCESS,"El topico con id:"+id+" fue eliminado correctamente");
    }
}
