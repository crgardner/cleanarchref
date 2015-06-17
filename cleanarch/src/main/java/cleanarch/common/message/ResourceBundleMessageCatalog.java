package cleanarch.common.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleMessageCatalog implements MessageCatalog {

    private final String bundleName;

    public ResourceBundleMessageCatalog(String bundleName) {
        this.bundleName = bundleName;
    }

    @Override
    public Message assembleFrom(MessageKey messageKey, Object...arguments) {
        return createMessage(messageKey, Locale.getDefault(), arguments);
    }
    
    private Message createMessage(MessageKey messageKey, Locale locale, Object...arguments) {
        return new StandardMessage(completeTextFrom(messageKey, locale, arguments));
    }

    private String completeTextFrom(MessageKey messageKey, Locale locale, Object[] arguments) {
        String messageText = bundleFor(locale).getString(messageKey.name());

        return (arguments.length == 0) ? messageText : new MessageFormat(messageText, locale).format(arguments);
    }

    private ResourceBundle bundleFor(Locale locale) {
        return ResourceBundle.getBundle(bundleName, locale);
    }

    @Override
    public Message assembleFrom(Locale locale, MessageKey messageKey, Object...arguments) {
        return createMessage(messageKey, locale, arguments);
    }

}
