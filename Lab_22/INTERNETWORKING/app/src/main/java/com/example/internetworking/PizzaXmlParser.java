package com.example.internetworking;

import android.os.AsyncTask;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PizzaXmlParser extends AsyncTask<Void, Void, List<Pizza>> {

    private ListView listView;
    private ProgressBar progressBar;

    public PizzaXmlParser(ListView listView, ProgressBar progressBar) {
        this.listView = listView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected List<Pizza> doInBackground(Void... voids) {
        List<Pizza> pizzas = new ArrayList<>();

        try {
            URL url = new URL("https://run.mocky.io/v3/your-mocky-id"); // Thay bằng link XML mocky nếu bạn dùng online
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            Pizza pizza = null;
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("item".equals(tagName)) {
                            pizza = new Pizza("", "");
                        } else if (pizza != null) {
                            if ("title".equals(tagName)) {
                                pizza = new Pizza(parser.nextText(), pizza.getDescription());
                            } else if ("description".equals(tagName)) {
                                pizza = new Pizza(pizza.getTitle(), parser.nextText());
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("item".equals(tagName) && pizza != null) {
                            pizzas.add(pizza);
                        }
                        break;
                }

                eventType = parser.next();
            }
            inputStream.close();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        return pizzas;
    }

    @Override
    protected void onPostExecute(List<Pizza> pizzas) {
        super.onPostExecute(pizzas);
        progressBar.setVisibility(ProgressBar.GONE);

        if (pizzas != null && !pizzas.isEmpty()) {
            ArrayAdapter<Pizza> adapter = new ArrayAdapter<>(
                    listView.getContext(),
                    android.R.layout.simple_list_item_1,
                    pizzas
            );
            listView.setAdapter(adapter);
        }
    }
}
