package com.samatkinson;

import com.google.gson.Gson;
import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.handlers.ResourceHandlerBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class App {

    public static void main(String[] args) {
        MuServer server = MuServerBuilder.httpServer().withHttpPort(50909)
                .addHandler(Method.GET, "v1/{url}", (request, response, pathParams) -> {
                    response.write(new Gson().toJson(getUrl(pathParams.get("url"))));
                })
                .addHandler(ResourceHandlerBuilder.fileOrClasspath("src/main/resources/web", "/web"))
                .start();
        System.out.println("Started server at " + server.uri());
    }

    public static SitePreview getUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        String image = getImage(doc);
        String desc = getDesc(doc);
        String title = doc.title();

        return new SitePreview(image, desc, title);
    }

    private static String getDesc(Document doc) {
        return getSome(doc, asList(
                "meta[property=og:description]",
                "meta[itemprop=description]",
                "meta[name=description]",
                "title"
        ), false);


    }

    private static String getSome(Document doc, List<String> tags, boolean link) {
        for (String tag : tags) {
            Elements select = doc.select(tag);
            if (select.size() != 0) {
                Element element = select.get(0);
                if (element.tag().getName().equals("meta")) {
                    if (element.attr("content").isEmpty()) return "";
                    return element.attr((link ? "abs:" : "") + "content");
                } else return element.val();
            }
        }
        return null;
    }

    private static String getImage(Document doc) {
        return getSome(doc, asList(
                "meta[property=og:image]",
                "meta[itemprop=image]"
        ), true);
    }
}
