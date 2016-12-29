package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kiranacustomerapp.Database.ItemNamesTableHelper;
import com.kiranacustomerapp.Database.ItemsTableHelper;
import com.kiranacustomerapp.Models.Item;
import com.kiranacustomerapp.Models.OrderItem;
import com.kiranacustomerapp.R;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class AddItemAsyncTask extends AsyncTask<String,String,Item> {

        private Context mContext;
        private ItemNamesTableHelper dbConnector;
        private ProgressDialog progressDialog;

        public AddItemAsyncTask(Context context)
        {
            this.mContext = context;
            dbConnector = new ItemNamesTableHelper(context);

        }
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog=new ProgressDialog(mContext);
            progressDialog.setMessage(mContext.getString(R.string.wait));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
         //   progressDialog.show();

        }

        @Override
        public Item doInBackground(String... params) {

            Item item = new Item();

            item.setItemName(params[0]);

            dbConnector.addItem(item);

            return item;
        }

        @Override
        public void onPostExecute(Item b) {
            if (b != null) {
                // set the adapter's Cursor

                dbConnector.close();
             //   progressDialog.dismiss();
            }
        }

}
