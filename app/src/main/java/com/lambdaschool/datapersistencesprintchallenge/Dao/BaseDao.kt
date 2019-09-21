package com.lambdaschool.datapersistencesprintchallenge.Dao


//NOT NEEDED DEPRECIATED LIKE AND3
//was going to use this to demonstrate inheritance but it was inefficient on an app of this scale
import androidx.room.*

//Using this from previous assignment to make my life easier

/*@Dao //Must be added so Room knows this is a Dao
interface BaseDao<in T> { //Must contain an interface because that is essential what a dao is

    //Insert, Delete and Update here will be inherited by daos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Insert(t: T): Long // what to do incase of conflict? insert the t is what it does!

    @Delete
    fun delete(type: T) //Delete the book etc

    @Update
    fun update(type: T) //update the book etc
//If I don't comment like this I will look at my code and forget how it works

}


 */