package com.example.demo.markdown;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.util.options.MutableDataHolder;
public class ImageAdaperExtension implements HtmlRenderer.HtmlRendererExtension {
    @Override
    public void rendererOptions(final MutableDataHolder options) {
        // add any configuration settings to options you want to apply to everything, here
    }

    @Override
    public void extend(final HtmlRenderer.Builder rendererBuilder, final String rendererType) {
        rendererBuilder.attributeProviderFactory(new IndependentAttributeProviderFactory() {
			
			@Override
			public AttributeProvider create(NodeRendererContext context) {
				// TODO Auto-generated method stub
				 return new CustomAttributeProvider();
			}
		});
    }

    static ImageAdaperExtension create() {
        return new ImageAdaperExtension();
    }
}

