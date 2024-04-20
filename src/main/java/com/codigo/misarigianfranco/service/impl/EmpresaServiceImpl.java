package com.codigo.misarigianfranco.service.impl;

import com.codigo.misarigianfranco.constans.Constans;
import com.codigo.misarigianfranco.dao.EmpresaRepository;
import com.codigo.misarigianfranco.entity.EmpresaEntity;
import com.codigo.misarigianfranco.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    // Registrar en BD
    @Override
    public EmpresaEntity crear(EmpresaEntity empresaEntity) {
        return empresaRepository.save(empresaEntity);
    }

    // Buscar por id
    @Override
    public Optional<EmpresaEntity> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    // Buscar todos
    @Override
    public List<EmpresaEntity> buscarTodos() {
        return empresaRepository.findAll();
    }

    //Actualizar
    @Override
    public EmpresaEntity actualizar(Long id, EmpresaEntity empresaEntity) {
        Optional<EmpresaEntity> empresaRecuperada = empresaRepository.findById(id);
        if(empresaRecuperada.isPresent()){
            EmpresaEntity empresa = empresaRecuperada.get();
            empresa.setRazon_social(empresaEntity.getRazon_social());
            empresa.setTipo_documento(empresaEntity.getTipo_documento());
            empresa.setNumero_documento(empresaEntity.getNumero_documento());
            empresa.setCondicion(empresaEntity.getCondicion());
            empresa.setDireccion(empresaEntity.getDireccion());
            empresa.setDistrito(empresaEntity.getDistrito());
            empresa.setProvincia(empresaEntity.getProvincia());
            empresa.setDepartamento(empresaEntity.getDepartamento());
            empresa.setEs_agente_retencion(empresaEntity.isEs_agente_retencion());
            empresa.setEstado(Constans.STATUS_ACTIVE);
            empresa.setUsua_crea(Constans.USU_ADMIN);
            empresa.setDate_create(getTimestamp());
            empresa.setUsua_modif(Constans.USU_ADMIN);
            empresa.setDate_modif(getTimestamp());
            empresa.setUsua_delet(Constans.USU_ADMIN);
            empresa.setDate_delet(getTimestamp());
            return empresaRepository.save(empresa);
        }
        return null;
    }

    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }

    // Eliminar de manera Logica
    @Override
    public EmpresaEntity borrar(Long id) {
        Optional<EmpresaEntity> empresaRecuperada = empresaRepository.findById(id);
        if(empresaRecuperada.isPresent()){
            empresaRecuperada.get().setEstado(0);
            return empresaRepository.save(empresaRecuperada.get());
        }
        return null;
    }
}
