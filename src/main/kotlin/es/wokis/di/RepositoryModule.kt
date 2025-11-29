package es.wokis.di

import es.wokis.data.repository.recover.RecoverRepository
import es.wokis.data.repository.recover.RecoverRepositoryImpl
import es.wokis.data.repository.user.UserRepository
import es.wokis.data.repository.user.UserRepositoryImpl
import es.wokis.data.repository.verify.VerifyRepository
import es.wokis.data.repository.verify.VerifyRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<VerifyRepository> { VerifyRepositoryImpl(get(), get()) }
    single<RecoverRepository> { RecoverRepositoryImpl(get(), get(), get()) }
}
