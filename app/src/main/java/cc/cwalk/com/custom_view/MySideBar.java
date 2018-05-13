package cc.cwalk.com.custom_view;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cc.cwalk.com.R;


public class MySideBar extends View {
	OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	private int worldSize=28;//字体大小
	// 按住改变背景色
	private boolean showBkg;
	public static String[] b = { "热", "A", "B", "C", "D", "E", "F", "G", "H",
			"J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "W",
			"X", "Y", "Z" };
	/** 被选中位置 */
	int choose = -1;
	private Paint paint = new Paint();
	@SuppressWarnings("unused")
	private Context context;

	public MySideBar(Context context) {
		super(context);
		this.context = context;
	}

	public MySideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public MySideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public void setLetter(String[] b){
		this.b=b;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (showBkg) {
			canvas.drawColor(Color.parseColor("#dddddd"));
		}

		float height = getHeight();
		float width = getWidth();
		// 计算单个字母高度
		float singleHeight = height / (float) (b.length);
		for (int i = 0; i < b.length; i++) {
			paint.setColor(context.getResources().getColor(R.color.blue));
			paint.setTextSize(worldSize);
			paint.setAntiAlias(true);
			paint.setFakeBoldText(true);
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			if (i == choose) {
				// 选中的颜色
				paint.setColor(Color.parseColor("#3399ff"));
				// 加粗
				paint.setFakeBoldText(true);
				paint.setTextSize(100);
			}
			// 设置文本坐标
			float xPos = width / 2 - paint.measureText(b[i]) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();
		}
	}

	/**
	 * 单个子母的触摸监听
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float y = event.getY();
		final int oldChoose = choose;
		final int c = (int) (y / getHeight() * b.length);
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			showBkg = true;
			if (oldChoose != c && listener != null) {
				if (c >= 0 && c < b.length) {
					listener.onTouchingLetterChanged(c);
					choose = c;
					invalidate();// Invalidate()函数的作用是使整个窗口客户区无效，窗口客户无效即需要重绘
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (oldChoose != c && listener != null) {
				if (c >= 0 && c < b.length) {
					listener.onTouchingLetterChanged(c);
					choose = c;
					invalidate();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			showBkg = false;
			choose = -1;
			invalidate();
			listener.onTouchingLetterEnd();
			break;
		}
		return true;
	}

	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(int s);
		public void onTouchingLetterEnd();
	}
}
