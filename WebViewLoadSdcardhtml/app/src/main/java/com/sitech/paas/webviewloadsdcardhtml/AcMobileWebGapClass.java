package com.sitech.paas.webviewloadsdcardhtml;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;


//<LocalStorageWebViewImport>
//<SessionStorageStorageWebViewImport>
//<NetProgressDialogWebViewImport>
//<ThreeDesSecretLibWebViewImport>
//<NetworkManagerWebViewImport>
//<OSUtilsWebViewImport>
//<SystemCameraWebViewImport>
//<HttpClientGapWebViewImport>
//<MediaWebViewImport>
//<AudiohandlerWebViewImport>
//<GeoLocationWebViewImport>
//<LocationWebViewImport>
//<FileOperationWebViewImport>
//<AppStoreFunctionWebViewImport>
//<IDCardScanBlueWebViewImport>
//<SimCardOperateWebViewImport>
//<SitechLogWebViewImport>
//<FingerPrintIdentificationWebViewImport>
//<DateFormatLibWebViewImport>
//<FaceCheckWebViewImport>
//<QRcodeWebViewImport>
//<AppUpdateWebViewImport>
//<ListPersistenceWebViewImport>
//<OpenNativeUiBoxWebViewImport>
//<DrawBoardWebViewImport>
//<ZipUnArchiverWebViewImport>
//<HybirdPageWebViewImport>

//<GesturePasswordWebViewImport>
//<SoundRecordingWebViewImport>
//<IDCardScanImport>
//<VoiceImport>
//<CalenderEventWebViewImport>
//<SocialShareWebViewImport>
//<VideoRecordingWebViewImport>
//<AIFunctionWebViewImport>
public class AcMobileWebGapClass extends WebView {

	public Context mContext;
	public Activity mActivity;

	//页面加载失败时，加载默认的错误页面
	private String errorPage;

	//拍照、二维码扫描成功和失败回调方法
	private String succCallback;
	private String failCallback;
	private int quality;

    public ValueCallback<Uri[]> mUploadCallbackAboveL;
    public ValueCallback<Uri> mUploadMessage;

	public AcMobileWebGapClass(Context context) {
		super(context);
		this.mContext = context;
		initWebView();
	}

	public AcMobileWebGapClass(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initWebView();
	}

	public AcMobileWebGapClass(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		initWebView();
	}


	private void initWebView() {
		setWebChromeClient(new AcWebChromeClient());
		setWebViewClient(new AcViewClient());
		setVerticalScrollBarEnabled(false);
		setHorizontalScrollBarEnabled(false);
		setFocusableInTouchMode(true);
		requestFocus(View.FOCUS_DOWN);
		setDrawingCacheEnabled(true);
		WebSettings settings = getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		settings.setAppCacheEnabled(false);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setDomStorageEnabled(true);
		settings.setGeolocationEnabled(false);
		settings.setSupportZoom(false);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		bindInterface();
	}

	public void bindInterface() {
		//<LocalStorageWebViewbind>
        //<SessionStorageStorageWebViewbind>
        //<NetProgressDialogWebViewbind>
        //<ThreeDesSecretLibWebViewbind>
		//<NetworkManagerWebViewbind>
		//<OSUtilsWebViewbind>
        //<SystemCameraWebViewbind>

	}

	/**
	 * 在ui线程里调用js方法<br/>
	 * @author wangxd created at 2017/6/22 17:20
	 * @param
	 * @return
	 */
	private Handler loadJsHandle = new Handler(){
		@Override
		public void handleMessage(final Message msg) {
			super.handleMessage(msg);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				AcMobileWebGapClass.this.evaluateJavascript((String)msg.obj, null);
			} else {
				AcMobileWebGapClass.this.loadUrl( (String)msg.obj);
			}
		}
	};

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getFailCallback() {
		return failCallback;
	}

	public void setFailCallback(String failCallback) {
		this.failCallback = failCallback;
	}

	public String getSuccCallback() {
		return succCallback;
	}

	public void setSuccCallback(String succCallback) {
		this.succCallback = succCallback;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	private class AcWebChromeClient extends WebChromeClient {



		@Override
		public boolean onJsBeforeUnload(WebView view, String url,
                                        String message, JsResult result) {
			return super.onJsBeforeUnload(view, url, message, result);
		}





		public class apiOKDialog implements DialogInterface.OnClickListener {
			private JsResult result;

			public apiOKDialog(JsResult result) {
				this.result = result;
			}

			public void onClick(DialogInterface dialog, int which) {
				result.confirm();
				dialog.dismiss();
			}
		}

		public class apiCancelDialog implements DialogInterface.OnClickListener {
			private JsResult result;

			public apiCancelDialog(JsResult result) {
				this.result = result;
			}

			public void onClick(DialogInterface dialog, int which) {
				result.cancel();
				dialog.dismiss();
			}
		}

        public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String capture) {
            //jsImage1

        }
        //API >= 21(Android 5.0)回调此方法
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

            //jsImage2
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);

        }

	}

	public class AcViewClient extends WebViewClient {
		@Override
		public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
			if(getErrorPage()!=null){
				view.loadUrl("file:///android_asset/html/" + getErrorPage());
			}else
			{
				super.onReceivedError(view,request,error);
			}

		}
	}

}
