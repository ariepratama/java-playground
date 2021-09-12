package controllers;


import akka.stream.javadsl.Source;
import play.libs.EventSource;
import play.mvc.Http;
import play.mvc.Result;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class SseController {
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public CompletionStage<Result> sse() {
        var dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        var tickSource = Source.tick(Duration.ofMillis(0), Duration.ofMillis(1000), System.currentTimeMillis());
        var eventStream = tickSource.map(Instant::ofEpochMilli)
                .map(inst -> LocalDateTime.ofInstant(inst, ZoneId.of("Asia/Singapore")))
                .map(dateFormatter::format)
                .map(EventSource.Event::event);


        return supplyAsync(() -> ok()
                .chunked(eventStream.via(EventSource.flow()))
                .as(Http.MimeTypes.EVENT_STREAM));
    }
}
