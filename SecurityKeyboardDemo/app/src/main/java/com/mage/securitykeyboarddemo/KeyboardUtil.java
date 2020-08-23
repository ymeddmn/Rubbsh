package com.mage.securitykeyboarddemo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class KeyboardUtil {
	private Context myContext;
	private Activity myActivity;
	private KeyboardView keyboardView;
	private Keyboard key_english;// 字母键盘
	private Keyboard symbols, symbols_shift;// 数字键盘及第二个菜单
	public boolean isnun = false;// 是否数据键盘
	public boolean isupper = false;// 是否大写
	private WebView contentWebView = null;
	private EditText ed;

	public KeyboardUtil(Activity activity, Context context, EditText editText) {
		this.myActivity = activity;
		this.myContext = context;
		this.ed = editText;
		key_english = new Keyboard(myContext, R.xml.english);
		symbols = new Keyboard(myContext, R.xml.symbols);
		symbols_shift = new Keyboard(myContext, R.xml.symbols_shift);

		keyboardView = (KeyboardView) myActivity
				.findViewById(R.id.keyboard_view);
		keyboardView.setKeyboard(key_english);
		keyboardView.setEnabled(true);
		keyboardView.setPreviewEnabled(true);
		keyboardView.setOnKeyboardActionListener(listener);
	}

	public KeyboardUtil(Activity activity, Context context,
			WebView contentWebView) {
		this.myActivity = activity;
		this.myContext = context;
		this.contentWebView = contentWebView;
		key_english = new Keyboard(myContext, R.xml.english);
		symbols = new Keyboard(myContext, R.xml.symbols);
		symbols_shift = new Keyboard(myContext, R.xml.symbols_shift);

		keyboardView = (KeyboardView) myActivity
				.findViewById(R.id.keyboard_view);
		keyboardView.setKeyboard(key_english);
		keyboardView.setEnabled(true);
		keyboardView.setPreviewEnabled(true);
		keyboardView.setOnKeyboardActionListener(listener);
	}

	private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onPress(int primaryCode) {
		}

		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			if (ed != null) {
				Editable editable = ed.getText();
				int start = ed.getSelectionStart();
				if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
					// 完成按钮所做的动作
					hideKeyboard();
				} else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退

					 //删除按钮所做的动作
					 if (editable != null && editable.length() > 0) {
					 if (start > 0) {
					 editable.delete(start - 1, start);
					 }
					 }
				} else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
					// 大小写切换
					changeKey();
					keyboardView.setKeyboard(key_english);

				} else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
					// 数字键盘切换,默认是英文键盘
					if (isnun) {
						isnun = false;
						keyboardView.setKeyboard(key_english);
					} else {
						isnun = true;
						keyboardView.setKeyboard(symbols);
						if (primaryCode == -102) {
							keyboardView.setKeyboard(symbols_shift);
						}

					}
				}

				else if (primaryCode == 57419) { // go left
					if (start > 0) {
						ed.setSelection(start - 1);
					}
				} else if (primaryCode == 57421) { // go right
					if (start < ed.length()) {
						ed.setSelection(start + 1);
					}
				}
				// 此部分为股票特殊代码区域
				// else if (primaryCode == 00001) {
				// editable.insert(start, "600");
				// } else if (primaryCode == 00002) {
				// editable.insert(start, "601");
				// } else if (primaryCode == 00003) {
				// editable.insert(start, "000");
				// } else if (primaryCode == 00004) {
				// editable.insert(start, "002");
				// } else if (primaryCode == 00005) {
				// editable.insert(start, "300");
				// } else if (primaryCode == 00006) {
				// editable.clear();
				// } else if (primaryCode == 00007) {
				// // Toast.makeText(myContext, "查看资讯按钮", 1).show();
				// }

				else {
					editable.insert(start, Character.toString((char)
					primaryCode));
				}
			
			} else {

				// Editable editable = ed.getText();
				// int start = ed.getSelectionStart();
				if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
					// 完成按钮所做的动作
					hideKeyboard();
				} else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
					contentWebView.loadUrl("javascript:javacalljsclear()");

					// 删除按钮所做的动作
					// if (editable != null && editable.length() > 0) {
					// if (start > 0) {
					// editable.delete(start - 1, start);
					// }
					// }
				} else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
					// 大小写切换
					changeKey();
					keyboardView.setKeyboard(key_english);

				} else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
					// 数字键盘切换,默认是英文键盘
					if (isnun) {
						isnun = false;
						keyboardView.setKeyboard(key_english);
					} else {
						isnun = true;
						keyboardView.setKeyboard(symbols);
						if (primaryCode == -102) {
							keyboardView.setKeyboard(symbols_shift);
						}

					}
				}

				// else if (primaryCode == 57419) { // go left
				// if (start > 0) {
				// ed.setSelection(start - 1);
				// }
				// } else if (primaryCode == 57421) { // go right
				// if (start < ed.length()) {
				// ed.setSelection(start + 1);
				// }
				// }
				// 此部分为股票特殊代码区域
				// else if (primaryCode == 00001) {
				// editable.insert(start, "600");
				// } else if (primaryCode == 00002) {
				// editable.insert(start, "601");
				// } else if (primaryCode == 00003) {
				// editable.insert(start, "000");
				// } else if (primaryCode == 00004) {
				// editable.insert(start, "002");
				// } else if (primaryCode == 00005) {
				// editable.insert(start, "300");
				// } else if (primaryCode == 00006) {
				// editable.clear();
				// } else if (primaryCode == 00007) {
				// // Toast.makeText(myContext, "查看资讯按钮", 1).show();
				// }

				else {
					// editable.insert(start, Character.toString((char)
					// primaryCode));
					Log.i("YUNCUNCHU",
							(char) primaryCode + "|||"
									+ Character.toString((char) primaryCode));
					contentWebView.loadUrl("javascript:javacalljswithargs("
							+ "'-$-'" + ",'"
							+ Character.toString((char) primaryCode) + "')");
				}
			}
		}
	};

	/**
	 * 键盘大小写切换
	 */
	private void changeKey() {
		List<Key> keylist = key_english.getKeys();
		if (isupper) {// 大写切换小写
			isupper = false;
			for (Key key : keylist) {
				if (key.label != null && isword(key.label.toString())) {
					key.label = key.label.toString().toLowerCase();
					key.codes[0] = key.codes[0] + 32;
				}
			}
		} else {// 小写切换大写
			isupper = true;
			for (Key key : keylist) {
				if (key.label != null && isword(key.label.toString())) {
					key.label = key.label.toString().toUpperCase();
					key.codes[0] = key.codes[0] - 32;
				}
			}
		}
	}

	// 显示键盘
	public void showKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.GONE || visibility == View.INVISIBLE) {
			keyboardView.setVisibility(View.VISIBLE);
		}
	}

	// 隐藏键盘
	public void hideKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.VISIBLE) {
			keyboardView.setVisibility(View.INVISIBLE);
		}
	}

	private boolean isword(String str) {
		String wordstr = "abcdefghijklmnopqrstuvwxyz";
		if (wordstr.indexOf(str.toLowerCase()) > -1) {
			return true;
		}
		return false;
	}

}
