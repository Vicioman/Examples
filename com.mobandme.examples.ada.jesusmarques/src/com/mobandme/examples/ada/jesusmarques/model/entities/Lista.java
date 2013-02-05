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

package com.mobandme.examples.ada.jesusmarques.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

@Table(name = "tLista")
public class Lista extends Entity {

	@TableField(name = "Nombre", datatype = DATATYPE_STRING)
	public String Nombre;
	
	@TableField(name = "Productos", datatype = DATATYPE_ENTITY)
	public List<Producto> Productos = new ArrayList<Producto>();
	
	public Lista() { }
	public Lista(String pName) {
		Nombre = pName;
	}
}
