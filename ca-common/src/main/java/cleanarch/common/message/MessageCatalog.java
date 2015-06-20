package cleanarch.common.message;

import java.util.Locale;

public interface MessageCatalog {

    Message assembleFrom(MessageKey messageKey, Object...arguments);

    Message assembleFrom(Locale locale, MessageKey messageKey, Object...arguments);

}
