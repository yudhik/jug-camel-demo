package org.jug.id.brainmaster.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.jug.id.brainmaster.camel.entity.TransaksiPenjualan;

public class RekapPenjualanRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		BindyCsvDataFormat csvDataFormat = new BindyCsvDataFormat(TransaksiPenjualan.class);
		
		from("file://rekap?delete=true")
			.filter(body().isNotNull())
//			.convertBodyTo(String.class)
			.to("direct:JMS-REKAP-APP1");
		
		from("direct:JMS-REKAP-APP1")
			.to("activemq:queue:APP-1-REKAP")
			.unmarshal(csvDataFormat)
			.split(body())
			.to("log:APP-1-REKAP-LOG?level=DEBUG&showAll=true")
			.to("jpa://org.jug.id.brainmaster.camel.entity.TransaksiPenjualan");
		
		
	}

}
