package dk.tbsalling.ais.decoder.aisdecoder;

import dk.tbsalling.aismessages.ais.messages.AISMessage;
import dk.tbsalling.aismessages.nmea.NMEAMessageHandler;
import dk.tbsalling.aismessages.nmea.exceptions.NMEAParseException;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequestScope
public class AisdecoderService implements Consumer<AISMessage> {

    public List<AISMessage> decode(List<String> nmeaMessagesAsStrings) {

        NMEAMessageHandler nmeaMessageHandler = new NMEAMessageHandler("SRC1", this);

        // Decode all received messages
        nmeaMessagesAsStrings.forEach(nmeaMessageAsString -> {
            try {
                NMEAMessage nmeaMessage = new NMEAMessage(nmeaMessageAsString);
                nmeaMessageHandler.accept(nmeaMessage);
            } catch(NMEAParseException e) {
                System.err.printf(e.getMessage());
            }
        });

        // Flush receiver for unparsed message fragments
        List<NMEAMessage> unparsedMessages = nmeaMessageHandler.flush();
        unparsedMessages.forEach(unparsedMessage -> {
            System.err.println("NMEA message not used: " + unparsedMessage);
        });

        // Return result
        return aisMessages;
    }

    private final List<AISMessage> aisMessages = new LinkedList<>();

    @Override
    public void accept(AISMessage aisMessage) {
        aisMessages.add(aisMessage);
    }

}
