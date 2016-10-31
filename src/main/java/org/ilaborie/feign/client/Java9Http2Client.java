package org.ilaborie.feign.client;

import feign.Client;
import feign.Request;
import feign.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Java9Http2Client implements Client {

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        try {
            System.out.println("Using JEP 110");
            HttpRequest.Builder builder = HttpRequest.create(new URI(request.url()));
            // handle body
            byte[] reqBody = request.body();
            if (reqBody == null || reqBody.length == 0) {
                builder = builder.body(HttpRequest.noBody());
            } else {
                builder = builder.body(HttpRequest.fromByteArray(reqBody));
            }

            // apply headers
            for (Map.Entry<String, Collection<String>> entry : request.headers().entrySet()) {
                String values = entry.getValue().stream()
                        .collect(Collectors.joining(", "));
                builder = builder.setHeader(entry.getKey(), values);
            }

            // Execute request
            HttpResponse response = builder.method(request.method())
                    .response();

            // retrieve values
            int status = response.statusCode();
            byte[] body = response.body(HttpResponse.asByteArray());
            Map<String, Collection<String>> headers = mapList2MapCollection(
                    response.headers().map()
            );

            // Build Feign response
            return Response.builder()
                    .status(status)
                    .headers(headers)
                    .body(body)
                    .build();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Collection<String>> mapList2MapCollection(Map<String, List<String>> mapList) {
        return mapList.entrySet().stream()
                .collect(
                        toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().stream()
                                        .collect(toList())
                        )
                );
    }
}
