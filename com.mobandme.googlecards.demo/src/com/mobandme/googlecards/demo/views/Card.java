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

package com.mobandme.googlecards.demo.views;

import com.mobandme.googlecards.demo.R;
import com.mobandme.googlecards.demo.helpers.ExceptionsHelper;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	private Animation mCardAnimation;
	private TextView  mCardText;
	private String    mText;
	
	public void   setText(String pText) { mText = pText; }
	public String getText() { return mText; }
	
	public void startAnimation() {
		this.startAnimation(mCardAnimation);
	}
	
	public Card(Context context) { super(context); }
	public Card(Context context, AttributeSet attrs) { super(context, attrs); }
	public Card(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		initializeView();
	}
	
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
	}
	
	private void initializeView() {
		try {
			
			mCardAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_card);
			LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layoutInflater.inflate(R.layout.view_card, this, true);
			
			mCardText = (TextView)findViewById(R.id.CardText);
			if (mCardText != null && mText != null)
				mCardText.setText(mText);
			
			this.startAnimation(mCardAnimation);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
	}
}
