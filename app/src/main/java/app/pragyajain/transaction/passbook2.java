package app.pragyajain.transaction;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class passbook2 extends AppCompatActivity {
    TextView wal, t;
    /* private String transactions =
             "https://horizon-testnet.stellar.org/accounts/GCNE242XH5PQ7JPS7EII52HPV6HDBYDZWO57WFEQJG5G737DOF4ZAAYM/transactions";
     */private String payments =
            "https://horizon-testnet.stellar.org/accounts/GCNE242XH5PQ7JPS7EII52HPV6HDBYDZWO57WFEQJG5G737DOF4ZAAYM/payments";
    private String TAG = passbook.class.getSimpleName();
    public ListView lv;
    private ProgressDialog pDialog;
    private ArrayList<ArrayList<String>> history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passbook);
        wal = (TextView) findViewById(R.id.wallet);

        String b = getIntent().getStringExtra("balance");
        wal.setText(b);
        lv= (ListView)findViewById(R.id.list);
        history = new ArrayList<>();

        new passbook2.display_history().execute();
    }
    private class display_history extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(passbook2.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String ... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            //String t = sh.makeServiceCall(transactions);
            String p = sh.makeServiceCall(payments);
            //Log.e(TAG, "Response from url: " + jsonStr);
            if ( p != null) {
                return p;
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            } return null;
        }
        @Override
        protected void onPostExecute(String p)
        {
            //super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            try {
                //JSONObject trans = new JSONObject(t);
                JSONObject pay = new JSONObject(p);
                JSONObject obj = pay.getJSONObject("_embedded");
                JSONArray rec = obj.getJSONArray("records");
                JSONObject ob = rec.getJSONObject(1);
                String acc_id = ob.getString("from");
                String amount = ob.getString("amount");
                ArrayList<String> entry = new ArrayList<>();
                entry.add(acc_id);
                entry.add(amount);

                history.add(entry);
                ArrayAdapter adapter = new ArrayAdapter (getApplicationContext(),R.layout.list_item );
                adapter.add(history);
                lv.setAdapter(adapter);
            }
            catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        }
    }
}