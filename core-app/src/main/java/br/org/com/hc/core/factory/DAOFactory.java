package br.org.com.hc.core.factory;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.org.com.hc.core.dao.DAOGenerico;

public class DAOFactory {
	
	@Inject
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Produces
	public <T, I> DAOGenerico<T, I> construtor(InjectionPoint injectionPoint) {
		ParameterizedType tipoClasse = (ParameterizedType) injectionPoint.getType();
		
		Class<T> classe = (Class<T>) tipoClasse.getActualTypeArguments()[0];
		
		return new DAOGenerico<T, I>(entityManager, classe);
	}

}
