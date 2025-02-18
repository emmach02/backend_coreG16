package com.backend3K6_2024.backendG16.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

import java.util.Arrays;
import java.util.List;

@Getter @Setter @ToString
public class ErrorEntity {
    private String handledBy;   // fines demostrativos simplemente (para facilitar seguimiento)
    private String exception;   // idem
    private HttpStatusCode oriStatus;   // idem
    private HttpStatusCode status;
    private String uri;
    private String localizedMessage;
    private List<String> messages;

    public ErrorEntity(
            String handledBy,
            String exception,
            HttpStatusCode oriStatus,
            HttpStatusCode status,
            String uri,
            String localizedMessage,
            List<String> messages
    ) {
        super();
        this.handledBy = handledBy;
        this.exception = exception;
        this.oriStatus = oriStatus;
        this.status = status;
        this.uri = uri;
        this.localizedMessage = localizedMessage;
        this.messages = messages;
    }

    public ErrorEntity(
            String handledBy,
            String exception,
            HttpStatusCode oriStatus,
            HttpStatusCode status,
            String uri,
            String localizedMessage,
            String message
    ) {
        this(handledBy, exception, oriStatus, status, uri, localizedMessage, Arrays.asList(message));
    }

}
