package com.moduretick.simplerestcamel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.moduretick.simplerestcamel.entity.NameAddress;

@Component
public class BatchJPAProcessingRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:readDB?period=10000")
		.routeId("readDBId")
		.to("jpa:"+ NameAddress.class.getName()+"?namedQuery=fetchAllRows")
		.log(LoggingLevel.INFO, "Fetched Rows: ${body}");
	}

}
