/* 
 * Date: Dec 19, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MacroParamInputActivity extends Activity implements OnClickListener
{
	Button subButton;
	EditText entryField;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_macro_param);
		
		subButton = (Button)findViewById(R.id.param_submit);
		entryField = (EditText)findViewById(R.id.param_entry);
		
		subButton.setOnClickListener(this);
		
		this.setResult(RESULT_CANCELED);
	}

	@Override
	public void onClick(View v)
	{
		String entry = entryField.getText().toString();
		
		if (entry.equals("") || entry.equals(" "))
		{
			Toast.makeText(this, R.string.error_no_input, Toast.LENGTH_SHORT).show();
			return;
		}
		
		Intent data = new Intent();
		data.putExtra("input", entry);
		
		this.setResult(RESULT_OK, data);
		this.finish();
		
	}
}
