package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by "Avishek" on 8/20/2019.
 */
@Module
abstract class CatModule {

//  @Binds
//  abstract fun bindCatRepository(catDataRepository: CatDataRepository): CatRepository
//
//  @Module
//  companion object {
//
//    @JvmStatic
//    @Singleton
//    @Provides
//    fun provideCatService(retrofit: Retrofit): CatService {
//      return retrofit.create(CatService::class.java)
//    }
//  }
}
