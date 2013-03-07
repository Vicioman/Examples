package com.mobandme.examples.zxing;

import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;
import com.mobandme.examples.zxing.helpers.ExceptionHelper;

import android.content.Intent;
import android.graphics.Bitmap;

public class CaptureActivity extends com.google.zxing.client.android.CaptureActivity {

	@Override
	public void handleDecodeInternally(Result rawResult, ResultHandler resultHandler, Bitmap barcode) {
		//super.handleDecodeInternally(rawResult, resultHandler, barcode);
		
		try {
			
			Intent resultIntent = new Intent();
			resultIntent.putExtra(com.google.zxing.client.android.Intents.Scan.RESULT_FORMAT, rawResult.getBarcodeFormat().toString());
			resultIntent.putExtra(com.google.zxing.client.android.Intents.Scan.RESULT, resultHandler.getDisplayContents());
			setResult(RESULT_OK, resultIntent);
			finish();
			
		} catch (Exception e) {
			ExceptionHelper.manage(this, e);
		}
	}
}
