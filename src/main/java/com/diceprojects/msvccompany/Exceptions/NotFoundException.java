package com.diceprojects.msvccompany.Exceptions;

/**
 * Excepción lanzada cuando no se encuentra la compañía solicitada.
 * <p>
 * Esta excepción se utiliza para indicar que no se ha podido localizar una compañía
 * en el repositorio según el identificador o algún otro criterio.
 * </p>
 *
 * @author
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva instancia de NotFoundException sin mensaje.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Crea una nueva instancia de NotFoundException con el mensaje especificado.
     *
     * @param message El mensaje que describe el error.
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de NotFoundException con el mensaje y la causa especificados.
     *
     * @param message El mensaje que describe el error.
     * @param cause   La causa que originó la excepción.
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una nueva instancia de NotFoundException con la causa especificada.
     *
     * @param cause La causa que originó la excepción.
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
