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

package com.mobandme.ada.examples.jmruiz.model.context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.ObjectSet;
import com.mobandme.ada.examples.jmruiz.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.jmruiz.model.entities.Order;
import com.mobandme.ada.examples.jmruiz.model.entities.OrderLine;
import com.mobandme.ada.exceptions.AdaFrameworkException;

public class DataContext extends ObjectContext {

	private boolean          creationTime = false;
	public  ObjectSet<Order> OrdersSet;
	
	public DataContext(Context pContext) { 
		super(pContext);
		
		initializeContext();
	}
	
	@Override
	protected void onCreate(SQLiteDatabase pDataBase) throws AdaFrameworkException {
		creationTime = true;
		super.onCreate(pDataBase);
	}
	
	@Override
	protected void onError(Exception pException) {
		ExceptionsHelper.manage(getContext(), pException);
	}
	
	@Override
	protected void onPopulate(SQLiteDatabase pDatabase) {
		try {
			
			if (creationTime) {
				Order order = new Order();
				for(int counter = 1; counter <= 10; counter++) {
					order.lines.add(new OrderLine(String.format("Product %03d", counter), counter));
				}
				OrdersSet.add(order);
				OrdersSet.save(pDatabase);
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
	
	private void initializeContext() {
		try {
				
			OrdersSet = new ObjectSet<Order>(Order.class, this);
			OrdersSet.fill(Order.DEFAULT_SORT);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
}
