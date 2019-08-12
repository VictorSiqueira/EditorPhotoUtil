package nurik.com.br.myeditor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*
import nurik.com.br.interfaceUtils.gesture.MultiTouchListener

class MainActivity : AppCompatActivity(), MultiTouchListener.OnMultiTouchListener {

    var items = arrayOf("teste", "teste", "teste", "teste")
    var icons = intArrayOf(R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_root.addView(addView())
        addText()

        addGesture(photo_raw, object : MultiTouchListener.OnGestureControl {
            override fun onClick() {}
            override fun onLongClick() {}
        })
    }

    private fun addText() {
        gradientTextView.background = resources.getDrawable(R.drawable.rounded_border)
        addGesture(gradientTextView, object : MultiTouchListener.OnGestureControl {
            override fun onClick() {}
            override fun onLongClick() {}
        })
    }

    private fun addView(): ImageView {
        var img = ImageView(this)
        img.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_launcher_background))
        addGesture(img,object : MultiTouchListener.OnGestureControl {
            override fun onClick() {
                if(img.tag == null){
                    img.tag = "white"
                    img.setImageDrawable(baseContext.getResources().getDrawable(R.drawable.ic_launcher_foreground))
                }else{
                    img.tag = null
                    img.setImageDrawable(baseContext.getResources().getDrawable(R.drawable.ic_launcher_background))
                }
            }
            override fun onLongClick(){ }
        })
        return img
    }

    private fun addGesture(v: View, callback: MultiTouchListener.OnGestureControl) {
        val multiTouchListener = getMultiTouchListener(v)
        multiTouchListener.setOnGestureControl(callback)
        v.setOnTouchListener(multiTouchListener)
    }

    private fun getMultiTouchListener(img : View): MultiTouchListener {
        var mult = MultiTouchListener(trash, view_root, img, true)
        mult.setOnMultiTouchListener(this);
        return mult
    }

    override fun onRemoveViewListener(removedView: View?) {
        if(removedView!=null)
            (removedView.parent as RelativeLayout).removeView(removedView)
    }

    override fun onEditTextClickListener(text: String?, colorCode: Int) {}
}
