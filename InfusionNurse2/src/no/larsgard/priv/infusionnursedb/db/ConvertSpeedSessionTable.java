package no.larsgard.priv.infusionnursedb.db;

import android.database.sqlite.SQLiteDatabase;

public class ConvertSpeedSessionTable {
	
	//Tabell 
			public static final String CONVERT_SPEED_SESSION_TABLE="CONVERT_SPEED_SESSION";
			
			// tabell-kolonne navn 
		    public static final String COLUMN_ID = "_id";
		    public static final String COLUMN_SESSION_START = "TAPP_SESSION_START";
		    public static final String COLUMN_SESSION_END="TAPP_SESSION_END";
		  
		    
		    public static final String CREATE_CONVERT_SPEED_SESSION_TABLE = "CREATE TABLE " + CONVERT_SPEED_SESSION_TABLE + "("
	                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SESSION_START + " DATETIME, "
	                + COLUMN_SESSION_END + " DATETIME, " 
	               + ")";
		

			public static void onCreate(SQLiteDatabase db) {
				// oppretter tabellen
				
			        db.execSQL(CREATE_CONVERT_SPEED_SESSION_TABLE);
				
				
			}

			public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// Upgrading database
			
			        db.execSQL("DROP TABLE IF EXISTS " + CONVERT_SPEED_SESSION_TABLE);
			 
			        // Create tables again
			        onCreate(db);
				
			}
	}



