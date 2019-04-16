package AppModels;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class JSON
{
    private static JsonElement json;

    public JSON()
    {
    }

    public static JsonElement getData(String search)
    {
        try
        {
            //Construct the API url
            String apiURL = "http://dnd5eapi.co/api/" + search;

            //Create URL object
            URL DndURL = new URL(apiURL);

            //Create IS Object
            InputStream is = DndURL.openStream();

            //Create ISR
            InputStreamReader isr = new InputStreamReader(is);

            //Parse through the input stream into JsonElement
            JsonParser parser = new JsonParser();

            json = parser.parse(isr);
        }

        catch (MalformedURLException mue)
        {
            System.out.println("Malformed URL");
        }

        catch (IOException e)
        {
            System.out.println("IO Error");
        }

        return json;
    }
}

