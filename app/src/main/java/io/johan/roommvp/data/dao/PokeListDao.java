package io.johan.roommvp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.johan.roommvp.data.entity.PokeList;
import java.util.List;

/**
 * Dao
 *
 * It defines the method that access the database.
 * Annotations are user to bind SQL with each method
 * declared in DAO.
 */

@Dao
public interface PokeListDao {

  //Show all pokemon
  @Query("SELECT * FROM pokemon")
  List<PokeList> getAll();

  //Show how many pokemon in pokemon table
  @Query("SELECT COUNT(*) FROM pokemon")
  int countPokemon();

  //Search pokemon in pokemon table
  @Query("SELECT * FROM pokemon where name LIKE :pokeName")
  PokeList findByName(String pokeName);

  //Insert all pokemon
  @Insert
  void insertAll(List<PokeList> pokeLists);
//  void insertAll(PokeList... pokeLists);

  @Query("UPDATE pokemon SET name = :pokeName WHERE name = :beforeName")
  void updateItem(String beforeName, String pokeName);

  //Insert on pokemon
  @Insert
  void insert(PokeList pokeList);

  //Delete one pokemon
  @Delete
  void delete(PokeList pokeList);

}
