package me.xiu.vortex;

import me.xiu.vortex.view.Helix;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle iceberg) {
		super.onCreate(iceberg);
		RelativeLayout layout = new RelativeLayout(this);

		final Helix helix = new Helix(this);
		helix.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));

		LayoutParams params = new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		
		layout.addView(helix, params);
		helix.post(new Runnable() {

			@Override
			public void run() {
				helix.invalidate();
			}
		});

		setContentView(layout);
	}
}
