package br.com.empresa.loja.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mongodb.MongoWriteException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	private static final String EMAIL = "email"; 
	private static final String MSG_EMAIL_DUPLICADO = "O e-mail informado já existe.";
	private static final int DUPLICATED_KEY_CODE = 11000;
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public List<ErroDeFormulario> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormulario> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormulario erro = new ErroDeFormulario(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({MongoWriteException.class})
	public List<ErroDeFormulario> handleMongoWrite(MongoWriteException exception) {
		List<ErroDeFormulario> dto = new ArrayList<>();
		System.out.println(exception.getCode());
		ErroDeFormulario erro;
		if(exception.getCode() == DUPLICATED_KEY_CODE) {
			erro = new ErroDeFormulario(EMAIL, MSG_EMAIL_DUPLICADO);
		}
		else{
			erro = new ErroDeFormulario(String.valueOf(exception.getCode()), exception.getMessage());
		}
		
		dto.add(erro);
		
		return dto;
	}

}
