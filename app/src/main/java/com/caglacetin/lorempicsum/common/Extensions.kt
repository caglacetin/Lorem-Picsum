package com.caglacetin.lorempicsum.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
  liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T : ViewDataBinding> ViewGroup?.inflate(@LayoutRes layoutId: Int, attachToParent: Boolean = true): T {
  return DataBindingUtil.inflate(
    LayoutInflater.from(this!!.context),
    layoutId,
    this,
    attachToParent
  )
}
