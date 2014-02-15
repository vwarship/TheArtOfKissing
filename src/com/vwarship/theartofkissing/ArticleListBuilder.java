package com.vwarship.theartofkissing;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ArticleListBuilder {
	public static final String ROOT = "article";
	public static final String ELEMENT_TITLE = "title";
	public static final String ELEMENT_TEXT = "text";

	public static List<Article> create(InputStream is)
	{
		List<Article> articles = new ArrayList<Article>();
		
		try 
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			Element element = doc.getDocumentElement();
			NodeList articleNodes = element.getElementsByTagName(ROOT);
			for (int i=0; i<articleNodes.getLength(); ++i)
			{
				Element articleElement = (Element)articleNodes.item(i);
				
				Article article = new Article();
				
				NodeList childNodes = articleElement.getChildNodes();
				for (int j=0; j<childNodes.getLength(); ++j)
				{
					String name = childNodes.item(j).getNodeName();
					String value = childNodes.item(j).getTextContent();
					
					if (ELEMENT_TITLE.equals(name))
						article.setTitle(value);
					else if (ELEMENT_TEXT.equals(name))
						article.setText(value);
				}
				
				articles.add(article);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return articles;
	}

}
