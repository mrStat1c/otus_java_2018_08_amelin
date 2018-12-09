package ru.otus;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateProcessor {
    //задаем директорию, где лежат темплейт - страницы и дефолтные плейсхолдеры
    private static final String HTML_DIR = "../HW12/public_html";
    private static Map<String, String> pageVariables = new HashMap<>();
    static {
        pageVariables.put("userName", "");
        pageVariables.put("userAge", "");
        pageVariables.put("userCount", "");
    }
    private final Configuration configuration;

    public TemplateProcessor() throws IOException {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDirectoryForTemplateLoading(new File(HTML_DIR));
        configuration.setDefaultEncoding("UTF-8");
    }

    //передаем имя файла - теплейта и данные, которые нужно в него подставить. Возвращается страница с подстановками в виде строки
    String getPage(String filename, Map<String, String> data) throws IOException {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(filename);
            pageVariables.putAll(data);
            template.process(pageVariables, stream);
            return stream.toString();
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }
}
