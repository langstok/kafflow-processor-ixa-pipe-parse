package com.langstok.nlp.ixaparse;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.google.common.io.Files;

import eus.ixa.ixa.pipe.parse.Annotate;
import eus.ixa.ixa.pipe.parse.CLI;
import ixa.kaflib.KAFDocument;


@Service
@EnableConfigurationProperties(ParseProperties.class)
public class IxaParseService {

	private final static Logger LOGGER = Logger.getLogger(IxaParseService.class);

	@Autowired
	private ParseProperties properties;

	private final String version = CLI.class.getPackage().getImplementationVersion();

	private final String commit = CLI.class.getPackage().getSpecificationVersion();

	private String model;

	private Annotate annotator;


	public KAFDocument transform(KAFDocument kaf){
		LOGGER.info("KAF PARSE started (publicId / uri): " 
				+ kaf.getPublic().publicId + " / " + kaf.getPublic().uri);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			kaf = annotate(kaf);
		} catch (IOException e) {
			LOGGER.error("IOException for kaf: " + kaf.getPublic().publicId);
		}
		stopWatch.stop();
		LOGGER.info("KAF PARSE finished in " 
				+ stopWatch.getTotalTimeMillis() + " ms for publicId " + kaf.getPublic().publicId);
		return kaf;
	}

	public final KAFDocument annotate(KAFDocument kaf) throws IOException	{
		KAFDocument.LinguisticProcessor newLp = kaf.addLinguisticProcessor("constituency", "ixa-pipe-parse-" +
				Files.getNameWithoutExtension(model), this.version + "-" + this.commit);
		newLp.setBeginTimestamp();
		annotator.parseToKAF(kaf);
		newLp.setEndTimestamp();
		return kaf;
	}

	private Properties getAnnotateProperties(){
		Properties annotateProperties = new Properties();
		annotateProperties.setProperty("model", properties.getModel());
		annotateProperties.setProperty("language", properties.getLanguage());
		annotateProperties.setProperty("headFinder", properties.getHeadFinder());
		return annotateProperties;
	}

	@PostConstruct
	private void init(){
		this.model = properties.getModel();
		this.annotator = new Annotate(getAnnotateProperties());
	}

}
