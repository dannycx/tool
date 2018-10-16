package com.danny.widget.expand;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * @description 可展开控件
 * @author danny
 * @date 2018/9/28.
 */

public class ExpandTextView extends AppCompatTextView {
    private final String TEXT_EXPAND = " 全文";
    private final String TEXT_CLOSE = " 展开";

    private SpannableString spanExpand = null;
    private SpannableString spanClose = null;

    private int width = 0;// 最大扩展宽度
    private int maxLine = 2;// 最多显示行数

    private String original;// 文本

    public ExpandTextView(Context context) {
        super(context);
        initCloseEnd(context);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCloseEnd(context);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCloseEnd(context);
    }

    private void initCloseEnd(Context context) {
        String content = TEXT_EXPAND;
        spanClose = new SpannableString(content);
        SpanBtn span = new SpanBtn(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpandTextView.super.setMaxLines(500);
                setExpandText(original);
            }
        }, context);
        spanClose.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private void initExpandEnd(Context context) {
        String content = TEXT_EXPAND;
        spanExpand = new SpannableString(content);
        SpanBtn span = new SpanBtn(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpandTextView.super.setMaxLines(maxLine);
                setCloseText(original);
            }
        }, context);
        spanExpand.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置闭合文本
     */
    private void setCloseText(String text) {
        if (spanClose == null) {
            initCloseEnd(getContext());
        }

        boolean appendShow = false;// true 不需要展开闭合    false 需要展开
        original = text.toString();

        int maxlines = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {// api大于16，可从资源中获取
            maxlines = getMaxLines();
        }else {
            maxlines = this.maxLine;
        }
        String workText = new StringBuilder(original).toString();

        if (maxlines != -1) {
            Layout layout = createWorkingLayout(original);
            if (layout.getLineCount() > maxlines) {
                workText = original.substring(0, getLineCount() - 1);
                String showText = original.substring(0, getLineCount() - 1);
                Layout layout2 = createWorkingLayout(showText);
                if (layout2.getLineCount() > maxlines) {
                    
                }
            }

        }
        
        append(workText);
        if (appendShow) {
            
        }
    }

    /**
     *
     * @param text
     * @return
     */
    private Layout createWorkingLayout(CharSequence text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return new StaticLayout(text, getPaint(), width - getPaddingLeft() - getPaddingRight()
                    , Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
        }else {
            return new StaticLayout(text, getPaint(), width - getPaddingLeft() - getPaddingRight()
                    , Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        }
    }

    /**
     * 设置展开文本
     */
    private void setExpandText(String text) {
        if (spanExpand == null) {
            initExpandEnd(getContext());
        }
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }
}
