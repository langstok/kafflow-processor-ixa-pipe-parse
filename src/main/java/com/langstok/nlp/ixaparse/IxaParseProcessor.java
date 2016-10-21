package com.langstok.nlp.ixaparse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import ixa.kaflib.KAFDocument;


@EnableBinding(Processor.class)
public class IxaParseProcessor {
	
	@Autowired
	IxaParseService ixaParseService;
 
	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public KAFDocument handle(KAFDocument kaf) {
      return ixaParseService.transform(kaf);
    }
}