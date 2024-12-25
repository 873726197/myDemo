package com.own.web.area;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author liuChang
 * @date 2023/4/24 18:04
 * @describe
 */
public class Spider {

    public static void main(String[] args) {
        // 爬取首页获取省份列表
        List<Province> provinceList = getProvinceList();
        // 遍历省份列表，依次爬取每个省份的城市列表和地区列表
        for (Province province : provinceList) {
            List<City> cityList = getCityList(province.getCode());
            province.setCityList(cityList);
            for (City city : cityList) {
                List<Area> areaList = getAreaList(province.getCode(), city.getCode());
                city.setAreaList(areaList);
            }
        }
        // 输出结果
        System.out.println(provinceList);
    }

    /**
     * 爬取首页获取省份列表
     * @return 省份列表
     */
    private static List<Province> getProvinceList() {
        String url = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2022/index.html";
        List<Province> provinceList = new ArrayList<>();
        try {
            String content = sendGet(url);
            String regex = "<a href='(\\d{2})\\.html'>(.*?)<\\/a>";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String code = matcher.group(1);
                String name = matcher.group(2);
                Province province = new Province(code, name);
                provinceList.add(province);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provinceList;
    }

    /**
     * 爬取指定省份的城市列表
     * @param provinceCode 省份代码
     * @return 城市列表
     */
    private static List<City> getCityList(String provinceCode) {
        String url = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2022/" + provinceCode + ".html";
        List<City> cityList = new ArrayList<>();
        try {
            String content = sendGet(url);
            String regex = "<a href='" + provinceCode + "\\d{2}\\.html'>(.*?)<\\/a>";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String code = matcher.group(0).substring(matcher.group(0).lastIndexOf("/") + 1, matcher.group(0).lastIndexOf("."));
                String name = matcher.group(1);
                City city = new City(code, name);
                cityList.add(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

    /**
     * 爬取指定省份和城市的地区列表
     * @param provinceCode 省份代码
     * @param cityCode 城市代码
     * @return 地区列表
     */
    private static List<Area> getAreaList(String provinceCode, String cityCode) {
        String url = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2022/" + provinceCode + "/" + cityCode + ".html";
        List<Area> areaList = new ArrayList<>();
        try {
            String content = sendGet(url);
            String regex = "<tr class='countytr'><td>(\\d{12})<\\/td><td>(.*?)<\\/td><\\/tr>";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String code = matcher.group(1);
                String name = matcher.group(2);
                Area area = new Area(code, name);
                areaList.add(area);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areaList;
    }

    /**
     * 发送HTTP GET请求
     * @param url 请求URL
     * @return 响应内容
     */
    private static String sendGet(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        URLConnection connection = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        return result.toString();
    }

    /**
     * 省份类
     */
    public static class Province {
        private String code;
        private String name;
        private List<City> cityList;

        public Province(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<City> getCityList() {
            return cityList;
        }

        public void setCityList(List<City> cityList) {
            this.cityList = cityList;
        }

        @Override
        public String toString() {
            return "Province{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", cityList=" + cityList +
                    '}';
        }
    }

    /**
     * 城市类
     */
    public static class City {
        private String code;
        private String name;
        private List<Area> areaList;

        public City(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Area> getAreaList() {
            return areaList;
        }

        public void setAreaList(List<Area> areaList) {
            this.areaList = areaList;
        }

        @Override
        public String toString() {
            return "City{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", areaList=" + areaList +
                    '}';
        }
    }

    /**
     * 地区类
     */
    public static class Area {
        private String code;
        private String name;

        public Area(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
