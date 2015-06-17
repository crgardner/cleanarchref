package cleanarch.common.exception;

import cleanarch.common.message.MessageKey;

public class InvalidException extends ApplicationException {
    private static final long serialVersionUID = 1L;

    public InvalidException(MessageKey messageKey, String message, Object...orderedDetails) {
        super(messageKey, message, orderedDetails);
    }

}
