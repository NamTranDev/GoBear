package dev.tran.nam.gobear.mapper

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataMapper @Inject
internal constructor(val preferenceMapper: PreferenceMapper)
