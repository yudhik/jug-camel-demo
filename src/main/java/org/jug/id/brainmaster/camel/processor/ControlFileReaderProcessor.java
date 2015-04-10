package org.jug.id.brainmaster.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControlFileReaderProcessor implements Processor {

  private static final Logger LOG = LoggerFactory.getLogger(ControlFileReaderProcessor.class);

  @Override
  public void process(Exchange exchange) throws Exception {
    String[] contents = exchange.getIn().getBody(String.class).split(",");
    for (String content : contents) {
      LOG.info("content : {}", content);
      Exchange newExchange =
          exchange.getContext().createConsumerTemplate()
              .receive("file://control-folder?autoCreate=false&delay=60000&fileName=" + content);
      exchange.getContext().createProducerTemplate().send("direct:fileProcessor", newExchange);
    }
  }

}
