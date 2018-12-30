package tran.nam.core.viewmodel

import nam.tran.data.model.core.state.Resource

interface IProgressViewModel {
    fun resource(): Resource<*>?
}
