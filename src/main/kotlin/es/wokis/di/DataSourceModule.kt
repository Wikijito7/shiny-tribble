package es.wokis.di

import es.wokis.data.database.AppDataBase
import es.wokis.data.datasource.local.recover.RecoverLocalDataSource
import es.wokis.data.datasource.local.recover.RecoverLocalDataSourceImpl
import es.wokis.data.datasource.local.verify.VerifyLocalDataSource
import es.wokis.data.datasource.local.verify.VerifyLocalDataSourceImpl
import es.wokis.data.datasource.local.user.UserLocalDataSource
import es.wokis.data.datasource.local.user.UserLocalDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localDataSourceModule = module {
    single { AppDataBase() }
    single(named("usersCollection")) { getUsersCollection(get()) }
    single(named("verificationCollection")) { getVerificationCollection(get()) }
    single(named("recoverCollection")) { getRecoverCollection(get()) }
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get(named("usersCollection"))) }
    single<VerifyLocalDataSource> { VerifyLocalDataSourceImpl(get(named("verificationCollection"))) }
    single<RecoverLocalDataSource> { RecoverLocalDataSourceImpl(get(named("recoverCollection"))) }
}

private fun getUsersCollection(database: AppDataBase) = database.usersCollection

private fun getVerificationCollection(database: AppDataBase) = database.verificationCollection

private fun getRecoverCollection(database: AppDataBase) = database.recoverCollection