package br.org.com.hc.core.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAOGenerico<T, I> implements Serializable {

	private static final long serialVersionUID = 4575263698407140574L;

	private EntityManager entityManager;
	private Class<T> classe;

	public DAOGenerico(EntityManager entityManager, Class<T> classe) {
		this.entityManager = entityManager;
		this.classe = classe;
	}

	public T buscarPorId(I id) {
		return this.entityManager.find(classe, id);
	}
	
	public T incluir(T entidade) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(entidade);
			this.entityManager.getTransaction().commit();
			return entidade;
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public void atualizar(T entidade) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(entidade);
			this.entityManager.getTransaction().commit();

		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public void excluir(T entidade) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(entidade);
			this.entityManager.getTransaction().commit();

		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public List<T> listarTodos(String... campoOrdenacao) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<T> query = criteriaBuilder.createQuery(classe);
		Optional<List<String>> campoOrdenacaoOptional = Optional.ofNullable(Arrays.asList(campoOrdenacao));

		if (campoOrdenacaoOptional.isPresent()) {
			Root<T> campos = query.from(classe);
			query.orderBy(campoOrdenacaoOptional
					.get()
					.stream()
					.map(campo -> criteriaBuilder.asc(campos.get(campo)))
					.collect(Collectors.toList())
					);
		}

		List<T> lista = this.entityManager.createQuery(query).getResultList();

		return lista;
	}

}
