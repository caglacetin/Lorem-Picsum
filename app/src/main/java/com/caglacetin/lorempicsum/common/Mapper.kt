package com.caglacetin.lorempicsum.common

interface Mapper<R, D> {
  fun mapFrom(type: R): D
}
