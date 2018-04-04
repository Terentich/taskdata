package com.taskdata.timetracker.configuration;

import com.taskdata.timetracker.service.TimeTrackerService;
import com.taskdata.timetracker.service.TimeTrackerServiceImpl;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfiguration {
    @Bean("timeTrackerEndpoint")
    public CxfEndpoint timeTrackerServiceEndpoint() {
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setServiceClass(TimeTrackerService.class);
        endpoint.setAddress("/timeTrackerService");
        return endpoint;
    }

    @Bean
    public TimeTrackerService timeTrackerService() {
        return new TimeTrackerServiceImpl();
    }

    @Bean
    public RouteBuilder routeToQueue(TimeTrackerService timeTrackerService) {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("cxf:bean:timeTrackerEndpoint?")
                        .log("Incoming message with the action '${header.soapAction}' and input data '${body}'")
                        .toD("bean:timeTrackerService?method=${header.soapAction}")
                        .log("Outcoming message data '${body}'");


//                SoapJaxbDataFormat soapDF = new SoapJaxbDataFormat(TimeTrackerService.class.getPackage().getName(),
//                        new ServiceInterfaceStrategy(TimeTrackerService.class, false));
////                QName elementName = new QName("http://www.example.com/ZCSISUINT/",
////                        "NewOperation");
////                QNameStrategy strategy = new QNameStrategy(elementName);
////                soapDF.setElementNameStrategy(strategy);
//                from("cxf:bean:timeTrackerEndpoint")
//                        .log("Incoming message with the action '${header.soapAction}' and input data '${body}'")
//                        .onException(Exception.class)
//                        .handled(true)
//                        .marshal(soapDF)
//                        .end()
//                        .unmarshal(soapDF)
//                        .bean(timeTrackerService)
//                        .marshal(soapDF);

            }
        };
    }
}
