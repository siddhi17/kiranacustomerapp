package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kiranacustomerapp.Database.ItemsTableHelper;
import com.kiranacustomerapp.Models.OrderItem;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class GetAllItemsAsyncTask  extends AsyncTask<Void,Void,ArrayList<OrderItem>> {

    private Context mContext;
    private ItemsTableHelper dbConnector;
    private GetItemsFromDbCallback getItemsFromDbCallback;
    private ProgressDialog progressDialog;

    public GetAllItemsAsyncTask(Context context,GetItemsFromDbCallback getItemsFromDbCallback)
    {

        this.mContext = context;
        this.getItemsFromDbCallback = getItemsFromDbCallback;
        dbConnector = new ItemsTableHelper(context);

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

    public interface GetItemsFromDbCallback{
        void doPostExecute(ArrayList<OrderItem> contacts);
    }

    @Override
    public ArrayList<OrderItem> doInBackground(Void... params) {

        ArrayList<OrderItem> items = new ArrayList<>();

        items = dbConnector.getAllItems();

        return items;
    }

    @Override
    public void onPostExecute(ArrayList<OrderItem> b) {
        if (b != null) {
            // set the adapter's Cursor

            getItemsFromDbCallback.doPostExecute(b);

            dbConnector.close();
           // progressDialog.dismiss();
        }
    }
}
