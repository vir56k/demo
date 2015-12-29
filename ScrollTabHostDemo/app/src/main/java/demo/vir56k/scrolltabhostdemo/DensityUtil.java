/*   
 * Copyright (c) 2014-2015 Zhong Ke Fu Chuang (Beijing) Technology Co., Ltd.  All Rights Reserved.   
 *    
 */

package demo.vir56k.scrolltabhostdemo;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

/**
 * @Description: TODO
 * @author
 * @date 2015-6-17 下午5:38:24
 * @version V1.0
 */
public class DensityUtil {
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取屏幕尺寸
	 */
	public static int getScreenValue(Activity act,int orientation){
		DisplayMetrics dm = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(dm);
		if(LinearLayout.VERTICAL==orientation)
			return dm.heightPixels;
		if(LinearLayout.HORIZONTAL==orientation)
			return dm.widthPixels;
		return 0;
	}

	public static int getScreenWidth(Activity act){
		return getScreenValue(act, LinearLayout.HORIZONTAL);
	}

	public static int getScreenHight(Activity act){
		return getScreenValue(act, LinearLayout.VERTICAL);
	}
}
