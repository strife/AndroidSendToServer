package sendto.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendToServerMain {
	private Context context;
	private EditText editText1;
	private SendToServerActivity sendToServerActivity;
	
	final static String PREFS_FILE = "preferences";
	
    public void initialize(EditText editText1, Button button1, final Context context, SendToServerActivity sendToServerActivity) {
    	// we need name of object that we will listen
    	this.sendToServerActivity = sendToServerActivity;
    	this.editText1 = editText1;
    	this.context   = context;
    	
    	// validation message for textfield 
    	editText1.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
				// validate(s);
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	// send it action
    	this.sendItNow(button1);
    	
    }
    
    public boolean validate(CharSequence s) {
		if (s.length() >= 50) {
			Toast toast = Toast.makeText(context, "Invalid value, only 50 chars max!", 1);
			toast.show();					
			return false;
		} else if (s.length() < 11) {
			Toast toast = Toast.makeText(context, "Invalid value, you have to fill more than 10 chars!", 1);
			toast.show();
			return false;
			
		}
    	return true;
    }
    
    public void sendItNow(Button button1) {
    	button1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// check if every 
				boolean isValidate = validate(editText1.getText());
				
				if (isValidate) {
					// send to server and open progress dialog box
					new postData().execute();
				}
			}
		});
    }
    
    
    private class postData extends AsyncTask<String, Void, String> {

    	private final ProgressDialog dialog = ProgressDialog.show(sendToServerActivity, "", 
    			"Saving data to server. Please wait...", true);

    	@Override
    	protected String doInBackground(String... params) {
    		// perform long running operation operation

    		SharedPreferences settings = context.getSharedPreferences(PREFS_FILE, 0);
    		String server = settings.getString("server", "");
    		
    		HttpClient httpclient = new DefaultHttpClient();
    		HttpPost httppost = new HttpPost(server);

    		try {
    			// Add your data
    			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
    			nameValuePairs.add(new BasicNameValuePair("android", editText1.getText().toString()));     
    			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

    			try {
    				httpclient.execute(httppost);
    			} catch (UnsupportedEncodingException e) {
    				e.printStackTrace();
    			}


    			// Execute HTTP Post Request
    			// ResponseHandler<String> responseHandler=new BasicResponseHandler();
    			// String responseBody = httpclient.execute(httppost, responseHandler);

    			// if (Boolean.parseBoolean(responseBody)) {
    			//	dialog.cancel();
    			// }


    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			Log.i("HTTP Failed", e.toString());
    		}    		
    		
    		return null;
    	}

    	/* (non-Javadoc)
    	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
    	 */
    	@Override
    	protected void onPostExecute(String result) {
    		// execution of result of Long time consuming operation
    		dialog.dismiss();
    		
    		Toast toast = Toast.makeText(context, "File has been changed", 1000);
    		toast.show();
    		
    	}

    	/* (non-Javadoc)
    	 * @see android.os.AsyncTask#onPreExecute()
    	 */
    	@Override
    	protected void onPreExecute() {
    		// Things to be done before execution of long running operation. For example showing ProgessDialog
    		
    		dialog.setCancelable(false);
    		dialog.show();
    	}

    	/* (non-Javadoc)
    	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
    	 */
    	@Override
    	protected void onProgressUpdate(Void... values) {
    		// Things to be done while execution of long running operation is in progress. For example updating ProgessDialog

    	}
    }    


}


