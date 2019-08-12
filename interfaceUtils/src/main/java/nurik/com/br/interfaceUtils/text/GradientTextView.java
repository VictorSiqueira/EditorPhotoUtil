package nurik.com.br.interfaceUtils.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import nurik.com.br.interfaceUtils.R;


public class GradientTextView extends android.support.v7.widget.AppCompatTextView {

    private int[] colorArray = new int[]{
            Color.parseColor("#F97C3C"),
            Color.parseColor("#64B678"),
            Color.parseColor("#8446CC")};

    public GradientTextView(Context context) {
        super(context);
        init(null);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //Setting the gradient if layout is changed
        if (changed) {
            draw();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw();
    }

    private void init(@Nullable AttributeSet attrs) {
        if(attrs!=null){
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.GradientTextView);
            int initialcolor = ta.getColor(R.styleable.GradientTextView_initialColor, Color.BLACK);
            int midcolor = ta.getColor(R.styleable.GradientTextView_midColor, Color.BLACK);
            int finalcolor = ta.getColor(R.styleable.GradientTextView_finalColor, Color.BLACK);
            colorArray = new int[]{
                    initialcolor,
                    midcolor,
                    finalcolor};
            ta.recycle();
        }
    }

    private void draw(){
        Shader textShader = new LinearGradient(0, 0, getWidth(), getTextSize(), colorArray, null, Shader.TileMode.CLAMP);
        getPaint().setShader(textShader);
    }

    public void setInitialColor(int color){
        colorArray[0] = color;
        invalidate();
    }

    public void setMidColor(int color){
        colorArray[1] = color;
        invalidate();
    }

    public void setFinalColor(int color){
        colorArray[2] = color;
        invalidate();
    }

    public void setGradientColor(int color1, int color2, int color3){
        colorArray[0] = color1;
        colorArray[1] = color2;
        colorArray[2] = color3;
        invalidate();
    }
}
