package com.emcc.markdown;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.emcc.markdown.core.MarkDown2HtmlWrapper;
import com.emcc.markdown.core.MarkdownEntity;

/**
 * Created by yihui on 2017/9/11.
 */
public class Html2ImageWrapperTest {



    @Test
    public void markdown2html() throws IOException {
        String file = "md/tutorial.md";
        MarkdownEntity html = MarkDown2HtmlWrapper.ofFile(file);
        System.out.println(html.toString());
    }

    public String inputStream2String(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
