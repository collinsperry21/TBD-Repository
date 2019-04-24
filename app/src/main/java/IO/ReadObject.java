package IO;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import AppModels.CharSheet;


public class ReadObject {

    //need context to access file path
    private Context context;


    //constructor for context
    public ReadObject(Context context){
        this.context=context;
    }

    public CharSheet deserialzeCharacter(String filename) {

        CharSheet character = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {
            fin = new FileInputStream(context.getFilesDir().getAbsolutePath() + "/" + filename );
            ois = new ObjectInputStream(fin);
            character = (CharSheet) ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return character;

    }
}
