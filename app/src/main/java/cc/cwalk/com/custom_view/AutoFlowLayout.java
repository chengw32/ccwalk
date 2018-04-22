package cc.cwalk.com.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * Creat By_Chen
 * Time 2018/4/21 8:21
 * Des 自定义控件
 * */
public class AutoFlowLayout extends ViewGroup {
    public AutoFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public AutoFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public AutoFlowLayout(Context context) {
        super(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int stages = 1;
        int stageHeight = 0;
        int stageWidth = 0;

        int wholeWidth = MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            // measure
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            stageWidth += (child.getMeasuredWidth() + child.getPaddingLeft() + child.getPaddingRight());
            if (stageWidth >= wholeWidth) {
                stages++;
                // reset stageWidth
                stageWidth = child.getMeasuredWidth();
            }

        }
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            stageHeight = childAt.getMeasuredHeight() + childAt.getPaddingTop() + childAt.getPaddingBottom();
        }
        int wholeHeight = stageHeight * stages;

        // report this final dimension
        setMeasuredDimension(resolveSize(wholeWidth, widthMeasureSpec),
                resolveSize(wholeHeight, heightMeasureSpec));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        t = 0;
        final int count = getChildCount();
        r -= l ;
        int row = 0; // which row lay your view relative to parent
        int lengthX = 0; // right position of child relative to parent
        int lengthY = t; // bottom position of child relative to parent

        for (int i = 0; i < count; i++) {
            final View child = this.getChildAt(i);
            int paddingLeft = child.getPaddingLeft();
            int paddingRight = child.getPaddingRight();
            int paddingTop = child.getPaddingTop();
            int paddingBottom = child.getPaddingBottom();
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            lengthX += paddingLeft;

            // if it cant't draw in a same line ,skip it to next line
            if (lengthX + width + paddingRight > r) {
                //换行的出事 x 值是 paddingLeft
                lengthX = paddingLeft;
                row++;
                lengthY = row * (height + paddingTop+paddingBottom);

            }

            child.layout(lengthX, lengthY, lengthX + width, lengthY + height);

            lengthX += width + paddingRight;

        }

    }

}
