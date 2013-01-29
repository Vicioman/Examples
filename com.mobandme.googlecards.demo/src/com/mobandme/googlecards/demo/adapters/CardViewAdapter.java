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

package com.mobandme.googlecards.demo.adapters;

import com.mobandme.googlecards.demo.R;
import com.mobandme.googlecards.demo.views.Card;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CardViewAdapter extends ArrayAdapter<String> {

	int mLastPosition;
	
	public CardViewAdapter(Context context) {
		super(context, R.layout.view_card);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View returnedValue = convertView;
		
		if (returnedValue == null) {
			returnedValue = new Card(parent.getContext());
		} else {
			if (position >= mLastPosition)
				((Card)returnedValue).startAnimation();
		}
		
		mLastPosition = position;
		return returnedValue;
	}


	
}
