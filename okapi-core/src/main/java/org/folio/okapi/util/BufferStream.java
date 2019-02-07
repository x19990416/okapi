package org.folio.okapi.util;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

public class BufferStream implements ReadStream<Buffer> {

  boolean active = false;
  Handler<Buffer> handler = null;
  Handler<Void> endHandler = null;
  Buffer buffer = null;

  public BufferStream(Buffer b) {
    buffer = b;
  }

  @Override
  public ReadStream<Buffer> exceptionHandler(Handler<Throwable> handler) {
    return this;
  }

  @Override
  public ReadStream<Buffer> handler(Handler<Buffer> handler) {
    this.handler = handler;
    return this;
  }

  @Override
  public ReadStream<Buffer> pause() {
    active = false;
    return this;
  }

  @Override
  public ReadStream<Buffer> resume() {
    active = true;   
    if (handler != null && buffer != null) {
      handler.handle(buffer);
    }
    if (endHandler != null) {
      endHandler.handle(null);
    }
    return this;
  }

  @Override
  public ReadStream<Buffer> endHandler(Handler<Void> endHandler) {
    this.endHandler = endHandler;
    return this;
  }

  
}
