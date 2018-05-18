package cc.cwalk.com.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.List;

import cc.cwalk.com.R;

/**
 * Des：对话框工具
 * Author：林智聪
 * Time：2017/8/30 16:18
 */
public class DialogUtils {


	public interface DialogClickBack {
		public void define();

		public void cancel();
	}

	//====================自定义界面======================================

	/**
	 * @param title
	 * @param message
	 * @param btn1            取消按钮
	 * @param btn2            确定按钮
	 * @param context
	 * @param dialogClickBack
	 * @return
	 */
	public static void showTopicDialogsCustom(String title, String message, String btn1, String btn2, Context context, final DialogClickBack dialogClickBack) {
		try {
			View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom_layout, null);
			TextView titleTv = (TextView) view.findViewById(R.id.dialog_title);
			TextView contextTv = (TextView) view.findViewById(R.id.dialog_content);
			Button Btn1 = (Button) view.findViewById(R.id.dialog_btn1);
			Button Btn2 = (Button) view.findViewById(R.id.dialog_btn2);
			Button Btn = (Button) view.findViewById(R.id.dialog_btn);
			View BtnLayout = view.findViewById(R.id.dialog_twobtn_layout);
			Btn.setVisibility(View.GONE);
			BtnLayout.setVisibility(View.VISIBLE);
			Btn1.setText(btn1);
			Btn2.setText(btn2);
			titleTv.setText(title);
			contextTv.setText(message);
			final MyDialog myDialog = new MyDialog(context, 0, 0, view, R.style.CustomDialog);
			Btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (dialogClickBack != null) {
						dialogClickBack.cancel();
					}
					myDialog.dismiss();
				}
			});
			Btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (dialogClickBack != null) {
						dialogClickBack.cancel();
					}
					myDialog.dismiss();
				}
			});
			Btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (dialogClickBack != null) {
						dialogClickBack.define();
					}

					myDialog.dismiss();
				}
			});

			myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (dialogClickBack != null) {
						dialogClickBack.cancel();
					}
				}
			});
			//AlertDialog.Builder builder = new AlertDialog.Builder(context, yb.lib.R.style.CustomDialog);
			myDialog.setCancelable(true);
			//aDialog = builder.create();
			myDialog.show();

//			Window dialogWindow = myDialog.getWindow();
//			dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
//			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//			lp.width = (int) context.getResources().getDimension(yb.lib.R.dimen.d150);
//			lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//			dialogWindow.setGravity(Gravity.CENTER | Gravity.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param title
	 * @param message
	 * @param btn1            取消按钮
	 * @param btn2            确定按钮
	 * @param context
	 * @param dialogClickBack
	 * @return
	 */
	public static void showAotuYuyueTopicDialogs(String title, String message, String btn1, String btn2, Context context, final DialogClickBack dialogClickBack) {
		try {

			View view = LayoutInflater.from(context).inflate(R.layout.dialog_auto_yuuye, null);
			TextView titleTv = (TextView) view.findViewById(R.id.dialog_title);
			TextView contextTv = (TextView) view.findViewById(R.id.dialog_content);
			Button Btn1 = (Button) view.findViewById(R.id.dialog_btn1);
			Button Btn2 = (Button) view.findViewById(R.id.dialog_btn2);
			Button Btn = (Button) view.findViewById(R.id.dialog_btn);
			View BtnLayout = view.findViewById(R.id.dialog_twobtn_layout);
			Btn.setVisibility(View.GONE);
			BtnLayout.setVisibility(View.VISIBLE);
			Btn1.setText(btn1);
			Btn2.setText(btn2);
			titleTv.setText(title);
			contextTv.setGravity(Gravity.LEFT);
			contextTv.setText(message);
			final MyDialog myDialog = new MyDialog(context, 0, 0, view, R.style.CustomDialog);
			Btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (dialogClickBack != null) {
						dialogClickBack.cancel();
					}
					myDialog.dismiss();
				}
			});
			Btn1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (dialogClickBack != null) {
						dialogClickBack.cancel();
					}
					myDialog.dismiss();
				}
			});
			Btn2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (dialogClickBack != null) {
						dialogClickBack.define();
					}

					myDialog.dismiss();
				}
			});

			myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if (dialogClickBack != null) {
						dialogClickBack.cancel();
					}
				}
			});

			myDialog.setCancelable(true);


			myDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 两个按钮
	 *
	 * @param message
	 * @param context
	 * @param dialogClickBack
	 * @return
	 */
	public static void showTopicDialogsCustom(String message, Context context, final DialogClickBack dialogClickBack) {
		showTopicDialogsCustom("提示", message, "取消", "确定", context, dialogClickBack);
	}

	/**
	 * 一个按钮
	 *
	 * @param title
	 * @param message
	 * @param btn
	 * @param context
	 * @param dialogClickBack
	 * @return
	 */
	public static void showTopicDialogsCustom(String title, String message, String btn, Context context, final DialogClickBack dialogClickBack) {
		try {
			View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom_layout, null);
			TextView titleTv = (TextView) view.findViewById(R.id.dialog_title);
			TextView contextTv = (TextView) view.findViewById(R.id.dialog_content);
			Button Btn = (Button) view.findViewById(R.id.dialog_btn);
			View BtnLayout = view.findViewById(R.id.dialog_twobtn_layout);
			BtnLayout.setVisibility(View.GONE);
			Btn.setVisibility(View.VISIBLE);

			if (btn != null && !btn.equals("")) {
				Btn.setText(btn);
			}

			if (title != null && !title.equals("")) {
				titleTv.setText(title);
			}

			contextTv.setText(message);

			final MyDialog dialog = new MyDialog(context, 0, 0, view, R.style.CustomDialog);

			Btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (dialogClickBack != null) {
						dialogClickBack.define();
					}
					dialog.dismiss();
				}
			});

			dialog.setCancelable(true);
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//======================ListDialogs================================================================
	public interface DialogSelectClickBack {
		public void select(String selectMsg, int position);
	}

}

