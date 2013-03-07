/**
   Copyright Mob&Me 2013 (@MobAndMe)

   Licensed under the GPL General Public License, Version 3.0 (the "License"),  
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.gnu.org/licenses/gpl.html

   Unless required by applicable law or agreed to in writing, software 
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   Website: http://mobandme.com
   Contact: Txus Ballesteros <txus.ballesteros@mobandme.com>
*/

package com.mobandme.examples.zxing;

import com.mobandme.examples.zxing.helpers.ExceptionHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	private final static int SCAN_CODE_REQUEST_CODE = 123456;
	
	private TextView scanedValue;
	private TextView scanedFormat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		scanedValue  = (TextView)findViewById(R.id.scanedValue);
		scanedFormat = (TextView)findViewById(R.id.scanedFormat);
	}
	
	@Override
	protected void onActivityResult(int pRequestCode, int pResultCode, Intent pData) {
		try {
			
			if (pRequestCode == SCAN_CODE_REQUEST_CODE) {
				if (pResultCode == RESULT_OK) {
					
					String result = pData.getStringExtra(com.google.zxing.client.android.Intents.Scan.RESULT);
					String format = pData.getStringExtra(com.google.zxing.client.android.Intents.Scan.RESULT_FORMAT);
					
					if (scanedValue != null) {
						if (result != null && !result.trim().equals("")) {
							scanedValue.setText(result);
						} else {
							scanedValue.setText(getString(R.string.title_scaned_value_empty));
						}
					}
					
					if (scanedFormat != null) {
						if (format != null && !format.trim().equals("")) {
							scanedFormat.setText(format);
						} else {
							scanedFormat.setText(getString(R.string.title_scaned_value_empty));
						}
					}
				} else {
					if (scanedValue != null)
						scanedValue.setText(getString(R.string.title_scaned_value_empty));
					if (scanedFormat != null)
						scanedFormat.setText(getString(R.string.title_scaned_value_empty));
				}
			}
			
		} catch (Exception e) {
			ExceptionHelper.manage(this, e);
		}
	}
	public void commandScan(View pView) {
		try {
			
			Intent captureIntent = new Intent(this, CaptureActivity.class);
			captureIntent.putExtra(com.google.zxing.client.android.Intents.Scan.MODE, com.google.zxing.client.android.Intents.Scan.QR_CODE_MODE);
			startActivityForResult(captureIntent, SCAN_CODE_REQUEST_CODE);
			
		} catch (Exception e) {
			ExceptionHelper.manage(this, e);
		}
	}
}
