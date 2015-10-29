package com.example.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greeting {
		@XmlElement
		private final long id;
	    
		@XmlElement
		private final String content;
		
		public Greeting() {
			this.id = 0;
	        this.content = "";
	    }

	    public Greeting(long id, String content) {
	        this.id = id;
	        this.content = content;
	    }

	    public long getId() {
	        return id;
	    }

	    public String getContent() {
	        return content;
	    }
}
