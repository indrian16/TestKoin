package io.indrian16.testkoin

interface BaseView<out T: BasePresenter<*>> {

    val presenter: T
}