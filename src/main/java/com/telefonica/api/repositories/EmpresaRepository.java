package com.telefonica.api.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.telefonica.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Empresa findByCnpj(String cnpj);
}
