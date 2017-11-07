package com.emcc.markdown.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yihui on 2017/9/11.
 */
public class MarkdownEntity {

    public static String TAG_WIDTH = "<style type=\"text/css\"> %s { width:85%%} </style>";

    // css 样式
    private String css;
    
    private boolean withCss = false;

    // 最外网的div标签， 可以用来设置样式，宽高，字体等
    private Map<String, String> divStyle = new ConcurrentHashMap<>();

    // 转换后的html文档
    private String html;

    public MarkdownEntity() {
    }
    public MarkdownEntity(boolean withCss) {
    	this.withCss = withCss;
    }

    public MarkdownEntity(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
    	if(withCss){
    		return css + "\n<div " + parseDiv() + ">\n" + html + "\n</div>";
    	}else{
    		 return "<div " + parseDiv() + ">\n" + html + "\n</div>";
    	}
       
    }


    private String parseDiv() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : divStyle.entrySet()) {
            builder.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\" ");
        }
        return builder.toString();
    }


    public void addDivStyle(String attrKey, String value) {
        if (divStyle.containsKey(attrKey)) {
            divStyle.put(attrKey, divStyle.get(attrKey) + " " + value);
        } else {
            divStyle.put(attrKey, value);
        }
    }

    public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public Map<String, String> getDivStyle() {
		return divStyle;
	}

	public void setDivStyle(Map<String, String> divStyle) {
		this.divStyle = divStyle;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public void addWidthCss(String tag) {
        String wcss = String.format(TAG_WIDTH, tag);
        css += wcss;
    }
}
