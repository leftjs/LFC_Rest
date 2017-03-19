package com.leftjs.lfc.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by jason on 2017/3/12.
 */
public class XmlParseUtils {
    public static String objectToXmlString(Object object) throws Exception {
        Class clazz = object.getClass();
        StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, sw);
        return sw.toString();
    }

    public static Object xmlStringToObject(String xmlString, Class clazz) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlString);
        return unmarshaller.unmarshal(reader);
    }

}
