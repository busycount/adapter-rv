package com.busycount.rvadapter.divider;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Line Divider
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class LineDivider extends RecyclerView.ItemDecoration {

    private Paint paint;
    private int dividerSize;
    private int leftPadding;
    private int rightPadding;
    private int normalColor;
    private SparseArray<SpecialItem> specialArray;

    public LineDivider(@NonNull Builder builder) {
        this.dividerSize = builder.getDividerSize();
        this.leftPadding = builder.getLeftPadding();
        this.rightPadding = builder.getRightPadding();
        this.normalColor = builder.getColor();
        this.specialArray = builder.getSpecialArray();
        paint = new Paint();
    }

    public LineDivider(int dividerPx) {
        this.dividerSize = dividerPx;
        paint = new Paint();
    }

    private SpecialItem getSpecialItem(int position) {
        return specialArray != null ? specialArray.get(position) : null;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        offsetVertical(outRect, position);
    }

    private void offsetVertical(@NonNull Rect outRect, int position) {
        SpecialItem item = getSpecialItem(position);
        if (item != null) {
            //特殊
            outRect.left = item.leftPadding;
            outRect.right = item.rightPadding;
            outRect.top = item.size;
        } else {
            //普通
            if (position != 0) {
                outRect.top = dividerSize;
            }
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawLine(c, parent);
    }

    private void drawLine(@NonNull Canvas c, @NonNull RecyclerView parent) {
        View view;
        for (int i = 0, length = parent.getChildCount(); i < length; i++) {
            view = parent.getChildAt(i);
            SpecialItem item = getSpecialItem(i);
            if (item != null) {
                //绘制特殊
                paint.setColor(item.color);
                c.drawRect(view.getLeft() + item.leftPadding, view.getTop() - item.size, view.getRight() - item.rightPadding, view.getTop(), paint);
            } else {
                //绘制普通
                if (i == 0) {
                    continue;
                }
                paint.setColor(normalColor);
                c.drawRect(view.getLeft() + leftPadding, view.getTop() - dividerSize, view.getRight() - rightPadding, view.getTop(), paint);
            }
        }
    }

    /**
     * 特殊分割线
     */
    public static class SpecialItem {
        @ColorInt
        public int color;
        public int size;
        public int leftPadding;
        public int rightPadding;

        public SpecialItem() {
        }

        public SpecialItem(int color, int size, int leftPadding, int rightPadding) {
            this.color = color;
            this.size = size;
            this.leftPadding = leftPadding;
            this.rightPadding = rightPadding;
        }
    }

    public static class Builder {
        @ColorInt
        private int color;
        private int dividerSize;
        private int leftPadding;
        private int rightPadding;
        private SparseArray<SpecialItem> specialArray = new SparseArray<>();

        public static Builder get() {
            return new Builder();
        }

        public Builder setColor(@ColorInt int color) {
            this.color = color;
            return this;
        }

        public Builder setDividerSize(int dividerSize) {
            this.dividerSize = dividerSize;
            return this;
        }

        public Builder setLeftPadding(int leftPadding) {
            this.leftPadding = leftPadding;
            return this;
        }

        public Builder setRightPadding(int rightPadding) {
            this.rightPadding = rightPadding;
            return this;
        }

        public Builder setSpecialItem(int position, SpecialItem item) {
            this.specialArray.put(position, item);
            return this;
        }

        public int getColor() {
            return color;
        }

        public int getDividerSize() {
            return dividerSize;
        }

        public int getLeftPadding() {
            return leftPadding;
        }

        public int getRightPadding() {
            return rightPadding;
        }

        public SparseArray<SpecialItem> getSpecialArray() {
            return specialArray;
        }
    }
}
