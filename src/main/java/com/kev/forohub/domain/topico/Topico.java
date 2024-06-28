package com.kev.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kev.forohub.domain.curso.Curso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String titulo;
    @Column(unique = true)
    @NotBlank
    private String mensaje;
    @NotNull
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
    @NotNull
    private String autor;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    @JsonBackReference
    private Curso curso;


    public Topico(DatosRegistroTopico datos, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.CREATED;
        this.autor = datos.autor();
        this.curso = curso;
    }

    public void updateDatos(DatosUpdateTopico datosUpdateTopico) {
        if(datosUpdateTopico != null){
            if(datosUpdateTopico.titulo() != null){
                this.titulo = datosUpdateTopico.titulo();
            }
            if(datosUpdateTopico.mensaje() != null){
                this.mensaje = datosUpdateTopico.mensaje();
            }
            this.fechaActualizacion = LocalDateTime.now();
            this.status = Status.UPDATED;
        }
    }
}
