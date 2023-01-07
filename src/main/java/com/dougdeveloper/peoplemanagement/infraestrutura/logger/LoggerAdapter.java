package com.dougdeveloper.peoplemanagement.infraestrutura.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;

/**
 * A classe <b>LoggerAdapter</b> é uma Adapter responsável por implementar os métodos da interface <b>AppLoger</b>
 * para geração dos logs da API.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@Service
public class LoggerAdapter implements AppLogger {

	private Logger logger = LoggerFactory.getLogger(LoggerAdapter.class);


	@Override
	public void error(String mensagem, Class<?> clazz) {
		logger.error(mensagem, clazz);
	}

	@Override
	public void warn(String mensagem, Class<?> clazz) {
		logger.warn(mensagem, clazz);
	}
	
	@Override
	public void info(String mensagem, Class<?> clazz) {
		logger.info(mensagem, clazz);
	}

	@Override
	public void debug(String mensagem, Class<?> clazz) {
		logger.debug(mensagem, clazz);
	}

	@Override
	public void trace(String mensagem, Class<?> clazz) {
		logger.trace(mensagem, clazz);
	}

}
