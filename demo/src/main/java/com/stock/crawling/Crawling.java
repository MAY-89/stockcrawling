package com.stock.crawling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.stock.items.StockDto;

@Service
public class Crawling {

  public static void getStockPriceList() throws IOException, ParseException {
    Connection finvizConn = Jsoup.connect(Constants.FINVIZ_URL);
    // Json 불러와서 가져오기
    String[] tiggers = getTiggers();

    Document finvizDoc = finvizConn.get();
    String str = getStockHeader(finvizDoc);
    List<StockDto> list = new ArrayList<>();
    StockDto result = stringToDtoList(str);
  }

  public static String[] getTiggers() throws FileNotFoundException, IOException, ParseException {

    ClassPathResource resource = new ClassPathResource("/data/tigger.json");
    Object ob = new JSONParser().parse(new FileReader(resource.getPath()));
    JSONObject js = (JSONObject) ob;

    String[] tiggers= (String[])js.get("tiggers");

    return tiggers;
  }

  /**
   * 전체를 넘겨주고 할것인지, 아니면 한개만 넘겨 받고 줄것인지 고민해볼것
   * 
   * @param str
   * @return
   */
  private static StockDto stringToDtoList(String str) {
    String[] arr = str.split("`");
    List<StockDto> list = new ArrayList<>();
    StockDto dto = new StockDto();
    for (String column : arr) {
      String[] tmp = column.split("//");
      switch (tmp[0]) {
        case "Index":
          dto.setIndex(tmp[1]);
          break;
        case "P/E":
          dto.setPe(tmp[1]);
          break;
        case "EPS (ttm)":
          dto.setEps(tmp[1]);
          break;
        case "Insider Own":
          dto.setInsiderOwn(tmp[1]);
          break;
        case "Shs Outstand":
          dto.setShsOutstand(tmp[1]);
          break;
        case "Perf Week":
          dto.setPerfWeek(tmp[1]);
          break;
        case "Market Cap":
          dto.setMarketCap(tmp[1]);
          break;
        case "Forward P/E":
          dto.setFowardPe(tmp[1]);
          break;
        case "EPS next Y":
          dto.setEpsNextY(tmp[1]);

          if (tmp[1].indexOf("%") > 0) {

            dto.setEpsNextYRatio(tmp[1]);
          }
          break;
        case "Insider Trans":
          dto.setIniderTrans(tmp[1]);
          break;
        case "Shs Float":
          dto.setShsFloat(tmp[1]);
          break;
        case "Perf Month":
          dto.setPerfMonth(tmp[1]);
          break;
        case "Income":
          dto.setIncome(tmp[1]);
          break;
        case "PEG":
          dto.setPeg(tmp[1]);
          break;
        case "IndEPS next Q":
          dto.setEpsNextQ(tmp[1]);
          break;
        case "Inst Own":
          dto.setInstOwn(tmp[1]);
          break;
        case "Short Float / Ratio":
          dto.setShortFloat(tmp[1]);
          break;
        case "Perf Quarter":
          dto.setPerfQuarter(tmp[1]);
          break;
        case "Sales":
          dto.setSales(tmp[1]);
          break;
        case "P/S":
          dto.setPs(tmp[1]);
          break;
        case "EPS this Y":
          dto.setEpsThisY(tmp[1]);
          break;
        case "Inst Trans":
          dto.setInstTrans(tmp[1]);
          break;
        case "Short Interest":
          dto.setShortInterest(tmp[1]);
          break;
        case "Perf Half Y":
          dto.setPerfHalfY(tmp[1]);
          break;
        case "Book/sh":
          dto.setBookSh(tmp[1]);
          break;
        case "P/B":
          dto.setPb(tmp[1]);
          break;
        case "ROA":
          dto.setRoa(tmp[1]);
          break;
        case "Target Price":
          dto.setTargetPrice(tmp[1]);
          break;
        case "Perf Year":
          dto.setPerfYear(tmp[1]);
          break;
        case "Cash/sh":
          dto.setCashSh(tmp[1]);
          break;
        case "P/C":
          dto.setPc(tmp[1]);
          break;
        case "EPS next 5Y":
          dto.setEpsNextFiveY(tmp[1]);
          break;
        case "ROE":
          dto.setRoe(tmp[1]);
          break;
        case "52W Range":
          dto.setFiveTwoWRange(tmp[1]);
          break;
        case "Perf YTD":
          dto.setPerfYTD(tmp[1]);
          break;
        case "Dividend":
          dto.setDividend(tmp[1]);
          break;
        case "P/FCF":
          dto.setPFCF(tmp[1]);
          break;
        case "EPS past 5Y":
          dto.setEpsPastFiveY(tmp[1]);
          break;
        case "ROI":
          dto.setRoi(tmp[1]);
          break;
        case "52W High":
          dto.setFiveTowHight(tmp[1]);
          break;
        case "Beta":
          dto.setBeta(tmp[1]);
          break;
        case "Dividend %":
          dto.setDividendRatio(tmp[1]);
          break;
        case "Quick Ratio":
          dto.setQuickRatio(tmp[1]);
          break;
        case "Sales past 5Y":
          dto.setSalesPastFiveY(tmp[1]);
          break;
        case "Gross Margin":
          dto.setGrossMargine(tmp[1]);
          break;
        case "52W Low":
          dto.setFiveTowLow(tmp[1]);
          break;
        case "ATR":
          dto.setAtr(tmp[1]);
          break;
        case "Employees":
          dto.setEmployees(tmp[1]);
          break;
        case "Current Ratio":
          dto.setCurrentRatio(tmp[1]);
          break;
        case "Sales Q/Q":
          dto.setSalesQQ(tmp[1]);
          break;
        case "Oper. Margin":
          dto.setOperMargine(tmp[1]);
          break;
        case "RSI (14)":
          dto.setRsi(tmp[1]);
          break;
        case "Volatility":
          dto.setVolatility(tmp[1]);
          break;
        case "Optionable":
          dto.setOptionable(tmp[1]);
          break;
        case "Debt/Eq":
          dto.setDebtEq(tmp[1]);
          break;
        case "EPS Q/Q":
          dto.setEpsQQ(tmp[1]);
          break;
        case "Profit Margin":
          dto.setProfitMargin(tmp[1]);
          break;
        case "Rel Volume":
          dto.setRelVolume(tmp[1]);
          break;
        case "Prev Close":
          dto.setPrevClose(tmp[1]);
          break;
        case "Shortable":
          dto.setShortable(tmp[1]);
          break;
        case "LT Debt/Eq":
          dto.setLtDebtEq(tmp[1]);
          break;
        case "Earnings":
          dto.setEarnings(tmp[1]);
          break;
        case "Payout":
          dto.setPayout(tmp[1]);
          break;
        case "Avg Volume":
          dto.setAvgVolume(tmp[1]);
          break;
        case "Price":
          dto.setPrice(tmp[1]);
          break;
        case "Recom":
          dto.setRecom(tmp[1]);
          break;
        case "SMA20":
          dto.setSma20(tmp[1]);
          break;
        case "SMA50":
          dto.setSma50(tmp[1]);
          break;
        case "SMA200":
          dto.setSma200(tmp[1]);
          break;
        case "Volume":
          dto.setVolume(tmp[1]);
          break;
        case "Change":
          dto.setChange(tmp[1]);
          break;
      }
    }
    return dto;
  }

  // body는 12개
  public static String getStockHeader(Document doc) {
    Elements tableBody = doc.select("table.snapshot-table2 tbody tr");
    StringBuilder sb = new StringBuilder();
    for (Element el : tableBody) {
      Elements td = el.select("td");
      if (td.isEmpty() == true)
        continue;
      List<String> tds = td.eachText();
      for (int i = 0; i < tds.size(); i++) {

        if (i % 2 != 0 == false) { // 여기서는 키값
          sb.append(tds.get(i));
          sb.append("//");

        } else { // 여기는 밸류
          sb.append(tds.get(i));
          sb.append("`");
        }
      }

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
    Elements tableBody = doc.select("table.snapshot-table2 tbody tr");
    StringBuilder sb = new StringBuilder();

    for (Element el : tableBody) {
      if (el.attr("onmouseover").isEmpty()) {
        continue;
      }

      for (Element td : el.select("id")) {
        String text;
        if (td.select(".center a").attr("href").isEmpty()) {
          text = td.text();
        } else {
          text = "https://finance.naver.com" + td.select(".center a").attr("href");
        }
        sb.append(text);
        sb.append("   ");
      }
      sb.append(System.getProperty("line.separator")); // 줄바꿈
    }

    return sb.toString();
  }

}
