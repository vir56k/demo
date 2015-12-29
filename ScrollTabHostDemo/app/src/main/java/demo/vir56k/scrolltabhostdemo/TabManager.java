package demo.vir56k.scrolltabhostdemo;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * Created by zhangyunfei on 15/10/23.
 */
public class TabManager {
    private TabManager() {
    }

    /**
     * 创建子 tab
     *
     * @param context
     * @param id
     */
    public static RadioButton creatTabItem(Context context, int id, String text) {
        final int width = DensityUtil.dip2px(context, 80);
        final int height = DensityUtil.dip2px(context, 46);
        final int padding = DensityUtil.dip2px(context, 3);

        RadioButton button;
        button = new RadioButton(context);
        button.setId(id);
//        button.setWidth(width);
//        button.setHeight(height);
        button.setText(text);
        button.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        button.setHeight(height);
        button.setBackgroundResource(R.drawable.tab_item_bg_selector);
        button.setButtonDrawable(context.getResources().getDrawable(android.R.color.transparent));
        button.setGravity(Gravity.CENTER);
        button.setTextColor(context.getResources().getColorStateList(R.color.tab_item_font_color_selector));
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        button.setPadding(0, 0, 0, padding);
        return button;
    }
}
