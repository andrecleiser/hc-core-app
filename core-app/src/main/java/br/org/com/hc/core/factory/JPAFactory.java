package br.org.com.hc.core.factory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAFactory {

	private EntityManagerFactory emf;
	
	@Produces
	@RequestScoped
	public EntityManager criarEntityManeger() {
		return emf.createEntityManager();
	}
	
	public void fecharEntityManager(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}
	
	@PreDestroy
	public void antesDeFecharAplicacao(){
		if (emf.isOpen()) {
			emf.close();
		}
	}
	
	@PostConstruct
	public void carregaEntityManagerFactory(){
		this.emf = Persistence.createEntityManagerFactory("rhm_db");
	}	
}
