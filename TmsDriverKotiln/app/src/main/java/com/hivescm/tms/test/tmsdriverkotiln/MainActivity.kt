package com.hivescm.tms.test.tmsdriverkotiln

import android.animation.Animator
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initlistener()
        findViewById<FrameLayout>(R.id.fl_container)
    }

    private fun initlistener() {
        ll_main.setOnClickListener(this)
        ll_dynamic.setOnClickListener(this)
        ll_mine.setOnClickListener(this)
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener {

        }

        animator.addListener(MainAniminatorListener())

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_main -> {
                Toast.makeText(this,"首页",Toast.LENGTH_LONG).show()
            }
            R.id.ll_dynamic -> {
                Toast.makeText(this,"动态",Toast.LENGTH_LONG).show()
            }
            R.id.ll_mine -> {
                Toast.makeText(this,"我的",Toast.LENGTH_LONG).show()
            }
        }
    }
    class MainAniminatorListener :Animator.AnimatorListener{
        override fun onAnimationRepeat(animation: Animator?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onAnimationEnd(animation: Animator?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onAnimationCancel(animation: Animator?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onAnimationStart(animation: Animator?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
