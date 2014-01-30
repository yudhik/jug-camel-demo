package org.jug.id.brainmaster.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.jug.id.brainmaster.camel.entity.TransaksiPenjualan;

public class RekapPenjualanRouteBuilder extends RouteBuilder {

	private static final String DEBUG_ALL_OPTION = "level=DEBUG&showAll=true";

	@Override
	public void configure() throws Exception {
		BindyCsvDataFormat csvDataFormat = new BindyCsvDataFormat(
				TransaksiPenjualan.class);

		from("file://rekap?delete=true")
			.filter(body().isNotNull())
			.to("activemq:queue:APP-1-REKAP");

		from("activemq:queue:APP-1-REKAP")
			.unmarshal(csvDataFormat)
			.multicast().parallelProcessing()
			.to("direct:transform-format", "direct:persist-data");

		from("direct:transform-format")
			.processRef("xmlTransformerProcessor")
			.to("log:APP-1-REKAP-TRANSFORM-FORMAT-LOG?" + DEBUG_ALL_OPTION)
			.to("file://target/result?fileName=$simple{exchangeId}.xml");

		from("direct:persist-data")
			.split(body())
			.to("log:APP-1-REKAP-PERSIST-DATA-LOG?" + DEBUG_ALL_OPTION,
				"jpa://org.jug.id.brainmaster.camel.entity.TransaksiPenjualan");

	}
}
