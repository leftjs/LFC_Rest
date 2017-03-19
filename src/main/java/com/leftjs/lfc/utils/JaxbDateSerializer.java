package com.leftjs.lfc.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jason on 2017/3/13.
 */
public class JaxbDateSerializer extends XmlAdapter<String, Date> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }

}
