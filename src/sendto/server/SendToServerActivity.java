package sendto.server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendToServerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		TextView footerLink = (TextView) findViewById(R.id.footerLink);		
		Linkify.addLinks(footerLink, Linkify.ALL);		
        
        Toast.makeText(getApplicationContext(), "You are typing ..", 1000);
        
        // this function just try to check what user has filled 
        SendToServerMain sendToServer = (SendToServerMain)new SendToServerMain();
        sendToServer.initialize((EditText)findViewById(R.id.editText1), 
        		(Button)findViewById(R.id.button1),
        		getApplicationContext(), this);
        
        
        // listener for click to the text
         
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// setContentView(R.layout.info);
				
                Intent myIntent = new Intent(v.getContext(), HelpActivity.class);
                startActivityForResult(myIntent, 0);				
				
	
			}
		}); 

        
    }

    
    
}