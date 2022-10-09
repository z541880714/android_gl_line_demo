package com.lionel.mycomposedemo.page.lg_line.data

import java.util.LinkedList

//点的坐标信息
data class FSize(val x: Float, val y: Float)

//记录点的个数.
const val traceCount = 30

//自定义 粒子.. 记录 最近的30个 点...
//x,y 坐标需要转化为 gl 坐标系 -1到1 范围内...
class Particle(val x: Float, val y: Float) {

    private val trace = LinkedList<FSize>().apply {
        repeat(traceCount) { add(FSize(x, y)) }
    }
    private val traceArray = Array<FSize>(traceCount) { FSize(x, y) }


    fun update(_x: Float, _y: Float) {
        trace.removeLast() //移除最后一个
        trace.add(FSize(_x, _y))//在头部添加一个.
    }

    fun toArray() = trace.toArray(traceArray).run { traceArray }
}

/**
 * @param vWidth view 的像素宽度
 * @param vHeight view 的像素高度
 * @param x view 对应的x坐标. 原点在左上角.
 * @param y view 对应的y坐标. 原点在左上角.
 */
fun convertCoor(vWidth: Float, vHeight: Float, x: Float, y: Float) {
    
}