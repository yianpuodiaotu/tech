package com.emcc.markdown;
import java.util.Arrays;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ast.NodeVisitor;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.VisitHandler;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;

public class FlexmarkExamples {
    public static void main(String[] args) {
        RenderingExamples f = new RenderingExamples();
        System.out.println(f.render("This is *Sparta*", "commonmark"));
        System.out.println(f.render("This is *Sparta*", "markdown"));
        System.out.println(f.render("This is *Sparta*", "multimarkdown"));
        System.out.println(f.render("This is *Sparta*", "kramdown"));

        NodeVisitingExamples nodeVisiting = new NodeVisitingExamples();
        nodeVisiting.traverse("This is *Sparta*");
    }
}

class RenderingExamples {
    private MutableDataHolder getOptions(String type) {
        MutableDataHolder options = new MutableDataSet();
        switch (type) {
            case "markdown":
                options.setFrom(ParserEmulationProfile.MARKDOWN);
            case "multimarkdown":
                options.setFrom(ParserEmulationProfile.MULTI_MARKDOWN);
            case "kramdown":
                options.setFrom(ParserEmulationProfile.KRAMDOWN);
                options.set(Parser.EXTENSIONS, Arrays.asList(
                    AbbreviationExtension.create(),
                    DefinitionExtension.create(),
                    FootnoteExtension.create(),
                    TablesExtension.create(),
                    TypographicExtension.create()
                    ));
         }
         return options;
    }

    private Parser getParser(String type) {
        switch (type) {
            case "commonmark":
                return Parser.builder().build();
            default:
                return Parser.builder(getOptions(type)).build();
        }
    }

    private HtmlRenderer getHtmlRenderer(String type) {
        switch (type) {
            case "commonmark":
                return HtmlRenderer.builder().build();
            default:
                return HtmlRenderer.builder(getOptions(type)).build();
        }
    }

    public String render(String markdownText, String type) {
        Node document = getParser(type).parse(markdownText);
        return getHtmlRenderer(type).render(document);
    }
}

class NodeVisitingExamples {
    public NodeVisitingExamples() {
        this.visitor = new NodeVisitor(
            new VisitHandler<>(Text.class, NodeVisitingExamples.this::visit)
        );
    }

    public void traverse(String markdownText) {
        Parser parser = Parser.builder().build();
        this.visitor.visit(parser.parse(markdownText));
    }

    private void visit(Text text) {
        System.out.println(text);
    }

    private final NodeVisitor visitor;
}