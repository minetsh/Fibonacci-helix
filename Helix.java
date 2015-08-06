package me.xiu.vortex.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class Helix extends View {

	private float mPivotX = 0;
	private float mPivotY = 0;
	private float mStep = 1;
	private float mQuarterCount = 12;
	private RectF mFrame;
	private Paint mPaint;
	private Path  mPath;
	private static final float[] dd = { 0f, 1f, 2f, 1f };

	public Helix(Context context) {
		super(context);
		initialize();
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		initialize();
	}
	
	private void initialize() {
		mPivotX = getWidth() >> 1;
		mPivotY = getHeight() >> 1;
		mFrame = new RectF();
		mPaint = new Paint();
		mPaint.setColor(Color.rgb(0, 0x96, 0x88));
		mPaint.setStrokeWidth(4);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setAntiAlias(true);
		mPath = new Path();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		float ns = mStep, ps = mStep;
		float x = mPivotX, y = mPivotY;
		float xx = x + ns, yy = y + ns;
		
		mPath.moveTo((x + xx) / 2, yy);
		mFrame.set(x, y, xx, yy);
		mPath.arcTo(mFrame, 90, 90);
		
		for (int i = 0; i < mQuarterCount-1; i++) {
			x -= dd[i & 3] * ps;
			y -= dd[(i + 3) & 3] * ps;
			xx += dd[(i + 2) & 3] * ps;
			yy += dd[(i + 1) & 3] * ps;
			mFrame.set(x, y, xx, yy);
			mPath.arcTo(mFrame, 90 * (i + 2) % 360, 90);
			ns += ps;
			ps  = ns - ps;
		}
		canvas.drawPath(mPath, mPaint);
	}
}
