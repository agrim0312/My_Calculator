package com.example.my2calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var tvInput : TextView? = null
    private var lastNumerical:Boolean = false
    private var lastDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastDot=false
        lastNumerical=true
    }
    fun onClear(view: View){
        tvInput?.text = ""
    }
    fun addingDecimal(view: View){
        if(lastNumerical && !lastDot){
            tvInput?.append((view as Button).text)
            lastDot=true
            lastNumerical=false
        }
    }
    private fun operatorCall(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("+")||value.contains("/")||value.contains("X")||value.contains("-")
        }
    }

    fun onOperator(view: View){
        if (lastNumerical && !operatorCall(tvInput?.text.toString())){
            tvInput?.append((view as Button).text)
            lastNumerical=false
            lastDot=false
        }
    }

    fun onEqual(view:View){
        if(lastNumerical){
            try {
                var textValue = tvInput?.text.toString()
                var prefix=""
                if(textValue.startsWith("-")){
                    prefix = "-"
                    textValue = textValue.substring(1)
                }

                    if(textValue.contains("-")){
                        val numbers = textValue.split("-")
                        var num1 = numbers[0]
                        val num2 = numbers[1]
                        if(prefix.isNotEmpty()){
                            num1 = prefix + numbers[0]
                        }
                        var result = (num1.toDouble()-num2.toDouble()).toString()
                        result = removeZeros(result)
                        tvInput?.text = result
                    }
                    if(textValue.contains("X")){
                        val numbers = textValue.split("X")
                        var num1 = numbers[0]
                        val num2 = numbers[1]
                        if(prefix.isNotEmpty()){
                            num1 = prefix + numbers[0]
                        }
                        var result = (num1.toDouble()*num2.toDouble()).toString()
                        result = removeZeros(result)
                        tvInput?.text = result
                    }
                    if(textValue.contains("+")){
                        val numbers = textValue.split("+")
                        var num1 = numbers[0]
                        val num2 = numbers[1]
                        if(prefix.isNotEmpty()){
                            num1 = prefix + numbers[0]
                        }
                        var result = (num1.toDouble()+num2.toDouble()).toString()
                        result = removeZeros(result)
                        tvInput?.text = result
                    }
                    if(textValue.contains("/")){
                        val numbers = textValue.split("/")
                        var num1 = numbers[0]
                        val num2 = numbers[1]
                        if(prefix.isNotEmpty()){
                            num1 = prefix + numbers[0]
                        }
                        var result = (num1.toDouble()/num2.toDouble()).toString()
                        result = removeZeros(result)
                        tvInput?.text = result
                    }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeros(result: String): String {
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }
}