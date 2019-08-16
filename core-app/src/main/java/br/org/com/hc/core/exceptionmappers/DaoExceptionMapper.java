package br.org.com.hc.core.exceptionmappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.org.com.hc.core.exception.DaoException;
import br.org.com.hc.core.exception.util.ValidacaoParametrosApiErro;

@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException> {

	@Override
	public Response toResponse(DaoException exception) {
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(prepareMessage(exception))
				.type("application/json")
				.build();
	}
	
	private ValidacaoParametrosApiErro prepareMessage(DaoException exception) {
		return new ValidacaoParametrosApiErro("Erro em operação de manipulação de dados", exception.getMessage());
	}
	
}
