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

package com.mobandme.examples.ada.jesusmarques.model.context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.ObjectSet;
import com.mobandme.examples.ada.jesusmarques.model.entities.Lista;
import com.mobandme.examples.ada.jesusmarques.model.entities.Producto;
import com.mobandme.examples.ada.jesusmarques.model.entities.Usuario;

public class DataContext extends ObjectContext {

	public ObjectSet<Usuario> UsuariosSet;
	
	public DataContext(Context pContext) { 
		super(pContext); 
		
		try {
			
			UsuariosSet = new ObjectSet<Usuario>(Usuario.class, this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onError(Exception pException) {
		pException.printStackTrace();
	}
	
	@Override
	protected void onPopulate(SQLiteDatabase pDatabase) {
		try {
			
			Usuario user = new Usuario("Mob&Me");
			user.Listas.add(new Lista("Lista 001"));
			user.Listas.get(0).Productos.add(new Producto("Producto 001", 12));
			
			UsuariosSet.add(user);
			UsuariosSet.save(pDatabase);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
