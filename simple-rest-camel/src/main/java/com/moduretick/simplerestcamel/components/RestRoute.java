package com.moduretick.simplerestcamel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.moduretick.simplerestcamel.beans.NameAddress;
import com.moduretick.simplerestcamel.processors.InboundMessageProcessor;

@Component
public class RestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		.component("jetty")
		.host("0.0.0.0")
		.port(8080)
		.bindingMode(RestBindingMode.json)
		.enableCORS(true);
		
		rest("masterclass")
		.id("restRouteId")
		.produces("application/json")
		.post("nameAddress").type(NameAddress.class)
		.to("direct:process");
		
		from("direct:process").routeId("processMessageRouteId")
		.process(new InboundMessageProcessor())
		.log(LoggingLevel.INFO, "Transformed Body: ${body}")
		.convertBodyTo(String.class)
		.to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n");
	}

}
