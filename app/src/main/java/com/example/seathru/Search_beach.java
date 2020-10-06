package com.example.seathru;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Search_beach extends AppCompatActivity {
    ListView listview;
    ArrayList<Listviewitem> data;
    String[] sido;
    String[] encsido;
    String selectedsido ="%EC%B6%A9%EB%82%A8";
    String serviceKey ="&ServiceKey=Tm2BOO71XacJAjxxuCm64GD42TwvC2pTZMqmgULgPuGBgn58ZSpBhoWr23w4%2FHsx1pdKZMPmFmghglHrrHKOmA%3D%3D";
    String str; //="http://apis.data.go.kr/1192000/OceansBeachInfoService/getOceansBeachInfo?numOfRows=10&SIDO_NM="+selectedsido+serviceKey;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_beach);

        listview = findViewById(R.id.listview);
        data = new ArrayList<>();

        final ListviewAdapter adapter = new ListviewAdapter(this, R.layout.item, data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Search_beach.this, DetailActivity.class);
                intent.putExtra("staNm", data.get(position).getStaName());
                intent.putExtra("lat", data.get(position).getLat());
                intent.putExtra("lon", data.get(position).getLon());
                intent.putExtra("phone", data.get(position).getPhone());
                startActivity(intent);
            }
        });

        sido = new String[]{"시도", "부산", "인천", "울산", "강원", "충남", "전북", "전남", "경북", "경남", "제주" };
        encsido = new String[]{};
        task = new Task();
        task.execute();
    }

    protected class Task extends AsyncTask<String, Void, Document> {
        Document doc;
        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            str ="http://apis.data.go.kr/1192000/OceansBeachInfoService/getOceansBeachInfo?numOfRows=10&SIDO_NM=%EC%B6%A9%EB%82%A8&ServiceKey=Tm2BOO71XacJAjxxuCm64GD42TwvC2pTZMqmgULgPuGBgn58ZSpBhoWr23w4%2FHsx1pdKZMPmFmghglHrrHKOmA%3D%3D";//&ServiceKey=A3k%2B8hNTIe%2FbgfHBwSCWvO9IzuOKHX6OZl%2B4%2BqV2gYk82hUkIOK%2FEIRQJLDosS9dwFcB%2FkVAmqQCfDeIthKhbw%3D%3D";

            try {
                url = new URL(str);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
            } catch (Exception e){

            }
            return doc;
        }

        protected void onPostExecute(Document doc){
            NodeList nodeList = doc.getElementsByTagName("item");
            for(int i=0; i< nodeList.getLength(); i++){
                String gugun ="";
                String sta = "";
                String phone="";
                double lat;
                double lon;
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;
                /*
                NodeList idx = fstElmnt.getElementsByTagName("num");
                s += "num = "+ idx.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList beachid = fstElmnt.getElementsByTagName("beachId");
                s += "beachId = "+ beachid.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList sidoNm = fstElmnt.getElementsByTagName("sidoNm");
                s += "sidoNm = "+ sidoNm.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                */
                NodeList gugunNm = fstElmnt.getElementsByTagName("gugunNm");
                gugun += ""+ gugunNm.item(0).getChildNodes().item(0).getNodeValue();
                NodeList staNm = fstElmnt.getElementsByTagName("staNm");
                sta += "\t"+ staNm.item(0).getChildNodes().item(0).getNodeValue() + "해수욕장";
                NodeList linkTel = fstElmnt.getElementsByTagName("linkTel");
                phone += linkTel.item(0).getChildNodes().item(0).getNodeValue();
                NodeList a_lat =fstElmnt.getElementsByTagName("lat");
                lat=Double.parseDouble(a_lat.item(0).getChildNodes().item(0).getNodeValue());
                NodeList a_lon =fstElmnt.getElementsByTagName("lon");
                lon=Double.parseDouble(a_lon.item(0).getChildNodes().item(0).getNodeValue());

                Listviewitem item = new Listviewitem(R.drawable.ic_check_black_24dp,gugun,sta,phone,lat,lon);
                data.add(item);
                listview.invalidateViews();
            }

            //textview.setText(s);
            super.onPostExecute(doc);
        }
    }

}
