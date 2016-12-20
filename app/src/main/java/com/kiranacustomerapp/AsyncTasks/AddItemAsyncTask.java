package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kiranacustomerapp.Database.ItemsTableHelper;
import com.kiranacustomerapp.Models.OrderItem;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class AddItemAsyncTask extends AsyncTask<String,String,OrderItem> {

        private Context mContext;
        private ItemsTableHelper dbConnector;
        private ProgressDialog progressDialog;

        public AddItemAsyncTask(Context context)
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
        public OrderItem doInBackground(String... params) {

            OrderItem orderItem = new OrderItem();

            orderItem.setItemName(params[0]);
            orderItem.setUnit(params[1]);
            orderItem.setQuantity(params[2]);

            dbConnector.addItem(orderItem);

            return orderItem;
        }

        @Override
        public void onPostExecute(OrderItem b) {
            if (b != null) {
                // set the adapter's Cursor

                dbConnector.close();
             //   progressDialog.dismiss();
            }
        }

}
