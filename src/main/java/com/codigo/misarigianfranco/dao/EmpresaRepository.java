package com.codigo.misarigianfranco.dao;

import com.codigo.misarigianfranco.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
}
