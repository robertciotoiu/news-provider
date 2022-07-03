package com.robertciotoiu.service;

import com.robertciotoiu.model.NewsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class NewsFetcherServiceImpl implements NewsFetcherService {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;

    public NewsFetcherServiceImpl() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }

    @Override
    public List<NewsEntity> fetchNews(String url) throws IOException, SAXException, XPathExpressionException {
        Document doc = builder.parse(new URL(url).openStream());
        doc.getDocumentElement().normalize();

        log.trace("Root Element :" + doc.getDocumentElement().getNodeName());
        log.trace("------");

        //get the <item> list of nodes
        String expression = "/rss/channel/item";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        List<NewsEntity> newsEntities = new ArrayList<>();
        //foreach item(which is an article), extract title, content, publishDate and imageUrl
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            log.trace("\nCurrent Element :" + node.getNodeName());

            if (!node.getNodeName().equals("item")) continue;
            if (!(node.getNodeType() == Node.ELEMENT_NODE)) continue;

            Element element = (Element) node;
            String title = element.getElementsByTagName("title").item(0).getTextContent();
            String content = element.getElementsByTagName("content").item(0).getTextContent();
            String imageUrl = element.getElementsByTagName("enclosure").item(0).getAttributes().getNamedItem("url").getTextContent();
            String publishDateString = element.getElementsByTagName("pubDate").item(0).getTextContent();
            OffsetDateTime publishDate = OffsetDateTime.parse(publishDateString, DateTimeFormatter.ofPattern( "EEE, dd MMM yyyy HH:mm:ss X" , Locale.US));
            newsEntities.add(NewsEntity.builder().title(title).content(content).imageUrl(imageUrl).publishDate(publishDate).build());
            log.info("Fetched:\n Title: " + title + "\nPublish: " + publishDate + "\nImageUrl: " + imageUrl + "\nContent: " + content + "\n---------------------------------------");
        }
        return newsEntities;
    }
}
