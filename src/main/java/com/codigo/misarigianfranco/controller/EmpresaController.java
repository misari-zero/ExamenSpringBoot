package com.codigo.misarigianfranco.controller;

import com.codigo.misarigianfranco.entity.EmpresaEntity;
import com.codigo.misarigianfranco.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/examenspring/v1/empresa")
@AllArgsConstructor
@Tag(
        name = "API DE MANTENIMIENTO EMPRESA",
        description = "Esta api contiene todos los endpoints para realizar el mantenimiento de la entidad empresa"
)
public class EmpresaController {
    private final EmpresaService empresaService;
    @PostMapping("/crear")
    @Operation(summary = "Registrar una nueva empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa registrada correctamente",
                        content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = EmpresaEntity.class))})
    })
    public ResponseEntity<EmpresaEntity> crear(@RequestBody EmpresaEntity empresaEntity){
        return ResponseEntity.ok(empresaService.crear(empresaEntity));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una empresa por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))})
    })
    public ResponseEntity<Optional<EmpresaEntity>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @GetMapping("/todos")
    @Operation(summary = "Listar todas las empresas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de empresas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))})
    })
    public ResponseEntity<List<EmpresaEntity>> buscarTodos(){
        return ResponseEntity.ok(empresaService.buscarTodos());
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Modificar datos de empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modificacion completada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))})
    })
    public ResponseEntity<EmpresaEntity> actualizar(@PathVariable Long id, @RequestBody EmpresaEntity empresaEntity){
        return ResponseEntity.ok(empresaService.actualizar(id,empresaEntity));
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar registro de una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa eliminada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))})
    })
    public ResponseEntity<EmpresaEntity> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.borrar(id));
    }
}
