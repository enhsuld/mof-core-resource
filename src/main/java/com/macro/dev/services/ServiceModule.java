package com.macro.dev.services;

import com.macro.dev.repository.mybatis.CrudMapper;
import com.macro.dev.repository.mybatis.DatatableMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceModule {

	@Bean
	public CrudService crudService(CrudMapper crudMapper) {
		return new CrudService(crudMapper);
	}

	@Bean
	public DatatableService datatableService(DatatableMapper datatableMapper) {
		return new DatatableService(datatableMapper);
	}
}
