package app.pragyajain.transaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Demo extends AppCompatActivity {
    private static String url = "https://horizon-testnet.stellar.org/accounts/GCNE242XH5PQ7JPS7EII52HPV6HDBYDZWO57WFEQJG5G737DOF4ZAAYM";
    //String JSON_STRING = "{\"id\":\"Pragya\",\"balance\":10010.00}";
    private String TAG = Home.class.getSimpleName();
    TextView accountID, current_balance;
    String bal, accno;
    ArrayList<HashMap<String, String>> profileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        accountID = (TextView) findViewById(R.id.user_id);
        current_balance = (TextView) findViewById(R.id.balance);
        //new Demo.updateText().execute();
        try {
            //String file = parseJSON();
            String file = getobj();
            JSONObject obj = new JSONObject(file);
            accno = obj.getString("id");
            JSONArray balance = obj.getJSONArray("balances");
            JSONObject b = balance.getJSONObject(0);
            bal = b.getString("balance");
            accountID.setText(accno);
            current_balance.setText(bal);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

     public String getobj()
     {
         HttpHandler sh = new HttpHandler();
         // Making a request to url and getting response
         String file = sh.makeServiceCall(url);
         Log.e(TAG, "Response from url: " + file);
         return file;
     }
    public String parseJSON() throws JSONException {
        String json = null;
        try {
            InputStream is = getAssets().open("account_det.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}