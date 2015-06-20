package cleanarch.common.exception;

import java.util.Locale;

import cleanarch.common.message.*;

public abstract class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final MessageKey messageKey;
    private final Object[] orderedDetails;

    public ApplicationException(MessageKey messageKey, String message, Object... orderedDetails) {
        super(message);
        this.messageKey = messageKey;
        this.orderedDetails = orderedDetails;
    }

    public Message createMessage(MessageCatalog messageCatalog) {
        return messageCatalog.assembleFrom(messageKey, orderedDetails);
    }

    public Message createMessage(MessageCatalog messageCatalog, Locale locale) {
        return messageCatalog.assembleFrom(locale, messageKey, orderedDetails);
    }
}
