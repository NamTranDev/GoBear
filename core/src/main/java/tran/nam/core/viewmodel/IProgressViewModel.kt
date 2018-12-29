package tran.nam.core.viewmodel

import nam.tran.flatform.model.core.state.Resource

interface IProgressViewModel {
    fun resource(): Resource<*>?
}
