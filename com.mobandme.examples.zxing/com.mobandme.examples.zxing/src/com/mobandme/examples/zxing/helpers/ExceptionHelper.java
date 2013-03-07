package com.mobandme.examples.zxing.helpers;

import android.content.Context;
import android.widget.Toast;

public class ExceptionHelper {

	public static void manage(Context pContext, Exception pException) {
		pException.printStackTrace();
		
		if (pContext != null) {
			Toast.makeText(pContext, pException.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
