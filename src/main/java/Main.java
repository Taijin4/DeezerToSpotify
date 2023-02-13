import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Main{
    private final String SECRET_KEY = "dffa94890cd0efb66f3ead78c381a06f";

    public static void main(String[] args){
        Deezer d = new Deezer();
        Spotify s = new Spotify();

        HashMap<String, String> musique = new HashMap<>();
        String[][] musiques = new String[2000][2];

        String str;

        int nbmusique = 0;

        for (int index = 0; index < 1751; index+= 25) {
            str = d.getBodyRequest(index);

            JSONObject json = new JSONObject(str);
            JSONArray results = json.getJSONArray("data");

            int taille = json.getJSONArray("data").length();

            for (int i = 0; i < taille; i++) {
                String titre;
                String artiste;

                JSONObject res = (JSONObject) results.get(0);
                titre = res.getString("title");
                artiste = (String) res.getJSONObject("artist").get("name");

                musiques[i + index][0] = titre;
                musiques[i + index][1] = artiste;

                results.remove(0);
                nbmusique++;
            }
        }
        int nb = 0;
        for (int i = 0; i < 1753; i++) {
            System.out.println(musiques[i][0] + " : " + musiques[i][1]);
            nb++;
        }
        System.out.println(nb);
        System.out.println(nbmusique);
        Spotify.ajoutMusique(musiques, "test");

    }
}