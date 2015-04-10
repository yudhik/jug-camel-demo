package org.jug.id.brainmaster.camel.route;

import org.apache.camel.builder.RouteBuilder;

public class DynamicEndpointByControlFile extends RouteBuilder {

  private static final String DEBUG_ALL_OPTION = "level=DEBUG&showAll=true";


  @Override
  public void configure() throws Exception {
    from(
        "file://control-folder?autoCreate=false&delay=10000&readLockCheckInterval=10000&fileName=control.txt")
        .filter(body().isNotNull()).processRef("controlFileReaderProcessor");

    from("direct:fileProcessor").convertBodyTo(String.class).to(
        "log:fileProcessor?" + DEBUG_ALL_OPTION);

  }
}
