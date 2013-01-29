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

package com.mobandme.googlecards.demo.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.app.Activity;
import com.mobandme.googlecards.demo.R;
import com.mobandme.googlecards.demo.adapters.CardViewAdapter;
import com.mobandme.googlecards.demo.helpers.ExceptionsHelper;

public class MainActivity extends Activity {

	private ListView mCardsList;
	private CardViewAdapter mCardsListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializaActivity();
	}
	
	private void initializaActivity() {
		try {
			
			setContentView(R.layout.activity_main);
			mCardsList = (ListView)findViewById(R.id.CardsList);
			if (mCardsList != null) {
				mCardsListAdapter = new CardViewAdapter(this);
				for(int i = 1; i <= 100; i++)
					mCardsListAdapter.add(String.format("Item %d", i));
				mCardsList.setAdapter(mCardsListAdapter);
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
}
