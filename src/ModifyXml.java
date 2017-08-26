import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by admin on 2017/7/25.
 */
public class ModifyXml {
    static final String XML_PATH = "E:\\picRecord\\data_0725\\data\\VOC2012\\Annotations";

    public static void main(String args[]) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
        for (File file : new File(XML_PATH).listFiles()) {
            InputStream in = null;
            InputStream is = null;
            OutputStream os = null;
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = dbf.newDocumentBuilder();
                in = new FileInputStream(file);
                Document doc = builder.parse(in);
                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
                XPathExpression expr = xpath.compile("//path");
                NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                for (int i = 0; i < nodes.getLength(); i++) {
                    String picFileName = nodes.item(0).getFirstChild().getNodeValue();
                    is = new FileInputStream(picFileName);
                    BufferedImage src = javax.imageio.ImageIO.read(is); //构造Image对象
                    int srcWidth = src.getWidth(null); //得到源图宽
                    int srcHeight = src.getHeight(null); //得到源图长
                    ((NodeList) (xpath.compile("//width").evaluate(doc, XPathConstants.NODESET))).item(0).getFirstChild().setNodeValue(String.valueOf(srcWidth));
                    ((NodeList) (xpath.compile("//height").evaluate(doc, XPathConstants.NODESET))).item(0).getFirstChild().setNodeValue(String.valueOf(srcHeight));
                    is.close();
                }
                in.close();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                t.setOutputProperty("encoding", "UTF8");
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                t.transform(new DOMSource(doc), new StreamResult(bos));
                String xmlStr = bos.toString();
                os = new FileOutputStream(file);
                os.write(xmlStr.getBytes());
                os.close();
            } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException | TransformerException e) {
                System.err.format("", e);
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                }
            }

        }
    }
}
