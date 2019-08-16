package br.org.com.hc.core.exceptionmappers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.org.com.hc.core.exception.util.ValidacaoParametrosApiErro;

@Provider
public class BeanValidationException implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		System.out.println("Validator Core");
		return Response.status(Response.Status.BAD_REQUEST).entity(prepareMessage(exception)).type("application/json")
				.build();
	}

	private List<ValidacaoParametrosApiErro> prepareMessage(ConstraintViolationException exception) {
		List<ValidacaoParametrosApiErro> listaDeErros = new ArrayList<>();

		for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
			listaDeErros.add(new ValidacaoParametrosApiErro(cv.getPropertyPath().toString(), cv.getMessage()));
		}
		return listaDeErros;
	}

}
