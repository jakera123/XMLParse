package com.zxp.xmlparse.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by xiaoxin on 2017/6/5.
 */


//适配器模式，假如是继承ContentHandler的话，需要实现很多的方法
public class MyContentHandler extends DefaultHandler {
    String hisname,address,money,sex,status;
    String tagName;
    public void startDocument() throws SAXException{
        System.out.println("************begin****************");
    }
    public void endDocument() throws SAXException{
        System.out.println("************end****************");
    }

    /**
     *
     * @param namespaceURI 为了防止标签重名而设置
     * @param localName     不带前缀的标签值
     * @param qName         带前缀的标签，冒号前
     * @param attr          标签的属性Id值等等……
     * @throws SAXException
     */
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException{
        tagName=localName;
        if(localName.equals("workers")){
            //获取标签的所有属性
            for(int i=0;i<attr.getLength();i++){
                System.out.println(attr.getLocalName(i)+"="+attr.getValue(i));
            }
        }
    }
    public void endElement(String namespaceURI,String localName,String qName) throws  SAXException{
       //在标签解析后，打印出所有的数据！
        if(localName.equals("workers")){
            this.printout();
        }
    }

    /**
     *
     * @param ch          读取到标签中间的值
     * @param start
     * @param length
     * @throws SAXException
     */
    public void characters(char[] ch,int start,int length) throws SAXException{
        if(tagName.equals("name"))
            hisname=new String(ch,start,length);
        else if(tagName.equals("sex"))
            sex=new String(ch,start,length);
        else if(tagName.equals("status"))
            status=new String(ch,start,length);
        else if(tagName.equals("address"))
            address=new String(ch,start,length);
        else if(tagName.equals("money"))
            money=new String(ch,start,length);
    }
    private void printout(){
        System.out.print("name:");
        System.out.println(hisname);
        System.out.print("sex:");
        System.out.println(sex);
        System.out.print("status:");
        System.out.println(status);
        System.out.print("address:");
        System.out.println(address);
        System.out.print("money:");
        System.out.println(money);
    }
}
