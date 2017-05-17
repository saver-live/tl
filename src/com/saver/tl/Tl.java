package com.saver.tl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Tl {
	private Request request;

	public Tl(String appid, String secretKey) {
		request = new Request(appid, secretKey);
	}

	public boolean tl(File file, String languge) {
		String parent = file.getParent();
		String charAt = String.valueOf(parent.charAt(parent.length() - 1));
		File out;
		if (charAt == "\\") {
			out = new File(parent + languge);
		} else {
			out = new File(parent + File.separator + languge + ".xml");
		}
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		for (Element element : elements) {
			try {
				String result = request.get(element.getText(), languge);
				element.setText(result);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		root.clone();
		document.clone();
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置输出编码
		format.setEncoding("UTF-8");
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileOutputStream(out), format);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// 开始写入，write方法中包含上面创建的Document对象
		try {
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("translate failue");
			return false;
		}
		return true;

	}
}
