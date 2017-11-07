package com.emcc.markdown.core;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.sequence.BasedSequence;
public class CustomAttributeProvider implements AttributeProvider {
    @Override
    public void setAttributes(final Node node, final AttributablePart part, final Attributes attributes) {
        if (node instanceof Image && part == AttributablePart.LINK) {
        	Image image = (Image)node;
			BasedSequence url = image.getUrl();
			if(url.startsWith("/documents/uploads")){
				
			}
            // Put info in custom attribute instead
            attributes.replaceValue("class", "my-autolink-class");
        }
    }
}