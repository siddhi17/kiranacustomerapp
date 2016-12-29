package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kiranacustomerapp.Database.ItemsTableHelper;
import com.kiranacustomerapp.Models.OrderItem;
import com.kiranacustomerapp.Models.QueryOrderItem;
import com.kiranacustomerapp.R;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class GetAllItemsAsyncTask  extends AsyncTask<Void,Void,ArrayList<String>> {

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
        progressDialog.setMessage(mContext.getString(R.string.wait));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
    //    progressDialog.show();

    }

    public interface GetItemsFromDbCallback{
        void doPostExecute(ArrayList<String> contacts);
    }

    @Override
    public ArrayList<String> doInBackground(Void... params) {

        ArrayList<String> items = new ArrayList<>();

        items = dbConnector.getAllItemNames();

        return items;
    }

    @Override
    public void onPostExecute(ArrayList<String> b) {
        if (b != null) {
            // set the adapter's Cursor

            getItemsFromDbCallback.doPostExecute(b);

            dbConnector.close();
           // progressDialog.dismiss();
        }
    }
}
