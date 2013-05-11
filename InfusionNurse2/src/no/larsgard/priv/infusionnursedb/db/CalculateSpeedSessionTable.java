package no.larsgard.priv.infusionnursedb.db;

import android.database.sqlite.SQLiteDatabase;

public class CalculateSpeedSessionTable {
	
	//TABELL
	public static final String CALCULATE_SPEED_SESSION_TABLE="CALCULATE_SPEED_SESSION";
	
	//tabell kolonne navn
	   public static final String COLUMN_ID = "_id";
	   
	    
	    public static final String CREATE_CALCULATE_SPEED_SESSION_TABLE = "CREATE TABLE " + CALCULATE_SPEED_SESSION_TABLE + "("
               + COLUMN_ID + " INTEGER PRIMARY KEY," 
              + ")";

	
	
	public static void onCreate(SQLiteDatabase db) {
		// oppretter tabellen
		
	        db.execSQL(CREATE_CALCULATE_SPEED_SESSION_TABLE);
		
		
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Upgrading database
	
	        db.execSQL("DROP TABLE IF EXISTS " + CALCULATE_SPEED_SESSION_TABLE);
	 
	        // Create tables again
	        onCreate(db);
		
	}
	
}
