package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kiranacustomerapp.Database.ItemNamesTableHelper;
import com.kiranacustomerapp.Database.ItemsTableHelper;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/22/2016.
 */
public class GetAllItemNamesAsyncTask extends AsyncTask<Void,Void,ArrayList<String>> {

    private Context mContext;
    private ItemNamesTableHelper dbConnector;
    private GetItemNamesFromDbCallback getItemNamesFromDbCallback;
    private ProgressDialog progressDialog;

    public GetAllItemNamesAsyncTask(Context context,GetItemNamesFromDbCallback getItemNamesFromDbCallback)
    {

        this.mContext = context;
        this.getItemNamesFromDbCallback = getItemNamesFromDbCallback;
        dbConnector = new ItemNamesTableHelper(context);

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        progressDialog=new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        //    progressDialog.show();

    }

    public interface GetItemNamesFromDbCallback{
        void doPostExecute(ArrayList<String> itemNames,String s);
    }

    @Override
    public ArrayList<String> doInBackground(Void... params) {

        ArrayList<String> items = new ArrayList<>();

        items = dbConnector.getAllItems();

        return items;
    }

    @Override
    public void onPostExecute(ArrayList<String> b) {
        if (b != null) {
            // set the adapter's Cursor

            getItemNamesFromDbCallback.doPostExecute(b,"");

            dbConnector.close();
            // progressDialog.dismiss();
        }
    }
}
