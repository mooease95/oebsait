package com.mueez.oebsait.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.cache.annotation.Cacheable;
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
    private static final Logger LOGGER = LogManager.getLogger(MarkdownService.class);

    private final ResourceLoader resourceLoader;
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    @Cacheable("markdown")
    public String loadAndRender(String filename) {
        String markdownText = readFileToString("classpath:/content/" + filename);
        Node document = parser.parse(markdownText);
        return renderer.render(document);
    }

    private String readFileToString(String resourcePath) {
        try {
            Resource resource = resourceLoader.getResource(resourcePath);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    resource.getInputStream(), StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            LOGGER.error("Error reading Markdown file: {}", resourcePath, e);
            return "<p>Error loading content.</p>";
        }
    }
}
