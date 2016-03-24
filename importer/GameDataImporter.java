package edu.byu.cs.superasteroids.importer;

import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.database.dao.DaoHelper;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.MCHelper;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.lang.StringBuilder;
import android.content.Context;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by jakehasler on 2/10/16.
 */
public class GameDataImporter implements IGameDataImporter {

    private Context c;
    private DBHelper dbHelper;
    private DaoHelper daoHelper;
    private MCHelper mcHelper;
    private MainContainer container;

    public GameDataImporter(Context context) {
        this.c = context;
        dbHelper = new DBHelper(c);
        MainContainer.init(dbHelper);
    }

    /**
	 * Implementing IGameDataImporter and then Creates a GSON Object. Calls the DBHelper And then the Dao populate the db and model classes
	 * (non-Javadoc)
	 * @see edu.byu.cs.superasteroids.importer.IGameDataImporter#importData(java.io.InputStreamReader)
	 * @param dataInputReader Input file that gets read in
	 */
    public boolean importData(InputStreamReader dataInputReader) {
        // convert to JSON
        // call DaoHelper.insertAll()
        // ModelClassHelper.queryAll();]
        System.out.println("IMPORT DATA FUNCTION");
        dbHelper.onUpgrade(DBHelper.getWriteable(), 1, 1);

        JSONObject json = new JSONObject();
        Scanner sc = new Scanner(dataInputReader);
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()) {
            sb.append(sc.nextLine());
        }

        String jsonStr = sb.toString();
        //System.out.println(jsonStr);

        try {
            json = new JSONObject(jsonStr);

        } catch (JSONException e) {
            System.out.println("MYAPP unexpected JSON exception");
            System.out.println(e);
        }

        daoHelper = new DaoHelper(MainContainer.getDbHelper());
        daoHelper.insertAll(json);
        MainContainer.getModel().initModel(true);
        // mcHelper.queryAll();
        return true;
    }


}
