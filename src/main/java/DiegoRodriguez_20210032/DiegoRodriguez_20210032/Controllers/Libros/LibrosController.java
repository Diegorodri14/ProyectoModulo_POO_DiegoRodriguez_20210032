package DiegoRodriguez_20210032.DiegoRodriguez_20210032.Controllers.Libros;

import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Models.DTO.Libros.LibrosDTO;
import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Services.Libros.LibrosService;
import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Libros")
public class LibrosController {

    @Autowired
    private LibrosService service;

    //------------ CONSULTAR LIBROS
    @GetMapping("/IngresarLibro")
    public ResponseEntity<List<LibrosDTO>> AgregarLibro(){
        return ResponseEntity.ok(service.ObtenerLibros());
    }


    //------------ INSERCION DE LIBROS
    @PostMapping("/InsertarLibros")
    public ResponseEntity<?> AgregarLibro(@Valid @RequestBody LibrosDTO json, BindingResult bindingResult, HttpServlet resquest) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            LibrosDTO respuesta = service.AgregarLibro(json);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "status", "Registrado",
                            "data", respuesta
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "message", "Error al registrar libro",
                    "detail", e.getMessage()
            ));
        }
    }

        //-------------- ACTUALIZAR LIBRO
        @PutMapping("/EditarLibro/{id}")
        public ResponseEntity <?> ActualizarLibro(@PathVariable Long id, @Valid @RequestBody LibrosDTO data, BindingResult bindingResult){

            if (bindingResult.hasErrors()){
                Map<String, String> errores = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
                return ResponseEntity.badRequest().body(errores);
            }

            try{
                LibrosDTO dto = service.ActualizarLibro(id, data);
                return ResponseEntity.ok(Map.of(
                        "message", "Registro actualiza",
                        "data", dto
                ));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(Map.of(
                        "status", "Error",
                        "message", "Error al actualizar libro",
                        "details", e.getMessage()
                ));
            }
        }

        @DeleteMapping("/EliminarDato/{id]")
        public ResponseEntity<?> BorrarLibro(@PathVariable Long id)
        {
            try{
                if (!service.B)
            }
    }
}
