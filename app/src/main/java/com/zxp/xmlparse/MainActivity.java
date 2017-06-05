package com.zxp.xmlparse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zxp.xmlparse.utils.HttpDownloader;
import com.zxp.xmlparse.utils.MyContentHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_parse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_parse=(Button)findViewById(R.id.btn_parse);
        btn_parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpDownloader hd=new HttpDownloader();
                        String resultStr=hd.download("http://192.168.1.102/worker.xml");
                        System.out.println(resultStr);
                        try {
                            SAXParserFactory factory=SAXParserFactory.newInstance();
                            XMLReader reader =factory.newSAXParser().getXMLReader();
                            //为XML设置内容处理器
                            reader.setContentHandler(new MyContentHandler());
                            reader.parse(new InputSource(new StringReader(resultStr)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();



            }
        });
    }
}
