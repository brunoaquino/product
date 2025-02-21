package br.com.foursales.product.infrastructure.adapters.output.exception;

import br.com.foursales.product.domain.exception.PedidoException;
import br.com.foursales.product.domain.exception.ProdutoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomizedExceptionAdapter extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
        final ExceptionErrorResponse exceptionResponse = new ExceptionErrorResponse(LocalDateTime.now(), ex.getMessage(),
                List.of(request.getDescription(false)));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProdutoException.class)
    public final ResponseEntity<Object> handleProdutoException(final ProdutoException ex, final WebRequest request) {
        final ExceptionErrorResponse exceptionResponse = new ExceptionErrorResponse(LocalDateTime.now(), ex.getMessage(),
                List.of(request.getDescription(false)));

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PedidoException.class)
    public final ResponseEntity<Object> handlePedidoException(final PedidoException ex, final WebRequest request) {
        final ExceptionErrorResponse exceptionResponse = new ExceptionErrorResponse(LocalDateTime.now(), ex.getMessage(),
                List.of(request.getDescription(false)));

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        final List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });

        final ExceptionErrorResponse exceptionResponse = new ExceptionErrorResponse(LocalDateTime.now(), "Validation Failed", errors);

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}