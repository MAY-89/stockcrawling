package com.stock.crawling;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

  public static void getStockPriceList() throws IOException {
    Connection naverConn = Jsoup.connect(Constants.NAVER_URL);
    Connection finvizConn = Jsoup.connect(Constants.FINVIZ_URL);
    Document naverDoc = naverConn.get();
    Document finvizDoc = naverConn.get();

    String nThead = getStockHeader(naverDoc);
    // String fThead = getStockHeader(finvizDoc);
  }

  public static String getStockHeader(Document doc) {
    Elements tableBody = doc.select("table.type_2 thead tr");
    StringBuilder sb = new StringBuilder();
    for (Element el : tableBody) {
      for (Element td : el.select("th")) {
        sb.append(td.text());
        sb.append(" ");
      }
      break;
    }
    return sb.toString();
  }

  /**
   * 주식 리스트 불러오기
   * 
   * @param doc
   * @return
   */
  public static String getStockList(Document doc) {
    Elements tableBody = doc.select("table.type_2 tbody tr");
    StringBuilder sb = new StringBuilder();

    for(Element el : tableBody){
      if(el.attr("onmouseover").isEmpty()){
        continue;
      }

      for(Element td : el.select("id")){
        String text;
        if(td.select(".center a").attr("href").isEmpty()){
          text = td.text();
        }else{
          text = "https://finance.naver.com"+td.select(".center a").attr("href");
        }
        sb.append(text);
        sb.append("   ");
      }
      sb.append(System.getProperty("line.separator")); //줄바꿈
    }

    return sb.toString();
  }

}
