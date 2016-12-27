package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kiranacustomerapp.Database.ItemsTableHelper;
import com.kiranacustomerapp.Models.QueryOrderItem;

/**
 * Created by Siddhi on 12/23/2016.
 */
public class AddQueryItemAsyncTask extends AsyncTask<String,String,QueryOrderItem> {
    private Context mContext;
    private ItemsTableHelper dbConnector;
    private ProgressDialog progressDialog;

    public AddQueryItemAsyncTask(Context context)
    {
        this.mContext = context;
        dbConnector = new ItemsTableHelper(context);

    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        progressDialog=new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        //   progressDialog.show();

    }

    @Override
    public QueryOrderItem doInBackground(String... params) {

        QueryOrderItem item = new QueryOrderItem(params[0]);

        dbConnector.addQueryItem(item);

        return item;
    }

    @Override
    public void onPostExecute(QueryOrderItem b) {
        if (b != null) {
            // set the adapter's Cursor

            dbConnector.close();
            //   progressDialog.dismiss();
        }
    }
}
