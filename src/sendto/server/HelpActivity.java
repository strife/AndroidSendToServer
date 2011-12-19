package sendto.server;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelpActivity extends Activity
{
	
	final static String PREFS_FILE = "preferences";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.help);
		
		TextView footerLink = (TextView) findViewById(R.id.footerLink);		
		Linkify.addLinks(footerLink, Linkify.ALL);		
        
		
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		// get options 
		SharedPreferences settings = getSharedPreferences(PREFS_FILE, 0);
		String server = settings.getString("server", "");
		
		editText1.setText(server);
		
        // listener for save options 
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences settings = getSharedPreferences(PREFS_FILE, 0);
				SharedPreferences.Editor editor = settings.edit();
				
				EditText editText1 = (EditText) findViewById(R.id.editText1);
				editor.putString("server", editText1.getText().toString());
				editor.commit();
				
	    		Toast toast = Toast.makeText(getApplicationContext(), "Options have been saved", 1000);
	    		toast.show();
				
				
			}
			
		}); 				
		
        // listener for input go back
        Button button11 = (Button)findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		        Intent intent = new Intent();
		        setResult(RESULT_OK, intent);
		        finish();
				
			}
			
		}); 			 		
	}
}