package dk.tbsalling.ais.decoder.aisdecoder;

import dk.tbsalling.aismessages.ais.messages.AISMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AisdecoderController {

    @Autowired
    private AisdecoderService aisdecoderService;

    @RequestMapping(
            value = "/decode",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<AISMessage> greeting(@RequestBody List<String> nmea) {
        return aisdecoderService.decode(nmea);
    }

}