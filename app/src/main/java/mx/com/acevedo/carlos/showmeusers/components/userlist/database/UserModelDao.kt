package mx.com.acevedo.carlos.showmeusers.components.userlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserDbModel

@Dao
interface UserModelDao {
    /**
     * It gets all users from table userModel
     */
    @Query("SELECT * FROM userModel")
    fun getAll(): Single<List<UserDbModel>>

    /**
     * Gets userModel table count
     */
    @Query("SELECT COUNT() FROM userModel")
    fun getDataCount(): Single<Int>

    /**
     * Insert a list in [UserModelDatabase]. If the item already exists, replace it.
     *
     * @param modelList the items to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(modelList: List<UserDbModel>): Completable

    /**
     * Deletes userModel table from [UserModelDatabase]
     */
    @Query("DELETE FROM userModel")
    fun deleteAll(): Completable

}