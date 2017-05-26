package com.langstok.nlp.ixaparse;

import ixa.kaflib.KAFDocument;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IxaParseApplicationTests {

	private static final Logger logger = Logger.getLogger(IxaParseApplicationTests.class);

	@Autowired
	private IxaParseService ixaParseService;
	private KAFDocument kafDocument;
	private KAFDocument result;

	@Test
	public void transform() throws Exception {
		logger.info("Start transform");
		result = ixaParseService.transform(kafDocument);
		logger.info("Transform finished");

	}

	@Before
	public void loadDocument() throws Exception {
		File file = new File(IxaParseApplicationTests.this.getClass().getResource("/wikinews_1173_en-excl-parse.naf").getFile());
		kafDocument = KAFDocument.createFromFile(file);
	}

	@After
	public void writeDocument() throws IOException {
		Files.write(Paths.get("./kafoutput.naf"), result.toString().getBytes());
	}

}
