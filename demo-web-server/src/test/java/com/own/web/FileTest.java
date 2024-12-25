package com.own.web;

import com.own.web.area.Spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liuChang
 * @date 2023/3/29 10:05
 * @describe
 */
public class FileTest {

    public static final String path = "F:\\内部存储\\";

    public static final String[] musicType = {"mp3", "mp4", "m4a", "flac"};

    public static void main(String[] args) throws Exception {
//        File file = new File(path);
        //音乐重命名
//        renameMusic(file);

        //音乐去重
//        Map<String, Integer> map = new HashMap<>();
//        removeRepetition(file, map);
//        map.forEach((k, v) -> {
//            if (v == 1) {
//                System.out.println(k);
//            }
//        });

        //找到音乐位置
//        findMusic(file);

        getCity();
    }

    public static void getCity() throws Exception {
        // 获取省级数据
        String url = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2022/index.html";
        Document doc = Jsoup.connect(url).get();
        Elements provinceTables = doc.select("tr[class='provincetr']");

        List<Spider.Province> list = new ArrayList<>();
        for (Element provinceTable : provinceTables) {
            String name = provinceTable.select("td").text();
            String code = provinceTable.select("a").attr("href");
            list.add(new Spider.Province(name, code));
        }


        Element provinceTable = provinceTables.get(0);
        Elements provinceRows = provinceTable.select("tr");
        String[][] provinceData = new String[provinceRows.size()][3];

        for (int i = 0; i < provinceRows.size(); i++) {
            String code = provinceRows.get(i).select("td").get(0).text();
            String name = provinceRows.get(i).select("td").get(1).text();
            String link = provinceRows.get(i).select("td").get(1).select("a").attr("href");
            provinceData[i][0] = code;
            provinceData[i][1] = name;
            provinceData[i][2] = link;
        }

        // 获取市级数据
        String[][] cityData = new String[0][0];
        for (int i = 0; i < provinceData.length; i++) {
            String provinceLink = provinceData[i][2];
            doc = Jsoup.connect("http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2022/" + provinceLink).get();
            Elements cityTables = doc.select("table[class='citytable']");
            if (cityTables.size() > 0) {
                Element cityTable = cityTables.get(0);
                Elements cityRows = cityTable.select("tr");
                String[][] newData = new String[cityRows.size()][3];
                for (int j = 0; j < cityRows.size(); j++) {
                    String code = cityRows.get(j).select("td").get(0).text();
                    String name = cityRows.get(j).select("td").get(1).text();
                    String link = cityRows.get(j).select("td").get(1).select("a").attr("href");
                    newData[j][0] = code;
                    newData[j][1] = name;
                    newData[j][2] = link;
                }
                int originLength = cityData.length;
                int newLength = newData.length;
                String[][] temp = new String[originLength + newLength][3];
                System.arraycopy(cityData, 0, temp, 0, originLength);
                System.arraycopy(newData, 0, temp, originLength, newLength);
                cityData = temp;
            }
        }

        // 获取区县数据
        String[][] districtData = new String[0][0];
        for (int i = 0; i < cityData.length; i++) {
            String cityLink = cityData[i][2];
            doc = Jsoup.connect("http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2022/" + cityLink).get();
            Elements districtTables = doc.select("table[class='countytable']");
            if (districtTables.size() > 0) {
                Element districtTable = districtTables.get(0);
                Elements districtRows = districtTable.select("tr");
                String[][] newData = new String[districtRows.size() - 1][3];
                for (int j = 0; j < districtRows.size() - 1; j++) {
                    String code = districtRows.get(j + 1).select("td").get(0).text();
                    String name = districtRows.get(j + 1).select("td").get(1).text();
                    String link = districtRows.get(j + 1).select("td").get(1).select("a").attr("href");
                    newData[j][0] = code;
                    newData[j][1] = name;
                    newData[j][2] = link;
                }
                int originLength = districtData.length;
                int newLength = newData.length;
                String[][] temp = new String[originLength + newLength][3];
                System.arraycopy(districtData, 0, temp, 0, originLength);
                System.arraycopy(newData, 0, temp, originLength, newLength);
                districtData = temp;
            }
        }

        // 合并数据
        int totalLength = provinceData.length + cityData.length + districtData.length;
        String[][] tableData = new String[totalLength][3];
        System.out.println(tableData);
    }

    public static void findMusic(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        findMusic(f);
                    } else {
                        AtomicBoolean contains = new AtomicBoolean(false);
                        Arrays.stream(musicType).forEach(m -> {
                            if (f.getName().toLowerCase().contains(m)) {
                                contains.set(true);
                            }
                        });
                        if (contains.get()) {
                            System.out.println(f.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            boolean b = Arrays.stream(musicType).anyMatch(m -> file.getName().toLowerCase().contains(m));
            if (b) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    public static void removeRepetition(File file, Map<String, Integer> map) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        removeRepetition(f, map);
                    } else {
                        if (map.containsKey(f.getName())) {
                            map.put(f.getName(), 1);
                        } else {
                            map.put(f.getName(), 0);
                        }
                    }
                }
            }
        } else {
            if (map.containsKey(file.getName())) {
                map.put(file.getName(), 1);
            } else {
                map.put(file.getName(), 0);
            }
        }
    }

    @SuppressWarnings(value = "all")
    public static void renameMusic(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        renameMusic(f);
                    } else {
                        if (f.getName().contains("未知")) {
                            f.renameTo(new File(path + f.getName().substring(5)));
                        }
                    }
                }
            }
        } else {
            if (file.getName().contains("未知")) {
                file.renameTo(new File(path + file.getName().substring(6)));
            }
        }
    }
}
