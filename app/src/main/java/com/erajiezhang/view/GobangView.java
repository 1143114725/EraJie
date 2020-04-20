package com.erajiezhang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.erajiezhang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 五子棋View
 *
 * @author EraJieZhang
 * @data 2020/2/10
 */
public class GobangView extends View {
	public GobangView(Context context) {
		super(context);
	}
	
	public GobangView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	
	public GobangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	public GobangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}
	
	//棋盘宽度
	private int mPanelwidth;
	//棋盘高度
	private float mLingheight;
	//棋盘行数
	private int MAX_LINE = 10;
	//画棋盘的画笔
	private Paint mPaint = new Paint();
	//白子
	private Bitmap mWhitePiece;
	//黑子
	private Bitmap mBlackPiece;
	//棋子大小比例
	private float ratioPoeceLineHeight = 3 * 1.0f / 4;
	//白子集合
	private ArrayList<Point> mWhiteArray = new ArrayList<>();
	//黑子集合
	private ArrayList<Point> mBlackArray = new ArrayList<>();
	//白棋先手（true:当前点击落子白棋）
	private boolean mIsWhite = true;
	//游戏是否结束
	private boolean mIsGameOver;
	//白棋是否赢了
	private boolean mIsWhiteWinner;
	//最大连接数（胜利条件）
	private int MAX_COUNT_IN_LINE = 5;
	
	/**
	 * 初始化
	 */
	private void init() {
		//设置画笔
		mPaint.setColor(0x88000000);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStyle(Paint.Style.STROKE);
		//加载黑白棋子
		mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w);
		mBlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b);
		
		
	}
	
	/**
	 * 计算宽高
	 *
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//计算宽高
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		int widthModle = MeasureSpec.getMode(widthMeasureSpec);
		int heightModle = MeasureSpec.getMode(heightMeasureSpec);
		
		int width = Math.min(widthSize, heightSize);
		//兼容嵌套scorllView
		if ( widthModle == MeasureSpec.UNSPECIFIED ) {
			width = heightSize;
		} else if ( heightSize == MeasureSpec.UNSPECIFIED ) {
			width = widthSize;
		}
		
		setMeasuredDimension(width, width);
		
	}
	
	/**
	 * 改变布局
	 *
	 * @param w
	 * @param h
	 * @param oldw
	 * @param oldh
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mPanelwidth = w;
		mLingheight = mPanelwidth * 1.0f / MAX_LINE;
		//设置棋子大小
		int pieceWidth = (int) (mLingheight * ratioPoeceLineHeight);
		mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
		mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, pieceWidth, pieceWidth, false);
	}
	
	/**
	 * 点击事件
	 *
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//游戏结束返回false
		if ( mIsGameOver ) {
			return false;
		}
		int action = event.getAction();
		//抬起手指时
		if ( action == MotionEvent.ACTION_UP ) {
			
			int x = (int) event.getX();
			int y = (int) event.getY();
			Point point = getValidPoint(x, y);
			//如果这个点有其他棋子返回false
			if ( mWhiteArray.contains(point) || mBlackArray.contains(point) ) {
				return false;
			}
			//落子 加入对应的棋子list中
			if ( mIsWhite ) {
				mWhiteArray.add(point);
			} else {
				mBlackArray.add(point);
			}
			//改变当前是谁落子
			mIsWhite = !mIsWhite;
			//重绘布局
			invalidate();
			
			
		}
		return true;
	}
	
	/**
	 * 计算横纵坐标
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private Point getValidPoint(int x, int y) {
		
		return new Point((int) (x / mLingheight), (int) (y / mLingheight));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBoard(canvas);
		drawPieces(canvas);
		checkGameOver();
	}
	
	/**
	 * 检查游戏是否结束
	 */
	private void checkGameOver() {
		
		boolean whiteWin = checkFiveInLine(mWhiteArray);
		boolean blackWin = checkFiveInLine(mBlackArray);
		if ( whiteWin || blackWin ) {
			mIsGameOver = true;
			mIsWhiteWinner = whiteWin;
			String text = mIsWhiteWinner ? "白棋胜利！" : "黑棋胜利！";
			Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 检查五子连珠
	 *
	 * @param whiteArray
	 * @return
	 */
	private boolean checkFiveInLine(List<Point> whiteArray) {
		for (Point point : whiteArray) {
			int x = point.x;
			int y = point.y;
			boolean win = checkHorizontal(x, y, whiteArray);
			if ( win ) {
				return true;
			}
			win = checkVertical(x, y, whiteArray);
			if ( win ) {
				return true;
			}
			win = checkLeftDiagonal(x, y, whiteArray);
			if ( win ) {
				return true;
			}
			win = checkRightDiagonal(x, y, whiteArray);
			if ( win ) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 检查横向
	 *
	 * @param x
	 * @param y
	 * @param whiteArray
	 * @return
	 */
	private boolean checkHorizontal(int x, int y, List<Point> whiteArray) {
		int count = 1;
		for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
			if ( whiteArray.contains(new Point(x - i, y)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
			if ( whiteArray.contains(new Point(x + i, y)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检查纵向
	 *
	 * @param x
	 * @param y
	 * @param whiteArray
	 * @return
	 */
	private boolean checkVertical(int x, int y, List<Point> whiteArray) {
		int count = 1;
		for (int i = 1, size = MAX_COUNT_IN_LINE; i < size; i++) {
			if ( whiteArray.contains(new Point(x, y - i)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		for (int i = 1, size = MAX_COUNT_IN_LINE; i < size; i++) {
			if ( whiteArray.contains(new Point(x, y + i)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 检查左斜
	 *
	 * @param x
	 * @param y
	 * @param whiteArray
	 * @return
	 */
	private boolean checkLeftDiagonal(int x, int y, List<Point> whiteArray) {
		int count = 1;
		for (int i = 1, size = MAX_COUNT_IN_LINE; i < size; i++) {
			if ( whiteArray.contains(new Point(x - i, y + i)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		for (int i = 1, size = MAX_COUNT_IN_LINE; i < size; i++) {
			if ( whiteArray.contains(new Point(x + i, y + i)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 检查右斜
	 *
	 * @param x
	 * @param y
	 * @param whiteArray
	 * @return
	 */
	private boolean checkRightDiagonal(int x, int y, List<Point> whiteArray) {
		int count = 1;
		for (int i = 1, size = MAX_COUNT_IN_LINE; i < size; i++) {
			if ( whiteArray.contains(new Point(x - i, y - i)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		for (int i = 1, size = MAX_COUNT_IN_LINE; i < size; i++) {
			if ( whiteArray.contains(new Point(x + i, y + i)) ) {
				count++;
			} else {
				break;
			}
		}
		if ( count == MAX_COUNT_IN_LINE ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 绘制棋子
	 *
	 * @param canvas
	 */
	private void drawPieces(Canvas canvas) {
		//在数组中取出来绘制到对应的坐标上
		for (int i = 0, size = mWhiteArray.size(); i < size; i++) {
			Point whitePoint = mWhiteArray.get(i);
			canvas.drawBitmap(mWhitePiece,
				(whitePoint.x + (1 - ratioPoeceLineHeight) / 2) * mLingheight,
				(whitePoint.y + (1 - ratioPoeceLineHeight) / 2) * mLingheight, null);
		}
		
		for (int i = 0, size = mBlackArray.size(); i < size; i++) {
			Point blackPoint = mBlackArray.get(i);
			canvas.drawBitmap(mBlackPiece,
				(blackPoint.x + (1 - ratioPoeceLineHeight) / 2) * mLingheight,
				(blackPoint.y + (1 - ratioPoeceLineHeight) / 2) * mLingheight, null);
		}
	}
	
	/**
	 * 绘制棋盘
	 *
	 * @param canvas
	 */
	private void drawBoard(Canvas canvas) {
		int w = mPanelwidth;
		float lineheight = mLingheight;
		for (int i = 0, size = MAX_LINE; i < size; i++) {
			int startX = (int) (lineheight / 2);
			int endX = (int) (w - lineheight / 2);
			int y = (int) ((0.5 + i) * lineheight);
			//横线
			canvas.drawLine(startX, y, endX, y, mPaint);
			//竖线
			canvas.drawLine(y, startX, y, endX, mPaint);
		}
		
	}
	
	private static final String INSTANCE = "isntance";
	private static final String INSTANCE_GAME_OVER = "isntance_game_over";
	private static final String INSTANCE_WHITE_ARRAY = "isntance_white_array";
	private static final String INSTANCE_BLACK_ARRAY = "isntance_black_array";
	
	@Nullable
	@Override
	protected Parcelable onSaveInstanceState() {
		//保存数据
		Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
		bundle.putBoolean(INSTANCE_GAME_OVER, mIsGameOver);
		bundle.putParcelableArrayList(INSTANCE_WHITE_ARRAY, mWhiteArray);
		bundle.putParcelableArrayList(INSTANCE_BLACK_ARRAY, mBlackArray);
		return bundle;
	}
	
	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		//恢复数据
		if ( state instanceof Bundle ) {
			Bundle bundle = (Bundle) state;
			mIsGameOver = bundle.getBoolean(INSTANCE_GAME_OVER);
			mWhiteArray = bundle.getParcelableArrayList(INSTANCE_WHITE_ARRAY);
			mBlackArray = bundle.getParcelableArrayList(INSTANCE_BLACK_ARRAY);
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE));
			return;
		}
		
		super.onRestoreInstanceState(state);
	}
	
	/**
	 * 重新开始
	 */
	public void start() {
		mIsWhiteWinner = false;
		mIsGameOver = false;
		mIsWhite = true;
		mBlackArray.clear();
		mWhiteArray.clear();
		invalidate();
	}
	
	/**
	 * 悔棋
	 */
	public void regretgame() {
		if ( mIsWhite ) {
			if ( mBlackArray.size() != 0 ) {
				mBlackArray.remove(mBlackArray.size() - 1);
			} else {
				return;
			}
		} else {
			if ( mWhiteArray.size() != 0 ) {
				mWhiteArray.remove(mWhiteArray.size() - 1);
			} else {
				return;
			}
		}
		mIsWhite = !mIsWhite;
		mIsGameOver = false;
		invalidate();
	}
	
	/**
	 * 认输
	 */
	public void admitdefeat() {
		mIsGameOver = true;
		if ( mIsWhite ) {
			mIsWhiteWinner = false;
		}else{
			mIsWhiteWinner = true;
		}
		String text = mIsWhiteWinner ? "白棋胜利！" : "黑棋胜利！";
		Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
		start();
	}
}
