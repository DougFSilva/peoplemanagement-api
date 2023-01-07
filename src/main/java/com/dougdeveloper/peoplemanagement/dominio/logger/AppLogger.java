package com.dougdeveloper.peoplemanagement.dominio.logger;

/**
 * A interface <b>AppLogger</b> define os métodos a serem implementados pela camada de infrastrutura referente a geração de logs.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
public interface AppLogger {

	void error(String mensagem, Class<?> clazz);
	
	void warn(String mensagem, Class<?> clazz);
	
	void info(String mensagem, Class<?> clazz);
	
	void debug(String mensagem, Class<?> clazz);
	
	void trace(String mensagem, Class<?> clazz);
	
}
