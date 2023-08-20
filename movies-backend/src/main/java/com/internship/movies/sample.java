//package com.internship.movies;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Scanner;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//    public class sample {
//
//        static class CountryInfo {
//            String name;
//            int population;
//
//            public CountryInfo(String name, int population) {
//                this.name = name;
//                this.population = population;
//            }
//        }
//
//        public static ArrayList<CountryInfo> getWeatherData(String region, String keyword) throws IOException {
//            ArrayList<CountryInfo> countriesList = new ArrayList<>();
//            String url = "https://jsonmock.hackerrank.com/api/countries/search?region=" + region + "&name=" + keyword + "&page=1";
//            URL urlObj = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
//
//            if (connection.getResponseCode() == 200) {
//                Scanner scanner = new Scanner(connection.getInputStream());
//                StringBuilder response = new StringBuilder();
//                while (scanner.hasNext()) {
//                    response.append(scanner.nextLine());
//                }
//                scanner.close();
//
//                // Parse JSON response
//                String jsonString = response.toString();
//                // Use your preferred JSON parsing library here. In this example, let's assume there's a method called `parseJsonString()` for parsing JSON.
//                // json library (e.g., Jackson, Gson, etc.) or native JSON APIs (e.g., org.json, javax.json) can be used.
//                // json library or native JSON APIs can be used here.
//                // For simplicity, we assume the existence of a method `parseJsonString()` for parsing JSON.
//                // You may need to modify this part to use your chosen JSON parsing library.
//                // JSONObject jsonObject = parseJsonString(jsonString);
//                // JSONArray data = jsonObject.getJSONArray("data");
//
//                // For the sake of the example, let's create a sample JSON parsing output.
//                String sampleJson = "{\"data\": [{\"name\": \"Denmark\", \"region\": \"Europe\", \"population\": 5678348},{\"name\": \"Sweden\", \"region\": \"Europe\", \"population\": 9793172}]}";
//                JSONObject jsonObject = parseJsonString(sampleJson);
//                JSONArray data = jsonObject.getJSONArray("data");
//
//                // Extract data
//                for (int i = 0; i < data.length(); i++) {
//                    JSONObject countryData = data.getJSONObject(i);
//                    String name = countryData.getString("name");
//                    int population = countryData.getInt("population");
//                    countriesList.add(new CountryInfo(name, population));
//                }
//
//                // Sort by population and name
//                countriesList.sort(Comparator.comparingInt((CountryInfo ci) -> ci.population).thenComparing(ci -> ci.name));
//            } else {
//                System.out.println("Error: Unable to retrieve data.");
//            }
//
//            connection.disconnect();
//            return countriesList;
//        }
//
//        // Assume this method is available to parse JSON string
//        // For actual use, you may use a JSON library like org.json, Gson, Jackson, etc.
//        private static JSONObject parseJsonString(String jsonString) {
//            // This is a placeholder method for JSON parsing.
//            // Use your preferred JSON parsing library here.
//            // For the sake of the example, we'll just return a dummy JSON object.
//            return new JSONObject(jsonString);
//        }
//
//        public static void main(String[] args) throws IOException {
//            // Input from user
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Enter the region: ");
//            String region = scanner.nextLine();
//            System.out.print("Enter the keyword: ");
//            String keyword = scanner.nextLine();
//            scanner.close();
//
//            // Get weather data and print the output
//            ArrayList<CountryInfo> output = getWeatherData(region, keyword);
//            for (CountryInfo countryInfo : output) {
//                System.out.println(countryInfo.name + ", " + countryInfo.population);
//            }
//        }
//    }
//
