package cleanarch.common.exception;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cleanarch.common.message.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationExceptionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Message message;

    @Mock
    private MessageKey messageKey;

    @Mock
    private MessageCatalog messageCatalog;
    
    private ApplicationException applicationException;

    @Test
    public void providesMeaningfulExceptionInformation() throws Exception {
        applicationException = new InvalidException(messageKey, "An exception occurred");
        when(messageCatalog.assembleFrom(messageKey)).thenReturn(message);

        assertThat(applicationException.createMessage(messageCatalog)).isEqualTo(message);
    }

    @Test
    public void providesMeaningfulExceptionInformationWithRegardToLocale() throws Exception {
        applicationException = new InvalidException(messageKey, "An exception occurred");
        when(messageCatalog.assembleFrom(Locale.getDefault(), messageKey)).thenReturn(message);

        assertThat(applicationException.createMessage(messageCatalog, Locale.getDefault())).isEqualTo(message);
    }

    @Test
    public void providesMeaningfulExceptionInformationIncludingDetails() throws Exception {
        applicationException = new InvalidException(messageKey,
                "Mutual funds cannot be added to the account owned by Joe.", "Mutual funds", "Joe");
        when(messageCatalog.assembleFrom(messageKey, "Mutual funds", "Joe")).thenReturn(message);

        assertThat(applicationException.createMessage(messageCatalog)).isEqualTo(message);
    }

    @Test
    public void providesMeaningfulExceptionInformationIncludingDetailsWithRegardToLocale() throws Exception {
        applicationException = new InvalidException(messageKey,
                "Mutual funds cannot be added to the account owned by Joe.", "Mutual funds", "Joe");
        when(messageCatalog.assembleFrom(Locale.getDefault(), messageKey, "Mutual funds", "Joe")).thenReturn(message);

        assertThat(applicationException.createMessage(messageCatalog, Locale.getDefault())).isEqualTo(message);
    }

}
