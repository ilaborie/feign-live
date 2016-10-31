package org.ilaborie.feign.client;

import feign.Client;
import feign.Request;
import feign.Response;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Java9Http2Client implements Client {
    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        try {
            System.out.println("Using JEP 110");

            // handle body
            byte[] reqBody = null;

            // apply headers

            // Execute request

            // retrieve values
            int status = 000;
            byte[] body = null;
            Map<String, Collection<String>> headers = null;

            // Build Feign response
            return null;
        } finally {
            System.out.println("Done");
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
