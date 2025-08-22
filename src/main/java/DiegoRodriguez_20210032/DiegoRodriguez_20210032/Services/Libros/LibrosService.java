package DiegoRodriguez_20210032.DiegoRodriguez_20210032.Services.Libros;

import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Entities.Libros.LibrosEntity;
import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Exceptions.ExceptionCampoNoEncotrado;
import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Exceptions.ExceptionDatoNoEncontrado;
import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Models.DTO.Libros.LibrosDTO;
import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Repositorys.Libros.LibrosRepository;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LibrosService {

    @Autowired
    private LibrosRepository repo;

    public List<LibrosDTO> ObtenerLibros(){
        List<LibrosEntity> list = repo.findAll();
        return list.stream()
                .map(this::ConvertirADTO)
                .collect(Collectors.toList());
    }

    private LibrosDTO ConvertirADTO(LibrosEntity entity){
        LibrosDTO dto = new LibrosDTO();

        dto.setId(entity.getId());
        dto.setAutor_Id(entity.getAutor_Id());
        dto.setTitulo(entity.getTitulo());
        dto.setISBN(entity.getISBN());
        dto.setAño_Publicacion(entity.getAño_Publicacion());
        dto.setGenero(entity.getGenero());
        return dto;
    }

    private LibrosEntity ConvertirAEntity(LibrosDTO data){
        LibrosEntity entity = new LibrosEntity();
        entity.setId(data.getId());
        entity.setAutor_Id(data.getAutor_Id());
        entity.setTitulo(data.getTitulo());
        entity.setISBN(data.getISBN());
        entity.setAño_Publicacion(data.getAño_Publicacion());
        entity.setGenero(data.getGenero());
        return entity;
    }

    //----------- Metodos de agregacion Ver Datos, Actualizacion y Eliminacion

    //Metodo para agregar un Libro
    public LibrosDTO AgregarLibro (@Valid LibrosDTO data){
        if (data == null || data.getTitulo().isEmpty()){
            throw new IllegalArgumentException("El campo de titulo no debe de ser nulo");
        }
        try {
            LibrosEntity entity = ConvertirAEntity(data);
            LibrosEntity LibroGuardado = repo.save(entity);
            return ConvertirADTO(LibroGuardado);
        }catch (Exception e){
            log.error("Error al registrar el libro" + e.getMessage());
            throw new ExceptionCampoNoEncotrado("Error al registrar libro " + e.getMessage());
        }
    }

    //Metodo para actualizar un Libro
    public LibrosDTO ActualizarLibro(Long id, @Valid LibrosDTO json){
                LibrosEntity existente = repo.getReferenceById(id);

                existente.setAutor_Id(json.getAutor_Id());
                existente.setTitulo(json.getTitulo());
                existente.setISBN(json.getISBN());
                existente.setAño_Publicacion(json.getAño_Publicacion());
                existente.setGenero(json.getGenero());

                LibrosEntity actualizado = repo.save(existente);
                return ConvertirADTO(actualizado);
    }

    //Metodo para eliminar un Libro
    public boolean BorrarLibro (Long id){
        try{
            LibrosEntity existente = repo.getReferenceById(id);
            if (existente != null){
                repo.deleteById(id);
                return true;
            }else{
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("Libro no encontrado " + id + " para eliminar", 1);
        }
    }
}
