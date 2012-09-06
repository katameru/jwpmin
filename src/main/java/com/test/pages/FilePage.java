package com.test.pages;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

public class FilePage {
    
    @Inject
    @Path("context:/audio.m4a")
    private Asset fileServedAsset;
    private InputStream fileServed;
    
    @SuppressWarnings("unused")
    private Object onActivate() throws IOException {
        fileServed = fileServedAsset.getResource().openStream();
        StreamResponse media = new MediaResponse();
        return media;
    }
    
    private class MediaResponse implements StreamResponse {
        
        public void prepareResponse(Response response) {
            try {
                response.setContentLength(fileServed.available());
            } catch (IOException e) {
            }
        }
        
        public InputStream getStream() {
            return fileServed;
        }
        
        public String getContentType() {
            return "audio/m4a";
        }
    }
}
