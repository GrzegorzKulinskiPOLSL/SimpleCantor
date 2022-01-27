package pl.projekt.simplecantor.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtility {
    public static HttpHeaders getHeader(String path, String id) {
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(path)
                .buildAndExpand(id).toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return responseHeaders;
    }
}
