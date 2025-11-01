package com.mueez.oebsait.services;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class MarkdownService {
    private final ResourceLoader resourceLoader;
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;

        // Initialize CommonMark Parser and Renderer
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    /**
     * Reads a .md file from the 'classpath:/content/' directory,
     * converts it to HTML, and returns the HTML string.
     *
     * @param filename The name of the markdown file (e.g., "about.md")
     * @return The HTML content as a String.
     */
    public String loadAndRender(String filename) {
        String markdownText = readFileToString("classpath:/content/" + filename);

        // 1. Parse the Markdown text into a document Node
        Node document = parser.parse(markdownText);

        // 2. Render the document as HTML
        return renderer.render(document);
    }

    // Utility method to safely read the file content
    private String readFileToString(String resourcePath) {
        try {
            Resource resource = resourceLoader.getResource(resourcePath);
            // Use try-with-resources for safe resource handling
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    resource.getInputStream(), StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            System.err.println("Error reading Markdown file: " + resourcePath);
            e.printStackTrace();
            return "<p>Error loading content.</p>";
        }
    }
}
