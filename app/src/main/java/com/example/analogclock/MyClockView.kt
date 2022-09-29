package com.example.analogclock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

private const val MINUTE_PAINT_WIDTH = 5F
private const val HOUR_PAINT_WIDTH = 8F
private const val SECOND_PAINT_WIDTH = 3F

class MyClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private var mHours = 0
    private var mMinutes = 0
    private var mSeconds = 0


    //Кисти для рисования циферблата
    private val paintClock = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 8F
    }

    //Кисти для рисования цифр
    private var mNumPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 8F
        textSize = 50F
    }

    //Кисти для рисования стрелок
    private var mHourPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = HOUR_PAINT_WIDTH

    }
    private var mMinutePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.DKGRAY
        strokeWidth = MINUTE_PAINT_WIDTH

    }
    private var mSecondPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.RED
        mNumPaint.strokeWidth = SECOND_PAINT_WIDTH

    }

    private fun setTime(){
        // Получаем системное время
        val calendar = Calendar.getInstance()
        mHours = calendar[Calendar.HOUR]
        mMinutes = calendar[Calendar.MINUTE]
        mSeconds = calendar[Calendar.SECOND]
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Рисуем циферблат
        canvas.drawCircle((width/2).toFloat(), (height/2).toFloat(),
            (width/2 - 10).toFloat(), paintClock)

        //нарисовать центр круга
        canvas.drawCircle((width/2).toFloat(), (height/2).toFloat(), 4F, paintClock)

        drawClockFace(canvas)
        setTime()
        drawHourHand(canvas)
        drawMinuteHand(canvas)
        drawSecondHand(canvas)

    }

    private fun drawClockFace(canvas: Canvas){
        // нарисовать часовую шкалу
        for (i in 0..11) {
            canvas.save()
            canvas.rotate(
                (360/12 * (i + 1)).toFloat(),
                (width/2).toFloat(),
                (height/2).toFloat()
            )
            canvas.drawLine(
                (width / 2).toFloat(),
                (height/2 - width/2 + 10).toFloat(),
                (width/2).toFloat(),
                (height/2 - width/2 + 50).toFloat(),
                mNumPaint
            )
            mNumPaint.strokeWidth = 5F
            canvas.drawText(
                (i + 1).toString() + "",
                (width/2 - 15).toFloat(),
                (height/2 - width/2 + 100).toFloat(),
                mNumPaint
            ) // рисуем часы
            canvas.restore() // Восстанавливаем
        }
        mNumPaint.strokeWidth = 2F
        for (i in 0..59) {
            canvas.save()
            canvas.rotate(
                (360/60 * (i + 1)).toFloat(),
                (width/2).toFloat(),
                (height/2).toFloat()
            )
            canvas.drawLine(
                (width/2).toFloat(),
                (height/2 - width/2 + 10).toFloat(),
                (width/2).toFloat(),
                (height/2 - width/2 + 30).toFloat(),
                mNumPaint
            )
            canvas.restore()
        }
    }

    private fun drawHourHand(canvas: Canvas){
        // TODO рисует часовую стрелку
        canvas.save()
        canvas.rotate((mHours*30 + mMinutes/60*30).toFloat(), (width/2).toFloat(), (height/2).toFloat()
        )
        canvas.drawLine((width/2).toFloat(),
            (height/2).toFloat(), (width/2).toFloat(),
            (height/2 - height/7).toFloat(), mHourPaint)
        canvas.restore()
    }

    private fun drawMinuteHand(canvas: Canvas){
        // TODO рисует минутную стрелку
        canvas.save()
        canvas.rotate(360 / 60 * mMinutes + mSeconds * 0.1f, (width/ 2).toFloat(),
            (height/ 2).toFloat()
        )
        canvas.drawLine(
            (width/2).toFloat(), (height/2).toFloat(), (width/2).toFloat(), (height/2 - height/5).toFloat(),
            mMinutePaint)
        canvas.restore()
    }

    private fun drawSecondHand(canvas: Canvas){
        // TODO рисует секундную стрелку
        canvas.save()
        canvas.rotate((mSeconds*6).toFloat(), (width/2).toFloat(), (height/2).toFloat())
        canvas.drawLine((width/2).toFloat(),
            (height/2).toFloat(),
            (width/2).toFloat(), (height/2 - height/ 4).toFloat(), mSecondPaint)
        canvas.restore()
        invalidate()
    }


}