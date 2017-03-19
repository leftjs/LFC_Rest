package com.leftjs.lfc.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by jason on 2017/3/13.
 */
public class JaxbMaskIdSerializer extends XmlAdapter<String, Long> {
    @Override
    public Long unmarshal(String v) throws Exception {
        return Long.parseLong(v);
    }

    @Override
    public String marshal(Long v) throws Exception {
        return null;
    }
}
