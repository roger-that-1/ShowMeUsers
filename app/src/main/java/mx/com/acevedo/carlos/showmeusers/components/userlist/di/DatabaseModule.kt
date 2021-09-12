package mx.com.acevedo.carlos.showmeusers.components.userlist.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.com.acevedo.carlos.showmeusers.components.userlist.database.UserModelDatabase

/**
 * Provides [UserModelDatabase] instance
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Reusable
    fun provideSheetDatabase(@ApplicationContext context: Context) = UserModelDatabase.getInstance(context)

    @Provides
    @Reusable
    fun provideSheetDao(userModelDatabase: UserModelDatabase) = userModelDatabase.userModelDao()
}