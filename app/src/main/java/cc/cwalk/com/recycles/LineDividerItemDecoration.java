package cc.cwalk.com.recycles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Date 2018/4/13 10:27
 * DES : 分割线
 */
public class LineDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint ;
    private float mDividerHeight;

    public LineDividerItemDecoration() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();

        for ( int i = 0; i < childCount; i++ ) {
            View view = parent.getChildAt(i);

            int index = parent.getChildAdapterPosition(view);
            //第一个ItemView不需要绘制
            if ( index == 0 ) {
                continue;
            }

            float dividerTop = view.getTop() - mDividerHeight;
            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();

            c.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //第一个ItemView不需要在上面绘制分割线
        if (parent.getChildAdapterPosition(view) != 0){
            //这里直接硬编码为1px
            outRect.top = 1;
            mDividerHeight = 1;
        }
    }
}
