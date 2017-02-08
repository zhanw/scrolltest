package com.zhanw.scrolltest;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PullToRefreshView extends LinearLayout{

	private ListView listView;
	private TextView headerTextView;
	
	int headerHeight;
	
	private Handler handler = new Handler();

	public PullToRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PullToRefreshView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		LayoutInflater.from(context).inflate(R.layout.widget_refresh_view, this);
		FrameLayout headerLayout = (FrameLayout) findViewById(R.id.header_layout);
		headerTextView  = (TextView) findViewById(R.id.header_text);
		listView = (ListView) findViewById(R.id.listview);
		
		headerHeight = DensityUtil.dip2px(context, 50);
		//设置的padding为-header height，这样正好把header隐藏
		setPadding(0, -headerHeight, 0, 0);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//简单起见，把touchevent都交给自身的ontouchevent()处理，所以这里的listview不能滑动
		return true;
	}
	
	float initialY;
	float lastY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastY = initialY = event.getY();
			return true;
		case MotionEvent.ACTION_MOVE:
			lastY = event.getY();
			pullEvent();
			return true;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			upEvent();
			return true;

		default:
			break;
		}
		
		return false;
	}
	
	private void pullEvent(){
		//获取滑动的距离，这里除了一个摩擦系数2.0
		//如果往上滑，value就是取0，页面自然不会跟着滑动
		int scrollValue = Math.round(Math.min(initialY - lastY, 0) / 2.0f);
		//根据scrollValue往下滑动
		scrollTo(0, scrollValue);
	}
	
	private void upEvent(){
		//滑动到正好显示header的位置
		//正常会慢慢的滑动过去，这里为了示例代码简单，直接到目标位置
		scrollTo(0, -headerHeight);
		headerTextView.setText("正在刷新");
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				//刷新完毕后隐藏header，滑动到原始位置
				scrollTo(0, 0);
				headerTextView.setText("下拉刷新Header");
			}
		}, 2000);
	}
	
	public ListView getListView(){
		return listView;
	}
}
