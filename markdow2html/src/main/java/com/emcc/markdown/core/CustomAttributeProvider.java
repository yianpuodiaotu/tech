package com.emcc.markdown.core;
import com.emcc.markdown.Application;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.util.html.Attributes;
public class CustomAttributeProvider implements AttributeProvider {
    @Override
    public void setAttributes(final Node node, final AttributablePart part, final Attributes attributes) {
        if (node instanceof Image && part == AttributablePart.LINK) {
        	Image image = (Image)node;
        	String result = "";
			String url = image.getUrl().toString();
			if(url.startsWith("http://192.168.66.162:4567/documents")){
				result = Application.getProperty("img_url_predix") + url.substring("http://192.168.66.162:4567/documents".length());
			}else if(url.startsWith("/documents")){
				result = Application.getProperty("img_url_predix") + url.substring("/documents".length());
			}
            // Put info in custom attribute instead
        	if(result.length() > 0){
        		attributes.replaceValue("src", result);
        	}
        }
    }
}