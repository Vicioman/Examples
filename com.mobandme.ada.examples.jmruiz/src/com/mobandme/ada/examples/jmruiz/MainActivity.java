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
   
   Website: http://adaframework.com
   Contact: Txus Ballesteros <txus.ballesteros@mobandme.com>
*/


package com.mobandme.ada.examples.jmruiz;

import java.util.List;

import com.mobandme.ada.Entity;
import com.mobandme.ada.ObjectSet;
import com.mobandme.ada.examples.jmruiz.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.jmruiz.model.context.DataBase;
import com.mobandme.ada.examples.jmruiz.model.entities.OrderLine;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

	List<OrderLine> 		mLines;
	ListView                mLinesList;
	ArrayAdapter<OrderLine> mLinesAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			
			initializeActivity();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	private void initializeActivity() {
		setContentView(R.layout.activity_main);
		
		mLinesList = (ListView)findViewById(R.id.lvOrderLines);
		
		initializeDataContext();
	}
	
	private void initializeDataContext() {
		DataBase.createContext(this);
		
		if (DataBase.Context.OrdersSet.size() > 0) {
			mLines = DataBase.Context.OrdersSet.get(0).lines;
			if (mLinesList != null) {
				//ATENCION, no se inicializa el adapter con elementos ya que se hará automáticamente en el método setAdapter.
				mLinesAdapter = new ArrayAdapter<OrderLine>(this, android.R.layout.simple_list_item_1);
			
				//Te faltaría añadir esto para hacer que la UI se actualice:
				((ObjectSet<OrderLine>)mLines).setAdapter(mLinesAdapter);
				
				mLinesList.setAdapter(mLinesAdapter);
			}
		}
	}
	
	public void commandDelete(View pView) {
		try {
			
			if (mLinesAdapter != null && mLinesAdapter.getCount() > 0) {
				mLines.remove(mLinesAdapter.getItem(0));
				Toast.makeText(this, String.format("Lines: %d", mLinesAdapter.getCount()), Toast.LENGTH_SHORT).show();
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	public void commandRevert(View pView) {
		try {
			
			((ObjectSet<OrderLine>)mLines).setAdapter(mLinesAdapter);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	public void commandSave(View pView) {
		try {
			
			if (DataBase.Context.OrdersSet.size() > 0) {
				DataBase.Context.OrdersSet.get(0).setStatus(Entity.STATUS_UPDATED);
				DataBase.Context.OrdersSet.save();
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
}
