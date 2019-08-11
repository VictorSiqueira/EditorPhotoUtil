package nurik.com.br.myeditor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import nurik.com.br.editorphotoutil.MultiTouchListener
import nurik.com.br.editorphotoutil.ViewType

class MainActivity : AppCompatActivity(), MultiTouchListener.OnMultiTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*var iv_sticker = StickerImageView(this)
        iv_sticker.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_launcher_background))
        view_root.addView(iv_sticker)*/


        var img = ImageView(this)
        img.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_launcher_background))
        view_root.addView(img)

        val multiTouchListener = getMultiTouchListener(img)
        multiTouchListener.setOnGestureControl(object : MultiTouchListener.OnGestureControl {

            override fun onClick() {
                /*val isBackgroundVisible = frmBorder.getTag() != null && frmBorder.getTag()
                frmBorder.setBackgroundResource(if (isBackgroundVisible) 0 else R.drawable.rounded_border_tv)
                imgClose.setVisibility(if (isBackgroundVisible) View.GONE else View.VISIBLE)
                frmBorder.setTag(!isBackgroundVisible)*/
            }

            override fun onLongClick() {

            }
        })

        img.setOnTouchListener(multiTouchListener)

    }

    private fun getMultiTouchListener(img : ImageView): MultiTouchListener {
        var mult = MultiTouchListener(
            trash,
            view_root,
            img,
            true
        )
        mult.setOnMultiTouchListener(this);
        return mult
    }

    override fun onRemoveViewListener(removedView: View?) {
        removedView!!.visibility = View.GONE
    }

    override fun onEditTextClickListener(text: String?, colorCode: Int) {

    }
}
