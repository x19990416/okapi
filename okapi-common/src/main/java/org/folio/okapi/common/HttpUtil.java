package org.folio.okapi.common;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;

public class HttpUtil {
  private HttpUtil() {
    throw new IllegalStateException(this.toString());
  }
  
  public static void endHandler(HttpServerRequest req, Handler<Void> endHandler) {
    if (req.isEnded()) {
      endHandler.handle(null);
    } else {
      req.endHandler(endHandler);
    }
  }

  public static void handler(HttpServerRequest req, Handler<Buffer> handler) {
    if (!req.isEnded()) {
      req.handler(handler);
    }
  }
}
