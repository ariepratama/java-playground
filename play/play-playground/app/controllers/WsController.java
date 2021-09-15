package controllers;


import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import play.mvc.WebSocket;

import java.time.Duration;

public class WsController {

    public WebSocket socket() {
        return WebSocket.Text.accept(request -> {
            Sink<String, ?> in =  Sink.foreach(System.out::println);

            Source<String, ?> out = Source.tick(Duration.ofSeconds(1), Duration.ofSeconds(1), "Hi")
                    .concat(Source.maybe());

            return Flow.fromSinkAndSource(in, out);
        });
    }
}
