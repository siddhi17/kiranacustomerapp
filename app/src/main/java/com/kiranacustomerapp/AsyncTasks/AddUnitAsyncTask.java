package com.kiranacustomerapp.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.kiranacustomerapp.Database.ItemNamesTableHelper;
import com.kiranacustomerapp.Database.UnitsTableHelper;
import com.kiranacustomerapp.Models.Item;
import com.kiranacustomerapp.Models.Unit;
import com.kiranacustomerapp.R;

/**
 * Created by Siddhi on 12/23/2016.
 */
public class AddUnitAsyncTask extends AsyncTask<String,String,Unit> {

    private Context mContext;
    private UnitsTableHelper dbConnector;
    private ProgressDialog progressDialog;

    public AddUnitAsyncTask(Context context)
    {
        this.mContext = context;
        dbConnector = new UnitsTableHelper(context);

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
    public Unit doInBackground(String... params) {

        Unit unit = new Unit();

        unit.setUnit(params[0]);

        dbConnector.addUnits(unit);

        return unit;
    }

    @Override
    public void onPostExecute(Unit b) {
        if (b != null) {
            // set the adapter's Cursor

            dbConnector.close();
            //   progressDialog.dismiss();
        }
    }
}
