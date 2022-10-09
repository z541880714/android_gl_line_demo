package com.lionel.mycomposedemo.page

import android.opengl.GLSurfaceView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.*
import com.lionel.mycomposedemo.page.lg_line.LineRenderer
import com.lionel.mycomposedemo.page.lg_line.SimpleRenderer
import com.lionel.mycomposedemo.page.lg_line.VertexPointerRenderer


@Composable
fun LinePage(modifier: Modifier = Modifier) {

    var glView: GLSurfaceView? = null
    val lifeCycle = LocalLifecycleOwner.current.lifecycle

    val lifecycleObserver = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                glView?.onResume()
            }
            Lifecycle.Event.ON_PAUSE -> {
                glView?.onPause()
            }
            else -> {}
        }
    }

    DisposableEffect(key1 = lifeCycle, effect = {
        onDispose {
            lifeCycle.removeObserver(lifecycleObserver)
        }
    })

    AndroidView(modifier = modifier, factory = {
        GLSurfaceView(it)
    }) {
        glView = it
        //设置版本
        it.setEGLContextClientVersion(3)
        lifeCycle.addObserver(lifecycleObserver)
        it.setRenderer(LineRenderer(it.context))
//        it.setRenderer(VertexPointerRenderer(it.context))
//        it.setRenderer(SimpleRenderer())
    }
}