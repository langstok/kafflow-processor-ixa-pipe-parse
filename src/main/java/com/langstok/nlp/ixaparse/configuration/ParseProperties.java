package com.langstok.nlp.ixaparse.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="ixa.pipe.parse")
public class ParseProperties {
	
	/**
	 * Choose language; it defaults to the language value in incoming NAF file.\ns
	 */
	private String language = "en";
	
	
	/**
	 * Choose parsing model
	 */
	private String model;
	

	/**
	 * Choose between Collins or Semantic HeadFinder. "collins", "sem", "no" 
	 */
	private String headFinder = "sem";


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getHeadFinder() {
		return headFinder;
	}


	public void setHeadFinder(String headFinder) {
		this.headFinder = headFinder;
	}
	
}