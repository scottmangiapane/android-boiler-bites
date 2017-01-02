package com.cactuslabs.boilerbites;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListDivider extends RecyclerView.ItemDecoration {
    private Drawable divider;

    public ListDivider(Context context) {
        TypedArray styledAttributes = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        divider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0)
            return;
        outRect.top = divider.getIntrinsicHeight();
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + divider.getIntrinsicHeight();
            divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            divider.draw(canvas);
        }
    }
}